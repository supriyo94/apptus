package Apptus.service;


import Apptus.util.ResponseDTO;
import com.groupdocs.comparison.Comparer;
import com.groupdocs.comparison.result.ChangeInfo;
import com.groupdocs.comparison.result.ChangeType;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;


@Component

public class DocxDiffServiceImpl {


    public ResponseDTO generateDiffDocx(String path1, String path2) throws FileNotFoundException {
        ResponseDTO responseDTO=new ResponseDTO();
        Comparer comparer = new Comparer(new FileInputStream(path1));
        try {
            comparer.add(new FileInputStream(path2));
            String filePath = System.getProperty("user.dir")+ "\\uploads\\result.docx";
            final Path resultPath = comparer.compare(new FileOutputStream(filePath));
            ChangeInfo[] changes = comparer.getChanges();
            int noOfChanges = changes.length;
            if(noOfChanges>1) {
                responseDTO.setTotalChangeCount(noOfChanges);

                long insertedCount = Arrays.stream(changes).filter(s -> s.getType().equals(ChangeType.INSERTED)).count();
                responseDTO.setInsertCount(insertedCount);

                long deletedCount = Arrays.stream(changes).filter(s -> s.getType().equals(ChangeType.DELETED)).count();
                responseDTO.setDeleteCount(deletedCount);

                List<String> insertedLines = new ArrayList<>();
                Arrays.stream(changes).filter(s -> s.getType().equals(ChangeType.INSERTED)).forEach(s -> insertedLines.add(s.getText()));
                responseDTO.setInsertedText(insertedLines);

                List<String> deletedLines = new ArrayList<>();
                Arrays.stream(changes).filter(s -> s.getType().equals(ChangeType.DELETED)).forEach(s -> deletedLines.add(s.getText()));
                responseDTO.setDeletedText(deletedLines);

                String url="http://127.0.0.1:8090/uploads/result.docx";
                responseDTO.setDocxUrl(url);
            }
            String url="http://127.0.0.1:8090/uploads/result.docx";
            responseDTO.setDocxUrl(url);
            comparer.dispose();
            return responseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
