/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.views;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import student.proiect.swing.ProiectSwing;
import student.proiect.swing.util.Database;

/**
 *
 * @author cptsoarbezeama
 */
public class ChangePasswordView extends JFrame {

    private final Database db;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton changeButton;

    public ChangePasswordView(Database db) {
        this.db = db;
        initUI();
    }

    private void initUI() {
        setTitle("Change Password" + ProiectSwing.TITLE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Old Password:"));
        oldPasswordField = new JPasswordField();
        add(oldPasswordField);

        add(new JLabel("New Password:"));
        newPasswordField = new JPasswordField();
        add(newPasswordField);

        add(new JLabel("Confirm New Password:"));
        confirmPasswordField = new JPasswordField();
        add(confirmPasswordField);

        changeButton = new JButton("Change Password");
        changeButton.addActionListener(e -> handleChangePassword());
        add(changeButton);
    }

    private void handleChangePassword() {
        String oldPass = new String(oldPasswordField.getPassword());
        String newPass = new String(newPasswordField.getPassword());
        String confirmPass = new String(confirmPasswordField.getPassword());

        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "New passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (!checkOldPassword(oldPass)) {
                JOptionPane.showMessageDialog(this, "Old password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            db.updatePassword(newPass);
            JOptionPane.showMessageDialog(this, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkOldPassword(String oldPass) {
        // Use your Database login logic to check old password
        return db.login(db.getUsername(), oldPass);
    }
}
