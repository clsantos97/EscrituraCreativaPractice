package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import threads.RetrieveRunnable;
import threads.SaveRunnable;
import threads.ThreadBlinker;
import threads.ThreadClock;
import threads.ThreadSave;

public class AppManager {

    private ThreadSave saver;
    private ThreadClock clock;
    private ThreadBlinker blinker;

    // TODO
    public void startCountdown(JTextArea textArea, JLabel lblTime) {
        saver = new ThreadSave("Save text thread", textArea);
        clock = new ThreadClock("Clock thread", lblTime, textArea, saver);
        blinker = new ThreadBlinker("Blinker", clock, lblTime);
    }

    public void exit() {
        if (saver.isAlive()) {
            saver.interrupt();
        }
        if (clock.isAlive()) {
            clock.interrupt();
        }
        if (blinker.isAlive()) {
            blinker.interrupt();
        }
    }

    public void saveText(JTextArea textArea) {
        new SaveRunnable(textArea);
    }

    public void getText(JTextArea textArea) {
        new RetrieveRunnable(textArea);
    }
}
