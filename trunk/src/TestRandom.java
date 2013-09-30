
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class TestRandom {
    public static void main(String[] a) {
        Random r = new Random();

        int max = 487150;

        boolean rep = false;

        Hashtable t = new Hashtable(max);
        String s = null;
        for (int i = 0; i < max; i++) {
            s = "" + r.nextLong();
            if (t.containsKey(s)) {
                rep = true;
                System.out.println("esta " + s);
            } else {
                t.put(s, s);
            }
        }

        if (!rep) {

            Enumeration e = t.elements();

            while (e.hasMoreElements()) {
                String element = (String) e.nextElement();
                //System.out.println(element);
            }

        }
System.out.println("-6304441519727728723".length());
    }
}