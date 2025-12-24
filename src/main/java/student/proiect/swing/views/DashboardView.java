/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.proiect.swing.views;

import javax.swing.JFrame;
import student.proiect.swing.util.Database;

/**
 *
 * @author cptsoarbezeama
 */
public class DashboardView extends JFrame {
    
    private final Database db;
    
    public DashboardView(Database db) {
    
        super("Dumitras Alexandru");
        this.db = db;
    }
}
