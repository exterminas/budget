/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.Month;
import budgetos.application.ProgramState;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Michael
 */
public class MonthDisplay extends JPanel {

    private ProgramState state;
    private ArrayList<JButton> buttonList;
    private MainDisplay mainDisplay;

    public MonthDisplay(ProgramState state, MainDisplay mainDisplay) {
        super(new GridLayout(0, 1));
        this.state = state;
        this.mainDisplay = mainDisplay;

        ArrayList<String> monthNames = new ArrayList<String>();
        buttonList = new ArrayList<JButton>();
        monthNames.add("January");
        monthNames.add("February");
        monthNames.add("March");
        monthNames.add("April");
        monthNames.add("May");
        monthNames.add("June");
        monthNames.add("July");
        monthNames.add("August");
        monthNames.add("September");
        monthNames.add("October");
        monthNames.add("November");
        monthNames.add("December");

        for (String blub : monthNames) {
            JButton button = new JButton(blub);
            MonthButtonWatcher watcher = new MonthButtonWatcher(button, mainDisplay, state);
            button.addActionListener(watcher);
            buttonList.add(button);
            super.add(button);
            

        }

    }

    @Override
    public void setEnabled(boolean bool) {

        for (JButton button : buttonList) {
            button.setEnabled(bool);
        }
    }

}


