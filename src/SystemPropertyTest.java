
import java.util.Enumeration;
import java.util.Properties;

public class SystemPropertyTest {
    public static void main(String[] arg) {
        Properties props = System.getProperties();

        Enumeration enums = props.propertyNames();
        for (; enums.hasMoreElements();) {

            String propName = (String) enums.nextElement();

            String propValue = (String) props.get(propName);
            System.out.println(propName + " = " + propValue);
        }
    }
}