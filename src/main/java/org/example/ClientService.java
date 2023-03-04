package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    static String createSql = "INSERT INTO CLIENT ( NAME) VALUES (?)";
    static String getById = "SELECT NAME FROM CLIENT WHERE ID = ?";
    static String setNameById = "UPDATE CLIENT SET NAME = ? WHERE ID = ?";
    static String delById = "DELETE FROM CLIENT WHERE ID = ?";

    public static long create(String name) throws SQLException {
        long index = 0;
        if (name.length() >= 2 & name.length() <= 1000) {
            try (Connection conn = Database.getInstance().getConnection();
                 PreparedStatement statSave = conn.prepareStatement(createSql);
                 PreparedStatement maxId = conn.prepareStatement("SELECT MAX(ID) ID FROM CLIENT")
            ) {
                statSave.setString(1, name);
                statSave.executeUpdate();

                ResultSet lastId = maxId.executeQuery();
                while (lastId.next()) {
                    index = lastId.getLong("ID");
                }
            }

        } else {
            System.out.println("INCORRECT LENGTH OF THE NAME");
        }
        return index;
    }

    public static String getById(long id) throws SQLException {
        String name = null;
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement statGetById = conn.prepareStatement(getById)

        ) {
            statGetById.setLong(1, id);
            ResultSet nameById = statGetById.executeQuery();

            while (nameById.next()) {
                name = nameById.getString("NAME");
            }
        }

        return name;
    }

    public static void setName(long id, String name) {

        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement statSetNameById = conn.prepareStatement(setNameById)
        ) {
            statSetNameById.setString(1, name);
            statSetNameById.setLong(2, id);
            statSetNameById.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(long id) {
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement statDelById = conn.prepareStatement(delById)
        ) {
            statDelById.setLong(1, id);
            statDelById.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement stat = conn.prepareStatement("SELECT * FROM CLIENT")) {
            ResultSet resultSet = stat.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("Id");
                String name = resultSet.getString("Name");
                clients.add(new Client(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }
}
