/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import dao.FileManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Carlos
 */
public class SaveRunnable implements Runnable {

    private JTextArea textArea;

    public SaveRunnable(JTextArea textArea) {
        this.textArea = textArea;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        FileManager manager = new FileManager();
        manager.writeToFile(textArea.getText());
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Data saved.");
    }
}
