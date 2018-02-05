/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import budgetos.application.Transaction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Michael
 */
public class LoadButtonEvent implements ActionListener {

    private ProgramState state;
    private MainUISet uiSet;
    private InputStream input;

    public LoadButtonEvent(ProgramState state, MainUISet uiSet) {
        this.state = state;
        this.uiSet = uiSet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //find the save directory
        File dir = new File("BudgetData");

        ArrayList<String> yearsLoaded = new ArrayList<String>();
        ArrayList<String> transactionsLoaded = new ArrayList<String>();

        //Go through the files    
        File[] directoryListing = dir.listFiles();

        for (File child : directoryListing) {

            String name = child.getName().substring(0, child.getName().length() - 4);

            //check if the name is only a four-digit number 
            if (name.matches("[0-9]{4}")) {
                //load all the years from the files into current years. 
                state.addYear(Integer.parseInt(name));
                yearsLoaded.add(name);

                try {
                    input = new FileInputStream("BudgetData/" + name + ".xls");

                    Workbook wb = WorkbookFactory.create(input);
                    Sheet sheet = wb.getSheetAt(0);

                    Integer rowMax = sheet.getPhysicalNumberOfRows();

                    Integer monthNum = 0;

                    while (monthNum < 12) {
                        Integer rowNum = 1;

                        while (rowNum < rowMax) {
                            String fromCell = "";

                            Row row = sheet.getRow(rowNum);
                            Cell cell = row.getCell(monthNum);

                            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

                            CellValue cellValue = evaluator.evaluate(cell);

                            fromCell = cellValue.getStringValue();

                            if (!(fromCell.length() < 3)) {
                                String[] parts = fromCell.split(":");
                                Transaction trans = new Transaction(Double.parseDouble(parts[0]), parts[1], parts[2]);
                                state.addTransaction(Integer.parseInt(name), monthNum, trans);
                                transactionsLoaded.add(parts[0]);

                            }

                            rowNum++;

                        }
                        monthNum++;
                    }

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LoadButtonEvent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LoadButtonEvent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidFormatException ex) {
                    Logger.getLogger(LoadButtonEvent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (EncryptedDocumentException ex) {
                    Logger.getLogger(LoadButtonEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (name.equals("state")) {
                // System.out.println("Loading state-file");

                try {
                    //load the rest of the categories from the state-file (if they are not already contained)

                    //read the state file into a string
                    input = new FileInputStream("BudgetData/state.txt");

                    ArrayList<String> lines = new ArrayList<String>();

                    Scanner reader = new Scanner(input);

                    while (reader.hasNextLine()) {
                        lines.add(reader.nextLine());

                    }

                    //add the categories from the file
                    String[] parts = lines.get(2).split(":");

                    for (String blub : parts) {
                        // System.out.println("I added the category " + blub);
                        state.addCategory(blub);

                    }

                    //set the current year and month from the file
                    state.setCurrentYear(Integer.parseInt(lines.get(0)));
                    state.setCurrentMonth(lines.get(1));

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LoadButtonEvent.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        if (yearsLoaded.size() > 1 && this.state.getYears().size() > 1) {
            uiSet.getSwitchYear().setEnabled(true);

        }

        if (state.getYears().size() > 0) {
            uiSet.getNewTransaction().setEnabled(true);
            uiSet.getSaveButton().setEnabled(true);
            uiSet.getTransactionOptions().setEnabled(true);
            uiSet.getCurYear().setText("Current year: " + state.getCurrentYear());
            uiSet.getMonthDisplay().setEnabled(true);

        }

        if (!yearsLoaded.isEmpty()) {

            uiSet.getDisplay().append("\r\n");
            uiSet.getDisplay().append("\r\n");
            uiSet.getDisplay().append("-----------Budget Data loaded!--------------");
            uiSet.getDisplay().append("\r\n");
            uiSet.getDisplay().append("Loaded the years: ");
            uiSet.getDisplay().append("\r\n");

            for (String year : yearsLoaded) {
                uiSet.getDisplay().append("   " + year);
                uiSet.getDisplay().append("\r\n");
                uiSet.getDisplay().append("\r\n");
            }

            uiSet.getDisplay().append("Also loaded a total of " + transactionsLoaded.size() + " Transactions.");
        }

    }

}
