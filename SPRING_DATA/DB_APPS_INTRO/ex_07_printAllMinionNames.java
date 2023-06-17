import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class ex_07_printAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        System.out.print("Enter password for user 'root': ");
        String password = scanner.nextLine();
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement namesQuery = connection.prepareStatement("SELECT `name` FROM `minions`");
        ResultSet namesSet = namesQuery.executeQuery();

        List<String> names = new ArrayList<>();

        while (namesSet.next()){
            String name = namesSet.getString("name");
            names.add(name);
        }

        for (int i = 0; i < names.size()/2; i++) {
            System.out.println(names.get(i));
            System.out.println(names.get(names.size()-1-i));
        }

        if (names.size() % 2 != 0) {
            System.out.println(names.get(names.size() / 2));
        }

        connection.close();
    }
}
