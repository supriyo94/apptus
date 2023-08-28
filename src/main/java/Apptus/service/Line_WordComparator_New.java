package Apptus.service;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class Line_WordComparator_New {
    public Map WordDifferences_inParagraph(List<String> page1_paragraphsList,
                                       List<String> page2_paragraphsList,
                                       XWPFDocument diffDoc,
                                       XWPFParagraph pg1_para,
                                       Map summaryMap ) {

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
                String paragraph1 = page1_paragraphsList.get(i);
                String paragraph2 = page2_paragraphsList.get(i);
                if(paragraph2.contains("Changes in the")){
                    System.out.println("Changes in the");
                }
                XWPFParagraph diffParagraph = diffDoc.createParagraph();
                diffParagraph.setAlignment(pg1_para.getAlignment());
                //diffParagraph.setStyle(pg1_para.getStyle());
                if ( pg1_para.getNumFmt()!=null && pg1_para.getNumFmt().equalsIgnoreCase("decimal")){
                    diffParagraph.setNumID(pg1_para.getNumID());
                }
                if ( pg1_para.getNumFmt()!=null && pg1_para.getNumFmt().equalsIgnoreCase("bullet")){
                   diffParagraph.setNumID(getBulletPoint(diffDoc));
                }

                // Compare the words of the paragraphs
                List<String> words_1 = splitIntoWords(paragraph1);
                ArrayList<String> array1List = new ArrayList<>(words_1);
                List<String> words_2 = splitIntoWords(paragraph2);
                ArrayList<String> array2List = new ArrayList<>(words_2);

                Map<String, String> finalMap = compareDocs(array1List, array2List);
                LinkedHashMap<String, String> finalSorted = finalMap.entrySet()
                        .stream()
                        .sorted(Comparator.comparingInt(o -> Integer.parseInt(o.getKey())))
                        .collect(Collectors.toMap(x-> x.getKey(),y->y.getValue(), (o, o2) -> o, LinkedHashMap :: new));
                for (Map.Entry<String, String> entry : finalSorted.entrySet()) {
                    String key = entry.getKey();
                    String[] keyval = key.split("-");
                    //String val = entry.getValue();
                    String[] valArray = entry.getValue().split("-");
                    String word = valArray[0];
                    String color = valArray[1];

                    if (color.equalsIgnoreCase("red")) {
                        deleteCount++;
                        deletedTexts.add(word);
                        XWPFRun diffRun = diffParagraph.createRun();
                        diffRun.setText(word + " ");
                        diffRun.setColor("FF0000");
                        for (XWPFRun run : pg1_para.getRuns()) {
                            String text = run.getText(0);
                            boolean isBold = run.isBold();
                            boolean isItalic = run.isItalic();
                            diffRun.setItalic(isItalic);
                            diffRun.setBold(isBold);
                            diffRun.setFontFamily("Calibri Light (Headings)");

                        }
                        diffRun.setStrikeThrough(true);
                        //diffRun.setFontSize(pg1_para.getRuns().get(0).getFontSize());
                        int fontSize = pg1_para.getRuns().get(0).getFontSize();
                        /*if (fontSize == -1){
                            fontSize = diffDoc.getStyles().getDefaultRunStyle().getFontSize();
                            diffRun.setFontSize(fontSize);
                        }else{
                            diffRun.setFontSize(fontSize);
                        }*/
                    } else if (color.equalsIgnoreCase("blue")) {
                        insertCount++;
                        insertedTexts.add(word);
                        XWPFRun diffRun = diffParagraph.createRun();
                        diffRun.setText(word + " ");
                        for (XWPFRun run : pg1_para.getRuns()) {
                            String text = run.getText(0);
                            boolean isBold = run.isBold();
                            boolean isItalic = run.isItalic();
                            diffRun.setItalic(isItalic);
                            diffRun.setBold(isBold);
                            diffRun.setFontFamily("Calibri Light (Headings)");

                        }
                        diffRun.setColor("0000FF");
                        //diffRun.setFontSize(pg1_para.getRuns().get(0).getFontSize());
                        int fontSize = pg1_para.getRuns().get(0).getFontSize();
                        /*if (fontSize == -1){
                            fontSize = diffDoc.getStyles().getDefaultRunStyle().getFontSize();
                            diffRun.setFontSize(fontSize);
                        }else{
                            diffRun.setFontSize(fontSize);
                        }*/

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
                       // diffRun.setFontSize(pg1_para.getRuns().get(0).getFontSize());
                       /* int fontSize = pg1_para.getRuns().get(0).getFontSize();
                        if (fontSize == -1){
                            //fontSize = diffDoc.getStyles().getDefaultRunStyle().getFontSize();
                            //diffRun.setFontSize(fontSize);
                        }else{
                            //diffRun.setFontSize(fontSize);
                        }*/
                    }
                }
            }
            // if page 1 size is greater than page 2
            numberChange = deleteCount + insertCount;
            summaryMap.put("deleteCount", deleteCount);
            summaryMap.put("insertCount", insertCount);
            summaryMap.put("numberOfChange", numberChange);
            summaryMap.put("insertedTexts", insertedTexts);
            summaryMap.put("deletedTexts", deletedTexts);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return summaryMap;
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
                int newIndex = getNewIndexForArray2Values(occupiedIndexesList, currentIndex,finalMap);
                occupiedIndexesList.add(newIndex);
                //finalMap.put(String.valueOf(newIndex) + "-" + array2Value, "-blue");
                finalMap.put(String.valueOf(newIndex), array2Value+"-blue");

            } else {
                for (Map.Entry entry : duplicateMap.entrySet()) {
                    if ((Long) entry.getValue() > 1) {

                        tempList.add(array2Value);
                        int currentIndex = getCurrentIndexForArray2DuplicateValues(bckupArray2List, array2Value, duplicateValueIndex);
                        int newIndex = getNewIndexForArray2Values(occupiedIndexesList, currentIndex,finalMap);
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
    public static int getNewIndexForArray2Values(List<Integer> occupiedIndexesList,
                                                 int currentIndex,
                                                 Map<String, String> finalMap) {

        if (occupiedIndexesList.contains(currentIndex)) {
            updateFinalMap(finalMap, currentIndex);
            currentIndex = currentIndex + 1;
        }
        return currentIndex;
    }

    public static void updateFinalMap(Map<String, String> finalMap, int currentIndex) {

        Map<String, String> finalMap2 = finalMap.entrySet().stream()
                .filter(x-> Integer.parseInt(x.getKey()) <= currentIndex)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        System.out.println("finalMap2=====");
        // finalMap2.entrySet().stream().forEach(x->System.out.println(x.getKey() + "="+ x.getValue()));

        Map<String, String> finalMap3 = finalMap.entrySet().stream()
                .filter(x-> Integer.parseInt(x.getKey()) > currentIndex)
                .collect(Collectors.toMap(x-> String.valueOf(Integer.parseInt(x.getKey())+1),x->x.getValue()));
        System.out.println("finalMap3=====");
        // finalMap3.entrySet().stream().forEach(x->System.out.println(Integer.parseInt(x.getKey()) + "="+ x.getValue()));

        finalMap.clear();
        finalMap.putAll(finalMap2);
        finalMap.putAll(finalMap3);
        System.out.println("finalMap=====");
        finalMap.entrySet().stream().forEach(x->System.out.println(x.getKey() + "="+ x.getValue()));

    }
    public BigInteger getBulletPoint(XWPFDocument diffDoc) {
        CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
        cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));
        CTLvl cTLvl = cTAbstractNum.addNewLvl();
        cTLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
        cTLvl.addNewLvlText().setVal("•");
        XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
        XWPFNumbering numbering = diffDoc.getNumbering();
        if(numbering == null){
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
