/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.views;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import student.proiect.swing.ProiectSwing;
import student.proiect.swing.util.Database;

/**
 *
 * @author cptsoarbezeama
 */
public class DashboardView extends JFrame {

    private final Database db;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel quizzesLabel;

    public DashboardView(Database db) {
        this.db = db;
        initUI();
    }

    private void initUI() {

        setTitle("Dashboard" + ProiectSwing.TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        setJMenuBar(createMenuBar());

        add(createUserInfoPanel(), BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Management menu
        JMenu managementMenu = new JMenu("Management");
        JMenuItem studentsItem = new JMenuItem("Students");
        JMenuItem coursesItem = new JMenuItem("Courses");

        studentsItem.addActionListener(e -> openStudents());
        coursesItem.addActionListener(e -> openCourses());

        managementMenu.add(studentsItem);
        managementMenu.add(coursesItem);

        // Quiz
        JMenuItem quizItem = new JMenuItem("Take Quiz");
        quizItem.addActionListener(e -> openQuiz());

        // Results
        JMenuItem resultsItem = new JMenuItem("Results");
        resultsItem.addActionListener(e -> openResults());

        // Help
        JMenuItem helpItem = new JMenuItem("Change password");
        helpItem.addActionListener(e -> openChangePassword());

        // Logout
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(e -> logout());

        menuBar.add(managementMenu);
        menuBar.add(quizItem);
        menuBar.add(resultsItem);
        menuBar.add(helpItem);
        menuBar.add(logoutItem);

        return menuBar;
    }

    private JPanel createUserInfoPanel() {

        JPanel root = new javax.swing.JPanel(new BorderLayout(15, 15));
        root.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        root.add(title, BorderLayout.NORTH);

        // Card panel
        javax.swing.JPanel card = new javax.swing.JPanel();
        card.setLayout(new java.awt.GridLayout(2, 1, 10, 10));
        card.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY),
                javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        usernameLabel = new JLabel();
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        passwordLabel = new JLabel();
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        quizzesLabel = new JLabel();
        quizzesLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        card.add(usernameLabel);
        card.add(passwordLabel);
        card.add(quizzesLabel);

        root.add(card, BorderLayout.CENTER);

        // Refresh button
        JButton refreshButton = new javax.swing.JButton("Refresh");
        refreshButton.addActionListener(e -> loadUserData());

        javax.swing.JPanel bottom = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        bottom.add(refreshButton);

        root.add(bottom, BorderLayout.SOUTH);

        // Initial load
        loadUserData();

        return root;
    }

    private void loadUserData() {
        try {
            usernameLabel.setText("Username: " + db.getUsername());
            passwordLabel.setText("Password: " + db.getPassword());
            quizzesLabel.setText("Quizzes taken: " + db.getQuizzesTaken());
        } catch (Exception e) {
            usernameLabel.setText("Failed to load user data");
            quizzesLabel.setText("");
        }
    }

    private void openStudents() {
//        new StudentFrame().setVisible(true);
    }

    private void openCourses() {
//        new CourseFrame().setVisible(true);
    }

    private void openQuiz() {
        new QuizSelectionView(db).setVisible(true);
    }

    private void openResults() {
        new ResultsView(db).setVisible(true);
    }

    private void openChangePassword() {
        new ChangePasswordView(db).setVisible(true);
    }

    private void logout() {
        dispose();
        new LoginView(db);
    }
}
