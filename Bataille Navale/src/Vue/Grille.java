package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modele.ABateau;
import modele.AJoueur;
import modele.Croiseur;
import modele.Cuirasse;
import modele.Destroyer;
import modele.SousMarin;

import java.awt.Dimension;
import java.awt.event.*;


public class Grille implements ActionListener, IGrille {
	
 
	private static final int SML_SIDE = 15;
    private static final int SIDE = SML_SIDE * SML_SIDE;
    private static final Color BG = Color.BLACK;
    private static final Dimension BTN_PREF_SIZE = new Dimension(2, 2);
    private boolean caseIsadv = false;
    private JButton[][] buttons = new JButton[SIDE][SIDE];
    private JButton[][] buttons1 = new JButton[SIDE][SIDE];
    private JButton[][] buttons2 = new JButton[SIDE][SIDE];
    private static final Dimension ddefault = new Dimension(1300,700);
    private static final Dimension dmin = new Dimension(1300,700);
	private JPanel panelOne = new JPanel(); 
	private JPanel panelTwo = new JPanel(); 
	private JPanel paneldummy = new JPanel(); 
	private JInternalFrame  frame = new JInternalFrame("Bienvenue sur la Bataille Navale ! ");
	
	private ABateau cJoueur;
	private ABateau dJoueur;
	private ABateau sJoueur;
	private ABateau crJoueur;
    
	private ABateau cIA;
	private ABateau dIA;
	private ABateau sIA;
	private ABateau crIA;
	private String caseclicked;
	private final Object SharedObject;
	private ABateau currentboat;
	
    public Grille(Object obj) {  	  
    	this.SharedObject = obj;
    	 cJoueur = new Cuirasse(SharedObject);
    	 dJoueur = new Destroyer(SharedObject);
    	 sJoueur = new SousMarin(SharedObject);
    	 crJoueur = new Croiseur(SharedObject);
        
    	 cIA = new Cuirasse(SharedObject);
    	dIA = new Destroyer(SharedObject);
    	sIA = new SousMarin(SharedObject);
    	 crIA = new Croiseur(SharedObject);
    }

    protected void AddBateauJoueur() {
		// TODO Auto-generated method stub
    	AddABateau(cJoueur, buttons);
    	AddABateau(sJoueur, buttons);
    	AddABateau(dJoueur, buttons);
    	AddABateau(crJoueur, buttons);
    	}

	protected void AddBateauIA() {
		// TODO Auto-generated method stub
    	AddABateau(cIA, buttons1);
    	AddABateau(dIA, buttons1);
    	AddABateau(sIA, buttons1);
    	AddABateau(crIA, buttons1);
    	}

    private void AddABateau(ABateau dJoueur, JButton[][] buttons) {
		// TODO Auto-generated method stub

    	int n = (int)(Math.random() * 2);
    	boolean exist = false;
    	
    	int originx = (int)(Math.random() * SML_SIDE-1) + 1;
    	int originy = (int)(Math.random() * SML_SIDE-1) + 1;
    	
    	if (n % 2 == 0) { 
    		exist = true;
    		while (originy + dJoueur.getSize() > SML_SIDE || exist == true ) { 
   			 		originy = (int)(Math.random() * SML_SIDE-1) + 1;
   			 		if (originy + dJoueur.getSize() <= SML_SIDE) {
    				 for (int i = originy; i < dJoueur.getSize() + originy; i++) { 
     	 				if (buttons[originx][i].getText() != "-") {
     	 					exist = true;
     	 					break;
     	 				}
     	 				else { 
     	 					exist = false;
     	 				}
     	 			}	
   			 	}
        	}
    	//System.out.println("ORIGIN Y : " + originy);
		//System.out.println("NOM : " + dJoueur.getNom());
    		for(int j = originy; j<dJoueur.getSize()+originy;j++) { 
    			
        		buttons[originx][j].setText(String.valueOf(dJoueur.getNom().charAt(0)).toLowerCase());
        		buttons[originx][j].setForeground(Color.MAGENTA);
        		panelOne.revalidate(); 
        		frame.repaint();//repaint a JFrame jframe in this case
    		}
    	}
    	 else { 
    		exist = true;
    		while (originx + dJoueur.getSize() > SML_SIDE || exist == true) {  		
       			originx = (int)(Math.random() * SML_SIDE-1) + 1;
       			if (originx + dJoueur.getSize() <= SML_SIDE) { 
       				for (int y = originx; y < dJoueur.getSize()+originx; y++) { 
    	 				if (buttons[y][originy].getText() != "-") {
    	 					exist = true;
    	 					break;
    	 				}
    	 				else { 
    	 					exist = false;
    	 				}
    	 			}
       			} 			 
    		}
	    	//System.out.println("ORIGIN X : " + originx);
			//System.out.println("NOM : " + dJoueur.getNom());
    		for(int j = originx; j<dJoueur.getSize()+originx;j++) { 
    			
        		buttons[j][originy].setText(String.valueOf(dJoueur.getNom().charAt(0)).toLowerCase());
        		buttons[j][originy].setForeground(Color.MAGENTA);
        		panelOne.revalidate(); 
        		frame.repaint();//repaint a JFrame jframe in this case
    		}
    	}
	}

	protected void CreateandShowUI() {
    
  	  frame.setBackground(BG);

        panelOne.setLayout(new GridLayout(SML_SIDE,SML_SIDE));
        panelTwo.setLayout(new GridLayout(SML_SIDE,SML_SIDE));
        paneldummy.setLayout(new GridLayout(SML_SIDE,SML_SIDE));
        
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {	
                String text = "-";
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
                String text = "-";
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
        
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {	
                String text = "-";
          	    buttons2[i][j] = new JButton(text);
                buttons2[i][j].setPreferredSize(BTN_PREF_SIZE);
                buttons2[i][j].setMinimumSize(BTN_PREF_SIZE);
                buttons2[i][j].setMaximumSize(BTN_PREF_SIZE);
                buttons2[i][j].setBackground(Color.CYAN);
                buttons2[i][j].setOpaque(true);
                buttons2[i][j].setBorderPainted(false);
                paneldummy.add(buttons2[i][j]);
            }
        }
        String title = "Votre flotte !";
        Border border = BorderFactory.createTitledBorder(title);
        panelOne.setBorder(border);
        String title2 = "La flotte adverse.";
        Border border2 = BorderFactory.createTitledBorder(title2);
        paneldummy.setBorder(border2);
        
        panelOne.setPreferredSize(new Dimension(650,350));
        paneldummy.setPreferredSize(new Dimension(650,350));
        frame.setLayout(new BorderLayout());
        
        
        frame.add( panelOne, BorderLayout.WEST );
        frame.add( paneldummy, BorderLayout.EAST );
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ddefault);
        frame.setMinimumSize(dmin);
        frame.setVisible(true);
        frame.pack();
        
     // Create the action listener to add to your buttons.
        ActionListener actionListener = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            // Execute whatever should happen on button click.
        	  setCaseclicked(((JButton) e.getSource()).getName());
              setCaseIsadv(false);
        	  synchronized(SharedObject){
          	      SharedObject.notifyAll();
          	 }
          }
        };
        
        for(int i = 1; i < SML_SIDE+1; i++) {
        	 for(int j = 1; j < SML_SIDE+1; j++) {
        		 buttons[i][j].setName(1 + "/" + Integer.toString(i) + "/"+Integer.toString(j));
        		 buttons[i][j].addActionListener(actionListener);
                }
        	}
        
        for(int i = 1; i < SML_SIDE+1; i++) {
       	 for(int j = 1; j < SML_SIDE+1; j++) {
       		 buttons1[i][j].setName(2 + "/" + Integer.toString(i) + "/"+Integer.toString(j));
       		 buttons1[i][j].addActionListener(actionListener);
               }
       	}
        
        for(int i = 1; i < SML_SIDE+1; i++) {
          	 for(int j = 1; j < SML_SIDE+1; j++) {
          		 buttons2[i][j].setName(2 + "/" + Integer.toString(i) + "/"+Integer.toString(j));
          		 buttons2[i][j].addActionListener(actionListener);
                  }
          	}
        
        }
    
	@Override
	public void Tir() {
  		int grillex = 0;
		int grilley = 0;
		boolean moved = false;
		setCaseIsadv(false);
		while(this.isCaseIsadv() == false) {
			Thread t = new Thread("WaitForTir");
			synchronized(SharedObject){
	     	t.start();
	         try{
	             SharedObject.wait();
	         }catch(InterruptedException e){
	             e.printStackTrace();
	         }
	        try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
  		if(getCaseclicked() != null) { 
  			System.out.println("Case cliqué : " + getCaseclicked());
  			String[] separated = getCaseclicked().split("/");
  			String grillenumber = separated[0];
  			grillex = Integer.valueOf(separated[1]);
  			grilley = Integer.valueOf(separated[2]);
  			if (Integer.valueOf(grillenumber) == 1 && (getButtons()[grillex][grilley].getText() == "-" || getButtons()[grillex][grilley].getText() == "*")) {
  			    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas tirer sur vos alliés ! ");
  			}
  			else if (Integer.valueOf(grillenumber) == 1 && getButtons()[grillex][grilley].getText().contains("c") && !moved && getCurrentboat().getNom() == "Cruiser") {
  				checkPossibleMove(currentboat,"c");
  				moved = true;
  			}
  			else if (Integer.valueOf(grillenumber) == 1 && getButtons()[grillex][grilley].getText().contains("d") && !moved && getCurrentboat().getNom() == "Destroyer") {
  				checkPossibleMove(getCurrentboat(),"d");
  				moved = true;
  			}
  			else if (Integer.valueOf(grillenumber) == 1 && getButtons()[grillex][grilley].getText().contains("b") && !moved && getCurrentboat().getNom() == "Battleship") {
  				checkPossibleMove(getCurrentboat(),"b");
  				moved = true;
  			}
  			else if (Integer.valueOf(grillenumber) == 1 && getButtons()[grillex][grilley].getText().contains("s") && !moved && getCurrentboat().getNom() == "Submarine") {
  				checkPossibleMove(getCurrentboat(),"s");
  				moved = true;
  			}
  			else if (Integer.valueOf(grillenumber) == 2 && getButtons1()[grillex][grilley].getForeground().equals(Color.red)) {
  				 JOptionPane.showMessageDialog(null, "Vous avez déjà tiré ici ! ");
  			}
  			else if (Integer.valueOf(grillenumber) == 1 && !(getButtons()[grillex][grilley].getText().charAt(0) == getCurrentboat().getNom().toLowerCase().charAt(0)) && !moved) {
  				JOptionPane.showMessageDialog(null, "Vous ne pouvez bouger le bateau que vous contrôler ! ");
  			}
  			else {
  				setCaseIsadv(true);
  			}
  		}
  		}
  			if (getButtons1()[grillex][grilley].getText().contains("-")) { 
  				setbutton1(grillex,grilley,"*", Color.red);
  				setbuttondummy(grillex,grilley,"*", Color.red);
  			}
  			else {
  				setbutton1(grillex,grilley,getButtons1()[grillex][grilley].getText(), Color.red);
  				setbuttondummy(grillex,grilley,getButtons1()[grillex][grilley].getText(), Color.red);
  				}
	}
	
	@Override
	public void TirFusee() {
		int grillex = 0;
		int grilley = 0;
		while(isCaseIsadv() == false) {
		Thread t = new Thread("WaitForTir");
		synchronized(SharedObject){
     	t.start();
         try{
             SharedObject.wait();
         }catch(InterruptedException e){
             e.printStackTrace();
         }
        try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		System.out.println("Case cliqué : " + getCaseclicked());
		String[] separated = getCaseclicked().split("/");
		String grillenumber = separated[0];
		grillex = Integer.valueOf(separated[1]);
		grilley = Integer.valueOf(separated[2]);
		if (Integer.valueOf(grillenumber) == 1) {
		    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas tirer sur vos alliés ! ");
		}
		else {
			caseIsadv = true;
		}
	}
	for(int i = grillex-2; i <= grillex+2; i++) {
		for(int y = grilley-2; y <= grilley+2; y++) {
			if(i > 0 && i < 15 && y > 0 && y < 15) {
				if(getButtons1()[i][y].getText() == "-") { 
					setbuttondummy(i, y, "*", Color.MAGENTA);
				}
				else { 
					setbuttondummy(i, y, getButtons1()[i][y].getText(), Color.MAGENTA);
				}
		}
	 }
	}
	}
	
	@Override
	public void TirRandom() {
		int grillex = 0;
		int grilley = 0;
		int ShotOrMove = (int) (Math.random() * 15) + 1;
		grillex = (int) (Math.random() * 15) + 1;
		grilley = (int) (Math.random() * 15) + 1;
		if(ShotOrMove<15) {
		while(getButtons()[grillex][grilley].getForeground().equals(Color.red)) { 
			System.out.println("L'IA tir !");
			grillex = (int) (Math.random() * 15) + 1;
			grilley = (int) (Math.random() * 15) + 1;
		}
		if (getButtons()[grillex][grilley].getText().contentEquals("-")) { 
			setbutton(grillex,grilley,"*", Color.red);
		}
		else {
			setbutton(grillex,grilley,getButtons()[grillex][grilley].getText(), Color.red);
		}
		} else { 
			System.out.println("L'IA déplace un de ses bateau ...");
			while(getButtons1()[grillex][grilley].getText().equals("-") || getButtons()[grillex][grilley].getText().equals("*")) { 
				grillex = (int) (Math.random() * 15) + 1;
				grilley = (int) (Math.random() * 15) + 1;
			 }
			
			if (getButtons1()[grillex][grilley].getText().contains("s") && getCurrentboat().getNom() == "Submarine") {
				checkPossibleMoveRandom(getCurrentboat(),"s");
			}
			else if (getButtons1()[grillex][grilley].getText().contains("c") && getCurrentboat().getNom() == "Cruiser") {
				checkPossibleMoveRandom(getCurrentboat(),"c");
			}
			else if (getButtons1()[grillex][grilley].getText().contains("d") && getCurrentboat().getNom() == "Destroyer") {
				checkPossibleMoveRandom(getCurrentboat(),"d");
			}
			else if (getButtons1()[grillex][grilley].getText().contains("b") && getCurrentboat().getNom() == "Battleship") {
				checkPossibleMoveRandom(getCurrentboat(),"b");
			}

		}
	}
	
	@Override
	public void TirFuseeRandom() { 
		int grillex = 0;
		int grilley = 0;
		grillex = (int) (Math.random() * 15) + 1;
		grilley = (int) (Math.random() * 15) + 1;
		for(int i = grillex-2; i <= grillex+2; i++) {
			for(int y = grilley-2; y <= grilley+2; y++) {
				if(i > 0 && i < 15 && y > 0 && y < 15) {
					if(getButtons()[i][y].getText() == "-") { 
						setbutton(i, y, "*", Color.MAGENTA);
					}
					else { 
						setbutton(i, y, getButtons()[i][y].getText(), Color.MAGENTA);
					}
			}
		 }
		}
	}
	
	public Component getJframe() {
		// TODO Auto-generated method stub
		return this.frame;
	}

	   @Override
	public ABateau getcJoueur() {
			return cJoueur;
		}

		@Override
		public ABateau getdJoueur() {
			return dJoueur;
		}

		@Override
		public ABateau getsJoueur() {
			return sJoueur;
		}

		@Override
		public ABateau getCrJoueur() {
			return crJoueur;
		}

		@Override
		public ABateau getcIA() {
			return cIA;
		}
		
		@Override
		public ABateau getdIA() {
			return dIA;
		}

		@Override
		public ABateau getsIA() {
			return sIA;
		}

		@Override
		public ABateau getCrIA() {
			return crIA;
		}


		@Override
		public String getCaseclicked() {
			return caseclicked;
		}
	    
	    @Override
		public void setCaseclicked(String caseclicked) {
			this.caseclicked = caseclicked;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
		
		@Override
		public JButton[][] getButtons() {
			return buttons;
		}

		@Override
		public JButton[][] getButtons1() {
			return buttons1;
		}
		
		@Override
		public JButton[][] getButtonsdummy() {
			return buttons2;
		}

		@Override
		public void setbutton(int i, int j, String string, Color color) {
			// TODO Auto-generated method stub
			buttons[i][j].setText(string);
    		buttons[i][j].setForeground(color);
    		panelOne.revalidate(); 
    		frame.repaint();//repaint a JFrame jframe in this case
		}
		
		@Override
		public void setbutton1(int i, int j, String string, Color color) {
			// TODO Auto-generated method stub
			buttons1[i][j].setText(string);
			buttons1[i][j].setForeground(color);
    		panelTwo.revalidate(); 
    		frame.repaint();//repaint a JFrame jframe in this case
		}
		
		@Override
		public void setbuttondummy(int i, int j, String string, Color color) {
			// TODO Auto-generated method stub
			buttons2[i][j].setText(string);
			buttons2[i][j].setForeground(color);
    		paneldummy.revalidate(); 
    		frame.repaint();//repaint a JFrame jframe in this case
		}

		@Override
		public void checkAliveBateau() {
			// TODO Auto-generated method stub
        	sJoueur.setIsAlive(false);
    		cJoueur.setIsAlive(false);
    		dJoueur.setIsAlive(false);
    		crJoueur.setIsAlive(false);
    		sIA.setIsAlive(false);
    		cIA.setIsAlive(false);
    		dIA.setIsAlive(false);
    		crIA.setIsAlive(false);
			 for (int i = 1; i < 16; i++) {
		            for (int j = 1; j < 16; j++) {	   
		            	if(buttons[i][j].getText().contains("s") && buttons[i][j].getForeground() != Color.RED) { 
		            		sJoueur.setIsAlive(true);
		            	}
		            	if(buttons[i][j].getText().contains("b") && buttons[i][j].getForeground() != Color.RED) { 
		            		cJoueur.setIsAlive(true);
		            	}
		            	if(buttons[i][j].getText().contains("d") && buttons[i][j].getForeground() != Color.RED) { 
		            		dJoueur.setIsAlive(true);
		            	}
		            	if(buttons[i][j].getText().contains("c") && buttons[i][j].getForeground() != Color.RED) { 
		            		crJoueur.setIsAlive(true);
		            	}
		            	if(buttons1[i][j].getText().contains("s") && buttons[i][j].getForeground() != Color.RED) { 
		            		sIA.setIsAlive(true);
		            	}
		            	if(buttons1[i][j].getText().contains("b") && buttons[i][j].getForeground() != Color.RED) { 
		            		cIA.setIsAlive(true);
		            	}
		            	if(buttons1[i][j].getText().contains("d") && buttons[i][j].getForeground() != Color.RED) { 
		            		dIA.setIsAlive(true);
		            	}
		            	if(buttons1[i][j].getText().contains("c") && buttons[i][j].getForeground() != Color.RED) { 
		            		crIA.setIsAlive(true);
		            	}
		            }       
		        }
		}

		@Override
		public void setWin(AJoueur j) {
			// TODO Auto-generated method stub
			if (j.getName() == "IA") {
		    	JOptionPane.showMessageDialog(null, "L'IA a gagné la partie ! ");
			} 
			else { 
		    	JOptionPane.showMessageDialog(null, "Vous avez gagné la partie !");
			}


		}

		@Override
		public void checkPossibleMove(ABateau aBateau, String string) {
			// TODO Auto-generated method stub
			boolean moveB = true;
			 for (int i = 1; i < 16; i++) {
		            for (int j = 1; j < 16; j++) {
			            	if(buttons[i][j].getText().contains(string) && buttons[i][j].getForeground() == Color.red) { 
				            		moveB= false;
			            }
		            }       
		        }
			 if(moveB) { 
				 MoveBoat(aBateau,string);
			 } else { 
				 JOptionPane.showMessageDialog(null, "Votre Bateau est touché ! Vous ne pouvez plus bouger. ");
			 }
		}

		private void MoveBoat(ABateau aBateau, String s) {
			boolean moveBoat = true;
			// TODO Auto-generated method stub
			int x = 0,y = 0;
	    	int response;
	    	boolean test1 = false;
	    	boolean test2 = false;
			//Find the first occurence of the string to get the boat position
	    	outerloop:
			for (int i = 1; i < 16; i++) {
		            for (int j = 1; j < 16; j++) {	  
		            	if(buttons[i][j].getText().contains(s)) { 
		            		x=i;
		            		y=j;
		            		break outerloop;
		            	}
		            }
		         }
			boolean down = false,right = false; 
			if(buttons[x][y+1] != null) { 
				 if(!buttons[x][y+1].getText().contains(s)) { 
					 down = true;
				 } else { 
					 right = true;
				 }
			}
			boolean possiblemove = false;
			while(!possiblemove) { 
				
				// In case the boat is vertical
				if(down) {
				    String[] options = new String[] {"Bas", "Haut"};
				     response = JOptionPane.showOptionDialog(null, "Votre "+ aBateau.getNom()+" est en mouvement ! Où voulez-vous le déplacer ?", "Amiral, Déplacez votre navire !",
					        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					        null, options, options[0]);
				     	
						if(response == 0) {		
							if(buttons[x+aBateau.getSize()][y] != null) {
								   if((!buttons[x+aBateau.getSize()][y].getText().contains("-") && !buttons[x+aBateau.getSize()][y].getText().contains("*")) || buttons[x+aBateau.getSize()][y].getForeground() == Color.red ) { 
										moveBoat = false;
										JOptionPane.showMessageDialog(null, "Déplacement impossible ! Essayez-en un autre. ");
										test1 = true;
										break;
									}
									else {
										moveBoat = true;
									}
								
								if(moveBoat) {
									JOptionPane.showMessageDialog(null, "DEPLACEMENT EN COURS ");
									for(int j = x; j < aBateau.getSize()+x; j++ ) { 
										buttons[j+1][y].setText(buttons[x][y].getText());
										buttons[j+1][y].setForeground(Color.MAGENTA);
									}
										
									buttons[x][y].setText("-");
									buttons[x][y].setForeground(Color.black);
									possiblemove = true;
									aBateau.setABouger(true);
						    		panelOne.revalidate(); 
						    		frame.repaint();//repaint a JFrame jframe in this case
								} 
							} else { 
								JOptionPane.showMessageDialog(null, "Vous ne pouvez partir du champ de bataille ! ");
								test1 = true;
							}
							
							} else if (response == 1) {
								if(buttons[x-1][y] != null) { 
									if((!buttons[x-1][y].getText().contains("-") && !buttons[x-1][y].getText().contains("*")) || buttons[x-1][y].getForeground() == Color.red) { 
											moveBoat = false;
											JOptionPane.showMessageDialog(null, "Déplacement impossible ! Essayez-en un autre. ");
											test2 = true;
											break;
										}
										else {
											moveBoat = true;
										}
									
									if(moveBoat) {
										JOptionPane.showMessageDialog(null, "DEPLACEMENT EN COURS ");
										for(int j = x-1; j < aBateau.getSize()+x; j++ ) { 
											buttons[j][y].setText(buttons[x][y].getText());
											buttons[j][y].setForeground(buttons[j+1][y].getForeground());
										}
											
										buttons[x+aBateau.getSize()-1][y].setText("-");
										buttons[x+aBateau.getSize()-1][y].setForeground(Color.black);
										possiblemove = true;
										aBateau.setABouger(true);
							    		panelOne.revalidate(); 
							    		frame.repaint();//repaint a JFrame jframe in this case
									} 
								} else { 
									JOptionPane.showMessageDialog(null, "Déplacement impossible ! Essayez-en un autre. ");
									test2 = true;
								}
						} 
						
						 if(test1 && test2) { 
							 possiblemove = true;
							 JOptionPane.showMessageDialog(null, "Aucun déplacement possible, veuillez tirer avec ce bateau");
						 }
			    	
						
				    }
				
				
				// In case the boat is horizontal
				if(right) {
				    String[] options = new String[] {"Gauche", "Droite"};
				     response = JOptionPane.showOptionDialog(null, "Votre "+ aBateau.getNom()+" est en mouvement ! Où voulez-vous le déplacer ?", "Amiral, Déplacez votre navire !",
					        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					        null, options, options[0]);
						if(response == 0) {		
							 if(buttons[x][y-1] != null) {
								   if((!buttons[x][y-1].getText().contains("-") && !buttons[x][y-1].getText().contains("*")) || buttons[x][y-1].getForeground() == Color.red) { 
										moveBoat = false;
										JOptionPane.showMessageDialog(null, "Déplacement impossible ! Essayez-en un autre. ");
										test1 = true;
										break;
									}
									else {
										moveBoat = true;
									}
								
								if(moveBoat) {
									JOptionPane.showMessageDialog(null, "DEPLACEMENT EN COURS ");
									for(int j = y-1; j < aBateau.getSize()+y; j++ ) { 
										buttons[x][j].setText(buttons[x][j+1].getText());
										buttons[x][j].setForeground(Color.MAGENTA);
									}
										
									buttons[x][y+aBateau.getSize()-1].setText("-");
									buttons[x][y+aBateau.getSize()-1].setForeground(Color.black);
									possiblemove = true;
									aBateau.setABouger(true);
						    		panelOne.revalidate(); 
						    		frame.repaint();//repaint a JFrame jframe in this case
								} 
							}
							 else { 
									JOptionPane.showMessageDialog(null, "Vous ne pouvez partir du champ de bataille ! ");
									test1 = true;
								} 
				      } 
				     else if (response == 1) {
				    	 if(buttons[x][y+aBateau.getSize()] != null) {
								if((!buttons[x][y+aBateau.getSize()].getText().contains("-") && !buttons[x][y+aBateau.getSize()].getText().contains("*")) || buttons[x][y+aBateau.getSize()].getForeground() == Color.red) { 
										moveBoat = false;
										JOptionPane.showMessageDialog(null, "Déplacement impossible ! Essayez-en un autre. ");
										test2 = true;
										break;
									}
									else {
										moveBoat = true;
									}
								
								if(moveBoat) {
									JOptionPane.showMessageDialog(null, "DEPLACEMENT EN COURS ");
									for(int j = y+1; j < aBateau.getSize()+y+1; j++ ) { 
										buttons[x][j].setText(buttons[x][j-1].getText());
										buttons[x][j].setForeground(buttons[x][j-1].getForeground());
									}
										
									buttons[x][y].setText("-");
									buttons[x][y].setForeground(Color.black);
									possiblemove = true;
									aBateau.setABouger(true);
						    		panelOne.revalidate(); 
						    		frame.repaint();//repaint a JFrame jframe in this case
								} 
							
						}  
						
						 if(test1 && test2) { 
							 possiblemove = true;
							 JOptionPane.showMessageDialog(null, "Aucun déplacement possible, veuillez tirer avec ce bateau");
						 }
			    	
						
				    }	
				    
			}
		}
		
		}

		@Override
		public void checkPossibleMoveRandom(ABateau aBateau, String string) {
			// TODO Auto-generated method stub
			boolean moveB = true;
			 for (int i = 1; i < 16; i++) {
		            for (int j = 1; j < 16; j++) {
			            	if(buttons[i][j].getText().contains(string) && buttons[i][j].getForeground() == Color.red) { 
			            		if(!cJoueur.isABouger()) { 
				            		moveB= false;
			            	}
			            }
		            }       
		        }
			 if(moveB) { 
				 MoveBoatRandom(aBateau,string);
			 }
		}

		private void MoveBoatRandom(ABateau aBateau, String s) {
			// TODO Auto-generated method stub
			boolean moveBoat = true;
			// TODO Auto-generated method stub
			int x = 0,y = 0;
	    	int response;
	    	boolean test1 = false;
	    	boolean test2 = false;
			//Find the first occurence of the string to get the boat position
	    	outerloop:
			for (int i = 1; i < 16; i++) {
		            for (int j = 1; j < 16; j++) {	  
		            	if(buttons[i][j].getText().contains(s)) { 
		            		x=i;
		            		y=j;
		            		break outerloop;
		            	}
		            }
		         }
			boolean down = false,right = false; 
			if(buttons[x][y+1] != null) { 
				 if(!buttons[x][y+1].getText().contains(s)) { 
					 down = true;
				 } else { 
					 right = true;
				 }
			}
			boolean possiblemove = false;
			while(!possiblemove) { 
				
				// In case the boat is vertical
				if(down) {
				     response = (int) (Math.random() * 1) + 1; 
						if(response == 0) {		
							if(buttons[x+aBateau.getSize()][y] != null) {
								   if((!buttons[x+aBateau.getSize()][y].getText().contains("-") && !buttons[x+aBateau.getSize()][y].getText().contains("*")) || buttons[x+aBateau.getSize()][y].getForeground() == Color.red ) { 
										moveBoat = false;
										test1 = true;
										break;
									}
									else {
										moveBoat = true;
									}
								
								if(moveBoat) {
									for(int j = x; j < aBateau.getSize()+x; j++ ) { 
										buttons[j+1][y].setText(buttons[x][y].getText());
										buttons[j+1][y].setForeground(Color.MAGENTA);
									}
										
									buttons[x][y].setText("-");
									buttons[x][y].setForeground(Color.black);
									possiblemove = true;
									aBateau.setABouger(true);
						    		panelOne.revalidate(); 
						    		frame.repaint();//repaint a JFrame jframe in this case
								} 
							} else { 
								test1 = true;
							}
							
							} else if (response == 1) {
								if(buttons[x-1][y] != null) { 
									if((!buttons[x-1][y].getText().contains("-") && !buttons[x-1][y].getText().contains("*")) || buttons[x-1][y].getForeground() == Color.red) { 
											moveBoat = false;
											test2 = true;
											break;
										}
										else {
											moveBoat = true;
										}
									
									if(moveBoat) {
										for(int j = x-1; j < aBateau.getSize()+x; j++ ) { 
											buttons[j][y].setText(buttons[x][y].getText());
											buttons[j][y].setForeground(buttons[j+1][y].getForeground());
										}
											
										buttons[x+aBateau.getSize()-1][y].setText("-");
										buttons[x+aBateau.getSize()-1][y].setForeground(Color.black);
										possiblemove = true;
										aBateau.setABouger(true);
							    		panelOne.revalidate(); 
							    		frame.repaint();//repaint a JFrame jframe in this case
									} 
								} else { 
									test2 = true;
								}
						} 
						
						 if(test1 && test2) { 
							 possiblemove = true;
						 }
			    	
						
				    }
				
				
				// In case the boat is horizontal
				if(right) {
					response = (int) (Math.random() * 1) + 1;;
						if(response == 0) {		
							 if(buttons[x][y-1] != null) {
								   if((!buttons[x][y-1].getText().contains("-") && !buttons[x][y-1].getText().contains("*")) || buttons[x][y-1].getForeground() == Color.red) { 
										moveBoat = false;
										test1 = true;
										break;
									}
									else {
										moveBoat = true;
									}
								
								if(moveBoat) {
									for(int j = y-1; j < aBateau.getSize()+y; j++ ) { 
										buttons[x][j].setText(buttons[x][j+1].getText());
										buttons[x][j].setForeground(Color.MAGENTA);
									}
										
									buttons[x][y+aBateau.getSize()-1].setText("-");
									buttons[x][y+aBateau.getSize()-1].setForeground(Color.black);
									possiblemove = true;
									aBateau.setABouger(true);
						    		panelOne.revalidate(); 
						    		frame.repaint();//repaint a JFrame jframe in this case
								} 
							}
							 else { 
									test1 = true;
								} 
				      } 
				     else if (response == 1) {
				    	 if(buttons[x][y+aBateau.getSize()] != null) {
								if((!buttons[x][y+aBateau.getSize()].getText().contains("-") && !buttons[x][y+aBateau.getSize()].getText().contains("*")) || buttons[x][y+aBateau.getSize()].getForeground() == Color.red) { 
										moveBoat = false;
										test2 = true;
										break;
									}
									else {
										moveBoat = true;
									}
								
								if(moveBoat) {
									for(int j = y+1; j < aBateau.getSize()+y+1; j++ ) { 
										buttons[x][j].setText(buttons[x][j-1].getText());
										buttons[x][j].setForeground(buttons[x][j-1].getForeground());
									}
										
									buttons[x][y].setText("-");
									buttons[x][y].setForeground(Color.black);
									possiblemove = true;
									aBateau.setABouger(true);
						    		panelOne.revalidate(); 
						    		frame.repaint();//repaint a JFrame jframe in this case
								} 
							
						}  
						
						 if(test1 && test2) { 
							 possiblemove = true;
						 }
			    	
						
				    }	
				    
			}
		}
		
		}

		@Override
		public boolean isCaseIsadv() {
			return caseIsadv;
		}

		@Override
		public void setCaseIsadv(boolean caseIsadv) {
			this.caseIsadv = caseIsadv;
		}

		@Override
		public ABateau getCurrentboat() {
			return currentboat;
		}

		@Override
		public void setCurrentboat(ABateau currentboat) {
			this.currentboat = currentboat;
		}
		
}
