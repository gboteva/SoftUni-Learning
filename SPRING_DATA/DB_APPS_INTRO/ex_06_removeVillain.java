import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ex_06_removeVillain {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        System.out.print("Enter password for user 'root': ");
        String password = scanner.nextLine();
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainNameStat = connection.prepareStatement("SELECT `name` FROM `villains` WHERE `id` = ?");
        villainNameStat.setInt(1, villainId);
        ResultSet resultVillainName = villainNameStat.executeQuery();
        if (!resultVillainName.next()){
            System.out.println("No such villain was found");
            return;
        }
        String villainName = resultVillainName.getString("name");

        PreparedStatement countMinionsQuery = connection.prepareStatement("SELECT COUNT(minion_id) AS 'count'" +
                " FROM `minions_villains`" +
                " WHERE `villain_id` = ?");
        countMinionsQuery.setInt(1, villainId);
        ResultSet minionsCountSet = countMinionsQuery.executeQuery();
        minionsCountSet.next();
        int countMinions = minionsCountSet.getInt("count");

        connection.setAutoCommit(false);

        try{
            PreparedStatement deleteVillainFromMappingTableQuery = connection.prepareStatement(
                    "DELETE FROM `minions_villains` AS v " +
                            " WHERE v.`villain_id` = ?");
            deleteVillainFromMappingTableQuery.setInt(1, villainId);
            deleteVillainFromMappingTableQuery.executeUpdate();

            PreparedStatement deleteVillain = connection.prepareStatement(
                    "DELETE FROM villains WHERE id = ?");
            deleteVillain.setInt(1, villainId);
            deleteVillain.executeUpdate();

            connection.commit();

            System.out.println(villainName + " was deleted");
            System.out.println(countMinions + " minions released");
        }catch (SQLException e){
            connection.rollback();
        }

        connection.close();
    }
}
