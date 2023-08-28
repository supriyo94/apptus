package Apptus.service;

import Apptus.util.ResponseDTO;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class WordComparatorNew {

    @Autowired
    Line_WordComparator line_WordComparator;

    @Autowired
    LineComparatorNew lineComparatorNew;

    public ResponseDTO WordDifferences(CopyOnWriteArrayList<XWPFParagraph> page1_parasList,
                                       CopyOnWriteArrayList<XWPFParagraph> page2_parasList,
                                       Map<Integer, String> parasWithNumber_map,
                                       ArrayList<String> matchedParasList,
                                       ArrayList<XWPFParagraph> pg1_line_matched_List,
                                       ArrayList<XWPFParagraph> pg2_line_matched_List,
                                       List<XWPFParagraph> page2_paragraphsList, XWPFDocument doc1,
                                       XWPFDocument doc2) {

        ResponseDTO responseDTO = new ResponseDTO();
        XWPFDocument diffDoc = new XWPFDocument();
        Map summaryMap = null;
        try {
            summaryMap = new HashMap();
            int deleteCount = 0;
            int insertCount = 0;
            int numberChange = 0;

            List<String> insertedTexts = new ArrayList<>();
            List<String> deletedTexts = new ArrayList<>();

            //add image
            new AddWatermark().addImage(doc1, doc2, diffDoc);

            int size1 = page1_parasList.size();
            int page2Index = 0;
            for (int pgIndex = 0; pgIndex < size1; pgIndex++) {

                XWPFParagraph pg1_para = page1_parasList.get(pgIndex);
                String pg1_para_String = pg1_para.getText();
                System.out.println(pg1_para_String);

                // Step 1 : paragraph matching
                if (matchedParasList.contains(pg1_para_String)) {
                    XWPFParagraph diffParagraph = diffDoc.createParagraph();
                    diffParagraph.setAlignment(pg1_para.getAlignment());
                    if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("decimal")) {
                        diffParagraph.setNumID(pg1_para.getNumID());
                    }
                    if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("bullet")) {
                        diffParagraph.setNumID(getBulletPoint(diffDoc));
                    }
                    XWPFRun diffRun = diffParagraph.createRun();
                    diffRun.setColor("000000");

                    for (XWPFRun run : pg1_para.getRuns()) {
                        String text = run.getText(0);
                        boolean isBold = run.isBold();
                        boolean isItalic = run.isItalic();
                        diffRun.setItalic(isItalic);
                        diffRun.setBold(isBold);
                        diffRun.setFontFamily("Calibri Light (Headings)");

                    }
                    diffRun.setText(pg1_para_String);
                    page2Index++;
                    continue;
                }

                // Step 2 : Line matching
                if (pg1_line_matched_List.contains(pg1_para)) {

                    Set<XWPFParagraph> matchedPg1_Para_Set = new HashSet<>();
                    Set<XWPFParagraph> matchedPg2_Para_Set = new HashSet<>();
                    boolean isLineMatched = lineComparatorNew.lineMatching(pg1_para,
                            page2_parasList,
                            parasWithNumber_map,
                            diffDoc,
                            matchedPg1_Para_Set,
                            matchedPg2_Para_Set,
                            pg2_line_matched_List,
                            summaryMap);
                    deleteCount = deleteCount + (int) summaryMap.get("deleteCount");
                    insertCount = insertCount + (int) summaryMap.get("insertCount");
                    for (String insertedText : (List<String>) summaryMap.get("insertedTexts")) {
                        insertedTexts.add(insertedText);
                    }
                    for (String deletedText : (List<String>) summaryMap.get("deletedTexts")) {
                        deletedTexts.add(deletedText);
                    }
                    page2Index++;
                    continue;
                }

                // Step 3 : Unmatched paras matching
                if (page2_paragraphsList.size() > 0) {
                    XWPFParagraph diffParagraph = diffDoc.createParagraph();
                    if (page2Index >= page2_paragraphsList.size()) {
                        XWPFRun diffRun2 = diffParagraph.createRun();
                        diffParagraph.setAlignment(pg1_para.getAlignment());
                        //diffParagraph.setStyle(pg1_para.getStyle());
                        if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("decimal")) {
                            diffParagraph.setNumID(pg1_para.getNumID());
                        }
                        if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("bullet")) {
                            diffParagraph.setNumID(getBulletPoint(diffDoc));
                        }
                        diffRun2.setColor("FF0000");
                        for (XWPFRun run : pg1_para.getRuns()) {
                            String text = run.getText(0);
                            boolean isBold = run.isBold();
                            boolean isItalic = run.isItalic();
                            diffRun2.setItalic(isItalic);
                            diffRun2.setBold(isBold);
                            diffRun2.setFontFamily("Calibri Light (Headings)");

                        }
                        
                        diffRun2.setText(pg1_para_String);
                        diffRun2.setStrikeThrough(true);
                        deleteCount++;
                        deletedTexts.add(pg1_para_String);
                        continue;
                    } else {
                        XWPFParagraph pg2_para = page2_paragraphsList.get(page2Index);
                        String pg2_para_String = pg2_para.getText();
                        if (matchedParasList.contains(pg2_para_String)
                                || pg2_line_matched_List.contains(pg2_para_String)) {
                            diffParagraph.setAlignment(pg1_para.getAlignment());
                            //diffParagraph.setStyle(pg1_para.getStyle());
                            if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("decimal")) {
                                diffParagraph.setNumID(pg1_para.getNumID());
                            }
                            if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("bullet")) {
                                diffParagraph.setNumID(getBulletPoint(diffDoc));
                            }
                            XWPFRun diffRun2 = diffParagraph.createRun();
                            diffRun2.setColor("FF0000");
                            diffRun2.setStrikeThrough(true);
                            for (XWPFRun run : pg1_para.getRuns()) {
                                String text = run.getText(0);
                                boolean isBold = run.isBold();
                                boolean isItalic = run.isItalic();
                                diffRun2.setItalic(isItalic);
                                diffRun2.setBold(isBold);
                                diffRun2.setFontFamily("Calibri Light (Headings)");

                            }
                            diffRun2.setText(pg1_para_String);
                            deleteCount++;
                            deletedTexts.add(pg1_para_String);
                            //page2_parasList.remove(pg2_para);
                            continue;
                        }
                        page2Index++;
                        diffParagraph.setAlignment(pg1_para.getAlignment());
                        //diffParagraph.setStyle(pg1_para.getStyle());

                        if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("decimal")) {
                            diffParagraph.setNumID(pg1_para.getNumID());
                        }
                        if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("bullet")) {
                            diffParagraph.setNumID(getBulletPoint(diffDoc));
                        }

                        // Compare the words of the paragraphs
                        List<String> words_1 = splitIntoWords(pg1_para_String);
                        ArrayList<String> array1List = new ArrayList<>(words_1);
                        List<String> words_2 = splitIntoWords(pg2_para_String);
                        ArrayList<String> array2List = new ArrayList<>(words_2);

                        Map<String, String> finalMap = compareDocs(array1List, array2List);
                        if (finalMap == null) {
                            //XWPFParagraph diffParagraph1 = diffDoc.createParagraph();
                            if (pg2_para.getNumFmt() != null && pg2_para.getNumFmt().equalsIgnoreCase("decimal")) {
                                diffParagraph.setNumID(pg2_para.getNumID());
                            }
                            if (pg2_para.getNumFmt() != null && pg2_para.getNumFmt().equalsIgnoreCase("bullet")) {
                                diffParagraph.setNumID(getBulletPoint(diffDoc));
                            }
                            XWPFRun diffRun = diffParagraph.createRun();
                            diffRun.setColor("0000FF");
                            for (XWPFRun run : pg1_para.getRuns()) {
                                String text = run.getText(0);
                                boolean isBold = run.isBold();
                                boolean isItalic = run.isItalic();
                                diffRun.setItalic(isItalic);
                                diffRun.setBold(isBold);
                                diffRun.setFontFamily("Calibri Light (Headings)");

                            }
                            diffRun.setText(pg2_para_String);
                            insertCount++;
                            insertedTexts.add(pg2_para_String);

                            XWPFParagraph diffParagraph2 = diffDoc.createParagraph();
                            if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("decimal")) {
                                diffParagraph2.setNumID(pg1_para.getNumID());
                            }
                            if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("bullet")) {
                                diffParagraph2.setNumID(getBulletPoint(diffDoc));
                            }
                            XWPFRun diffRun2 = diffParagraph2.createRun();
                            diffRun2.setColor("FF0000");
                            diffRun2.setStrikeThrough(true);
                            for (XWPFRun run : pg1_para.getRuns()) {
                                String text = run.getText(0);
                                boolean isBold = run.isBold();
                                boolean isItalic = run.isItalic();
                                diffRun2.setItalic(isItalic);
                                diffRun2.setBold(isBold);
                                diffRun2.setFontFamily("Calibri Light (Headings)");

                            }
                            diffRun2.setText(pg1_para_String);
                            deleteCount++;
                            deletedTexts.add(pg1_para_String);
                            //page2_parasList.remove(pg2_para);
                            continue;
                        }
                        LinkedHashMap<String, String> finalSorted = finalMap.entrySet()
                                .stream()
                                .sorted(Comparator.comparingInt(o -> Integer.parseInt(o.getKey())))
                                .collect(Collectors.toMap(x -> x.getKey(), y -> y.getValue(), (o, o2) -> o, LinkedHashMap::new));
                        for (Map.Entry<String, String> entry : finalSorted.entrySet()) {
                            String[] valArray = entry.getValue().split("-");
                            String word = valArray[0];
                            String color = valArray[1];

                            if (color.equalsIgnoreCase("red")) {
                                deleteCount++;
                                deletedTexts.add(word);
                                XWPFRun diffRun = diffParagraph.createRun();
                                for (XWPFRun run : pg1_para.getRuns()) {
                                    String text = run.getText(0);
                                    boolean isBold = run.isBold();
                                    boolean isItalic = run.isItalic();
                                    diffRun.setItalic(isItalic);
                                    diffRun.setBold(isBold);
                                    diffRun.setFontFamily("Calibri Light (Headings)");

                                }
                                diffRun.setText(word + " ");
                                diffRun.setColor("FF0000");
                                diffRun.setStrikeThrough(true);
                            } else if (color.equalsIgnoreCase("blue")) {
                                insertCount++;
                                insertedTexts.add(word);
                                XWPFRun diffRun = diffParagraph.createRun();
                                for (XWPFRun run : pg1_para.getRuns()) {
                                    String text = run.getText(0);
                                    boolean isBold = run.isBold();
                                    boolean isItalic = run.isItalic();
                                    diffRun.setItalic(isItalic);
                                    diffRun.setBold(isBold);
                                    diffRun.setFontFamily("Calibri Light (Headings)");

                                }
                                diffRun.setText(word + " ");
                                diffRun.setColor("0000FF");

                            } else if (color.equalsIgnoreCase("black")) {
                                XWPFRun diffRun = diffParagraph.createRun();
                                for (XWPFRun run : pg1_para.getRuns()) {
                                    String text = run.getText(0);
                                    boolean isBold = run.isBold();
                                    boolean isItalic = run.isItalic();
                                    diffRun.setItalic(isItalic);
                                    diffRun.setBold(isBold);
                                    diffRun.setFontFamily("Calibri Light (Headings)");

                                }
                                diffRun.setText(word + " ");
                                diffRun.setColor("000000");
                            }
                        }
                    }
                } else {
                    XWPFParagraph diffParagraph = diffDoc.createParagraph();
                    diffParagraph.setAlignment(pg1_para.getAlignment());
                    //diffParagraph.setStyle(pg1_para.getStyle());
                    if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("decimal")) {
                        diffParagraph.setNumID(pg1_para.getNumID());
                    }
                    if (pg1_para.getNumFmt() != null && pg1_para.getNumFmt().equalsIgnoreCase("bullet")) {
                        diffParagraph.setNumID(getBulletPoint(diffDoc));
                    }
                    XWPFRun diffRun = diffParagraph.createRun();
                    diffRun.setColor("FF0000");
                    diffRun.setStrikeThrough(true);
                    for (XWPFRun run : pg1_para.getRuns()) {
                        String text = run.getText(0);
                        boolean isBold = run.isBold();
                        boolean isItalic = run.isItalic();
                        diffRun.setItalic(isItalic);
                        diffRun.setBold(isBold);
                        diffRun.setFontFamily("Calibri Light (Headings)");

                    }
                    diffRun.setText(pg1_para_String);
                    deleteCount++;
                    deletedTexts.add(pg1_para_String);
                }
            }
            // compare the tables
            TableComparator tableComparator = new TableComparator();
            Map<String, List<String>> tableUpdates = tableComparator.compareTable(doc1, doc2, diffDoc);
            List<String> tableDeletions = new ArrayList<>();
            List<String> tableInsertions = new ArrayList<>();
            tableDeletions = tableUpdates.get(TableComparator.Deletions);
            tableInsertions = tableUpdates.get(TableComparator.Insertions);

            int insertTableCount = tableInsertions.size();
            int deleteTableCount = tableDeletions.size();
          //  int totalTableChanges = insertTableCount + deleteTableCount;

           //  numberChange = deleteCount + insertCount + totalTableChanges;
            deleteCount = deleteCount + deleteTableCount;
            insertCount = insertCount + insertTableCount;

            numberChange = deleteCount + insertCount;
            summaryMap.put("deleteCount", deleteCount);
            summaryMap.put("insertCount", insertCount);
            summaryMap.put("numberOfChange", numberChange);
            summaryMap.put("insertedTexts", insertedTexts);
            summaryMap.put("deletedTexts", deletedTexts);

            responseDTO.setTotalChangeCount(numberChange);
            responseDTO.setInsertCount(insertCount);
            responseDTO.setDeleteCount(deleteCount);
            responseDTO.setInsertedText(insertedTexts);
            responseDTO.setDeletedText(deletedTexts);
            String url = "http://127.0.0.1:8090/uploads/result.docx";
            String filePath = System.getProperty("user.dir") + "\\uploads\\result.docx";
            responseDTO.setDocxUrl(url);

            FileOutputStream outputStream = new FileOutputStream(filePath);
            diffDoc.write(outputStream);

            System.out.println("Document comparison completed. Differences saved to document_diff.docx");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDTO;
    }

    private List<String> splitIntoWords(String paragraphText) {
        return Arrays.asList(paragraphText.split("\\s+"));
    }

    private Map<String, String> compareDocs
            (ArrayList<String> array1List, ArrayList<String> array2List) {

        List<String> tempList = new ArrayList<>();
        Map<String, String> finalMap = new HashMap<>();
        List<String> finalList = new ArrayList<>();
        List<String> valuesTobeRemovedFromPg2 = new ArrayList<>();
        List<Integer> occupiedIndexesList = new ArrayList<>();
        List<String> bckupArray2List = new ArrayList<>(array2List);

        boolean isParaOrLineMatched = false;
        // part 1
        for (int i = 0; i < array1List.size(); i++) {
            boolean isMatched = false;

            for (int j = 0; j < array2List.size(); j++) {
                String str1 = array1List.get(i);
                String str2 = array2List.get(j);
                if (str1.isEmpty() || str2.isEmpty()) {

                } else {
                    if (str1.equalsIgnoreCase(str2)) {
                        isMatched = true;
                        isParaOrLineMatched = true;
                        valuesTobeRemovedFromPg2.add(array1List.get(i));

                        tempList.add(array1List.get(i));
                        int newIndex = 0;
                        if (i == j) {
                            newIndex = i;
                        } else if (i < j) {
                            newIndex = j;
                        } else {
                            newIndex = i;
                        }
                        occupiedIndexesList.add(newIndex);
                        //finalMap.put(String.valueOf(newIndex) + "-" + array1List.get(i), "-black");
                        finalMap.put(String.valueOf(newIndex), array1List.get(i) + "-black");
                        finalList.add(String.valueOf(newIndex) + array1List.get(i) + "-black");
                        break;
                    }
                }
            }
            if (!isMatched) {
                tempList.add(array1List.get(i));
                int newIndex = getIndex(array1List, array2List, i);
                occupiedIndexesList.add(newIndex);
                // finalMap.put(String.valueOf(newIndex) + "-" + array1List.get(i), "-red");
                finalMap.put(String.valueOf(newIndex), array1List.get(i) + "-red");
                finalList.add(String.valueOf(newIndex) + array1List.get(i) + "-red");
            }
        }

        if (!isParaOrLineMatched) {
            return null;
        }
        // part 2
        for (String removeIndex : valuesTobeRemovedFromPg2) {
            array2List.remove(removeIndex);

        }

        // part 3
        Map<String, Long> duplicateMap = array2List.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // part 4
        int duplicateValueIndex = 2;
        for (String array2Value : array2List) {
            if (!tempList.contains(array2Value)) {
                tempList.add(array2Value);
                int currentIndex = bckupArray2List.indexOf(array2Value);
                int newIndex = getNewIndexForArray2Values(occupiedIndexesList, currentIndex, finalMap);
                occupiedIndexesList.add(newIndex);
                //finalMap.put(String.valueOf(newIndex) + "-" + array2Value, "-blue");
                finalMap.put(String.valueOf(newIndex), array2Value + "-blue");
                finalList.add(String.valueOf(newIndex) + array2Value + "-blue");

            } else {
                for (Map.Entry entry : duplicateMap.entrySet()) {
                    if ((Long) entry.getValue() > 1) {

                        tempList.add(array2Value);
                        int currentIndex = getCurrentIndexForArray2DuplicateValues(bckupArray2List, array2Value, duplicateValueIndex);
                        int newIndex = getNewIndexForArray2Values(occupiedIndexesList, currentIndex, finalMap);
                        occupiedIndexesList.add(newIndex);
                        //finalMap.put(String.valueOf(newIndex) + "-" + array2Value, "-blue");
                        finalMap.put(String.valueOf(newIndex), array2Value + "-blue");
                        finalList.add(String.valueOf(newIndex) + array2Value + "-blue");
                        duplicateValueIndex = duplicateValueIndex + 1;
                    }
                }
            }
        }
        return finalMap;
    }

    public static int getIndex(List<String> array1List, ArrayList<String> array2List, int currentIndex) {

        int tempIndex = 0;
        String unmatechedValue = array1List.get(currentIndex);
        for (int j = 0; j < currentIndex; j++) {
            if (array2List.size() <= currentIndex) {
            } else {
                if (unmatechedValue.equalsIgnoreCase(array2List.get(j))) {
                    tempIndex = tempIndex + 1;
                }
            }
        }

        return currentIndex + tempIndex;
    }

    public static int getCurrentIndexForArray2DuplicateValues(List<String> bckupArray2List, String
            duplicateString, int currentIndex) {
        String item = duplicateString; // for item 1
        int itemNo = currentIndex; // get the second value
        Optional<Integer> index = IntStream.range(0, bckupArray2List.size()).boxed()
                .collect(Collectors.groupingBy(bckupArray2List::get))
                .getOrDefault(item, List.of(0))
                .stream().skip(itemNo - 1).findFirst();

        return index.orElseGet(() -> -1);
    }

    public static int getNewIndexForArray2Values(List<Integer> newIndexesList,
                                                 int currentIndex,
                                                 Map<String, String> finalMap) {

        if (newIndexesList.contains(currentIndex)) {
            updateFinalMap(finalMap, currentIndex);
            currentIndex = currentIndex + 1;
        }
        return currentIndex;
    }

    public static void updateFinalMap(Map<String, String> finalMap, int currentIndex) {

        Map<String, String> finalMap2 = finalMap.entrySet().stream()
                .filter(x -> Integer.parseInt(x.getKey()) <= currentIndex)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("finalMap2=====");
        // finalMap2.entrySet().stream().forEach(x->System.out.println(x.getKey() + "="+ x.getValue()));

        Map<String, String> finalMap3 = finalMap.entrySet().stream()
                .filter(x -> Integer.parseInt(x.getKey()) > currentIndex)
                .collect(Collectors.toMap(x -> String.valueOf(Integer.parseInt(x.getKey()) + 1), x -> x.getValue()));
        System.out.println("finalMap3=====");
        // finalMap3.entrySet().stream().forEach(x->System.out.println(Integer.parseInt(x.getKey()) + "="+ x.getValue()));

        finalMap.clear();
        finalMap.putAll(finalMap2);
        finalMap.putAll(finalMap3);
        System.out.println("finalMap=====");
        finalMap.entrySet().stream().forEach(x -> System.out.println(x.getKey() + "=" + x.getValue()));

    }

    public BigInteger getBulletPoint(XWPFDocument diffDoc) {
        CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
        cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));
        CTLvl cTLvl = cTAbstractNum.addNewLvl();
        cTLvl.setIlvl(BigInteger.valueOf(0));
        cTLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
        cTLvl.addNewLvlText().setVal("•");
        XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);

        XWPFNumbering numbering = diffDoc.getNumbering();
        if (numbering == null) {
            numbering = diffDoc.createNumbering();
        }
        BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
        BigInteger numID = numbering.addNum(abstractNumID);
        XWPFNum num = numbering.getNum(numID);
        CTNumLvl lvloverride = num.getCTNum().addNewLvlOverride();
        lvloverride.setIlvl(BigInteger.ZERO);
        return numID;
    }
}
