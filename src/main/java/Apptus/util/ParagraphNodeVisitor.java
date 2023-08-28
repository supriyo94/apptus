package Apptus.util;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

public class ParagraphNodeVisitor implements NodeVisitor {

    String nodeName;
    boolean needNewRun;
    boolean isItalic;
    boolean isBold;
    boolean isUnderlined;
    int fontSize;
    String fontColor;
    XWPFParagraph paragraph;
    XWPFRun run;

    public ParagraphNodeVisitor(XWPFParagraph paragraph) {
        this.paragraph = paragraph;
        this.run = paragraph.createRun();
        this.nodeName = "";
        this.isItalic = false;
        this.isBold = false;
        this.isUnderlined = false;
        this.fontSize = 11;
        this.fontColor = "000000";

    }

    @Override
    public void head(Node node, int depth) {
        nodeName = node.nodeName();
        System.out.println("Start " + nodeName + ": " + node);
        if ("#text".equals(nodeName)) {
            run.setText(((TextNode) node).text());
        } else if ("i".equals(nodeName)) {
            isItalic = true;
        } else if ("b".equals(nodeName)) {
            isBold = true;
        } else if ("u".equals(nodeName)) {
            isUnderlined = true;
        } else if ("br".equals(nodeName)) {
            //run.addBreak();
        } else if ("font".equals(nodeName)) {
            //fontColor = (!"".equals(node.attr("color")))?node.attr("color").substring(1):"000000";
            //fontSize = (!"".equals(node.attr("size")))?Integer.parseInt(node.attr("size")):11;
        }
        run.setItalic(isItalic);
        run.setBold(isBold);
        if (isUnderlined) run.setUnderline(UnderlinePatterns.SINGLE);
        else run.setUnderline(UnderlinePatterns.NONE);
        run.setColor(fontColor);
        run.setFontSize(fontSize);
    }

    @Override
    public void tail(Node node, int depth) {
        nodeName = node.nodeName();
        System.out.println("End " + nodeName);
        if ("#text".equals(nodeName)) {
            run = paragraph.createRun(); //after setting the text in the run a new run is needed
        } else if ("i".equals(nodeName)) {
            isItalic = false;
        } else if ("b".equals(nodeName)) {
            isBold = false;
        } else if ("u".equals(nodeName)) {
            isUnderlined = false;
        } else if ("br".equals(nodeName)) {
            run = paragraph.createRun(); //after setting a break a new run is needed
        } else if ("font".equals(nodeName)) {
            fontColor = "000000";
            fontSize = 11;
        }
        run.setItalic(isItalic);
        run.setBold(isBold);
        if (isUnderlined) run.setUnderline(UnderlinePatterns.SINGLE);
        else run.setUnderline(UnderlinePatterns.NONE);
        run.setColor(fontColor);
        run.setFontSize(fontSize);
    }
}
