/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cptsoarbezeama
 */
public class Database {

    private final Connection con;

    private final String USERS_SQL = """
        CREATE TABLE IF NOT EXISTS users (
            id INT AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(50) UNIQUE NOT NULL,
            password VARCHAR(50) NOT NULL
        )
        """;
    private final String POPULATE_EMPTY = """
                        INSERT INTO users (username, password)
                        SELECT 'admin', 'admin'
                        WHERE NOT EXISTS (SELECT 1 FROM users)
                                          """;

    public Database() throws SQLException {

        con = DriverManager.getConnection(
                "jdbc:h2:./data/appdb",
                "sa",
                ""
        );

        try (Statement ps = con.createStatement()) {

            ps.execute(USERS_SQL);
        }

        try (Statement ps = con.createStatement()) {

            ps.execute(POPULATE_EMPTY);
        }
    }

    public Connection getConnection() {

        return con;
    }

    public boolean login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            return ps.executeQuery().next();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
