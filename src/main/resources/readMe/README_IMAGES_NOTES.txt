For Copy Image form one document and Copy this image as result with same dimension and it is also copy multiple images.


public ResponseDTO addTextImage(String path1) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        XWPFDocument sourceDoc = new XWPFDocument(new FileInputStream(path1));
        XWPFDocument targetDoc = new XWPFDocument();
        List<XWPFPictureData> pictures = sourceDoc.getAllPictures();
        for (XWPFParagraph paragraph : sourceDoc.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                if (run.getEmbeddedPictures() != null && !run.getEmbeddedPictures().isEmpty()) {
                    for (XWPFPicture picture : run.getEmbeddedPictures()) {
                        byte[] imageData =  picture.getPictureData().getData();
                        int pictureType =  picture.getPictureData().getPictureType();
                      //  int position = sourceDoc.getAllPictures().indexOf( picture.getPictureData());
                        String FileName =  picture.getPictureData().getFileName();

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