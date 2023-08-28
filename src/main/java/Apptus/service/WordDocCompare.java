package Apptus.service;


import Apptus.util.ResponseDTO;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class WordDocCompare {

    public void compareWithApachePOI(String path1,String path2) {
        //ResponseDTO responseDTO=new ResponseDTO();
        try {
            // Load the first Word document
            XWPFDocument doc1 = new XWPFDocument(new FileInputStream("version1.docx"));

            // Load the second Word document
            XWPFDocument doc2 = new XWPFDocument(new FileInputStream("version2.docx"));

            // Create a new document for the differences
            XWPFDocument diffDoc = new XWPFDocument();

            // Compare the paragraphs of the two documents
            List<XWPFParagraph> paragraphs1 = doc1.getParagraphs();
            List<XWPFParagraph> paragraphs2 = doc2.getParagraphs();

            //XWPFParagraph diffParagraph = diffDoc.createParagraph();
            /*if(paragraphs1.size() == 0){
                // print pragraph 2
                XWPFRun diffRun = diffParagraph.createRun();
                diffRun.setText(String.join("", paragraphs2.toString()));
                diffRun.setColor("FF0000"); // RED color

            }else if(paragraphs2.size() == 0){
                // print paragraph 1
                // else
            }*/

            for (int i = 0; i < paragraphs1.size(); i++) {

                XWPFParagraph paragraph1 = paragraphs1.get(i);
                XWPFParagraph paragraph2 = paragraphs2.get(i);
                System.out.println(paragraph1.getText());
                // System.out.println("-------------------------------");
                //System.out.println(paragraph2.getText());
                // Create a new paragraph for the differences
                XWPFParagraph diffParagraph = diffDoc.createParagraph();

                // Compare the words of the paragraphs
                List<String> words_1 = splitIntoWords(paragraph1.getText());
                ArrayList<String> array1List = new ArrayList<>(words_1);
                List<String> words_2 = splitIntoWords(paragraph2.getText());
                ArrayList<String> array2List = new ArrayList<>(words_2);

                Map<String, String> finalMap = array1LesserThanAray2(array1List, array2List);

                finalMap.entrySet().stream().forEach(x -> System.out.println(x.getKey() + "----" + x.getValue()));
                Map<String, String> finalSorted
                        = new TreeMap<>(finalMap);

                // Display the TreeMap which is naturally sorted
                System.out.println("Display the TreeMap which is naturally sorted");
                //XWPFRun diffRun = diffParagraph.createRun();
                for (Map.Entry<String, String> entry : finalSorted.entrySet()) {
                    if (array2List.isEmpty()) {
                        XWPFRun diffRun = diffParagraph.createRun();
                        diffRun.setText(String.join("", array1List));
                        diffRun.setColor("FF0000"); // RED color
                    } else if (array1List.isEmpty()) {
                        XWPFRun diffRun = diffParagraph.createRun();
                        diffRun.setText(String.join("", array2List));
                        diffRun.setColor("0000FF"); // Blue color
                    } else {
                        String key = entry.getKey();
                        String[] keyval = key.split("-");
                        String val = entry.getValue();
                        if (val.equalsIgnoreCase("-red")) {
                            XWPFRun diffRun = diffParagraph.createRun();
                            diffRun.setText(keyval[1] + " ");
                            diffRun.setColor("FF0000");
                        } else if (val.equalsIgnoreCase("-blue")) {
                            XWPFRun diffRun = diffParagraph.createRun();
                            diffRun.setText(keyval[1] + " ");
                            diffRun.setColor("0000FF");

                        } else if (val.equalsIgnoreCase("-black")) {
                            XWPFRun diffRun = diffParagraph.createRun();
                            diffRun.setText(keyval[1] + " ");
                            diffRun.setColor("000000");
                        }
                        // System.out.println(diffRun.getText(0));System.out.println(diffRun.getText(1));System.out.println(diffRun.getText(2));
                        //System.out.println(diffRun.getText(3));
                        System.out.println("*******************************************");
                        // System.out.println(diffRun.toString());
                        System.out.println("Key = " + entry.getKey()
                                + ", Value = "
                                + entry.getValue());

                    }

                }
            }
            FileOutputStream outputStream = new FileOutputStream("document_diff.docx");
            diffDoc.write(outputStream);

            // Close the document streams
            doc1.close();
            doc2.close();
            diffDoc.close();

            System.out.println("Document comparison completed. Differences saved to document_diff.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
       // return responseDTO;

    }


    private static List<String> splitIntoWords(String paragraphText) {
        return Arrays.asList(paragraphText.split("\\s+"));
    }

    public static Map<String, String> array1LesserThanAray2
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
                if (array1List.get(i).equalsIgnoreCase(array2List.get(j))) {
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
                    //finalMap.put(newIndex, array1List.get(i) + "-black");

                    finalMap.put(String.valueOf(newIndex) + "-" + array1List.get(i), "-black");
                    break;
                }
            }
            if (!isMatched) {
                tempList.add(array1List.get(i));
                int newIndex = getIndex(array1List, array2List, i);
                occupiedIndexesList.add(newIndex);
                //finalMap.put(Integer.valueOf(newIndex+array1List.get(i)), "-red");
                finalMap.put(String.valueOf(newIndex) + "-" + array1List.get(i), "-red");
            }
        }

        // part 2
        int firstRemoveIndex = 1;
        for (String removeIndex : indexesTobeRemoved) {
            array2List.remove(removeIndex);
           /* if (firstRemoveIndex == 1) {
                array2List.remove(removeIndex.intValue());
                firstRemoveIndex = firstRemoveIndex + 1;
            } else {

                removeIndex = removeIndex - 1;
                array2List.remove(removeIndex.intValue());
            }*/
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
                //finalMap.put(newIndex, array2Value + "-blue");
                finalMap.put(String.valueOf(newIndex) + "-" + array2Value, "-blue");

            } else {
                for (Map.Entry entry : duplicateMap.entrySet()) {
                    if ((Long) entry.getValue() > 1) {

                        tempList.add(array2Value);
                        int currentIndex = getCurrentIndexForArray2DuplicateValues(bckupArray2List, array2Value, duplicateValueIndex);
                        int newIndex = getNewIndexForArray2Values(occupiedIndexesList, currentIndex);
                        occupiedIndexesList.add(newIndex);
                        //finalMap.put(newIndex, array2Value + "-blue");
                        finalMap.put(String.valueOf(newIndex) + "-" + array2Value, "-blue");

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
        // for(int i=0; i< currentIndex; i++){
        for (int j = 0; j < currentIndex; j++) {
            // if (!array2List.get(i).equalsIgnoreCase(array1List.get(j))){
            if (unmatechedValue.equalsIgnoreCase(array2List.get(j))) {
                tempIndex = tempIndex + 1;
            }
        }
        //}
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