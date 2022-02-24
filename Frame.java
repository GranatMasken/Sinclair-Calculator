package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Frame extends JFrame{

	public static int WIDTH = 600, HEIGHT = 500;
	
	//gender 1=male 2=female
	public static int g;
	
	//bw
	public static int bw;
	
	//total
	public static int t;
	
	//IWF Variables
	
	public static double A, b, X, sc;
	
	
	
	//COMPONENTS
	
	//gender radio buttons
	JRadioButton r1;
	JRadioButton r2;
	
	//kroppsvikt
	JTextField bwTextField;
	
	//total
	JTextField tTextField;

	//knapp
	JButton button;
	
	//resultat
	JLabel result;
	
	
	
	Frame(){
		this.setTitle("Sinclair Calculator");
		this.setSize(WIDTH,HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setLayout(null);
		
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		
		//Fonter
		Font font = new Font("Helvetica", Font.PLAIN, 30);
		Font font1 = new Font("Helvetica", Font.PLAIN, 20);

		
		
		//Rubrik
		JLabel label = new JLabel("Sinclair Calculator", SwingConstants.CENTER);
		label.setFont(font);
		label.setBounds(0, 0, WIDTH, 100);
		this.add(label);
		
		
		
		
		// RadioButtons för kön
		r1=new JRadioButton("Male");    
		r2=new JRadioButton("Female");    
		r1.setBounds(200,75,100,30);    
		r2.setBounds(300,75,100,30);    
		ButtonGroup bg=new ButtonGroup();    
		bg.add(r1);bg.add(r2);   
		r1.setFont(font1);
		r2.setFont(font1);
		this.add(r1); this.add(r2);

		
		
		
		//Kroppsvikt
		JLabel bwLabel = new JLabel("Body Weight", SwingConstants.LEFT);
		bwLabel.setBounds(185, 125, 150, 40);
		bwLabel.setFont(font1);
		
		
		bwTextField = new JTextField("");
		bwTextField.setBounds(310,125,100,40);
		
		this.add(bwLabel);
		this.add(bwTextField);
		
		//Total vikt
		JLabel tLabel = new JLabel("Total", SwingConstants.LEFT);
		tLabel.setBounds(185, 175, 150, 40);
		tLabel.setFont(font1);
		
		
		tTextField = new JTextField("");
		tTextField.setBounds(310,175,100,40);
		
		this.add(tLabel);
		this.add(tTextField);
	
	
		//uträknings knapp
		button = new JButton("calculate");
		
		button.setBounds(200, 250, 200, 100);
		
		this.add(button);
		
		knappSetup();

		
		//TEXT
		result = new JLabel("Your sinclair is: ", 0);
		result.setFont(font1);
		result.setBounds(0, 375, WIDTH, 100);
		this.add(result);
		
		
		
		//this.pack();
		
		
		this.setVisible(true);
		
		
	}
	
	public void getValues() {		
		//gender
		if(r1.isSelected()) g =1;
		else if(r2.isSelected()) g = 2;
		
		//bw
		bw = Integer.parseInt(bwTextField.getText());
		
		//total
		t = Integer.parseInt(tTextField.getText());
		
	}
	
	public void calculate() {
		
		if (g==1) {A = 0.751945030; b = 175.508;}
		else if (g==2) {A = 0.783497476; b = 153.655;}
		
		X = Math.log10(bw/b);
		
		if(bw <= b) sc = Math.pow(10, A*Math.pow(X, 2));
		else if(bw > b) sc = 1;
		
	}
	
	public void showResult() {

		double points = Math.round(sc*t*100);
		
		points = points / 100;
		
		result.setText("Your sinclair is: " + points);
		
	}
	
	public void knappSetup() {
	      button.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	try {
	        		getValues();
	        		calculate();
	        		showResult();
	        		System.out.println("knapp");
	        	}catch(Exception e1) {
	        		error();
	        	}

	          } 
	      });
	}
	
	public void error() {
		JOptionPane.showMessageDialog(this, "An error occured while trying to calculate,\ncheck that everything is entered correctly"
				, "Calculationg problem", JOptionPane.ERROR_MESSAGE);
	}
}
