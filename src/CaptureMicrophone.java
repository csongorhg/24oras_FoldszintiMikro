import javax.imageio.IIOException;
import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class CaptureMicrophone {


    private byte[] recorded_txt;

    public static byte[] getWAVByte(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{
            File f = new File("record.wav");
            AudioInputStream in = AudioSystem.getAudioInputStream(f);

            int read;

            byte[] buff = new byte[(int)f.length()];
            while ((read = in.read(buff)) > 0)
            {
                out.write(buff, 0, read);
            }
            out.flush();
            byte[] audioBytes = out.toByteArray();

            return audioBytes;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("sound test");
        try {
            AudioFormat format = new AudioFormat(8000.0F, 16, 1, true, false);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            if (!AudioSystem.isLineSupported(info))
                System.out.println("line not supported");

            TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(info);
            targetLine.open(); //start recording

            System.out.println("start recording");
            targetLine.start();


            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                            AudioInputStream audioInputStream = new AudioInputStream(targetLine);
                            File audioFile = new File("record.wav");
                            audioFile.createNewFile();
                            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, audioFile);
                    } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        System.out.println("stopped");
                    }
                };
            thread.start();
            thread.sleep(10000);
            targetLine.stop();
            byte teszt[] = new byte[1000];
            targetLine.read(teszt, 0, 1000);
            System.out.println(new String(teszt));


            targetLine.close();
            System.out.println("end");

            byte[] recorded_txt = getWAVByte();
            /*
                System.out.println(new String(recorded_txt));
            }*/

        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
}
