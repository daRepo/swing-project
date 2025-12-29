/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.quizes;

import java.util.HashMap;

/**
 *
 * @author cptsoarbezeama
 */
public class Question {

    private String question;
    private final HashMap<String, Boolean> answers = new HashMap();

    public Question(String question) {

        this.question = question;
    }

    public void setQuestion(String question) {

        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public HashMap<String, Boolean> getAnswers() {
        return answers;
    }

    public void addAnswer(String text, Boolean isCorrect) {

        answers.put(text, isCorrect);
    }
}
