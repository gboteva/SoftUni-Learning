import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ex_02_getVillainsName {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties prop = new Properties();
        prop.setProperty("user", "root");
        System.out.print("Enter password for account 'root': ");
        String password = scanner.nextLine();
        prop.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", prop);

        PreparedStatement selectVillains = connection.prepareStatement(
                "SELECT v.`name`, COUNT(DISTINCT mv.minion_id) AS 'minions_count'" +
                " FROM `villains` AS v" +
                " JOIN `minions_villains` AS mv" +
                " ON v.`id` = mv.`villain_id`" +
                " GROUP BY v.`id`" +
                " HAVING `minions_count` > 15" +
                " ORDER BY `minions_count` DESC;");

        ResultSet villainsSet = selectVillains.executeQuery();

        if (!villainsSet.next()){
            return;
        }

        String name = villainsSet.getString("name");
        int minionsCount = villainsSet.getInt("minions_count");
        System.out.println(name + " " + minionsCount);

        connection.close();
    }
}
