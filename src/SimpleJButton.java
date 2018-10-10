import javax.swing.JButton;
import javax.swing.JFrame;

public class SimpleJButton {
    SimpleJButton() {
        JFrame f = new JFrame("FoldszintiMikro");

        JButton speaker = new JButton("Speaker");
        speaker.setBounds(75, 100, 150, 50);
        f.add(speaker);

        JButton receiver = new JButton("Receiver");
        receiver.setBounds(75, 200, 150, 50);
        f.add(receiver);

        f.setSize(300, 400);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}