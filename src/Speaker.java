import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

public class Speaker {

    public static void sleep (int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        byte[] bFile = null;
        try {
            bFile = Files.readAllBytes(Paths.get("otpt"));
            for (int i = 0; i < bFile.length; i++) {
                System.out.println((int)bFile[i]+128 < 16 ? "0"+Integer.toHexString((int)bFile[i]+128) : Integer.toHexString((int)bFile[i]+128));
            }
            System.out.println(bFile.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream("otpt_out")) {
            fos.write(bFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String [] hexaArray = new String[bFile.length];
        for(int i = 0; i < bFile.length; i++){
            hexaArray[i] = Integer.toHexString((int)bFile[i]+128);
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            Runtime.getRuntime().exec("wscript tts_End.vbs");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                120000
        );


        try {
            for (int i = 0; i < hexaArray.length; i++) {
                String number = hexaArray[i];
                if(number.length() < 2){
                    number = "0" + number;
                }
                Vector<String> numberVector = new Vector<>();
                for (int j = 0; j < number.length(); j++) {
                    numberVector.add(number.charAt(j) + "");
                }
                for (int j = 0; j < numberVector.size(); j++) {
                    switch (numberVector.get(j)) {
                        case "0":
                            Runtime.getRuntime().exec("wscript tts_Zero.vbs");
                            break;
                        case "1":
                            Runtime.getRuntime().exec("wscript tts_One.vbs");
                            break;
                        case "2":
                            Runtime.getRuntime().exec("wscript tts_Two.vbs");
                            break;
                        case "3":
                            Runtime.getRuntime().exec("wscript tts_Three.vbs");
                            break;
                        case "4":
                            Runtime.getRuntime().exec("wscript tts_Four.vbs");
                            break;
                        case "5":
                            Runtime.getRuntime().exec("wscript tts_Five.vbs");
                            break;
                        case "6":
                            Runtime.getRuntime().exec("wscript tts_Six.vbs");
                            break;
                        case "7":
                            Runtime.getRuntime().exec("wscript tts_Seven.vbs");
                            break;
                        case "8":
                            Runtime.getRuntime().exec("wscript tts_Eight.vbs");
                            break;
                        case "9":
                            Runtime.getRuntime().exec("wscript tts_Nine.vbs");
                            break;
                        case "a":
                            Runtime.getRuntime().exec("wscript tts_A.vbs");
                            break;
                        case "b":
                            Runtime.getRuntime().exec("wscript tts_B.vbs");
                            break;
                        case "c":
                            Runtime.getRuntime().exec("wscript tts_C.vbs");
                            break;
                        case "d":
                            Runtime.getRuntime().exec("wscript tts_D.vbs");
                            break;
                        case "e":
                            Runtime.getRuntime().exec("wscript tts_E.vbs");
                            break;
                        case "f":
                            Runtime.getRuntime().exec("wscript tts_F.vbs");
                            break;
                    }
                    sleep(1550);
                }
                Runtime.getRuntime().exec("wscript tts_Next.vbs");
                sleep(1550);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

