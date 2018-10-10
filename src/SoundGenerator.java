import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundGenerator {

	public static void main(String[] args) {
		
		generateSound(1000, 440, 150); //length in millis, frequency (magassag), volume
		System.out.println("teszt");
	}
	
	public static void generateSound(int length, int frequency, int volume){
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
