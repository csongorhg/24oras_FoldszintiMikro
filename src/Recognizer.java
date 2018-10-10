import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Recognizer {

    public static void main(String args[]) {

        try {
            String content = new String(Files.readAllBytes(Paths.get("src/asd.txt")));
            byte[] decoded = Base64.decode(content);
            for (int i = 0; i < decoded.length; i++) {
                System.out.println(decoded[i]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
