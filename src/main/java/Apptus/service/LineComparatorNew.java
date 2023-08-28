package Apptus.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class LineComparatorNew {

    @Autowired
    Line_WordComparator_New line_WordComparator_New;
    public boolean lineMatching(XWPFParagraph pg1_para,
                             List<XWPFParagraph> pg2_paraList,
                             Map<Integer, String> parasWithNumber_map,
                             XWPFDocument diffDoc,
                             Set<XWPFParagraph> matchedPg1_Para_Set,
                             Set<XWPFParagraph> matchedPg2_Para_Set,
                             ArrayList<XWPFParagraph> pg2_line_matched_List,
                             Map summaryMap) {

        ArrayList<String> pg1_line_matched_Str_List = new ArrayList<>();
        ArrayList<String> pg2_line_matched_Str_List = new ArrayList<>();

//        for (int pg1_Index = 0; pg1_Index < pg1_paraList.size(); pg1_Index++) {

            String pg1_String = pg1_para.getText();
            System.out.println(pg1_String);
            String[] pg1_lines_array = pg1_String.split("\\.");
            boolean isLineMatched = false;
            for (int pg2_Index = 0; pg2_Index < pg2_line_matched_List.size(); pg2_Index++) {
                XWPFParagraph pg2_para = pg2_line_matched_List.get(pg2_Index);
                String pg2_String = pg2_para.getText();
                String[] pg2_lines_array = pg2_String.split("\\.");

                for (int pg1_para_line = 0; pg1_para_line < pg1_lines_array.length; pg1_para_line++) {

                    for (int pg2_para_line = 0; pg2_para_line < pg2_lines_array.length; pg2_para_line++) {
                        if (pg1_lines_array[pg1_para_line].equalsIgnoreCase(pg2_lines_array[pg2_para_line])) {
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
                    pg2_line_matched_Str_List.add(pg2_String);
                    break;
                }
            }
            if (isLineMatched) {
                pg1_line_matched_Str_List.add(pg1_String);
                line_WordComparator_New.WordDifferences_inParagraph(pg1_line_matched_Str_List,
                        pg2_line_matched_Str_List,
                        diffDoc,
                        pg1_para,
                        summaryMap );
            }
//        }
        return isLineMatched;
    }
}
