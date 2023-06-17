package org.example;
import entities.User;
import orm.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import static orm.MyConnector.createConnection;
import static orm.MyConnector.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password for database: ");
        String password = scanner.nextLine();

        createConnection("root", password, "custom_orm");
        Connection connection = getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);

//        User user = new User("Gosho", "password", 25, LocalDate.now());
//        user.setId(5);
//        user.setUsername("Gosho Updated");
//        userEntityManager.persist(user);
//        userEntityManager.doAlter(user);

       userEntityManager.delete(User.class, "id = 5");


        connection.close();
    }
}