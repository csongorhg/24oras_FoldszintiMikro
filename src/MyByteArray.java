import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyByteArray {

    byte bytes[];
    private int size = 0;

    public MyByteArray(){
        bytes = new byte[size];
    }

    public void add(byte b){
        byte newArray[] = new byte[++size];
        for(int i = 0; i < size-1; i++){
            newArray[i] = bytes[i];
        }
        newArray[size-1] = b;
        bytes = newArray;
    }

    public void generateFile(){
        try (FileOutputStream fos = new FileOutputStream("outFile")) {
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
