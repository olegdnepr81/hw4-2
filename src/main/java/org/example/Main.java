package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        InitDB.initDb();
        long id = ClientService.create("Lesha");
        System.out.println(id);
        String name = ClientService.getById(7);
        System.out.println(name);
        ClientService.setName(1, "Sasha");
        List<Client> clients = ClientService.listAll();
        System.out.println(clients);
        ClientService.deleteById(1);
        List<Client> allclients = ClientService.listAll();
        System.out.println(allclients);
    }
}