package Apptus.service;

import Apptus.util.ResponseDTO;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Component
public class TwoWordDiffNew {

    @Autowired
    WordComparator wordComparator;

    @Autowired
    Line_WordComparator line_WordComparator;

    public ResponseDTO WordDiffernces(String path1, String path2) {
        ResponseDTO responseDTO = new ResponseDTO();
        Map summaryMap = null;
        try {
            int deleteCount = 0;
            int insertCount = 0;
            int numberChange = 0;
            summaryMap = new HashMap();
            XWPFDocument doc1 = new XWPFDocument(new FileInputStream(path1));
            XWPFDocument doc2 = new XWPFDocument(new FileInputStream(path2));
            // Create a new document for the differences
            XWPFDocument diffDoc = new XWPFDocument();

            // Compare the paragraphs of the two documents
            List<XWPFParagraph> page1_paragraphsList = doc1.getParagraphs();
            List<XWPFParagraph> page2_paragraphsList = doc2.getParagraphs();

            ArrayList<XWPFParagraph> page1_paragraphsList_bkp = new ArrayList<>(page1_paragraphsList);
            ArrayList<XWPFParagraph> page2_paragraphsList_bkp = new ArrayList<>(page2_paragraphsList);

            int size1 = page1_paragraphsList_bkp.size();
            int size2 = page2_paragraphsList_bkp.size();

            List<String> insertedTexts = new ArrayList<>();
            List<String> deletedTexts = new ArrayList<>();

            Map<Integer, String> parassWithNumber_map = new LinkedHashMap<>();
            Set<XWPFParagraph> matchedPg1_Para_Set = new HashSet<>();
            Set<XWPFParagraph> matchedPg2_Para_Set = new HashSet<>();
            // Step 1 : paragraph matching
            for (int page1_Index = 0; page1_Index < size1; page1_Index++) {

                XWPFParagraph page1_paragraph = page1_paragraphsList_bkp.get(page1_Index);
                String page1_paragraph_string = page1_paragraph.getText();
                System.out.println(page1_paragraph_string);

                boolean isMatchedParagraphExists = false;
                for (int page2_Index = 0; page2_Index < size2; page2_Index++) {

                    XWPFParagraph page2_paragraph = page2_paragraphsList_bkp.get(page2_Index);
                    String page2_paragraph_string = page2_paragraph.getText();
                    System.out.println(page2_paragraph_string);

                    if (page1_paragraph_string.equalsIgnoreCase(page2_paragraph_string)) {
                        System.out.println("equals");
                        parassWithNumber_map.put(page1_Index, page1_paragraph_string);
                        matchedPg1_Para_Set.add(page1_paragraph);
                        matchedPg2_Para_Set.add(page2_paragraph);
                        isMatchedParagraphExists = true;
                        break;

                    }
                }
                if (isMatchedParagraphExists) {
                    XWPFParagraph diffParagraph = diffDoc.createParagraph();
                    XWPFRun diffRun = diffParagraph.createRun();
                    diffRun.setColor("000000");
                    diffRun.setText(page1_paragraph_string);
                }
            }

            // Step 2 : remove matched Paras
            removeMatchedParas(page1_paragraphsList_bkp,
                    page2_paragraphsList_bkp,
                    matchedPg1_Para_Set,
                    matchedPg2_Para_Set);


            // Step 3: Line matching
            lineMatching( page1_paragraphsList_bkp,
                    page2_paragraphsList_bkp,
                    parassWithNumber_map,
                    diffDoc,
                    matchedPg1_Para_Set,
                    matchedPg2_Para_Set,
                    summaryMap);

            // Step 2 : remove matched Paras
            removeMatchedParas(page1_paragraphsList_bkp,
                    page2_paragraphsList_bkp,
                    matchedPg1_Para_Set,
                    matchedPg2_Para_Set);

            // Step 3 : Word matching
            responseDTO = wordComparator.WordDifferences(page1_paragraphsList_bkp,
                    page2_paragraphsList_bkp,
                    parassWithNumber_map,
                    diffDoc,
                    summaryMap);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseDTO;
    }

    public void removeMatchedParas(ArrayList<XWPFParagraph> page1_paragraphsList_bkp,
                                   ArrayList<XWPFParagraph> page2_paragraphsList_bkp,
                                   Set<XWPFParagraph> matchedPg1_Para_Set,
                                   Set<XWPFParagraph> matchedPg2_Para_Set
    ) {
        for (XWPFParagraph matchedPara : matchedPg1_Para_Set) {
            page1_paragraphsList_bkp.remove(matchedPara);
        }
        for (XWPFParagraph matchedPara : matchedPg2_Para_Set) {
            page2_paragraphsList_bkp.remove(matchedPara);
        }
    }

    public void lineMatching(List<XWPFParagraph> page1_paragraphsList_bkp,
                             List<XWPFParagraph> page2_paragraphsList_bkp,
                             Map<Integer, String> parasWithNumber_map,
                             XWPFDocument diffDoc,
                             Set<XWPFParagraph> matchedPg1_Para_Set,
                             Set<XWPFParagraph> matchedPg2_Para_Set,
                             Map summaryMap) {

        matchedPg1_Para_Set.clear();
        matchedPg2_Para_Set.clear();

        ArrayList<String> pg1_line_matched_List = new ArrayList<>();
        ArrayList<String> pg2_line_matched_List = new ArrayList<>();

        for (int pg1_Index = 0; pg1_Index < page1_paragraphsList_bkp.size(); pg1_Index++) {

            XWPFParagraph pg1_para = page1_paragraphsList_bkp.get(pg1_Index);
            String pg1_String = pg1_para.getText();
            System.out.println(pg1_String);
            String[] pg1_lines_array = pg1_String.split("\\.");
            boolean isLineMatched = false;
            for (int pg2_Index = 0; pg2_Index < page2_paragraphsList_bkp.size(); pg2_Index++) {
                XWPFParagraph pg2_para = page2_paragraphsList_bkp.get(pg2_Index);
                String pg2_String = pg2_para.getText();
                String[] pg2_lines_array = pg2_String.split("\\.");

                for (int pg1_para_line = 0; pg1_para_line < pg1_lines_array.length; pg1_para_line++) {

                    for (int pg2_para_line = 0; pg2_para_line < pg2_lines_array.length; pg2_para_line++) {
                        if (pg1_lines_array[pg1_para_line].equalsIgnoreCase(pg2_lines_array[pg2_para_line])) {
                            System.out.println("line equals");
                            System.out.println("line equals 1" + pg1_lines_array[pg1_para_line]);
                            System.out.println("line equals 1" + pg2_lines_array[pg2_para_line]);

                            parasWithNumber_map.put(1, pg1_String);
                            matchedPg1_Para_Set.add(pg1_para);
                            matchedPg2_Para_Set.add(pg2_para);
                            isLineMatched = true;
                            break;

                        }
                    }
                    if(isLineMatched){
                        break;
                    }
                }
                if(isLineMatched){
                    pg2_line_matched_List.add(pg2_String);
                    break;
                }
            }
            if (isLineMatched) {
                pg1_line_matched_List.add(pg1_String);
                line_WordComparator.WordDifferences_inParagraph(pg1_line_matched_List,
                                                                pg2_line_matched_List,
                                                                diffDoc,
                                                                summaryMap );
            }
        }
    }
}
