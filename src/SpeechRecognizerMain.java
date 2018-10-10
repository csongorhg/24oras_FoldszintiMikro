

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

public class SpeechRecognizerMain {

    static MyByteArray myArray;
    static String current;

    // Necessary
    private LiveSpeechRecognizer recognizer;

    /**
     * This String contains the Result that is coming back from SpeechRecognizer
     */
    private String speechRecognitionResult;

    //-----------------Lock Variables-----------------------------

    /**
     * This variable is used to ignore the results of speech recognition cause actually it can't be stopped...
     *
     * <br>
     * Check this link for more information: <a href=
     * "https://sourceforge.net/p/cmusphinx/discussion/sphinx4/thread/3875fc39/">https://sourceforge.net/p/cmusphinx/discussion/sphinx4/thread/3875fc39/</a>
     */
    private boolean ignoreSpeechRecognitionResults = false;

    /**
     * Checks if the speech recognise is already running
     */
    private boolean speechRecognizerThreadRunning = false;

    /**
     * Checks if the resources Thread is already running
     */
    private boolean resourcesThreadRunning;

    //---

    /**
     * This executor service is used in order the playerState events to be executed in an order
     */
    private ExecutorService eventsExecutorService = Executors.newFixedThreadPool(2);

    //------------------------------------------------------------------------------------

    /**
     * Constructor
     */
    public SpeechRecognizerMain() {

        // Configuration
        Configuration configuration = new Configuration();

        // Load model from the jar
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

        //====================================================================================
        //=====================READ THIS!!!===============================================
        //Uncomment this line of code if you want the recognizer to recognize every word of the language
        //you are using , here it is English for example
        //====================================================================================
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        //====================================================================================
        //=====================READ THIS!!!===============================================
        //If you don't want to use a grammar file comment below 3 lines and uncomment the above line for language model
        //====================================================================================

        // Grammar
        configuration.setGrammarPath("res");
        configuration.setGrammarName("grammar");
        configuration.setUseGrammar(true);

        try {

            recognizer = new LiveSpeechRecognizer(configuration);
        } catch (IOException ex) {
            System.err.println(ex.getStackTrace());
        }

        // Start recognition process pruning previously cached data.
        // recognizer.startRecognition(true);

        //Check if needed resources are available
        startResourcesThread();
        //Start speech recognition thread
        startSpeechRecognition();
    }

    //-----------------------------------------------------------------------------------------------

    /**
     * Starts the Speech Recognition Thread
     */
    public synchronized void startSpeechRecognition() {

        //Check lock
        if (speechRecognizerThreadRunning)
            System.out.println("Speech Recognition Thread already running...");
        else
            //Submit to ExecutorService
            eventsExecutorService.submit(() -> {

                //locks
                speechRecognizerThreadRunning = true;
                ignoreSpeechRecognitionResults = false;

                //Start Recognition
                recognizer.startRecognition(true);

                //Information
                System.out.println("You can start to speak...");


                try {
                    whileloop:
                    while (speechRecognizerThreadRunning) {
                        /*
                         * This method will return when the end of speech is reached. Note that the end pointer will determine the end of speech.
                         */

                        SpeechResult speechResult = recognizer.getResult();

                        //Check if we ignore the speech recognition results
                        if (!ignoreSpeechRecognitionResults) {

                            //Check the result
                            if (speechResult == null)
                                System.out.println("I can't understand what you said.");
                            else {

                                //Get the hypothesis
                                speechRecognitionResult = speechResult.getHypothesis();

                                //You said?
                                System.out.println("You said: [" + speechRecognitionResult + "]\n");

                                switch(speechRecognitionResult){
                                    case "one":
                                        current += "1";
                                        break;
                                    case "two":
                                        current += "2";
                                        break;
                                    case "three":
                                        current += "3";
                                        break;
                                    case "four":
                                        current += "4";
                                        break;
                                    case "five":
                                        current += "5";
                                        break;
                                    case "six":
                                        current += "6";
                                        break;
                                    case "seven":
                                        current += "7";
                                        break;
                                    case "eight":
                                        current += "8";
                                        break;
                                    case "nine":
                                        current += "9";
                                        break;
                                    case "zero":
                                        current += "0";
                                        break;
                                    case "minus":
                                        if(current.length() > 0){
                                            myArray.add((byte)Integer.parseInt(current));
                                            current = "";
                                        }
                                        current += "-";
                                        break;
                                    case "next":
                                        int value = Integer.parseInt(current);
                                        if(value >= -128 && value < 128) {
                                            myArray.add((byte)value);
                                        }else{
                                            myArray.add((byte)0);
                                        }
                                        current = "";
                                        break;
                                    case "end":
                                        break whileloop;
                                }

                                //Call the appropriate method
                                makeDecision(speechRecognitionResult, speechResult.getWords());

                            }
                        } else
                            System.out.println("Ingoring Speech Recognition Results...");

                    }
                    myArray.generateFile();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    speechRecognizerThreadRunning = false;
                }

                System.out.println("SpeechThread has exited...");

            });
    }

    /**
     * Stops ignoring the results of SpeechRecognition
     */
    public synchronized void stopIgnoreSpeechRecognitionResults() {

        //Stop ignoring speech recognition results
        ignoreSpeechRecognitionResults = false;
    }

    /**
     * Ignores the results of SpeechRecognition
     */
    public synchronized void ignoreSpeechRecognitionResults() {

        //Instead of stopping the speech recognition we are ignoring it's results
        ignoreSpeechRecognitionResults = true;

    }

    //-----------------------------------------------------------------------------------------------

    /**
     * Starting a Thread that checks if the resources needed to the SpeechRecognition library are available
     */
    public void startResourcesThread() {

        //Check lock
        if (resourcesThreadRunning)
            System.err.println("Resources Thread already running...");
        else
            //Submit to ExecutorService
            eventsExecutorService.submit(() -> {
                try {

                    //Lock
                    resourcesThreadRunning = true;

                    // Detect if the microphone is available
                    while (true) {

                        //Is the Microphone Available
                        if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE))
                            System.err.println("Microphone is not available.");

                        // Sleep some period
                        Thread.sleep(350);
                    }

                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                    resourcesThreadRunning = false;
                }
            });
    }

    /**
     * Takes a decision based on the given result
     *
     * @param speechWords
     */
    public void makeDecision(String speech , List<WordResult> speechWords) {

        System.out.println(speech);

    }

    public boolean getIgnoreSpeechRecognitionResults() {
        return ignoreSpeechRecognitionResults;
    }

    public boolean getSpeechRecognizerThreadRunning() {
        return speechRecognizerThreadRunning;
    }

    /**
     * Main Method
     *
     * @param args
     */
    public static void main(String[] args) {
        myArray = new MyByteArray();
        current = "";
        new SpeechRecognizerMain();
    }
}