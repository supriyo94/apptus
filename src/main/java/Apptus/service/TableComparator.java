package Apptus.service;

import org.apache.poi.xwpf.usermodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to compare tables of two documents using below methods-
 * 1. compareTable()-
 *                 this will return hash map with summary of deleted and inserted texts
 * 2. saveDocWithColorCoding()-
 *                 this will color the deleted text in Red with strike through
 * and inserted text in blue
 */
public class TableComparator {

    public static final String Insertions = "INSERTIONS";
    public static final String Deletions = "DELETIONS";

    /**
     * This method will compare tables in two different versions of document
     * Logic:
     * 1. create map tableModifications, arraylists- deletions, insertions to collect the modified text
     * 2. get the maximum count of tables from two documents
     * 3. iterate through tables one by one
     *      A. get the row counts from tables of both document. There are three cases that needs to be considered:
     *          I. case 1: new row inserted in table of document 2
     *          i. e  if (numRows1 < numRows2) :
     *
     *            a. get brand-new row details:
     *              1. get the row from table2
     *              2. get the cells for that row
     *              3. iterate through each cell
     *              4. collect the inserted text in "insertions" arraylist and add it in hashMap "tableModifications" with key "INSERTIONS"
     *
     *            b. compare existing rows apart from newly inserted:
     *              1. get the rows from table1 & table2
     *                  i. find Difference in cells:
     *                       -if cell1 & cell2 texts are not equal compare the text
     *                       -add cell2 text in "insertions" arraylist
     *                       -add cell1 text in "deletions" arraylist
     *                       -add "insertions" arraylist in hashMap "tableModifications" with key "INSERTIONS"
     *                       -add "deletions" arraylist in hashMap "tableModifications" with key "DELETIONS"
     *
     *                  ii. find Insertion in cell:
     *                      - if cell1 is empty and cell2 have value add cell2 text in "insertions" arraylist
     *                      - add "insertions" arraylist in hashMap "tableModifications" with key "INSERTIONS"
     *                  iii. find Deletion in cell:
     *                      - if cell2 is empty and cell1 have value add cell1 text in "deletions" arraylist
     *                      - add "deletions" arraylist in hashMap "tableModifications" with key "DELETIONS"
     *
     *         II. case 2: existing row deleted from table of document 1
     *         i. e else if (numRows1 > numRows2)
     *
     *          a. get existing deleted row details from table1 of doc1:
     *              1. get the row from table1
     *              2. get the cells for that row
     *              3. iterate through each cell
     *              4. collect the deleted text in "deletions" arraylist and add it in hashMap "tableModifications" with key "DELETIONS"
     *          b. compare existing rows apart from deleted row: followed same steps metioned in 3.A.b
     *
     *        III. case 3: same row count: followed same steps mentioned in 3.A.b
     * 4. will call saveDocWithColorCoding(doc1, doc2, diffDoc) to get color coding
     *
     * @param doc1 - original version
     * @param doc2 - modified version
     * @param diffDoc - difference document
     * @return HashMap "tableModifications" which contains
     * key - string which gives the type of modification e. g Insertions, Deletions
     * value- ArrayList of string which will have inserted/ deleted words list
     * i. e summary of modified cells
     */
    public Map<String, List<String>> compareTable(XWPFDocument doc1, XWPFDocument doc2, XWPFDocument diffDoc) {
        Map<String, List<String>> tableModifications = new HashMap<>();

        List<String> deletions = new ArrayList<>();
        List<String> insertions = new ArrayList<>();
        //get maximum no.of tables out of doc1 & doc2
        int numTables = Math.max(doc1.getTables().size(), doc2.getTables().size());

        //iterate on max no.of tables
        for (int i = 0; i < numTables; i++) {

            // load tables from doc1 and doc2
            //table 1 represents first table in doc1
            //table 2 represents first table in doc2
            XWPFTable table1 = doc1.getTables().get(i);
            XWPFTable table2 = doc2.getTables().get(i);

            // get row counts of table1 and table2
            int numRows1 = table1.getRows().size();
            int numRows2 = table2.getRows().size();


            //case 1: new row inserted in table of document 2
            if (numRows1 < numRows2) {
                XWPFTableRow newRow = table2.getRow(numRows2 - 1);
                List<String> newCellValues = new ArrayList<>();
                //get brand new inserted row details
                for (int j = 0; j < newRow.getTableCells().size(); j++) {
                    XWPFTableCell newCell = newRow.getCell(j);
                    System.out.println("Column: " + (j + 1) + ": " + newCell.getText());
                    newCellValues.add(newCell.getText());

                }
                insertions.addAll(newCellValues);
                tableModifications.put(Insertions, insertions);

                // compare existing rows apart from newly inserted
                for (int j = 0; j < table1.getRows().size(); j++) {
                    XWPFTableRow row1 = table1.getRow(j);
                    XWPFTableRow row2 = table2.getRow(j);
                    List<String> oldCellValues = new ArrayList<>();
                    List<String> modifiedCellValues = new ArrayList<>();

                    for (int k = 0; k < row1.getTableCells().size(); k++) {
                        XWPFTableCell cell1 = row1.getCell(k);
                        XWPFTableCell cell2 = row2.getCell(k);
                        //find Difference in cell
                        if (!cell1.getText().isEmpty() && !cell2.getText().isEmpty() && !cell1.getText().equals(cell2.getText())) {
                            String result = "Difference found in table " + (i + 1) + ", row " + (j + 1) + ", column " + (k + 1);
                            result += "\nOld value: " + cell1.getText();
                            result += "\nNew value: " + cell2.getText();
                            System.out.println(result);

                            oldCellValues.add(cell1.getText());
                            modifiedCellValues.add(cell2.getText());

                            insertions.addAll(modifiedCellValues);
                            deletions.addAll(oldCellValues);

                            tableModifications.put(Deletions, deletions);
                            tableModifications.put(Insertions, insertions);

                        }
                        //find Insertion in cell
                        if (cell1.getText().isEmpty() && !cell2.getText().isEmpty()) {
                            insertions.add(cell2.getText());
                            tableModifications.put(Insertions, insertions);
                        }
                        //find Deletion in cell
                        if (!cell1.getText().isEmpty() && cell2.getText().isEmpty()) {
                            deletions.add(cell1.getText());
                            tableModifications.put(Deletions, deletions);
                        }
                    }
                }
            }
            // end of case 1
            //case 2: existing row deleted from table of document 1
            else if (numRows1 > numRows2) {
                System.out.println("existing row deleted from table of document 1");

                XWPFTableRow deletedRow = table1.getRow(numRows1 - 1);
                List<String> deletedCellValues = new ArrayList<>();
                for (int j = 0; j < deletedRow.getTableCells().size(); j++) {
                    XWPFTableCell deletedCell = deletedRow.getCell(j);
                    System.out.println("Column: " + (j + 1) + ": " + deletedCell.getText());
                    deletedCellValues.add(deletedCell.getText());
                    XWPFTableCell newCell = deletedRow.getCell(j);

                }
                deletions.addAll(deletedCellValues);
                tableModifications.put(Deletions, deletions);

                System.out.println("row count is same, compare existing rows");
                // compare existing rows apart from newly inserted
                for (int j = 0; j < table1.getRows().size(); j++) {
                    XWPFTableRow row1 = table1.getRow(j);
                    XWPFTableRow row2 = table2.getRow(j);
                    List<String> oldCellValues = new ArrayList<>();
                    List<String> modifiedCellValues = new ArrayList<>();

                    for (int k = 0; k < row1.getTableCells().size(); k++) {
                        XWPFTableCell cell1 = row1.getCell(k);
                        XWPFTableCell cell2 = null != row2 ? row2.getCell(k) : null;
                        //find Difference in cell
                        if (null != cell1 && null != cell2 && !cell1.getText().isEmpty() && !cell2.getText().isEmpty() && !cell1.getText().equals(cell2.getText())) {
                            String result = "Difference found in table " + (i + 1) + ", row " + (j + 1) + ", column " + (k + 1);
                            result += "\nOld value: " + cell1.getText();
                            result += "\nNew value: " + cell2.getText();
                            System.out.println(result);

                            oldCellValues.add(cell1.getText());
                            modifiedCellValues.add(cell2.getText());

                            insertions.addAll(modifiedCellValues);
                            deletions.addAll(oldCellValues);

                            tableModifications.put(Deletions, deletions);
                            tableModifications.put(Insertions, insertions);

                        }
                        //find Insertion in cell
                        if (null != cell1 && null != cell2 && cell1.getText().isEmpty() && !cell2.getText().isEmpty()) {
                            insertions.add(cell2.getText());
                            tableModifications.put(Insertions, insertions);
                        }
                        //find Deletion in cell
                        if (null != cell1 && null != cell2 && !cell1.getText().isEmpty() && cell2.getText().isEmpty()) {
                            deletions.add(cell1.getText());
                            tableModifications.put(Deletions, deletions);
                        }
                    }
                }

            }
            // end of case 2
            //case 3: same row count
            else {
                System.out.println("row count is same");
                for (int j = 0; j < table1.getRows().size(); j++) {

                    XWPFTableRow row1 = table1.getRow(j);
                    XWPFTableRow row2 = table2.getRow(j);
                    List<String> oldCellValues = new ArrayList<>();
                    List<String> newCellValues = new ArrayList<>();

                    for (int k = 0; k < row1.getTableCells().size(); k++) {
                        XWPFTableCell cell1 = row1.getCell(k);
                        XWPFTableCell cell2 = row2.getCell(k);
                        //find Difference in cell
                        if (!cell1.getText().isEmpty() && !cell2.getText().isEmpty() && !cell1.getText().equals(cell2.getText())) {
                            String result = "Difference found in table " + (i + 1) + ", row " + (j + 1) + ", column " + (k + 1);
                            result += "\nOld value: " + cell1.getText();
                            result += "\nNew value: " + cell2.getText();
                            System.out.println(result);

                            oldCellValues.add(cell1.getText());
                            newCellValues.add(cell2.getText());

                            insertions.addAll(newCellValues);
                            deletions.addAll(oldCellValues);

                            tableModifications.put(Deletions, deletions);
                            tableModifications.put(Insertions, insertions);

                        }
                        //find Insertion in cell
                        if (cell1.getText().isEmpty() && !cell2.getText().isEmpty()) {
                            insertions.add(cell2.getText());
                        }
                        //find Deletion in cell
                        if (!cell1.getText().isEmpty() && cell2.getText().isEmpty()) {
                            deletions.add(cell1.getText());
                        }
                    }

                }
            }
            //end of case 3
        }


        tableModifications.put(Insertions, insertions);
        tableModifications.put(Deletions, deletions);

        // Print the results

        System.out.println("Insertions:");
        for (String ins : insertions) {
            System.out.println(ins);
        }
        System.out.println("Deletions:");
        for (String del : deletions) {
            System.out.println(del);
        }

        saveDocWithColorCoding(doc1, doc2, diffDoc);

        return tableModifications;
    }

    /**
     * This method is used to do color coding in diffDoc. It will color deleted text as red and apply strike through,
     * will color inserted text as blue
     * Logic:
     * 1. get the no. of tables count from doc1 and doc2
     * 2. Iterate through which ever count is maximum
     * 3. check if table1 is null i. e new table inserted in doc2
     *      i. create new table "newTable" in diffDoc
     *      ii. iterate through rows of table2 of doc2 and create new XWPFTableRow ("newRow") from "newTable" object
     *      iii. get the cells of table2 row and iterate through it
     *      iv. create XWPFParagraph object "para" from the "newRow" object
     *      v. create XWPFRun, set the cell text and color as blue "0000FF"
     * 4. check if table2 is null i. e existing table is deleted from doc2
     *       i.create new table "newTable" in diffDoc create new table "newTable" in diffDoc
     *       ii. iterate through rows of table1 of doc1 and create new XWPFTableRow ("newRow") from "newTable" object
     *       iii. get the cells of that table1 row and iterate through it
     *       iv. create XWPFParagraph object "para" from the "newRow" object
     *       v.  create XWPFRun, set the cell text and set red "FF0000" color
     *       vi. set StrikeThrough true
     * 5. if table count is same , compare the cells of table and add color coding and styling there
     *  i. get the no. of rows from table1 and table2 and also calculate max no.of columns "numColumns"
     *  ii. iterate through max no.of rows
     *     A. get rows from table1 and table2
     *     B. get no. of cells from row1 and row2
     *     C. iterate through "numColumns"
     *          a.get cell1 and cell2 from numCell1 and numCell2 respectively
     *          b.create new XWPFTableCell object -"comparedCell"
     *              1. if cell1 is null - add new cell from modified doc & set color blue
     *              2. if cell2 is null - add deleted cell from original doc & set color red with strike through
     *              3. if no cell is empty
     *                  - compare cells in tables & add updated cell with old value in red and new value in blue
     *                  - added logic to have old value with red color and strike through and newly added value in blue in same cell
     *
     * @param doc1
     * @param doc2
     * @param diffDoc
     */
    private void saveDocWithColorCoding(XWPFDocument doc1, XWPFDocument doc2, XWPFDocument diffDoc) {
        int numTables1 = doc1.getTables().size();
        int numTables2 = doc2.getTables().size();
        for (int i = 0; i < Math.max(numTables1, numTables2); i++) {
            XWPFTable table1 = i < numTables1 ? doc1.getTables().get(i) : null;
            XWPFTable table2 = i < numTables2 ? doc2.getTables().get(i) : null;
            if (table1 == null) {
                //Add new table from modified doc
                XWPFTable newTable = diffDoc.createTable();
                for (XWPFTableRow row : table2.getRows()) {
                    XWPFTableRow newRow = newTable.createRow();
                    for (XWPFTableCell cell : row.getTableCells()) {
                        XWPFParagraph para = newRow.getCell(0).getParagraphs().get(0);
                        XWPFRun run = para.createRun();
                        run.setText(cell.getText());
                        run.setColor("0000FF");//blue color
                    }

                }

            } else if (table2 == null) {
                //add deleted table from the original document
                XWPFTable newTable = diffDoc.createTable();
                for (XWPFTableRow row : table1.getRows()) {
                    XWPFTableRow newRow = newTable.createRow();
                    for (XWPFTableCell cell : row.getTableCells()) {
                        XWPFParagraph para = newRow.getCell(0).getParagraphs().get(0);
                        XWPFRun run = para.createRun();
                        run.setText(cell.getText());
                        run.setColor("FF0000");//red color
                        run.setStrikeThrough(true); // add strike through
                    }

                }
            } else {
                //compare cells in tables
                XWPFTable comparedTable = diffDoc.createTable();
                int numRows1 = table1.getRows().size();
                int numRows2 = table2.getRows().size();
                int numColumns = Math.max(table1 == null ? 0 : table1.getRow(0).getTableCells().size(),
                        table2 == null ? 0 : table2.getRow(0).getTableCells().size());
                for (int j = 0; j < Math.max(numRows1, numRows2); j++) {
                    XWPFTableRow row1 = j < numRows1 ? table1.getRow(j) : null;
                    XWPFTableRow row2 = j < numRows2 ? table2.getRow(j) : null;
                    XWPFTableRow comparedRow = comparedTable.createRow();
                    int numCell1 = row1 == null ? 0 : row1.getTableCells().size();
                    int numCell2 = row2 == null ? 0 : row2.getTableCells().size();
                    for (int k = 0; k <numColumns; k++) { //Math.max(numCell1, numCell2)
                        XWPFTableCell cell1 = k < numCell1 ? row1.getTableCells().get(k) : null;
                        XWPFTableCell cell2 = k < numCell2 ? row2.getTableCells().get(k) : null;
                        XWPFTableCell comparedCell = comparedRow.createCell();
                        if (cell1 == null) {
                            // add new cell from modified doc
                            for (XWPFParagraph para : cell2.getParagraphs()) {
                                XWPFParagraph newPara = comparedCell.addParagraph();
                                for (XWPFRun run : para.getRuns()) {
                                    XWPFRun newRun = newPara.createRun();
                                    newRun.setText(run.getText(0));
                                    newRun.setColor("0000FF");//Blue Color
                                }

                            }
                        } else if (cell2 == null) {
                            //add deleted cell from original doc
                            for (XWPFParagraph para : cell1.getParagraphs()) {
                                XWPFParagraph newPara = comparedCell.addParagraph();
                                for (XWPFRun run : para.getRuns()) {
                                    XWPFRun newRun = newPara.createRun();
                                    newRun.setText(run.getText(0));
                                    newRun.setColor("FF0000");//red Color
                                    newRun.setStrikeThrough(true);//strike through
                                }
                            }

                        } else {
                            // compare text in the cells
                            String text1 = cell1.getText();
                            String text2 = cell2.getText();
                            if (!text1.equals(text2)) {
                                // add updated cell with old value in red and new value in blue
                                XWPFParagraph newPara = comparedCell.addParagraph();
                                int index = 0;
                                while (index < text1.length() && index < text2.length() && text1.charAt(index) == text2.charAt(index)) {
                                    index++;

                                }

                                if (index > 0) {
                                    XWPFRun oldRun = newPara.createRun();
                                    oldRun.setText(text1.substring(0, index));
                                    oldRun.setColor("FF0000"); // red color
                                    oldRun.setStrikeThrough(true);
                                    XWPFRun newRun = newPara.createRun();
                                    newRun.setText(text2.substring(0, index));
                                    newRun.setColor("0000FF");//blue color
                                }

                                if (index < text1.length()) {
                                    XWPFRun oldRun = newPara.createRun();
                                    oldRun.setText(text1.substring(index));
                                    oldRun.setColor("FF0000"); // red color
                                    oldRun.setStrikeThrough(true);
                                }

                                if (index < text2.length()) {
                                    XWPFRun newRun = newPara.createRun();
                                    newRun.setText(text2.substring(index));
                                    newRun.setColor("0000FF"); // blue color
                                }


                            } else {
                                // add unchanged cell from original doc
                                for (XWPFParagraph para : cell1.getParagraphs()) {
                                    XWPFParagraph newPara = comparedCell.addParagraph();
                                    for (XWPFRun run : para.getRuns()) {
                                        XWPFRun newRun = newPara.createRun();
                                        newRun.setText(run.getText(0));
                                        newRun.setColor("000000");//black Color
                                    }
                                }
                            }
                        }

                        // Copy the formatting from the original cell to the compared cell
/*                        if (cell1 != null && cell2 != null) {
                            CTTbl ctTbl = table1.getCTTbl();
                            XmlCursor cursor = ctTbl.newCursor();
                            cursor.selectPath(".//w:tblPr");
                            cursor.toNextToken();
                            String xml = cursor.xmlText();
                            cursor.dispose();
                            ctTbl = comparedTable.getCTTbl();
                            cursor = ctTbl.newCursor();
                            cursor.selectPath(".//w:tblPr");
                            cursor.toNextToken();
                            cursor.removeXml();
                            cursor.insertXml(xml);
                            cursor.dispose();
                        }*/
                    }
                }
            }

        }
        //copy table style from original document
/*        for(int i = 0; i< numTables1; i++){
            XWPFTable table1 = doc1.getTables().get(i);
            XWPFTable table2 = diffDoc.getTables().get(i);
            table2.setStyleID(table1.getStyleID());

        }*/


    }
}


