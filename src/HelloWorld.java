import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloWorld {

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        System.out.println("asd");
        System.out.println("yoforsen");
        System.out.println("KASBLB");

        //ReadMic.read();
        //fromInput();

        /*try {
            byte[] bFile = Files.readAllBytes(Paths.get("otpt"));
            for(int i = 0; i < bFile.length; i++){
                System.out.println(bFile[i]);
            }
            System.out.println(bFile.length*8/120);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*int time = 50;
        Sound sound0 = new Sound(time, 880, 150);
        Sound sound1 = new Sound(time, 1240, 150);
        sound1.play();
        sleep(time);
        sound0.play();
        sleep(time);
        sound1.play();
        sleep(time);
        sound1.play();
        sleep(time);
        sound0.play();
        sleep(time);
        sound0.play();
        sleep(time);
        sound0.play();
        sleep(time);
        sound1.play();
        sleep(time);*/

        Sound sound = new Sound(7000, 880, 100);
        sound.play();


    }

    public static void fromInput(){
        Sound sounds[] = ReadInput.read("input.txt");
        for(int i = 0; i < sounds.length; i++){
            sounds[i].play();
            System.out.println(i);
        }
    }

    public static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void boci(){
        Sound soundChalf = new Sound(300, 262, 150);
        Sound soundEhalf = new Sound(300, 330, 150);
        Sound soundGwhole = new Sound(600, 392, 150);
        Sound soundC2half = new Sound(300, 523, 150);
        Sound soundBhalf = new Sound(300, 494, 150);
        Sound soundAhalf = new Sound(300, 440, 150);
        Sound soundGhalf = new Sound(300, 392, 150);
        Sound soundFwhole = new Sound(600, 349, 150);
        Sound soundAwhole = new Sound(600, 440, 150);
        Sound soundFhalf = new Sound(300, 349, 150);
        Sound soundDhalf = new Sound(300, 294, 150);
        Sound soundCwhole = new Sound(600, 262, 150);

        soundChalf.play();
        soundEhalf.play();
        soundChalf.play();
        soundEhalf.play();
        soundGwhole.play();
        soundGwhole.play();

        soundChalf.play();
        soundEhalf.play();
        soundChalf.play();
        soundEhalf.play();
        soundGwhole.play();
        soundGwhole.play();

        soundC2half.play();
        soundBhalf.play();
        soundAhalf.play();
        soundGhalf.play();
        soundFwhole.play();
        soundAwhole.play();

        soundGhalf.play();
        soundFhalf.play();
        soundEhalf.play();
        soundDhalf.play();
        soundCwhole.play();
        soundCwhole.play();
    }

}