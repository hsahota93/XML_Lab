package sd_xml_lab;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class SD_XML_Lab {

    public static String idNumber;

    public static void main(String[] args) throws Exception {
        
        System.out.println("Enter a number");
        Scanner scanner = new Scanner(System. in);
        idNumber = scanner.nextLine();

        try {
            new SD_XML_Lab().start();
        } catch (Exception e) {}
    }

    private void start() throws Exception {
        URL url = new URL("http://129.32.92.49/xml/grade.php?id=" + idNumber);
        URLConnection connection = url.openConnection();

        Document doc = parseXML(connection.getInputStream());
        NodeList name = doc.getElementsByTagName("name");
        NodeList grade = doc.getElementsByTagName("grade");

        for (int i = 0; i < name.getLength(); i++) {
            System.out.println(name.item(i).getTextContent());
        }
        
        for (int i = 0; i < grade.getLength(); i++) {
            System.out.println(grade.item(i).getTextContent());
        }
    }

    private Document parseXML(InputStream stream) throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = (Document) objDocumentBuilder.parse(stream);
        } catch (Exception ex) {
            throw ex;
        }

        return doc;
    }

}
