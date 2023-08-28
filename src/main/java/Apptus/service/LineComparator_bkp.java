package Apptus.service;

import Apptus.util.ResponseDTO;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class LineComparator_bkp {
    public ResponseDTO WordDiffernces(List<XWPFParagraph> page1_paragraphsList,
                                      List<XWPFParagraph> page2_paragraphsList,
                                      Map<Integer,String> parasWithNumber_map,
                                      XWPFDocument diffDoc,
                                      Map summaryMap) {

        ResponseDTO responseDTO=new ResponseDTO();
        try {
            int deleteCount = 0;
            int insertCount = 0;
            int numberChange = 0;

            List<String> insertedTexts = new ArrayList<>();
            List<String> deletedTexts = new ArrayList<>();

            int size1 = page1_paragraphsList.size();
            int size2 = page2_paragraphsList.size();

            int size = size1>size2?size2:size1;

            for (int i = 0; i < size; i++) {
                XWPFParagraph paragraph1 = page1_paragraphsList.get(i);
                XWPFParagraph paragraph2 = page2_paragraphsList.get(i);
                System.out.println(paragraph1.getText());
                XWPFParagraph diffParagraph = diffDoc.createParagraph();

                // Compare the words of the paragraphs
                List<String> words_1 = splitIntoWords(paragraph1.getText());
                ArrayList<String> array1List = new ArrayList<>(words_1);
                List<String> words_2 = splitIntoWords(paragraph2.getText());
                ArrayList<String> array2List = new ArrayList<>(words_2);

                if(paragraph1.getText().isEmpty() || paragraph2.getText().isEmpty()) {
                    if (!paragraph1.getText().isEmpty()) {
                        XWPFRun diffRun = diffParagraph.createRun();
                        String deletedText = paragraph1.getText();
                        diffRun.setColor("FF0000");
                        diffRun.setText(deletedText);
                        deletedTexts.add(deletedText);
                        deleteCount = deleteCount + 1;
                    } else {
                        XWPFRun diffRun = diffParagraph.createRun();
                        String insertedText = paragraph2.getText();
                        diffRun.setColor("FF0000");
                        diffRun.setText(insertedText);
                        insertedTexts.add(insertedText);
                        insertCount = insertCount + 1;
                    }

                }else {
                    Map<String, String> finalMap = compareDocs(array1List, array2List);
                    finalMap.entrySet().stream().forEach(x -> System.out.println(x.getKey() + "----" + x.getValue()));

                    // Map<String, String> finalSorted = new TreeMap<>(finalMap);
                    Map<String, String> finalSorted = finalMap.entrySet()
                            .stream()
                            .sorted(byNumberComparator).collect(Collectors.toMap(x-> x.getKey(),x->x.getValue()));
                    for (Map.Entry<String, String> entry : finalSorted.entrySet()) {
                        String key = entry.getKey();
                        String[] keyval = key.split("-");
                        //String val = entry.getValue();
                        String[] valArray = entry.getValue().split("-");
                        String word = valArray[0];
                        String color = valArray[1];

                        if (color.equalsIgnoreCase("red")) {
                            deleteCount++;
                            // deletedTexts.add(keyval[1]);
                            deletedTexts.add(word);
                            XWPFRun diffRun = diffParagraph.createRun();
                            diffRun.setText(word + " ");
                            diffRun.setColor("FF0000");
                        } else if (color.equalsIgnoreCase("blue")) {
                            insertCount++;
                            insertedTexts.add(word);
                            XWPFRun diffRun = diffParagraph.createRun();
                            diffRun.setText(word + " ");
                            diffRun.setColor("0000FF");

                        } else if (color.equalsIgnoreCase("black")) {
                            XWPFRun diffRun = diffParagraph.createRun();
                            diffRun.setText(word + " ");
                            diffRun.setColor("000000");
                        }
                        System.out.println("Key = " + entry.getKey()
                                + ", Value = "
                                + entry.getValue());

                    }
                }
            }
            // if page 1 size is greater than page 2
            if  (size1 > size2) {
                for (int i = size2; i < size1; i++) {
                    XWPFParagraph diffParagraph = diffDoc.createParagraph();
                    XWPFRun diffRun = diffParagraph.createRun();
                    XWPFParagraph paragraph1 = page1_paragraphsList.get(i);
                    if(paragraph1.getText().isEmpty()){
                        diffRun.setText(paragraph1.getText());
                        deletedTexts.add(paragraph1.getText());
                    }else {
                        List<String> words_1 = splitIntoWords(paragraph1.getText());
                        String deletedText = String.join(" ", words_1);
                        diffRun.setText(deletedText);
                        deletedTexts.add(deletedText);
                    }
                    diffRun.setColor("FF0000");
                    deleteCount = deleteCount + 1;
                }

            } else {   // if page 2 size is greater than page 2
                for (int i = size1; i < size2; i++) {
                    XWPFParagraph diffParagraph = diffDoc.createParagraph();
                    XWPFRun diffRun = diffParagraph.createRun();
                    XWPFParagraph paragraph2 = page2_paragraphsList.get(i);
                    if(paragraph2.getText().isEmpty()){
                        diffRun.setText(paragraph2.getText());
                        insertedTexts.add(paragraph2.getText());
                    }else{
                        List<String> words_1 = splitIntoWords(paragraph2.getText());
                        String insertedText = String.join(" ", words_1);
                        diffRun.setText(insertedText);
                        insertedTexts.add(insertedText);
                    }
                    diffRun.setColor("0000FF");
                    insertCount = insertCount + 1;
                }
            }
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
            String url="http://127.0.0.1:8090/uploads/result.docx";
            String filePath = System.getProperty("user.dir")+ "\\uploads\\result.docx";
            responseDTO.setDocxUrl(url);
            FileOutputStream outputStream = new FileOutputStream(filePath);
            diffDoc.write(outputStream);
            // Close the document streams

            System.out.println("Document comparison completed. Differences saved to document_diff.docx");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDTO;
    }



    Comparator<Map.Entry<String, String>> byNumberComparator = new Comparator<Map.Entry<String, String>>() {
        @Override
        public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
               /* String[] keysArrays1 = o1.getKey().split("-");
                String order1 = keysArrays1[0];

                String[] keysArrays2 = o1.getKey().split("-");
                String order2 = keysArrays2[0];*/

            return Integer.parseInt(o1.getKey())-Integer.parseInt(o2.getKey());
        }
    };
    private List<String> splitIntoWords(String paragraphText) {
        return Arrays.asList(paragraphText.split("\\s+"));
    }

    private Map<String, String> compareDocs
            (ArrayList<String> array1List, ArrayList<String> array2List) {

        List<String> tempList = new ArrayList<>();
        Map<String, String> finalMap = new HashMap<>();
        List<String> indexesTobeRemoved = new ArrayList<>();
        List<Integer> occupiedIndexesList = new ArrayList<>();
        List<String> bckupArray2List = new ArrayList<>(array2List);

        // part 1
        for (int i = 0; i < array1List.size(); i++) {
            boolean isMatched = false;

            for (int j = 0; j < array2List.size(); j++) {
                String str1 = array1List.get(i);
                String str2 = array2List.get(j);
                if (str1.equalsIgnoreCase("can:")){
                    System.out.println("can");
                }
                //if (array1List.get(i).equalsIgnoreCase(array2List.get(j))) {
                if(str1.isEmpty() || str2.isEmpty()) {

                }else{
                    if (str1.equalsIgnoreCase(str2)) {
                        isMatched = true;
                        indexesTobeRemoved.add(array1List.get(i));

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
                        finalMap.put(String.valueOf(newIndex), array1List.get(i)+ "-black");
                        break;
                    }
                }
            }
            if (!isMatched) {
                tempList.add(array1List.get(i));
                int newIndex = getIndex(array1List, array2List, i);
                occupiedIndexesList.add(newIndex);
               // finalMap.put(String.valueOf(newIndex) + "-" + array1List.get(i), "-red");
                finalMap.put(String.valueOf(newIndex), array1List.get(i)+ "-red");
            }
        }

        // part 2
        for (String removeIndex : indexesTobeRemoved) {
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
                int newIndex = getNewIndexForArray2Values(occupiedIndexesList, currentIndex);
                occupiedIndexesList.add(newIndex);
                //finalMap.put(String.valueOf(newIndex) + "-" + array2Value, "-blue");
                finalMap.put(String.valueOf(newIndex), array2Value+"-blue");

            } else {
                for (Map.Entry entry : duplicateMap.entrySet()) {
                    if ((Long) entry.getValue() > 1) {

                        tempList.add(array2Value);
                        int currentIndex = getCurrentIndexForArray2DuplicateValues(bckupArray2List, array2Value, duplicateValueIndex);
                        int newIndex = getNewIndexForArray2Values(occupiedIndexesList, currentIndex);
                        occupiedIndexesList.add(newIndex);
                        //finalMap.put(String.valueOf(newIndex) + "-" + array2Value, "-blue");
                        finalMap.put(String.valueOf(newIndex), array2Value + "-blue");

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
            if(array2List.size()<= currentIndex){
               System.out.println("currentIndex" + currentIndex + j);
            }else {
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

    public static int getNewIndexForArray2Values(List<Integer> newIndexesList, int currentIndex) {

        if (newIndexesList.contains(currentIndex)) {
            currentIndex = currentIndex + 1;
        }
        return currentIndex;
    }
}
