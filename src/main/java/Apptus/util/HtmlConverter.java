package Apptus.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlConverter {
    /**
     * This method will change the extension of HTML file to .vm which is used by Apache velocity
     * for identification of merge fields written in VTL (velocity template language)
     * @param htmlFilePath
     * @param vmFilePath
     * @throws IOException
     */
    public static void convertHtmlToVm(String htmlFilePath, String vmFilePath) throws IOException {

        //load the HTML file using Jsoup's parse()
        File htmlFile = new File(htmlFilePath);
        htmlFile.createNewFile();
        Document doc = Jsoup.parse(htmlFile, "UTF-8");

        // Save the modified HTML as a Velocity Template Language file
        FileWriter writer = new FileWriter(vmFilePath);
        writer.write(doc.html());
        writer.close();
    }
}

