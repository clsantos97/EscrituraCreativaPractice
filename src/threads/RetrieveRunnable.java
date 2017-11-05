/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import dao.FileManager;
import javax.swing.JTextArea;

/**
 *
 * @author Carlos
 */
public class RetrieveRunnable implements Runnable{

    private JTextArea textArea;

    public RetrieveRunnable(JTextArea textArea) {
        this.textArea = textArea;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        FileManager manager = new FileManager();
        textArea.setText(manager.readFromFile());
        textArea.setWrapStyleWord(true);
    }
}
