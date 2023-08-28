package Apptus.controller;

import Apptus.service.*;
import Apptus.util.HtmlConverter;
import Apptus.util.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@CrossOrigin(origins = "*")
public class ProductServiceController {

    @Autowired
    private SampleVelocityApplication service;
    @Autowired
    private WordDocCompare wordDocCompare;

    @Autowired
    TwoWordDiff twoWordDiff;

    @Autowired
    TwoWordDiffNew twoWordDiffNew;
    @Autowired
    private DocxDiffServiceImpl diffService;

    @Autowired
    WordDocumentsComparator WordDocumentsComparator;

    /**
     * REST endpoint for validating template which will return templateData in
     * ResponseEntity which will contain merge fields
     * @param file
     * @return ResponseEntity
     */
    @PostMapping(value = "/validateTemplate", produces =  "application/json")
    @ResponseBody
    public ResponseEntity<?> validateTemplateFile( @RequestParam("file") MultipartFile file ) {

        String[] bridgeParams = null;
        try {

            // html file path
            String htmlFilePath = System.getProperty("user.dir")+ "\\src\\main\\resources\\templates\\" + file.getOriginalFilename();
            // vm file path where you want to save the .vm file
            String vmFilePath = System.getProperty("user.dir")+ "\\src\\main\\resources\\templates\\" + file.getOriginalFilename()
                    .substring(0, file.getOriginalFilename().lastIndexOf(".")) +".vm";
            // conversion method which will change the file extension from html to vm
            HtmlConverter.convertHtmlToVm(htmlFilePath, vmFilePath);

            File vmFile = new File(vmFilePath);
            file.transferTo( vmFile);
            bridgeParams = service.validateTemplate(vmFile.getName());

            TemplateData templateData = new TemplateData();
            templateData.setBridgeParams(bridgeParams);
            return new ResponseEntity<>(templateData, HttpStatus.OK);
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            System.out.println( "e.getMessage()======" +exceptionMessage);
            ErrorTemplateData errorTemplateData = new ErrorTemplateData();
            errorTemplateData.setErrorMessage("Invalid Document.Please provide valid document.Error message is - " + exceptionMessage);
            return new ResponseEntity<>(errorTemplateData, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //@PostMapping(value = "/createContractDocumet", produces =  "application/json")
    @PostMapping("/createContractDocumet")
    public ResponseEntity<?> createTemplateFile( @RequestBody String request) {

        try {
            String result = service.prepareResponse(request);
            System.out.println(result);

            String documentPath = service.convertToWord(result);

            SuccessTemplateData successTemplateData = new SuccessTemplateData();
            //successTemplateData.setDocumentLocation("http://localhost:8090/CreateWordParagraphFromHTML.docx");
            successTemplateData.setDocumentLocation(documentPath);
            return new ResponseEntity<>(successTemplateData, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * REST endpoint for generating word document which will return successTemplateData in
     * ResponseEntity which will contain generated document path - documentPath
     * @param request
     * @return ResponseEntity
     */
    @PostMapping("/createComplexDocumet")
    public ResponseEntity<?> createComplexTemplateFile( @RequestBody String request) {

        try {
            String result = service.prepareComplexResponse(request);
            System.out.println(result);

//            String documentPath = service.convertToWord(result);
            String documentPath = service.convertHtmlToWord(result);

            SuccessTemplateData successTemplateData = new SuccessTemplateData();
            successTemplateData.setDocumentLocation(documentPath);
            return new ResponseEntity<>(successTemplateData, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /*try {
            String result = service.prepareComplexResponse(request);
            System.out.println(result);

            String documentPath = service.convertToWord(result);

            SuccessTemplateData successTemplateData = new SuccessTemplateData();
            successTemplateData.setDocumentLocation(documentPath);
            return new ResponseEntity<>(successTemplateData, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }*/
    }

    /**
     * REST endpoint for comparing two versions of word document which will return responseDTO in
     * ResponseEntity which will modified text summary - i. e inserted/deleted text and their respective count
     * @param path1
     * @param path2
     * @return ResponseEntity
     */
    @GetMapping(value = "/convert")
    public ResponseEntity<String> convertDocxToBase( @RequestParam("path1") String path1,
                                                     @RequestParam("path2") String path2 ){
        path1=System.getProperty("user.dir")+ "\\"+ path1;
        path2=System.getProperty("user.dir")+ "\\"+ path2;
        ResponseDTO responseDTO =new ResponseDTO();
        try {
            responseDTO = WordDocumentsComparator.compareDocuments(path1,path2);
            ObjectMapper mapper=new ObjectMapper();
            String response = mapper.writeValueAsString(responseDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed compare docx file");
        }
    }

    @GetMapping(value = "/convert-groupdocs")
    public ResponseEntity<String> convertGroupDocxToBase(@RequestParam("path1") String path1,
                                                         @RequestParam("path2") String path2) {
        path1 = System.getProperty("user.dir") + "\\" + path1;
        path2 = System.getProperty("user.dir") + "\\" + path2;
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = diffService.generateDiffDocx(path1, path2);
            ObjectMapper mapper = new ObjectMapper();
            String response = mapper.writeValueAsString(responseDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed compare docx file");
        }
    }

}