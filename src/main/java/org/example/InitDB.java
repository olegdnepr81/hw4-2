package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitDB {
    public static void initDb() throws SQLException {
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement statCreateTable = conn.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS CLIENT " +
                             "( ID IDENTITY(1,1) " +
                             "PRIMARY KEY, " +
                             "NAME VARCHAR (1000) " +
                             "CHECK (Length(name)>2) NOT NULL);");
        ) {
            statCreateTable.executeUpdate();
        }
    }
}


