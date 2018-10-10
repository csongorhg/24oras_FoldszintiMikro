import javax.imageio.IIOException;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class CaptureMicrophone {

    public static void main(String[] args){
        System.out.println("sound test");
        try{
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 1, 4, 44100,false);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            if(!AudioSystem.isLineSupported(info))
                System.out.println("line not supported");

            TargetDataLine targetLine = (TargetDataLine)AudioSystem.getLine(info);
            targetLine.open(); //start recording

            System.out.println("start recording");
            targetLine.start();

            Thread thread = new Thread(){
                @Override public void run(){
                    AudioInputStream audioInputStream = new AudioInputStream(targetLine);
                    File audioFile = new File("record.wav");
                    try {
                        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, audioFile);
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                    System.out.println("stopped");
                }
            };
            Thread.sleep(5000);
            targetLine.stop();
            targetLine.close();
            System.out.println("end");
        }catch (LineUnavailableException ex){
            ex.printStackTrace();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
