/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package student.proiect.swing;

import java.sql.SQLException;
import student.proiect.swing.util.Database;
import student.proiect.swing.views.LoginView;

/**
 *
 * @author cptsoarbezeama
 */
public class ProiectSwing {

    public static void main(String[] args) throws SQLException {

        Database db = new Database();

        LoginView login = new LoginView(db);
    }
}
