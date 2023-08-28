package Apptus.service;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.List;

public class ParagraphReader {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("v5.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));

            List<XWPFParagraph> paragraphList = xdoc.getParagraphs();

            for (XWPFParagraph paragraph : paragraphList) {

                CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
                cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));
                CTLvl cTLvl = cTAbstractNum.addNewLvl();
                cTLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
                //CTLevelText a = cTLvl.addNewLvlText();

                XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
                XWPFNumbering numbering = xdoc.createNumbering();
                BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
                BigInteger numID = numbering.addNum(abstractNumID);

                System.out.println(paragraph.getText());
              //  System.out.println(paragraph.getAlignment());
                System.out.println(paragraph.getRuns().size());
                System.out.println(paragraph.getStyle());
                System.out.println("break");
                //paragraph.se
                // Returns numbering format for this paragraph, eg bullet or lowerLetter.
                System.out.println(paragraph.getNumFmt());
               // System.out.println(paragraph.getAlignment());

               // System.out.println(paragraph.isWordWrapped());

                System.out.println("********************************************************************");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
