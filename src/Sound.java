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
}
