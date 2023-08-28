/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package Apptus.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.util.*;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.AltChunkType;
import org.docx4j.openpackaging.parts.WordprocessingML.AlternativeFormatInputPart;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTAltChunk;
import Apptus.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.apache.velocity.tools.view.LinkTool;
import org.jsoup.nodes.Element;
import org.jsoup.select.NodeTraversor;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import static java.nio.file.Files.*;

@Service
//@Deprecated
public class SampleVelocityApplication {

    @org.springframework.beans.factory.annotation.Value("${application.message}")
    private String message;

    @org.springframework.beans.factory.annotation.Value("${templateLocation}")
    private String templateLocation;

	/*@Autowired
	private VelocityEngine engine;*/

    /**
     * This method will return array of merge fields that needs user inputs
     *
     * @param fileName
     * @return String[] bridgeParamsArray
     * @throws Exception
     */
    public String[] validateTemplate(String fileName) throws Exception {
        //fileName = "ErrorRequest-MissingField.vm";
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\templates\\" + fileName);
        validate(file);

        VelocityEngine velocity = new VelocityEngine();
        velocity.init();
        Template template = velocity.getTemplate("src/main/resources/templates/" + fileName); // syntaxError

        Set<String> bridgeParamsSet = getVariableNames(template);
        //String[] bridgeParamsArray = bridgeParamsSet.stream().toArray(String[]:: new);
        String[] bridgeParamsArray = bridgeParamsSet.stream()
                .filter(x -> x.contains("_merge"))
                .map(x -> x.replace("_merge", "")).toArray(String[]::new);
        return bridgeParamsArray;

    }

    /**
     * This method will parse the .vm template and collect the nodes
     * invoke collectVariableNames with arg nodes and Set<String> variableNames
     * It will return the merge field names
     *
     * @param template
     * @return Set<String> variableNames
     * @throws Exception
     */
    public Set<String> getVariableNames(Template template) throws Exception {
        SimpleNode node = (SimpleNode) template.getData();
        Set<String> variableNames = new HashSet<>();
        collectVariableNames(node, variableNames);
        return variableNames;
    }

    /**
     * this method will collect the merge fields from each node of vm template
     *
     * @param node
     * @param variableNames
     * @throws Exception
     */
    private void collectVariableNames(SimpleNode node, Set<String> variableNames) throws Exception {
        if (node instanceof ASTReference) {
            String variableName = ((ASTReference) node).getRootString();
            System.out.println("variableName-------" + variableName);
            if (StringUtils.isEmpty(variableName)) {
                throw new Exception("Element should not be Empty or null.");
            }
            variableNames.add(variableName);
        }
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            SimpleNode childNode = (SimpleNode) node.jjtGetChild(i);
            collectVariableNames(childNode, variableNames);
        }
    }

    public String prepareResponse(String request) {
        String[] bridgeParams = request.split(",");
        VelocityEngine velocity = new VelocityEngine();
        velocity.init();
        Template template = velocity.getTemplate("src/main/resources/templates/SimpleFile.vm"); // syntaxError
        VelocityContext context = new VelocityContext();
        context.put("variable", "test");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    /**
     * This method will render exact values for merge fields according to "request" received
     *
     * @param request
     * @return String
     */
    public String prepareComplexResponse(String request) {
        request = request.replaceAll("\"", "");
        String[] bridgeParams = request.split(",");

        VelocityEngine velocity = new VelocityEngine();
        velocity.init();
        Template template = velocity.getTemplate("src/main/resources/templates/DealQuote_Prod_complete.vm"); // DealQuote_Prod_complete.vm
        VelocityContext context = new VelocityContext();

        for (String param : bridgeParams) {
            String[] fields = param.split(":");
            if (fields.length == 1) {
                context.put(fields[0].concat("_merge"), "");
            } else {
                if (!StringUtils.isEmpty(fields[0]) && !StringUtils.isEmpty(fields[1])) {
                    context.put(fields[0].trim().concat("_merge"), fields[1].trim());
                } else {
                    context.put(fields[0].concat("_merge"), fields[1]);
                }
            }

        }
        context.put("OPPORTUNITY_CONTRACT_TERM_merge", Integer.parseInt((String) context.get("OPPORTUNITY_CONTRACT_TERM_merge")));
        context.put("showTable_merge", (String) context.get("showTable_merge"));
        context.put("QUOTE_EXPIRATIONDATE", new DateTool());
        //context.put("QUOTE_OPP_OWNER_PRM_PROFILE",new Quote_Opp_Owner_Prm_Profile("PrmProfile",101));
        context.put("QUOTE_OPP_OWNER_PRM_PROFILE", new ArrayList<>());
        context.put("DPRQuoteCheck", new DPRQuoteCheck(false, false));
        context.put("DPRQuoteL1", new DPRQuoteL1(false, 229060, 400));
        //-context.put("hideDPRQuotePlanHH",true);
        context.put("numberTool", new NumberTool());
        context.put("DPRQuotePlanHH_list", new DPRQuotePlanHH_bkp().getDPRQuotePlanHHList());
        context.put("DPRQuotePlanWLS_list", new DPRQuotePlanWLS().getDPRQuotePlanWLSList());
        context.put("DPRQuotePlanMBB_list", new DPRQuotePlanMBB().getDPRQuotePlanMBBList());
        context.put("DPRDealQuoteAMC_list", new DPRDealQuoteAMC().getDPRDealQuoteAMCList());
        context.put("DPRQuoteDeviceRC_list", new DPRQuoteDeviceRC().getDPRQuoteDeviceRCList());
        //-context.put("DPRQuoteCheck_list",new DPRDealQuoteAMC().getDPRDealQuoteAMCList());
        //-context.put("DPRQuoteCheck",new DPRQuoteCheck(false));
        //context.put("DPRQuoteDeviceOC_list",new DPRQuoteDeviceOC().getDPRQuoteDeviceOCList());
        context.put("IOTRateCardLPWAN_list", false);
        context.put("projectPath", System.getProperty("user.dir"));
        context.put("link", new LinkTool());
        context.put("IOTRateCardLPWAN_list", new IOTRateCardLPWAN().getIOTRateCardLPWANList());
        context.put("IOTRateCardLTE_list", new IOTRateCardLTE().getIOTRateCardLTEList());
        context.put("IOTRateCardUtilities_list", new IOTRateCardUtilities().getIOTRateCardUtilitiesList());
        context.put("TCRateCard_list", new TCRateCard().getTCRateCardList());
        context.put("DPRQuoteTC_list", new DPRQuoteTC().getDPRQuoteTCList());
        context.put("DPRQuoteIoT_list", new ArrayList<>());//DPRQuoteL1_New
        context.put("DPRQuoteL1_list", new DPRQuoteL1_New().getDPRQuoteL1List());
        context.put("DPRQuoteL2RC_list", new DPRQuoteL2RC().getDPRQuoteL2RCL1List());

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }


    /**
     * To convert HTML file to Word document
     *
     * @param html
     * @return
     * @throws Docx4JException
     * @throws JAXBException
     */
    public String convertHtmlToWord(String html) throws Docx4JException, JAXBException {
        long milliseconds = ZonedDateTime.now().toInstant().toEpochMilli();
        String fileName = "Demo1" + milliseconds;
        String PATH = System.getProperty("user.dir") + "\\uploads\\";
        File file = new File(PATH + fileName + ".docx");
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
        wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
        ndp.unmarshalDefaultNumbering();
        AlternativeFormatInputPart inputPart = new AlternativeFormatInputPart(AltChunkType.Xhtml);
        inputPart.setContentType(new org.docx4j.openpackaging.contenttype.ContentType("text/html"));
        inputPart.setBinaryData(html.getBytes());
        Relationship altChunkRel = wordMLPackage.getMainDocumentPart().addTargetPart(inputPart);
        CTAltChunk ac = Context.getWmlObjectFactory().createCTAltChunk();
        ac.setId(altChunkRel.getId());
        wordMLPackage.getMainDocumentPart().addObject(ac);
        wordMLPackage.getContentTypeManager().addDefaultContentType("html", "text/html");
        wordMLPackage.save(file);
        return "http://localhost:8090/uploads/" + fileName + ".docx";
    }


    /**
     * This method will convert the string result into word document
     *
     * @param result
     * @return String - url of the document
     * @throws IOException
     */
    public String convertToWord(String result) throws IOException {

        long milliseconds = ZonedDateTime.now().toInstant().toEpochMilli();
        String fileName = "Demo" + milliseconds;
        String wordFile = "AGR" + milliseconds + "_v1";
        String suffix = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
        Path path = Paths.get(suffix + fileName + ".html");
        String str = result;
        try {
            write(path, str.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            System.out.print("Invalid Path");
        }
        File file = new File(suffix + fileName + ".html");
        String filepathnew = System.getProperty("user.dir") + "\\uploads\\";
        File rename = new File(filepathnew + wordFile + ".doc");
        file.renameTo(rename);
        return "http://localhost:8090/uploads/" + wordFile + ".doc";

    }


    private void createParagraphFromHTML(XWPFParagraph paragraph, Element htmlParagraph) {
        ParagraphNodeVisitor nodeVisitor = new ParagraphNodeVisitor(paragraph);
        NodeTraversor.traverse(nodeVisitor, htmlParagraph);
    }

    public void validate(File file) throws Exception {
        try {
            RuntimeSingleton.init();
        } catch (Exception e) {
            throw new Exception("Failed to init VelocityParserValidator", e);
        }

        try (
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
        ) {
            RuntimeSingleton.getRuntimeServices().parse(br, file.getAbsolutePath());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new Exception("Failed to parse Template.");

        }
    }

    public List<List<Product>> getProductList() {

        List<List<Product>> productList = new ArrayList<>();

		/*Product product1 = new Product();
		product1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1("P-1");
		product1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2("P-1");
		product1.setBASKETSNAPSHOT__X_QUANTITY("Quantity-1");

		Product product2 = new Product();
		product2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1("P-2");
		product2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2("P-3");
		product2.setBASKETSNAPSHOT__X_QUANTITY("Quantity-2");

		Product product3 = new Product();
		product3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1("P-3");
		product3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2("P-3");
		product3.setBASKETSNAPSHOT__X_QUANTITY("Quantity-3");

		productList.add(product1);
		productList.add(product2);
		productList.add(product3);*/
        productList.add(new Product().getNewProductList());

        return productList;

    }

    public List<String> getProductModuleNames() {

        List<String> ProductModuleNames = Arrays.asList("Prodcuct1", "Prodcuct2", "Prodcuct3", "Prodcuct4", "Prodcuct5");
        return ProductModuleNames;

    }

    public List<String> getQuantity() {

        List<String> quantityList = Arrays.asList("100", "200", "300", "400", "500");
        return quantityList;

    }

    public List<String> getVolumeTier1() {

        List<String> volumeTier1List = Arrays.asList("v1-100", "v1-200", "v1-300", "v1-400", "v1-500");
        return volumeTier1List;

    }

    public List<String> getVolumeTier2() {

        List<String> volumeTier1List = Arrays.asList("v2-100", "v2-200", "v2-300", "v2-400", "v2-500");
        return volumeTier1List;

    }

    public List<String> getVolumeTier3() {

        List<String> volumeTier1List = Arrays.asList("v3-100", "v3-200", "v3-300", "v3-400", "v3-500");
        return volumeTier1List;

    }

    public List<String> getTotalRecurringCharges() {

        List<String> totalRecurringCharges = Arrays.asList("1000", "2000", "3000", "4000", "5000");
        return totalRecurringCharges;

    }

}