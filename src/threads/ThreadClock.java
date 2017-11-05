/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Carlos
 */
public class ThreadClock extends Thread {

    NumberFormat formatter = new DecimalFormat("00");
    private JLabel label;
    private JTextArea textArea;
    private ThreadSave saver;
    private final AtomicBoolean timeExpired = new AtomicBoolean(false);
    private int min;
    private int seg;

    public ThreadClock(String name, JLabel label, JTextArea textArea, ThreadSave saver) {
        super(name);
        this.label = label;
        this.textArea = textArea;
        this.saver = saver;
        initialize();
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run() {
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Timer started");
        try {
            timeExpired.set(false);
            while (!timeExpired.get()) {
                Thread.sleep(1000);
                decrement();
                display();
            }
            label.setText("Time expired!");
            label.setForeground(new Color(255, 0, 0));
            textArea.setEnabled(false);

        } catch (InterruptedException e) {
            System.out.println("Thread \"" + this.getName() + "\" interrupted.");
        }
        System.out.println("Thread \"" + this.getName() + "\" finished.");
        saver.interrupt();
    }

    private void display() {
        label.setText(String.valueOf(formatter.format(min)) + ":" + String.valueOf(formatter.format(seg)));
    }

    private void decrement() {
        if (seg == 0) {
            if (min == 0) {
                timeExpired.set(true);
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Timer finished.");
            } else {
                seg = 59;
                min--;
            }
        } else {
            seg--;
        }
    }

    private void initialize() {
        seg = 10;
        min = 0;
        display();
    }

}
