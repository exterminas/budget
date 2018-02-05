/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import budgetos.application.Year;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author Michael
 */
public class SwitchYearWindow implements ActionListener {

    private ProgramState state;
    private MainDisplay display;
    private JLabel yearLabel;

    public SwitchYearWindow(ProgramState state, MainUISet uiSet) {
        this.state = state;
        this.display = uiSet.getDisplay();
        this.yearLabel = uiSet.getCurYear();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state.getSwitchYearWindow() == false) {
            state.setSwitchYearWindow(true);
            createComponents();
        }
    }

    private void createComponents() {

        JFrame frame = new JFrame("Switch Current Year");
        frame.setPreferredSize(new Dimension(200, 500));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scroll, BorderLayout.WEST);

        Integer count = 0;

        for (Year year : state.getYears()) {
            JButton button = new JButton(year.toString());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = count;
            constraints.gridheight = 1;
            constraints.gridwidth = 1;
            constraints.weightx = 0.1;
            constraints.weighty = 0.1;
            constraints.fill = GridBagConstraints.BOTH;
            panel.add(button, constraints);
            count++;
            YearButtonEvent witnessMe = new YearButtonEvent(state, display, button, yearLabel);
            button.addActionListener(witnessMe);

        }
        
        frame.add(panel, BorderLayout.CENTER);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                state.setSwitchYearWindow(Boolean.FALSE);

            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
