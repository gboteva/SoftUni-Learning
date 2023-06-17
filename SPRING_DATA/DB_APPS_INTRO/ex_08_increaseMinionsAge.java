import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ex_08_increaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        System.out.print("Enter password for user 'root': ");
        String password = scanner.nextLine();
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
        List<Integer> input = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        connection.setAutoCommit(false);

        try{
            for (int i = 0; i < input.size(); i++) {
                int currentId = input.get(i);
                PreparedStatement changeNameQuery = connection.prepareStatement("UPDATE `minions` SET `name` = lower(`name`) WHERE `id` = ?");
                changeNameQuery.setInt(1, currentId);
                changeNameQuery.executeUpdate();

                PreparedStatement increaseAgeQuery = connection.prepareStatement("UPDATE `minions` SET `age` = `age` + 1 WHERE `id` = ?");
                increaseAgeQuery.setInt(1, currentId);
                increaseAgeQuery.executeUpdate();
            }

            PreparedStatement allMinionsQuery = connection.prepareStatement("SELECT `name`, `age` FROM `minions`");
            ResultSet allMinionsSet = allMinionsQuery.executeQuery();

            while(allMinionsSet.next()){
                String name = allMinionsSet.getString("name");
                int age = allMinionsSet.getInt("age");
                System.out.println(name + " " + age);
            }

            connection.commit();

        }catch (SQLException e){
            connection.rollback();
        }

        connection.close();
    }
}
