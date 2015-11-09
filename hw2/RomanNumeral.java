package hw2;

/*
 * Author:    7813
 * Class:     COP 3003
 * Project:   Homework 2
 * Date:      16 October 2015
 * 
 */

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class RomanNumeral extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JTextField arabicField;
	private JTextField romanField;
	private GridLayout gridLayout1;
	
    public static void main(String[] args) {
		
		//Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
        
			public void run() {
            	RomanNumeral romanNumeralFrame = new RomanNumeral();
        		romanNumeralFrame.setVisible(true);
        		romanNumeralFrame.setSize(350,125);
        		romanNumeralFrame.setLocationRelativeTo(null);
        		
            }
        });
		
}
	
	//Constructor
	public RomanNumeral(){
		super("Roman<-->Arabic");
		gridLayout1 = new GridLayout(2,2);
		setLayout(gridLayout1);
		
		//Creates components
		label1 = new JLabel("Arabic Numeral");
		label1.setFont(new Font("Arial", Font.PLAIN, 18));
		label2 = new JLabel("Roman Numeral");
		label2.setFont(new Font("Arial", Font.PLAIN, 18));
		arabicField = new JTextField();
		arabicField.setFont(new Font("Arial", Font.PLAIN, 18));
		romanField = new JTextField();
		romanField.setFont(new Font("Arial", Font.PLAIN, 18));
		
		
		//Adds components to the grid
		add(label1);
		add(arabicField);
		
		add(label2);
		add(romanField);
		
		//KeyListener for the arabic Field
		//Handles all input in the arabic text field
		arabicField.addKeyListener( new KeyAdapter() {
			
			//Anonymous inner class key released
			public void keyReleased(KeyEvent e){
				
				String text = arabicField.getText();
				
				//Variable for parsing string to int
				int result = 0;
				
				try{
					//suppose text is a String variable
				     result = Integer.parseInt(text); 
				     
				     if (result >= 1 && result <= 3999){
				    	 
				    	 //Value that will go in roman text field
				    	 String roman="";
				         int repeat;
				        
				         int magnitude[]={1000,900, 500, 400, 100, 
				        		 90, 50, 40, 10, 9, 5, 4, 1};
				         String symbol[]={"M","CM", "D", "CD", "C", "XC", "L",
				        		 "XL", "X", "IX", "V", "IV", "I"};
				        
				         repeat = result;
				         
				         /*Loop that starts at 0 and looks at the magnitude.
				          *Each number in the magnitude array has a corresponding
				          *string value located in the the symbol array at the same
				          *index.
				          */
				         for(int j = 0; result > 0; j++){
				             repeat = result / magnitude[j];
				             
				             for(int i = 1; i <= repeat; i++){
				                 roman = roman + symbol[j];
				                 
				             }
				             result = result % magnitude[j];
				             romanField.setText(roman);
				             
				         }
				  
				     }
				     else{
				    	 arabicField.setText(null);
						 romanField.setText(null);
				    	 
				     }
				
				}
				// the string contains non-digit characters if the program runs
			     // to here
				catch(NumberFormatException error){
					 arabicField.setText(null);
					 romanField.setText(null);
				     
				}
			
			}
						
		});
	
		//keyListener for the roman text field
		romanField.addKeyListener(new KeyAdapter() {
			
			//Anonymous inner class keyReleased
			public void keyReleased(KeyEvent e){
				
				String result = romanField.getText();
				
				//Makes all characters entered upper case
				String romanNumeral = result.toUpperCase();
				
				//Regular Expressions (regex.Pattern)
				//If characters are not M,C,D,L,X,V,I, input is rejected
				if (!(Pattern.matches("^[MCDLXVI]+$", romanNumeral))) {
					
					arabicField.setText(null);
					romanField.setText(null);
				   
				}
				else{
				
					int decimalNum = 0;
					
					//Gets length of the string entered
					int len =  romanNumeral.length();
					
					int num = 0;
					int previousNum = 0;
					
					//Steps through the string entered by the 
					//user, going backwards
					for (int i = len - 1; i >= 0 ;i--) { 
                        
						char x =  romanNumeral.charAt(i);
						x = Character.toUpperCase(x);
                    
						switch(x){  
                           
							case 'I':
                                previousNum = num;
                                num = 1;
                                break;
                             case 'V':
                                     previousNum = num;
                                num = 5;
                                break;
                             case 'X':
                                        previousNum = num;
                                num = 10;
                                break;
                             case 'L':
                                        previousNum = num;
                                num = 50;
                                break;
                             case 'C':
                                        previousNum = num;
                                num = 100;
                                break;
                             case 'D':
                                        previousNum = num;
                                num = 500;
                                break;
                             case 'M':
                                        previousNum = num;
                                num = 1000;
                                break;
                        }  
                
                        if (num<previousNum)
                        {decimalNum = decimalNum - num;}
                         
                        else
                        decimalNum = decimalNum + num;

			    
                }
				//Turns the int into a string to allow setText to work
                arabicField.setText((String.valueOf(decimalNum)));
                
				}    
			}
		});
	}

}
	