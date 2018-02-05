/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos;

import budgetos.gui.UserInterface;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Michael
 */
public class BudgetOs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
     //   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);
    }
    
}
