/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.dto;

/**
 *
 * @author cptsoarbezeama
 */
public class ResultDto {

    private final int id;
    private final String username;
    private final String quiz;
    private final int correct;
    private final int total;
    private final java.sql.Timestamp completionTime;

    public ResultDto(int id, String username, String quiz, int correct, int total, java.sql.Timestamp completionTime) {

        this.id = id;
        this.username = username;
        this.quiz = quiz;
        this.correct = correct;
        this.total = total;
        this.completionTime = completionTime;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getQuiz() {
        return quiz;
    }

    public int getCorrect() {
        return correct;
    }

    public int getTotal() {
        return total;
    }

    public java.sql.Timestamp getCompletionTime() {
        return completionTime;
    }
}
