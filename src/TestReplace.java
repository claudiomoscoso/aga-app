
import org.dom4j.Document;

import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.util.BSXMLManager;

public class TestReplace {
    public static void main(String[] s) {
        //        String find = "<BS Content=\"\">";
        //        String o = "<xsl:...><BS Content=\"\"></xsl:...>";
        String find = "<BS Content=\"   \"/>";
        String o = "<xsl:...>" + find + "</xsl:...>";
        String h = "<html>hola mundo</html>";
        StringBuffer out = null;

        StringBuffer sb = new StringBuffer(o);
        int i = sb.indexOf(find);
        out = sb.replace(i, i + find.length(), h);

        System.out.println(out);

        
        
        
        
        String xsl = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\" xmlns:xalan=\"http://xml.apache.org/xslt\">	<xsl:template match=\"/\">	"
                + find + "</xsl:template></xsl:stylesheet>";

        Document d = null;
        try {
            d = BSXMLManager.stringToDocument(xsl);
        } catch (BSProgrammerException e) {
            e.printStackTrace();
        }

        System.out.println(d.selectSingleNode("//xBS").asXML());

    }
}