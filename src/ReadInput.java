import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadInput {

    static BufferedReader reader;

    public static Sound[] read(String path){

        Sound[] sounds = null;
        int idx = 0;

        try {
            reader = new BufferedReader(new FileReader(path));
            int noLines = Integer.parseInt(reader.readLine());
            sounds = new Sound[noLines];

            String currentLine[];
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                currentLine = line.split("-");
                int length = Integer.parseInt(currentLine[0]);
                int frequency = Integer.parseInt(currentLine[1]);
                int volume = Integer.parseInt(currentLine[2]);
                sounds[idx++] = new Sound(length, frequency, volume);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sounds;
    }
}
