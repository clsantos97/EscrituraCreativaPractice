/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Carlos
 */
public class ThreadBlinker extends Thread{
    private ThreadClock clock;
	private JLabel label;

	public ThreadBlinker(String name, ThreadClock clock, JLabel label) {
		super(name);
		this.clock = clock;
		this.label = label;
		this.start();
	}

	public void run() {
		try {
			clock.join();
			while (true) {
				label.setForeground(new Color(255, 0, 0));
				Thread.sleep(500);
				label.setForeground(new Color(0, 0, 0));
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread \"" + this.getName() + "\" interrupted.");
		}
		System.out.println("Thread \"" + this.getName() + "\" finished.");
	}
}
