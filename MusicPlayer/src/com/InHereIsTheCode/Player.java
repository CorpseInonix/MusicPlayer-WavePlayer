//Not the best code ever but it gets the job done

package com.InHereIsTheCode;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Player extends JFrame {
	
	JButton replay;
	JButton stop;
	JButton play;
	JButton select;
	Label l;
	JButton[] buttons;
	ImageIcon image;
	JLabel displayField;
	File file = null;
	Clip clip;
	Timer timer;
	
	public static void main(String args[]) {
		
		new Player();
		
	}
	
	Player() {
		
		try {
			
			image = new ImageIcon(getClass().getResource("wav.jpg"));
			displayField = new JLabel(image);
			displayField.setBounds(70, 60, 200, 200);
			
		} catch(Exception e) { displayField = new JLabel(); }
		
		setSize(350, 400);
		setTitle("Player");
		setLayout(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		add(panel());
		add(l);
		add(displayField);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	JPanel panel() {
		
		JPanel panel = new JPanel();
		l = new Label("Select a .wav file using \"Select\" and press \"Play\"");
		buttons = new JButton[4];
		panel.setBounds(18, 290, 300, 50);
		panel.setLayout(new FlowLayout());
		
		l.setFont(new Font("Ariel", Font.PLAIN, 15));
		l.setBounds(8, 10, 350, 30);
		
		play = new JButton("Play");
		select = new JButton("Select");
		stop = new JButton("Stop");
		replay = new JButton("Replay");
		
		buttons[0] = play;
		buttons[1] = select;
		buttons[2] = stop;
		buttons[3] = replay;
		
		for(int i = 0; i < 4; i++) {
			
			JButton b = buttons[i];
			b.setBackground(Color.white);
		    b.setFocusable(false);
		    b.addActionListener(new ButtonListener());
		    panel.add(b);
			
		}
		
		return panel;
		
	}
	
	void replay() {
		
		if(file != null) {
			
			if(clip != null) {
				
				clip.stop();
				clip = null;
				
			}
			
			try {
				
				AudioInputStream in = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(in);
				clip.start();
				
			} catch(Exception e) { }
			
		}
		
	}
	
	void getFile() {
		
		try {
			
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(this);
			file = chooser.getSelectedFile();
			
		} catch(Exception ex) { }
		
	}
	
	public class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == select)
				getFile();
			
			if(e.getSource() == play)
				if(clip != null)
					clip.start();
				else
					replay();
			
			if(e.getSource() == stop)
				if(clip != null)
					clip.stop();
			
			if(e.getSource() == replay)
				replay();
			
		}
		
	}

}
