/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import student.proiect.swing.dto.ResultDto;

/**
 *
 * @author cptsoarbezeama
 */
public class Database {

    private final Connection con;
    private int userId;
    private String username;

    private final String USERS_SQL = """
        CREATE TABLE IF NOT EXISTS users (
            id INT AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(50) UNIQUE NOT NULL,
            password VARCHAR(50) NOT NULL
        )
        """;

    private final String RESULTS_SQL = """
                                       CREATE TABLE IF NOT EXISTS results (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           user_id INT NOT NULL,
                                           quiz VARCHAR(50) NOT NULL,
                                           correct_answers INT NOT NULL,
                                           total_answers INT NOT NULL,
                                           completion_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           FOREIGN KEY (user_id) REFERENCES users(id)
                                       );
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

        //http://localhost:8082
        org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();

        try (Statement ps = con.createStatement()) {

            ps.execute(USERS_SQL);
        }

        try (Statement ps = con.createStatement()) {

            ps.execute(RESULTS_SQL);
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

            ResultSet rs = ps.executeQuery();

            boolean found = rs.next();

            if (found) {
                userId = rs.getInt("id");
                this.username = username;
            }

            return found;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void saveResult(String title, int correct, int total) throws SQLException {

        String sql = """
            INSERT INTO results (user_id, correct_answers, total_answers, quiz)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, correct);
            ps.setInt(3, total);
            ps.setString(4, title);

            ps.executeUpdate();
        }
    }

    public List<ResultDto> getAllResults() throws SQLException {
        List<ResultDto> results = new ArrayList();

        String sql = "SELECT r.id, u.username, r.quiz, r.correct_answers, r.total_answers, r.completion_time "
                + "FROM results r JOIN users u ON r.user_id = u.id "
                + "ORDER BY r.completion_time DESC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(new ResultDto(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("quiz"),
                        rs.getInt("correct_answers"),
                        rs.getInt("total_answers"),
                        rs.getTimestamp("completion_time")
                ));
            }
        }

        return results;
    }

    public void updatePassword(String newPass) throws SQLException {
        String sql = "UPDATE users SET password=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newPass);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }

    public String getPassword() throws SQLException {

        String sql = "SELECT password FROM users WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        }

        return "errorPs";
    }

    public int getQuizzesTaken() throws SQLException {
        String sql = "SELECT COUNT(*) FROM results WHERE user_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public String getUsername() {

        return username;
    }
}
