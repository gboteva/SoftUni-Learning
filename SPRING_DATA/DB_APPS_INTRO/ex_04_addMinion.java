import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ex_04_addMinion {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        System.out.println("Enter password for 'root': ");
        String password = scanner.nextLine();
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
        String minionInfo = scanner.nextLine();
        String villainInfo = scanner.nextLine();
        String minionName = minionInfo.split("\\s+")[1];
        int minionAge = Integer.parseInt(minionInfo.split("\\s+")[2]);
        String minionTown = minionInfo.split("\\s+")[3];
        String villainName = villainInfo.split("\\s+")[1];

        //Add town if needed
        addTownIfDoesntExist(connection, minionTown);

        //Add villain if needed
        addVillainIfDoesntExist(connection, villainName);

        //if minion exist - return (not in requirements)
        PreparedStatement minionIdExist = connection.prepareStatement("SELECT `id` FROM `minions` WHERE `name` = ?");
        minionIdExist.setString(1, minionName);
        ResultSet setIfMinionExist = minionIdExist.executeQuery();
        if(setIfMinionExist.next()){
            return;
        }

        //get town id
        PreparedStatement townIdQuery = connection.prepareStatement("SELECT `id` FROM `towns` WHERE `name` = ?");
        townIdQuery.setString(1, minionTown);
        ResultSet townIdResult = townIdQuery.executeQuery();
        townIdResult.next();
        int townId = townIdResult.getInt("id");

        //get villain id
        PreparedStatement villainIdQuery = connection.prepareStatement("SELECT `id` FROM `villains` WHERE `name` = ?");
        villainIdQuery.setString(1, villainName);
        ResultSet villainIdResult = villainIdQuery.executeQuery();
        villainIdResult.next();
        int villainId = townIdResult.getInt("id");

        //Add minion and add it to villain
        PreparedStatement addMinionQuery = connection.prepareStatement("INSERT INTO `minions` (`name`, `age`, `town_id`) VALUES(?, ?, ?)");
        addMinionQuery.setString(1, minionName);
        addMinionQuery.setInt(2, minionAge);
        addMinionQuery.setInt(3, townId);
        addMinionQuery.executeUpdate();

        PreparedStatement minionIdQuery = connection.prepareStatement("SELECT `id` FROM `minions` WHERE `name` = ?");
        minionIdQuery.setString(1, minionName);
        ResultSet minionIdSet = minionIdQuery.executeQuery();
        minionIdSet.next();
        int minionId = minionIdSet.getInt("id");

        PreparedStatement addMinionToVillainQuery = connection.prepareStatement("INSERT INTO `minions_villains` VALUES(?, ?)");
        addMinionToVillainQuery.setInt(1, minionId);
        addMinionToVillainQuery.setInt(2, villainId);
        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);

        connection.close();
    }

    private static void addVillainIfDoesntExist(Connection connection, String villainName) throws SQLException {
        PreparedStatement selectVillainId = connection.prepareStatement("SELECT `id` FROM `villains` WHERE `name` = ?;");
        selectVillainId.setString(1, villainName);
        ResultSet villainIdSet = selectVillainId.executeQuery();

        if (!villainIdSet.next()){
            PreparedStatement addVillainQuery = connection.prepareStatement("INSERT INTO `villains` (`name`, `evilness_factor`) VALUES (?, 'Evil')");
            addVillainQuery.setString(1, villainName);
            addVillainQuery.executeUpdate();
            System.out.printf("Villain %s was added to the database.%n", villainName);
        }
    }

    private static void addTownIfDoesntExist(Connection connection, String minionTown) throws SQLException {
        PreparedStatement selectTownStat = connection.prepareStatement(
                "SELECT `id` FROM `towns` WHERE `name` = ?;");
        selectTownStat.setString(1, minionTown);
        ResultSet townIdSet = selectTownStat.executeQuery();

        if (!townIdSet.next()){
            PreparedStatement addTownQuery = connection.prepareStatement("INSERT INTO `towns` (`name`) VALUES (?)");
            addTownQuery.setString(1, minionTown);
            System.out.printf("Town %s was added to the database.%n", minionTown);
            addTownQuery.executeUpdate();
        }
    }
}
