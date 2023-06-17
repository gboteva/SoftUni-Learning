package orm;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<E> implements DBContext<E>{
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {

        String tableName = getTableName(entity.getClass());

        String values = getColumnsValuesWithoutId(entity);

        String tableFields = getColumnsWithoutId(entity.getClass());

        Field idField = getIdColumn(entity.getClass());
        idField.setAccessible(true);
        Object id = idField.get(entity);

        if (id == null || (long)id <=0){
            return doInsert(tableName, values, tableFields);
        }

        return doUpdate((long)id, tableName, tableFields, values);
    }

    private boolean doUpdate(long id, String tableName, String tableFields, String values) throws SQLException {

        List<String> fieldsList = Arrays.stream(tableFields.split(", ")).collect(Collectors.toList());
        List<String> valuesList = Arrays.stream(values.split(", ")).collect(Collectors.toList());

        StringBuilder query = new StringBuilder();
        for (int i = 0; i < fieldsList.size() -1; i++) {
            query.append(fieldsList.get(i)).append(" = ").append(valuesList.get(i)).append(", ");
        }
        query.append(fieldsList.get(fieldsList.size()-1)).append(" = ").append(valuesList.get(valuesList.size()-1));


        String updateQuery = String.format("UPDATE %s SET %s WHERE `id` = %s", tableName, query.toString(),id);

        return connection.prepareStatement(updateQuery).execute();
    }

    private boolean doInsert(String tableName, String values, String tableFields) throws SQLException {

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES(%s)", tableName, tableFields, values );

        return  connection.prepareStatement(insertQuery).execute();
    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(table, null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<E> founded = new ArrayList<>();
        Statement statement = connection.createStatement();
        String tableName = getTableName(table);

        String query = String.format("SELECT * FROM %s %s;", tableName, where != null ? "WHERE " + where : "");
        ResultSet resultSet = statement.executeQuery(query);

        if (!resultSet.next()){
            throw new IllegalArgumentException("There are no records matching your search");
        }

        String username = resultSet.getString("username");

        while (username != null){
            E entity = table.getDeclaredConstructor().newInstance();

            fillEntity(table, resultSet, entity);

            founded.add(entity);

            if (!resultSet.next()){
                break;
            }

            username = resultSet.getString("username");
        }

        return founded;
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table, null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Statement statement = connection.createStatement();
        String tableName = getTableName(table);

        String query = String.format("SELECT * FROM %s %s LIMIT 1;", tableName, where != null ? "WHERE " + where : "");
        ResultSet selectResults = statement.executeQuery(query);

        E entity = table.getDeclaredConstructor().newInstance();

        selectResults.next();

        fillEntity(table, selectResults, entity);

        return entity;

    }

    @Override
    public void doCreate(Class<E> entityClass) throws SQLException {
        String entityName = entityClass.getDeclaredAnnotationsByType(Entity.class)[0].name();

        List<Field> fields = Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> dataTypesOfFields = getDataType(entityClass);

        String query = buildQuery(entityName, fields, dataTypesOfFields);

        PreparedStatement statement = connection.prepareStatement(query);

        statement.execute();

    }

    @Override
    public void doAlter(E entity) throws SQLException {
        String tableName = getTableName(entity.getClass());
        String newFields = getNewFields(entity.getClass());
        String query = String.format("ALTER TABLE %s ADD COLUMN %s", tableName, newFields);

        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
    }

    @Override
    public int delete(Class<E> table, String where) throws SQLException {
        Statement statement = connection.createStatement();
        String tableName = getTableName(table);

        String query = String.format("DELETE FROM %s %s;", tableName, where != null ? "WHERE " + where : "");
        return statement.executeUpdate(query);

    }

    private String getNewFields(Class<?> aClass) throws SQLException {
        String tableName = getTableName(aClass);
        String idColumnName = getIdColumn(aClass).getName();
        Set<String> allFieldsFromBaseWithoutId = getAllTableFieldsFromBase(tableName, idColumnName);

        StringBuilder builder = new StringBuilder();

        Arrays.stream(aClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .forEach(field -> {
                    String fieldName = field.getName();
                    if (!allFieldsFromBaseWithoutId.contains(fieldName)){
                        builder.append(getNameAndDataTypeOfField(field));
                    }
                });

        return builder.toString();
    }

    private String getNameAndDataTypeOfField(Field field) {
        String fieldName = field.getName();
        String sqlType = getSQLType(field);
        return fieldName + " " + sqlType;
    }

    private Set<String> getAllTableFieldsFromBase(String tableName, String idColumnName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(String.format("SELECT COLUMN_NAME FROM information_schema.`COLUMNS` AS c" +
                " WHERE c.TABLE_SCHEMA = 'custom_orm'" +
                        " AND COLUMN_NAME != '%s'" +
                        " AND TABLE_NAME = '%s';", idColumnName, tableName));
        ResultSet columnsResultSet = statement.executeQuery();

        Set<String> columnsName = new HashSet<>();

        while (columnsResultSet.next()){
            String columnName = columnsResultSet.getString("COLUMN_NAME");

            columnsName.add(columnName);
        }

        return columnsName;

    }

    private String buildQuery(String entityName, List<Field> fields, List<String> dataTypesOfFields) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ").append(entityName).append("(");

        query.append("id INT PRIMARY KEY AUTO_INCREMENT, ");

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            String dataType = dataTypesOfFields.get(i);

            if (field.getName().equals("id")){
                continue;
            }

            if (i == fields.size() - 1){
                query.append(field.getName()).append(" ").append(dataType);
            }else {
                query.append(field.getName()).append(" ").append(dataType).append(", ");
            }
        }

        query.append(");");

        return query.toString();
    }


    private List<String> getDataType(Class<E> entityClass) {
        List<String> types = new ArrayList<>();

        Arrays.stream(entityClass.getDeclaredFields())
                .forEach(f-> types.add(getSQLType(f)));


       return types;
    }

    private String getSQLType(Field field) {

        String fieldType = field.getType().getSimpleName();

            if (fieldType.equals("Integer") || fieldType.equals("int") || fieldType.equals("long")){
                return ("INT");
            }else if (fieldType.equals("String")){
               return ("VARCHAR(100)");
            }else if (fieldType.equals("LocalDate")){
               return ("DATE");
            }
        return null;
    }

    private void fillEntity(Class<E> table, ResultSet selectResults, E entity) throws SQLException, IllegalAccessException {
        Field[] declaredFields = table.getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            fillField(field, selectResults, entity);
        }
    }

    private void fillField(Field field, ResultSet selectResults, E entity) throws SQLException, IllegalAccessException {
        if (field.getType() == int.class || field.getType() == long.class){
            field.set(entity, selectResults.getInt(field.getName()));
        }else if (field.getType() == LocalDate.class){
            field.set(entity, LocalDate.parse(selectResults.getString(field.getName())));
        }else {
            field.set(entity, selectResults.getString(field.getName()));
        }
    }


    private Field getIdColumn(Class<?> clazz) throws SQLException {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() ->
                        new UnsupportedOperationException("Entity missing an Id column"));
    }

    private String getColumnsValuesWithoutId(E entity) throws IllegalAccessException {
        Class<?> aClass = entity.getClass();
        List<Field> fields = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(entity);

            if (o instanceof String || o instanceof LocalDate){
                values.add("'" + o + "'");
            }else if (o==null){
                values.add("NULL");
            } else {
                values.add(o.toString());
            }
        }

        return String.join(", ", values);
    }

    private String getColumnsWithoutId(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotationsByType(Column.class))
                .map(a -> a[0].name())
                .collect(Collectors.joining(", "));
    }

    private String getTableName(Class<?> aClass) {
        Entity[] annotations = aClass.getAnnotationsByType(Entity.class);
        if (annotations.length == 0){
            throw new UnsupportedOperationException("Class must be Entity");
        }

        return annotations[0].name();
    }
}
