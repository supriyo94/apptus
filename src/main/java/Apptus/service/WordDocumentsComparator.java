package Apptus.service;

import Apptus.util.ResponseDTO;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class WordDocumentsComparator {

    @Autowired
    WordComparatorNew wordComparatorNew;

    @Autowired
    Line_WordComparator line_WordComparator;

    /**
     * This method will compare two version of document and return responseDTO
     * it will invoke wordComparatorNew.WordDifferences to get the responseDTO which will contain summary of
     * modifications - i. e insertions/deletions and their respective counts
     * @param path1
     * @param path2
     * @return
     */
    public ResponseDTO compareDocuments(String path1, String path2) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            XWPFDocument doc1 = new XWPFDocument(new FileInputStream(path1));
            XWPFDocument doc2 = new XWPFDocument(new FileInputStream(path2));
            List<XWPFParagraph> page1_paragraphsList = doc1.getParagraphs();
            List<XWPFParagraph> page2_paragraphsList = doc2.getParagraphs();

            CopyOnWriteArrayList<XWPFParagraph> pg1_parasList_bkp = new CopyOnWriteArrayList<>(page1_paragraphsList);
            CopyOnWriteArrayList<XWPFParagraph> pg2_parasList_bkp = new CopyOnWriteArrayList<>(page2_paragraphsList);

            ArrayList<String> matchedParasList = new ArrayList<>();

            Map<Integer, String> parassWithNumber_map = new LinkedHashMap<>();
            Set<XWPFParagraph> matchedPg1_Para_Set = new HashSet<>();
            Set<XWPFParagraph> matchedPg2_Para_Set = new HashSet<>();

            // Step 1 : paragraph matching
            for (int page1_Index = 0; page1_Index < pg1_parasList_bkp.size(); page1_Index++) {

                XWPFParagraph page1_paragraph = pg1_parasList_bkp.get(page1_Index);
                String page1_paragraph_string = page1_paragraph.getText();

                boolean isMatchedParagraphExists = false;
                for (int page2_Index = 0; page2_Index < pg2_parasList_bkp.size(); page2_Index++) {

                    XWPFParagraph page2_paragraph = pg2_parasList_bkp.get(page2_Index);
                    String page2_paragraph_string = page2_paragraph.getText();

                    if (page1_paragraph_string.equalsIgnoreCase(page2_paragraph_string)) {
                        parassWithNumber_map.put(page1_Index, page1_paragraph_string);
                        matchedPg1_Para_Set.add(page1_paragraph);
                        matchedPg2_Para_Set.add(page2_paragraph);
                        isMatchedParagraphExists = true;
                        break;

                    }
                }
                if (isMatchedParagraphExists) {
                    matchedParasList.add(page1_paragraph_string);
                }
            }

            // Step 2 : remove matched Paras
            removeMatchedParas(pg1_parasList_bkp,
                    pg2_parasList_bkp,
                    matchedPg1_Para_Set,
                    matchedPg2_Para_Set);


            // Step 3: Line matching
            ArrayList<XWPFParagraph> pg2_line_matched_List = new ArrayList<>();
            ArrayList<XWPFParagraph> pg1_line_matched_List = lineMatching( pg1_parasList_bkp,
                    pg2_parasList_bkp,
                    parassWithNumber_map,
                    matchedPg1_Para_Set,
                    matchedPg2_Para_Set,
                    pg2_line_matched_List
                    );
            // Step 4 : remove matched Paras
            removeMatchedParas(pg1_parasList_bkp,
                    pg2_parasList_bkp,
                    matchedPg1_Para_Set,
                    matchedPg2_Para_Set);

            // Step 5 : Word matching
            responseDTO = wordComparatorNew.WordDifferences(pg1_parasList_bkp,
                    pg2_parasList_bkp,
                    parassWithNumber_map,
                    matchedParasList,
                    pg1_line_matched_List,
                    pg2_line_matched_List,
                    page2_paragraphsList, doc1, doc2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseDTO;
    }

    public void removeMatchedParas(CopyOnWriteArrayList<XWPFParagraph> page1_paraList_bkp,
                                   CopyOnWriteArrayList<XWPFParagraph> page2_paraList_bkp,
                                   Set<XWPFParagraph> matchedPg1_Para_Set,
                                   Set<XWPFParagraph> matchedPg2_Para_Set) {

        for (XWPFParagraph matchedPara : matchedPg2_Para_Set) {
            page2_paraList_bkp.remove(matchedPara);
        }
    }

    public ArrayList<XWPFParagraph> lineMatching(List<XWPFParagraph> page1_paragraphsList_bkp,
                             List<XWPFParagraph> page2_paragraphsList_bkp,
                             Map<Integer, String> parasWithNumber_map,
                             Set<XWPFParagraph> matchedPg1_Para_Set,
                             Set<XWPFParagraph> matchedPg2_Para_Set,
                             ArrayList<XWPFParagraph> pg2_line_matched_List ) {

        matchedPg1_Para_Set.clear();
        matchedPg2_Para_Set.clear();

        ArrayList<XWPFParagraph> matched_Line_List = new ArrayList<>();


        for (int pg1_Index = 0; pg1_Index < page1_paragraphsList_bkp.size(); pg1_Index++) {

            XWPFParagraph pg1_para = page1_paragraphsList_bkp.get(pg1_Index);
            String pg1_para_String = pg1_para.getText();
            String[] pg1_lines_array = pg1_para_String.split("\\.");
            boolean isLineMatched = false;
            for (int pg2_Index = 0; pg2_Index < page2_paragraphsList_bkp.size(); pg2_Index++) {
                XWPFParagraph pg2_para = page2_paragraphsList_bkp.get(pg2_Index);
                String pg2_String = pg2_para.getText();
                String[] pg2_lines_array = pg2_String.split("\\.");

                for (int pg1_para_line = 0; pg1_para_line < pg1_lines_array.length; pg1_para_line++) {

                    for (int pg2_para_line = 0; pg2_para_line < pg2_lines_array.length; pg2_para_line++) {
                        if (pg1_lines_array[pg1_para_line].equalsIgnoreCase(pg2_lines_array[pg2_para_line])) {
                            parasWithNumber_map.put(1, pg1_para_String);
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
                    pg2_line_matched_List.add(pg2_para);
                    break;
                }
            }
            if (isLineMatched) {
                matched_Line_List.add(pg1_para);
            }
        }
        return  matched_Line_List;
    }
}
