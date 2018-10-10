import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Sound {

    private int length; // length of generated signal
    private int frequency; // frequency of generated signal
    private int volume; // volume of generated signal

    public Sound(int length, int frequency, int volume) {
        this.length = length;
        this.frequency = frequency;
        this.volume = volume;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

   public void play(){
        byte[] buf = new byte[ 1 ];
         AudioFormat af = new AudioFormat( (float )44100, 8, 1, true, false );
        SourceDataLine sdl;
        try {
            sdl = AudioSystem.getSourceDataLine( af );
            sdl.open();
            sdl.start();
            for( int i = 0; i < length * (float )44100 / 1000; i++ ) {
                double angle = i / ( (float )44100 / frequency ) * 2.0 * Math.PI;
                buf[ 0 ] = (byte )( Math.sin( angle ) * volume );
                sdl.write( buf, 0, 1 );
            }
            sdl.drain();
            sdl.stop();
       } catch (LineUnavailableException e) {
           e.printStackTrace();
       }
   }
}
