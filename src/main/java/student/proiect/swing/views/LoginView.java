/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import student.proiect.swing.ProiectSwing;
import student.proiect.swing.util.Database;

/**
 *
 * @author cptsoarbezeama
 */
public class LoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    private final Database db;

    public LoginView(Database db) {

        super("Login" + ProiectSwing.TITLE);
        this.db = db;
        initUI();
    }

    private void initUI() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton loginButton = new JButton("Login");
        panel.add(loginButton, gbc);

        gbc.gridy = 3;
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
        panel.add(statusLabel, gbc);

        add(panel);

        loginButton.addActionListener(e -> handleLogin());
        
        this.setVisible(true);
    }

    private void handleLogin() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (db.login(username, password)) {
            dispose(); // close login
            new DashboardView(db).setVisible(true);
        } else {
            statusLabel.setText("Invalid username or password");
        }
    }
}
