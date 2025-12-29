/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import student.proiect.swing.ProiectSwing;
import student.proiect.swing.quizes.Question;
import student.proiect.swing.quizes.Quiz;
import student.proiect.swing.quizes.QuizFactory;
import student.proiect.swing.util.Database;

/**
 *
 * @author cptsoarbezeama
 */
public class QuizView extends JFrame {

    private final Quiz quiz;
    private List<String> correctAnswers;
    private int currentIndex = 0;

    private final Database db;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton nextButton;

    public QuizView(String quiz, Database db) {

        this.db = db;
        this.quiz = QuizFactory.getQuiz(quiz);
        initUI();
        loadQuestion();
    }

    private void initUI() {
        setTitle(quiz.getTitle() + ProiectSwing.TITLE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        questionLabel = new JLabel("Question", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            buttonGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> handleNext());
        add(nextButton, BorderLayout.SOUTH);
    }

    private void loadQuestion() {

        buttonGroup.clearSelection();
        correctAnswers = new ArrayList();
        Question q = quiz.getQuestion();
        questionLabel.setText(q.getQuestion());

        HashMap<String, Boolean> answers = q.getAnswers();
        String[] options = answers.keySet().toArray(new String[0]);

        for (int i = 0; i < options.length; i++) {

            optionButtons[i].setText(options[i]);

            if (answers.get(options[i]) == true) {

                correctAnswers.add(options[i]);
            }
        }

        if (currentIndex == quiz.getTotalQuestions() - 1) {
            nextButton.setText("Finish");
        }
    }

    private void handleNext() {

        String selected = getSelectedOptionText();

        if (selected == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an answer.",
                    "No Answer",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (correctAnswers.contains(getSelectedOptionText())) {

            quiz.correct();
        } else {

            quiz.wrong();
        }

        currentIndex++;

        if (currentIndex < quiz.getTotalQuestions()) {
            loadQuestion();
        } else {
            showResult();
            try {
                db.saveResult(quiz.getTitle(), quiz.getCorrect(), quiz.getTotalQuestions());
            } catch (SQLException ex) {
                Logger.getLogger(QuizView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String getSelectedOptionText() {

        for (JRadioButton radioButton : optionButtons) {

            if (radioButton.isSelected()) {

                return radioButton.getText();
            }
        }

        return null;
    }

    private void showResult() {
        JOptionPane.showMessageDialog(
                this,
                "Quiz finished!\nScore: " + quiz.calculateResult(),
                "Result",
                JOptionPane.INFORMATION_MESSAGE
        );
        dispose();
    }
}
