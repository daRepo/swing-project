/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.views;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import student.proiect.swing.ProiectSwing;
import student.proiect.swing.quizes.QuizFactory;
import student.proiect.swing.util.Database;

/**
 *
 * @author cptsoarbezeama
 */
public class QuizSelectionView extends JFrame {

    private JList<String> quizList;
    private final Database db;

    public QuizSelectionView(Database db) {
        this.db = db;
        initUI();
    }

    private void initUI() {
        setTitle("Select Quiz" + ProiectSwing.TITLE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Choose a Quiz", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Mock quiz data (single field: Programming)
        DefaultListModel<String> model = new DefaultListModel<>();

        for (String quiz : QuizFactory.getQuizes()) {

            model.addElement(quiz);
        }

        quizList = new JList<>(model);
        quizList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(quizList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton startButton = new JButton("Start Quiz");
        JButton cancelButton = new JButton("Cancel");

        startButton.addActionListener(e -> startQuiz());
        cancelButton.addActionListener(e -> goBack());

        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startQuiz() {
        String selectedQuiz = quizList.getSelectedValue();

        if (selectedQuiz == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a quiz.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Open quiz-taking screen
        new QuizView(selectedQuiz, db).setVisible(true);
        dispose();
    }

    private void goBack() {
        dispose();
    }
}
