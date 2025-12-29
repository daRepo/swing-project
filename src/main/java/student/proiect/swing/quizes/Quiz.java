/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.quizes;

import java.util.List;

/**
 *
 * @author cptsoarbezeama
 */
public abstract class Quiz {

    private int index = 0;
    private int correctCount = 0;
    private int wrongCount = 0;

    public abstract String getTitle();

    public abstract int getTotalQuestions();

    protected abstract List<Question> getQuestions();

    public Question getQuestion() {

        List<Question> questions = getQuestions();

        if (questions.size() == index) {

            return null;
        }

        Question returnable = questions.get(index);
        index++;

        return returnable;
    }

    public void wrong() {
        wrongCount++;
    }

    public void correct() {

        correctCount++;
    }

    public String calculateResult() {

        if (getTotalQuestions() == 0) {
            return "0.00%";
        }

        double percentage = (correctCount * 100.0) / getTotalQuestions();

        // Format to 2 decimal places
        return String.format("%.2f%%", percentage);
    }
    
    public int getCorrect() {
    
        return correctCount;
    }
}
