package Apptus.service;

import Apptus.util.ResponseDTO;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class AddWatermark {
    public ResponseDTO addTextWatermark(String path1) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        XWPFDocument sourceDoc = new XWPFDocument(new FileInputStream(path1));
        XWPFDocument targetDoc = new XWPFDocument();
        List<XWPFPictureData> pictures = sourceDoc.getAllPictures();
        for (XWPFParagraph paragraph : sourceDoc.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                if (run.getEmbeddedPictures() != null && !run.getEmbeddedPictures().isEmpty()) {
                    for (XWPFPicture picture : run.getEmbeddedPictures()) {
                        byte[] imageData = picture.getPictureData().getData();
                        int pictureType = picture.getPictureData().getPictureType();
                        String FileName = picture.getPictureData().getFileName();

                        CTPicture ctPicture = picture.getCTPicture();
                        CTShapeProperties shapeProperties = ctPicture.getSpPr();
                        CTPositiveSize2D size = shapeProperties.getXfrm().getExt();
                        int width = (int) size.getCx();
                        int height = (int) size.getCy();
                        XWPFParagraph newParagraph = targetDoc.createParagraph();
                        XWPFRun newRun = newParagraph.createRun();
                        newRun.addPicture(new ByteArrayInputStream(imageData), pictureType, FileName, width, height);
                        String newFilePath = System.getProperty("user.dir") + "\\uploads\\result.docx";
                        FileOutputStream out = new FileOutputStream(newFilePath);
                        targetDoc.write(out);
                        out.close();
                    }
                }

            }

        }
        targetDoc.close();
        return responseDTO;
    }

    /**
     * this method will get the images from doc2 and render it in diffDoc
     * @param doc1
     * @param doc2
     * @param diffDoc
     * @throws Exception
     */
    public void addImage(XWPFDocument doc1, XWPFDocument doc2, XWPFDocument diffDoc) throws Exception {
        List<XWPFPictureData> pictures = doc2.getAllPictures();
        for (XWPFParagraph paragraph : doc2.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                if (run.getEmbeddedPictures() != null && !run.getEmbeddedPictures().isEmpty()) {
                    for (XWPFPicture picture : run.getEmbeddedPictures()) {
                        byte[] imageData = picture.getPictureData().getData();
                        int pictureType = picture.getPictureData().getPictureType();
                        String FileName = picture.getPictureData().getFileName();

                        CTPicture ctPicture = picture.getCTPicture();
                        CTShapeProperties shapeProperties = ctPicture.getSpPr();
                        CTPositiveSize2D size = shapeProperties.getXfrm().getExt();
                        int width = (int) size.getCx();
                        int height = (int) size.getCy();
                        XWPFParagraph newParagraph = diffDoc.createParagraph();
                        XWPFRun newRun = newParagraph.createRun();
                        newRun.addPicture(new ByteArrayInputStream(imageData), pictureType, FileName, width, height);
                    }
                }

            }

        }

    }


}