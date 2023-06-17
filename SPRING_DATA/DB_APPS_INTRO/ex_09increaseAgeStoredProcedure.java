import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ex_09increaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        System.out.print("Enter password for user 'root': ");
        String password = scanner.nextLine();
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        int minion_id = Integer.parseInt(scanner.nextLine());
        CallableStatement callProcedureQuery = connection.prepareCall("CALL usp_get_older(?)");
        callProcedureQuery.setInt(1, minion_id);
        callProcedureQuery.execute();

        PreparedStatement selectQuery = connection.prepareStatement("SELECT `name`, `age` FROM `minions` WHERE `id` = ?");
        selectQuery.setInt(1, minion_id);
        ResultSet executedQuery = selectQuery.executeQuery();
        executedQuery.next();
        String name = executedQuery.getString("name");
        int age = executedQuery.getInt("age");
        System.out.println(name + " " + age);
    }
}
