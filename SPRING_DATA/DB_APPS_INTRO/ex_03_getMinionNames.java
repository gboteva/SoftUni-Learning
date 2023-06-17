import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ex_03_getMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        System.out.println("Enter password for 'root': ");
        String password = scanner.nextLine();
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement villainsNameStat = connection.prepareStatement("SELECT `name` FROM `villains` WHERE `id` = ?;");

        System.out.print("Enter villain's id: ");
        int villainId = Integer.parseInt(scanner.nextLine());
        villainsNameStat.setInt(1, villainId);

        ResultSet villainNameSet = villainsNameStat.executeQuery();
        if(!villainNameSet.next()){
            System.out.printf("No villain with ID %d exists in the database.", villainId);
            return;
        }
        String villainName = villainNameSet.getString("name");

        PreparedStatement minionsNameStat = connection.prepareStatement("SELECT DISTINCT m.`name` AS 'minion_name'," +
                " m. `age`" +
                " FROM `minions` AS m" +
                " JOIN `minions_villains` AS mv" +
                " ON m.`id` = mv.`minion_id`" +
                " WHERE mv.villain_id = ?;");
        minionsNameStat.setInt(1, villainId);

        ResultSet minionsNameSet = minionsNameStat.executeQuery();

        System.out.println("Villain: " + villainName);

        int count = 1;
        while (minionsNameSet.next()){
            String name = minionsNameSet.getString("minion_name");
            int age = minionsNameSet.getInt("age");
            System.out.printf("%d. %s %d%n", count,name, age);
            count++;
        }


        connection.close();
    }
}
