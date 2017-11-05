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
public class ThreadSave extends Thread {

    private JTextArea textArea;

    public ThreadSave(String nombre, JTextArea textArea) {
        super(nombre);
        this.textArea = textArea;
        setPriority(MIN_PRIORITY);
        this.start();
    }

    public void run() {
        FileManager manager = new FileManager();
        try {
            while (true) {
                Thread.sleep(120000); // 2 min
                manager.writeToFile(textArea.getText());
            }
        } catch (InterruptedException e) {
            System.out.println("Thread \"" + this.getName() + "\" interrupted.");
        }
        System.out.println("Thread \"" + this.getName() + "\" finished.");
    }
}
