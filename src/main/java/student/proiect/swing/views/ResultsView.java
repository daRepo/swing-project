/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.views;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import student.proiect.swing.ProiectSwing;
import student.proiect.swing.dto.ResultDto;
import student.proiect.swing.util.Database;

/**
 *
 * @author cptsoarbezeama
 */
public class ResultsView extends JFrame {

    private final Database db;
    private JTable table;

    public ResultsView(Database db) {

        this.db = db;
        initUI();
        loadResults();
    }

    private void initUI() {

        setTitle("All Quiz Results" + ProiectSwing.TITLE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadResults());
        add(refreshButton, BorderLayout.SOUTH);
    }

    private void loadResults() {

        try {
            List<ResultDto> results = db.getAllResults();

            String[] columnNames = {"ID", "User", "Quiz", "Correct", "Total", "Percentage", "Completed At"};
            Object[][] data = new Object[results.size()][columnNames.length];

            for (int i = 0; i < results.size(); i++) {
                ResultDto r = results.get(i);
                data[i][0] = r.getId();
                data[i][1] = r.getUsername();
                data[i][2] = r.getQuiz();
                data[i][3] = r.getCorrect();
                data[i][4] = r.getTotal();
                data[i][5] = String.format("%.2f%%", r.getCorrect() * 100.0 / r.getTotal());
                data[i][6] = r.getCompletionTime();
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // make table read-only
                }
            };

            table.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load results: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
