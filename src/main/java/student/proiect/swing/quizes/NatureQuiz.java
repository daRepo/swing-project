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
public class NatureQuiz extends Quiz {
    
    private final List<Question> questions = new ArrayList();
    
    public NatureQuiz() {
    
        Question q = new Question("Which animal is known as the largest land mammal?");
        q.addAnswer("Giraffe", false);
        q.addAnswer("African elephant", true);
        q.addAnswer("Rhinoceros", false);
        q.addAnswer("Hippopotamus", false);
        questions.add(q);
    
        q = new Question("What do you call a group of wolves?");
        q.addAnswer("Herd", false);
        q.addAnswer("Flock", false);
        q.addAnswer("Pack", true);
        q.addAnswer("Swarm", false);
        questions.add(q);
    
        q = new Question("What type of animal is a dolphin?");
        q.addAnswer("Fish", false);
        q.addAnswer("Reptile", false);
        q.addAnswer("Mammal", true);
        q.addAnswer("Amphibian", false);
        questions.add(q);
    
        q = new Question("Which animal is famous for changing its color to blend with its surroundings?");
        q.addAnswer("Octopus", false);
        q.addAnswer("Chameleon", true);
        q.addAnswer("Frog", false);
        q.addAnswer("Snake", false);
        questions.add(q);
    
        q = new Question("Which bird is known for its ability to fly backward?");
        q.addAnswer("Eagle", false);
        q.addAnswer("Sparrow", false);
        q.addAnswer("Owl", false);
        q.addAnswer("Hummingbird", true);
        questions.add(q);
    }

    @Override
    public String getTitle() {
        
        return "Nature quiz";
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
