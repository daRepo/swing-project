/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.quizes;

/**
 *
 * @author cptsoarbezeama
 */
public class QuizFactory {

    private static final String PF = "Programming Fundamentals";
    private static final String NQ = "Nature Quiz";

    public static String[] getQuizes() {

        return new String[]{
            PF,
            NQ
        };
    }

    public static Quiz getQuiz(String quiz) {

        switch (quiz) {

            case PF:
                return new ProgrammingFundamentalsQuiz();

            case NQ:
                return new NatureQuiz();

            default:
                return null;
        }
    }
}
