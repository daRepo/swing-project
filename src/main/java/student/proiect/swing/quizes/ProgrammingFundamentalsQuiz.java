/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.quizes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cptsoarbezeama
 */
public class ProgrammingFundamentalsQuiz extends Quiz {
    
    private final List<Question> questions = new ArrayList();
    
    public ProgrammingFundamentalsQuiz() {
    
        Question q = new Question("What is the main purpose of a loop in programming?");
        q.addAnswer("To store data", false);
        q.addAnswer("To repeat a block of code", true);
        q.addAnswer("To define variables", false);
        q.addAnswer("To stop program execution", false);
        questions.add(q);
    
        q = new Question("Which of the following is a valid Java data type?");
        q.addAnswer("integer", false);
        q.addAnswer("number", false);
        q.addAnswer("int", true);
        q.addAnswer("real", false);
        questions.add(q);
    
        q = new Question("What does OOP stand for?");
        q.addAnswer("Order of Operations Process", false);
        q.addAnswer("Object-Oriented Programming", true);
        q.addAnswer("Open Output Protocol", false);
        q.addAnswer("Optional Object Processing", false);
        questions.add(q);
    
        q = new Question("Which keyword is used to create an object in Java?");
        q.addAnswer("class", false);
        q.addAnswer("new", false);
        q.addAnswer("this", true);
        q.addAnswer("static", false);
        questions.add(q);
    
        q = new Question("What happens if you access an index outside the bounds of a list in Java?");
        q.addAnswer("The program continues normally", false);
        q.addAnswer("The value is set to null", false);
        q.addAnswer("A warning is printed", false);
        q.addAnswer("An exception is thrown", true);
        questions.add(q);
    }

    @Override
    public String getTitle() {
        
        return "Programming fundamentals";
    }

    @Override
    protected List<Question> getQuestions() {
        return questions;
    }

    @Override
    public int getTotalQuestions() {
        return questions.size();
    }
}
