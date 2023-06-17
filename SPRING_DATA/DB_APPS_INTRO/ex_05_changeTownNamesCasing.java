import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ex_05_changeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        System.out.println("Enter password for 'root': ");
        String password = scanner.nextLine();
        properties.setProperty("password", password);
        String country = scanner.nextLine();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement upperQuery = connection.prepareStatement(
                "UPDATE `towns`" +
                " SET `name` = UPPER(`name`)" +
                " WHERE `country` = ?");
        upperQuery.setString(1, country);
        int countChangedRows = upperQuery.executeUpdate();

        if (countChangedRows == 0){
            System.out.println("No town names were affected.");
            return;
        }

        PreparedStatement selectTownNameQuery = connection.prepareStatement("SELECT `name` FROM `towns` WHERE `country` = ?");
        selectTownNameQuery.setString(1, country);
        ResultSet allTownsInCountry = selectTownNameQuery.executeQuery();

        List<String> towns = new ArrayList<>();

        while (allTownsInCountry.next()){
            String name = allTownsInCountry.getString("name");
            towns.add(name);
        }

        System.out.println(countChangedRows + " town names were affected.");
        System.out.printf("[%s]%n", String.join(", ", towns));

    }
}
