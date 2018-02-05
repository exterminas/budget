/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.Month;
import budgetos.application.ProgramState;
import budgetos.application.Transaction;
import budgetos.application.Year;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Michael
 */
public class SaveButtonEvent implements ActionListener {

    private ProgramState state;
    private FileOutputStream out;
    private MainUISet uiSet;

    public SaveButtonEvent(ProgramState state, MainUISet uiSet) {
        this.state = state;
        this.uiSet = uiSet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        File dir = new File("BudgetData");
        dir.mkdir();

        Workbook wb = new HSSFWorkbook();

        Row r = null;

        int curMax = 0;

        Sheet s = wb.createSheet();

        for (Year year : state.getYears()) {
            try {
                out = new FileOutputStream("BudgetData/" + year.toString() + ".xls");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SaveButtonEvent.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (Month month : year.getMonths()) {

                if (month.getTransactions().size() > curMax) {
                    curMax = month.getTransactions().size();
                }

            }

            for (Integer num = 0; num <= curMax; num++) {
                // create a row
                r = s.createRow(num);
                System.out.println("Created row number " + num);

                Integer cellNumber = 0;

                if (num == 0) {
                    for (Month month : year.getMonths()) {
                        Cell cell = r.createCell(cellNumber);
                        cell.setCellValue(month.toString());
                        cellNumber++;
                    }

                } else {

                    for (Month month : year.getMonths()) {

                        Cell cell = r.createCell(cellNumber);

                        try {
                            Transaction curTrans = year.getMonths().get(cellNumber).getTransactions().get(num - 1);
                            cell.setCellValue(curTrans.getValue() + ":" + curTrans.getCategory() + ":" + curTrans.getDescription());
                        } catch (Exception p) {
                            cell.setCellValue(" ");
                        }
                        cellNumber++;
                    }
                }

            }

            try {
                wb.write(out);
            } catch (IOException ex) {
                Logger.getLogger(SaveButtonEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(SaveButtonEvent.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("BudgetData/state.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveButtonEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.append(state.getCurrentYear().toString());
        writer.append("\r\n");
        writer.append(state.getCurrentMonth().toString());
        writer.append("\r\n");
        
        for (String cat : state.getCategories()) {
            writer.append(cat);
            writer.append(":");
        
        }
        
        writer.close();

        uiSet.getDisplay().append("\r\n");
        uiSet.getDisplay().append("\r\n");
        uiSet.getDisplay().append("-----------Budget Data saved!--------------");

    }
}
