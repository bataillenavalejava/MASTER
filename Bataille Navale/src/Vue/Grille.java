package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;

import Modèle.Croiseur;
import Modèle.Cuirassé;
import Modèle.Destroyer;
import Modèle.SousMarin;

import Modèle.ABateau;

import java.awt.Dimension;
import java.awt.event.*;
@SuppressWarnings("serial")

public class Grille implements ActionListener {
	
    private static final int SML_SIDE = 15;
    private static final int SIDE = SML_SIDE * SML_SIDE;
    private static final Color BG = Color.BLACK;
    private static final Dimension BTN_PREF_SIZE = new Dimension(2, 2);
    
    private JButton[][] buttons = new JButton[SIDE][SIDE];
    private JButton[][] buttons1 = new JButton[SIDE][SIDE];
    private Dimension ddefault = new Dimension(1300,700);
    private Dimension dmin = new Dimension(1300,700);
	private JPanel panelOne = new JPanel(); 
	private JPanel panelTwo = new JPanel(); 
	private JFrame frame = new JFrame("Bienvenue sur la Bataille Navale ! ");
	
	private ABateau cJoueur = new Cuirassé();
	private ABateau dJoueur = new Destroyer();
	private ABateau sJoueur = new SousMarin();
	private ABateau crJoueur = new Croiseur();
    
    public Grille() {  	  
    	  CreateandShowUI();
    	  AddBateauJoueur();
    	  AddBateauIA();
    }

    private void AddBateauJoueur() {
		// TODO Auto-generated method stub
    	AddABateau(cJoueur, buttons);
    	AddABateau(sJoueur, buttons);
    	AddABateau(dJoueur, buttons);
    	}
    
    private void AddBateauIA() {
		// TODO Auto-generated method stub
    	AddABateau(cJoueur, buttons1);
    	AddABateau(sJoueur, buttons1);
    	AddABateau(dJoueur, buttons1);
    	}

    private void AddABateau(ABateau dJoueur, JButton[][] buttons) {
		// TODO Auto-generated method stub
    	String text = "o";

    	int n = (int)(Math.random() * 2);
    	boolean exist = false;
    	
    	int originx = (int)(Math.random() * SML_SIDE-1) + 1;
    	int originy = (int)(Math.random() * SML_SIDE-1) + 1;
    	
    	if (n % 2 == 0) { 

    		while (originy + dJoueur.getSize() > SML_SIDE || exist == true) { 
    				 for (int i = 1; i < dJoueur.getSize(); i++) { 
     	 				if (buttons[i][originy].getText() == "o") {
     	 					exist = true;
     	 					break;
     	 				}
     	 				else { 
     	 					exist = false;
     	 				}
     	 			}	
    			 originy = (int)(Math.random() * SML_SIDE-1) + 1;
        	}
    		
    		for(int j = originy; j<dJoueur.getSize()+originy;j++) { 
    			System.out.println("ORIGIN Y : " + originy);
    			System.out.println("NOM : " + dJoueur.getNom());
        		buttons[originy][j].setText(text);
        		buttons[originy][j].setForeground(Color.MAGENTA);
        		panelOne.revalidate(); 
        		frame.repaint();//repaint a JFrame jframe in this case
    		}
    	}
    	 else { 
    		while (originx + dJoueur.getSize() > SML_SIDE || exist == true) {  		
    			 for (int i = 1; i < dJoueur.getSize(); i++) { 
    	 				if (buttons[i][originx].getText() == "o") {
    	 					exist = true;
    	 					break;
    	 				}
    	 				else { 
    	 					exist = false;
    	 				}
    	 			}
       			originx = (int)(Math.random() * SML_SIDE-1) + 1;
    		}

    		for(int j = originx; j<dJoueur.getSize()+originx;j++) { 
    			System.out.println("ORIGIN X : " + originx);
    			System.out.println("NOM : " + dJoueur.getNom());
        		buttons[j][originx].setText(text);
        		buttons[j][originx].setForeground(Color.MAGENTA);
        		panelOne.revalidate(); 
        		frame.repaint();//repaint a JFrame jframe in this case
    		}
    	}
	}


	private void CreateandShowUI() {
    
  	  frame.setBackground(BG);

        panelOne.setLayout(new GridLayout(SML_SIDE,SML_SIDE));
        panelTwo.setLayout(new GridLayout(SML_SIDE,SML_SIDE));
        
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {	
                String text = "x";
          	  buttons[i][j] = new JButton(text);
                buttons[i][j].setPreferredSize(BTN_PREF_SIZE);
                buttons[i][j].setMinimumSize(BTN_PREF_SIZE);
                buttons[i][j].setMaximumSize(BTN_PREF_SIZE);
                buttons[i][j].setBackground(Color.CYAN);
                buttons[i][j].setOpaque(true);
                buttons[i][j].setBorderPainted(false);
                panelOne.add(buttons[i][j]);
            }
        }
        
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {	
                String text = "x";
          	  buttons1[i][j] = new JButton(text);
                buttons1[i][j].setPreferredSize(BTN_PREF_SIZE);
                buttons1[i][j].setMinimumSize(BTN_PREF_SIZE);
                buttons1[i][j].setMaximumSize(BTN_PREF_SIZE);
                buttons1[i][j].setBackground(Color.CYAN);
                buttons1[i][j].setOpaque(true);
                buttons1[i][j].setBorderPainted(false);
                panelTwo.add(buttons1[i][j]);
            }
        }
        
        String title = "Votre flotte !";
        Border border = BorderFactory.createTitledBorder(title);
        panelOne.setBorder(border);
        String title2 = "La flotte adverse.";
        Border border2 = BorderFactory.createTitledBorder(title2);
        panelTwo.setBorder(border2);
        
        panelOne.setPreferredSize(new Dimension(650,350));
        panelTwo.setPreferredSize(new Dimension(650,350));
        frame.setLayout(new BorderLayout());
        
        
        frame.add( panelOne, BorderLayout.WEST );
        frame.add( panelTwo, BorderLayout.EAST );
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(ddefault);
        frame.setMinimumSize(dmin);
        frame.setVisible(true);
        frame.pack();
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

   
}
