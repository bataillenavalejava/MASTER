package Vue;


import modele.ABateau;
import modele.AJoueur;
import modele.Croiseur;
import modele.Cuirasse;
import modele.Destroyer;
import modele.SousMarin;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JInternalFrame;


public class GrilleConsole implements IGrille{
	
 
	private static final int SML_SIDE = 15;
    private static final int SIDE = SML_SIDE * SML_SIDE;
    
    private String[][] grid = new String[SIDE][SIDE];
    private String[][] grid1 = new String[SIDE][SIDE];
    private String[][] griddummy = new String[SIDE][SIDE];
     
    private JButton[][] buttons = new JButton[SIDE][SIDE];
    private JButton[][] buttons1 = new JButton[SIDE][SIDE];
    private JButton[][] buttons2 = new JButton[SIDE][SIDE];
    
	private JInternalFrame  frame = new JInternalFrame("Bienvenue sur la Bataille Navale ! ");
	
	private ABateau cJoueur;
	private ABateau dJoueur;
	private ABateau sJoueur;
	private ABateau crJoueur;
    
	private ABateau cIA;
	private ABateau dIA;
	private ABateau sIA;
	private ABateau crIA;
	private final Object SharedObject;
	private String caseclicked;
	private boolean caseIsadv = false;
	private ABateau currentboat;
	
    public GrilleConsole(Object obj) {  	  
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
    	AddABateau(cJoueur, buttons,grid);
    	AddABateau(sJoueur, buttons,grid);
    	AddABateau(dJoueur, buttons,grid);
    	AddABateau(crJoueur, buttons,grid);
    	}

	protected void AddBateauIA() {
		// TODO Auto-generated method stub
    	AddABateau(cIA, buttons1,grid1);
    	AddABateau(dIA, buttons1,grid1);
    	AddABateau(sIA, buttons1,grid1);
    	AddABateau(crIA, buttons1,grid1);
    	}

    private void AddABateau(ABateau dJoueur, JButton[][] buttons,String[][] grid) {
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

    		for(int j = originy; j<dJoueur.getSize()+originy;j++) { 
    			grid[originx][j] =  String.valueOf(dJoueur.getNom().charAt(0)).toLowerCase();
        		buttons[originx][j].setText(String.valueOf(dJoueur.getNom().charAt(0)).toLowerCase());
        		buttons[originx][j].setForeground(Color.MAGENTA);
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
    		
    		for(int j = originx; j<dJoueur.getSize()+originx;j++) { 
    			
        		buttons[j][originy].setText(String.valueOf(dJoueur.getNom().charAt(0)).toLowerCase());
        		grid[j][originy] =  String.valueOf(dJoueur.getNom().charAt(0)).toLowerCase();
        		buttons[j][originy].setForeground(Color.MAGENTA);
    		}
    	}
    
	}

	private void ClearConsole() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println();
		System.out.println();
	}

	protected void CreateandShowUI() {

        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {	
                String text = "-";
          	  	buttons[i][j] = new JButton(text);  	
          	    grid[i][j] =  text;
            }
        }
        
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {	
              String text = "-";
          	  buttons1[i][j] = new JButton(text);
          	  grid1[i][j] = text;
            }
        }
        
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {	
                String text = "-";
          	    buttons2[i][j] = new JButton(text);
          	    griddummy[i][j] = text;
            }
        }
  
        for(int i = 1; i < SML_SIDE+1; i++) {
        	 for(int j = 1; j < SML_SIDE+1; j++) {
        		 buttons[i][j].setName(1 + "/" + Integer.toString(i) + "/"+Integer.toString(j));
                }
        	}
        
        for(int i = 1; i < SML_SIDE+1; i++) {
       	 for(int j = 1; j < SML_SIDE+1; j++) {
       		 buttons1[i][j].setName(2 + "/" + Integer.toString(i) + "/"+Integer.toString(j));
               }
       	}
        
        for(int i = 1; i < SML_SIDE+1; i++) {
          	 for(int j = 1; j < SML_SIDE+1; j++) {
          		 buttons2[i][j].setName(2 + "/" + Integer.toString(i) + "/"+Integer.toString(j));
                  }
          	}

        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {	
            	System.out.printf(grid[i][j]);
            }
            System.out.printf("           ");
            for (int k = 1; k < 16; k++) {
            	System.out.printf(grid1[i][k]);
            }
            System.out.println();
            }
            
        }

    
	   public ABateau getcJoueur() {
			return cJoueur;
		}

		public ABateau getdJoueur() {
			return dJoueur;
		}

		public ABateau getsJoueur() {
			return sJoueur;
		}

		public ABateau getCrJoueur() {
			return crJoueur;
		}

		public ABateau getcIA() {
			return cIA;
		}
		
		public ABateau getdIA() {
			return dIA;
		}

		public ABateau getsIA() {
			return sIA;
		}

		public ABateau getCrIA() {
			return crIA;
		}
	    
	    public void setCaseclicked(String caseclicked) {
			this.caseclicked = caseclicked;
		}

		public JButton[][] getButtons() {
			return buttons;
		}

		public JButton[][] getButtons1() {
			return buttons1;
		}
		
		public JButton[][] getButtonsdummy() {
			return buttons2;
		}

		public void setbutton(int i, int j, String string, Color color) {
			// TODO Auto-generated method stub
			buttons[i][j].setText(string);
    		buttons[i][j].setForeground(color);
    		frame.repaint();//repaint a JFrame jframe in this case
		}
		
		public void setbutton1(int i, int j, String string, Color color) {
			// TODO Auto-generated method stub
			buttons1[i][j].setText(string);
			buttons1[i][j].setForeground(color);
    		frame.repaint();//repaint a JFrame jframe in this case
		}
		
		public void setbuttondummy(int i, int j, String string, Color color) {
			// TODO Auto-generated method stub
			buttons2[i][j].setText(string);
			buttons2[i][j].setForeground(color);
    		frame.repaint();//repaint a JFrame jframe in this case
		}

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

		public void setWin(AJoueur j) {
			// TODO Auto-generated method stub
			if (j.getName() == "IA") {
		    	System.out.println("L'IA a gagné la partie ! ");
			} 
			else { 
				System.out.println("Vous avez gagné la partie ! ");
			}


		}

		public void checkPossibleMove(ABateau aBateau, String string) {
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
				 MoveBoat(aBateau,string);
			 }
		}

		private void MoveBoat(ABateau aBateau, String s) {
			boolean moveBoat = true;
			// TODO Auto-generated method stub
			int x = 0,y = 0;
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
					boolean matchFound = false;
					String response1 = null;
					Scanner scan = new Scanner(System.in);
					Pattern p = Pattern .compile("([1-2])$");
					System.out.println("Où souhaitez-vous bouger votre bâteau ? \n 1 - Gauche \n 2 - Droite");
					while(!matchFound) { 
						response1 = scan.nextLine();
						Matcher m = p.matcher(response1);
						matchFound = m.find();
						if (!matchFound) {
							System.out.println("Rentrez 1 ou 2 !");
						} 
					}
						if(Integer.valueOf(response1) == 0) {		
							if(buttons[x+aBateau.getSize()][y] != null) {
								   if((!buttons[x+aBateau.getSize()][y].getText().contains("-") && !buttons[x+aBateau.getSize()][y].getText().contains("*")) || buttons[x+aBateau.getSize()][y].getForeground() == Color.red ) { 
										moveBoat = false;
										System.out.println("Déplacement impossible ! Essayez-en un autre. ");
										test1 = true;
										matchFound = false;
										break;
									}
									else {
										moveBoat = true;
									}
								
								if(moveBoat) {
									System.out.println("DEPLACEMENT EN COURS ");
									for(int j = x; j < aBateau.getSize()+x; j++ ) { 
										buttons[j+1][y].setText(buttons[x][y].getText());
										buttons[j+1][y].setForeground(Color.MAGENTA);
										grid[j+1][y] = buttons[x][y].getText();
									}
										
									buttons[x][y].setText("-");
									buttons[x][y].setForeground(Color.black);
									grid[x][y] = "-";
									possiblemove = true;
									aBateau.setABouger(true);
						    		frame.repaint();//repaint a JFrame jframe in this case
						    		showUI();

								} 
							} else { 
								System.out.println("Vous ne pouvez partir du champ de bataille ! ");
								test1 = true;
							}
							
							} else if (Integer.valueOf(response1) == 1) {
								if(buttons[x-1][y] != null) { 
									if((!buttons[x-1][y].getText().contains("-") && !buttons[x-1][y].getText().contains("*")) || buttons[x-1][y].getForeground() == Color.red) { 
											moveBoat = false;
											System.out.println("Déplacement impossible ! Essayez-en un autre. ");
											test2 = true;
											matchFound = false;
											break;
										}
										else {
											moveBoat = true;
										}
									
									if(moveBoat) {
										System.out.println("DEPLACEMENT EN COURS ");
										for(int j = x-1; j < aBateau.getSize()+x; j++ ) { 
											buttons[j][y].setText(buttons[x][y].getText());
											buttons[j][y].setForeground(buttons[j+1][y].getForeground());
											grid[j][y] = buttons[x][y].getText();
										}
											
										buttons[x+aBateau.getSize()-1][y].setText("-");
										buttons[x+aBateau.getSize()-1][y].setForeground(Color.black);
										grid[x+aBateau.getSize()-1][y] = "-";
										possiblemove = true;
										aBateau.setABouger(true);
							    		frame.repaint();//repaint a JFrame jframe in this case
							    		showUI();

									} 
								} else { 
									System.out.println("Déplacement impossible ! Essayez-en un autre. ");
									test2 = true;
								}
						} 
						
						 if(test1 && test2) { 
							 possiblemove = true;
							 System.out.println("Aucun déplacement possible, veuillez tirer avec ce bateau");
						 }
			    	
						
				    }
				
				
				// In case the boat is horizontal
				if(right) {
						boolean matchFound = false;
						String response1 = null;
						Scanner scan = new Scanner(System.in);
						Pattern p = Pattern .compile("([1-2])$");
						System.out.println("Où souhaitez-vous bouger votre bâteau ? \n 1 - Gauche \n 2 - Droite");
						while(!matchFound) { 
							response1 = scan.nextLine();
							Matcher m = p.matcher(response1);
							matchFound = m.find();
							if (!matchFound) {
								System.out.println("Rentrez 1 ou 2 !");
							} 
						}
						if(Integer.valueOf(response1) == 0) {		
							 if(buttons[x][y-1] != null) {
								   if((!buttons[x][y-1].getText().contains("-") && !buttons[x][y-1].getText().contains("*")) || buttons[x][y-1].getForeground() == Color.red) { 
										moveBoat = false;
										System.out.println("Déplacement impossible ! Essayez-en un autre. ");
										test1 = true;
										matchFound = false;
										break;
									}
									else {
										moveBoat = true;
									}
								
								if(moveBoat) {
									System.out.println("DEPLACEMENT EN COURS ");
									for(int j = y-1; j < aBateau.getSize()+y; j++ ) { 
										buttons[x][j].setText(buttons[x][j+1].getText());
										buttons[x][j].setForeground(Color.MAGENTA);
										grid[x][j] = buttons[x][j+1].getText();
									}
										
									buttons[x][y+aBateau.getSize()-1].setText("-");
									buttons[x][y+aBateau.getSize()-1].setForeground(Color.black);
									grid[x][y+aBateau.getSize()-1] = "-";
									possiblemove = true;
									aBateau.setABouger(true);
						    		frame.repaint();//repaint a JFrame jframe in this case
						    		showUI();

								} 
							}
							 else { 
									System.out.println("Vous ne pouvez partir du champ de bataille ! ");
									test1 = true;
								} 
				      } 
				     else if (Integer.valueOf(response1) == 1) {
				    	 if(buttons[x][y+aBateau.getSize()] != null) {
								if((!buttons[x][y+aBateau.getSize()].getText().contains("-") && !buttons[x][y+aBateau.getSize()].getText().contains("*")) || buttons[x][y+aBateau.getSize()].getForeground() == Color.red) { 
										moveBoat = false;
										System.out.println("Déplacement impossible ! Essayez-en un autre. ");
										test2 = true;
										matchFound = false;
										break;
									}
									else {
										moveBoat = true;
									}
								
								if(moveBoat) {
									System.out.println("DEPLACEMENT EN COURS ");
									for(int j = y+1; j < aBateau.getSize()+y+1; j++ ) { 
										buttons[x][j].setText(buttons[x][j-1].getText());
										grid[x][j] = buttons[x][j-1].getText();
										buttons[x][j].setForeground(buttons[x][j-1].getForeground());
									}
										
									buttons[x][y].setText("-");
									buttons[x][y].setForeground(Color.black);
									grid[x][y] = "-";
									possiblemove = true;
									aBateau.setABouger(true);
						    		frame.repaint();//repaint a JFrame jframe in this case
						    		showUI();

								} 
							
						}  
						
						 if(test1 && test2) { 
							 possiblemove = true;
							 System.out.println("Aucun déplacement possible, veuillez tirer avec ce bateau");
						 }
			    	
						
				    }	
				    
			}
		}
		
		}

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
										griddummy[j+1][y] = buttons[x][y].getText();
										buttons[j+1][y].setForeground(Color.MAGENTA);
									}
									
									griddummy[x][y] = "-";
									buttons[x][y].setText("-");
									buttons[x][y].setForeground(Color.black);
									possiblemove = true;
									aBateau.setABouger(true);
						    		frame.repaint();//repaint a JFrame jframe in this case
						    		showUI();

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
											griddummy[j][y] = buttons[x][y].getText();
										}
										
										griddummy[x+aBateau.getSize()-1][y] = buttons[x][y].getText();
										buttons[x+aBateau.getSize()-1][y].setText("-");
										buttons[x+aBateau.getSize()-1][y].setForeground(Color.black);
										possiblemove = true;
										aBateau.setABouger(true);
							    		frame.repaint();//repaint a JFrame jframe in this case
							    		showUI();

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
						    		frame.repaint();//repaint a JFrame jframe in this case
						    		showUI();

								} 
							}
							 else { 
									test1 = true;
								} 
				      } 
				     else if (response == 1) {
				    	 if(buttons[x][y+1] != null) {
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
						    		frame.repaint();//repaint a JFrame jframe in this case
						    		showUI();

								} 
							
						}  
						
						 if(test1 && test2) { 
							 possiblemove = true;
						 }
			    	
						
				    }	
				    
			}
		}
		
		}

		public void showUI() {
			// TODO Auto-generated method stub
	    	ClearConsole();
	        for (int i = 1; i < 16; i++) {
	            for (int j = 1; j < 16; j++) {	
	            	System.out.printf(grid[i][j]);
	            }
	            System.out.printf("           ");
	            for (int k = 1; k < 16; k++) {
	            	System.out.printf(griddummy[i][k]);
	            }
	            System.out.println();
	            }
		}

		@Override
		public void Tir(){
			// TODO Auto-generated method stub
			int grillex = 0;
			int grilley = 0;
			boolean matchFound = false;
			String entree = null;
			boolean moved = false;
			setCaseIsadv(false);
			Scanner scan = new Scanner(System.in);
			while(!isCaseIsadv()) {
				Pattern p = Pattern .compile("([1-2])\\s(1[0-5]|[1-9])\\s(1[0-5]|[1-9])$");
				System.out.println("Feu à volonté !");
				while(!matchFound) { 
					entree = scan.nextLine();
					Matcher m = p.matcher(entree);
					matchFound = m.find();
					 if (entree.contentEquals("save")) {
						Fenetre.SaveGrilleConsole();
						System.out.println("Feu à vonlonté !");
					} else if (entree.contentEquals("load")) { 
						Fenetre.chargerSaveConsole();
						showUI();
						System.out.println("Feu à vonlonté !");
					} else if (entree.contentEquals("move") && !moved) { 
						char c = getCurrentboat().getNom().toLowerCase().charAt(0);
						String s =Character.toString(c);
						checkPossibleMove(getCurrentboat(),s);
						moved = true;
						System.out.println("Feu à vonlonté !");
					} else if (entree.contentEquals("help") && !moved) { 
						showHelpConsole();
					}
					else if(moved) { 
						System.out.println("Vous avez déjà bouger !");
					}
					else if (!matchFound) {
						System.out.println("Mauvais format !");
					} 
				}
				setCaseClicked(entree);
	  			System.out.println("Case cliqué : " + getCaseclicked());
	  			String[] separated = getCaseclicked().split(" ");
	  			String grillenumber = separated[0];
	  			grillex = Integer.valueOf(separated[1]);
	  			grilley = Integer.valueOf(separated[2]);
	  			if (Integer.valueOf(grillenumber) == 1 && (getButtons()[grillex][grilley].getText() == "-" || getButtons()[grillex][grilley].getText() == "*")) {
	  				System.out.println("Vous ne pouvez pas tirer sur vos alliés ! ");
	  			    matchFound = false;
	  			}
	  			else if (Integer.valueOf(grillenumber) == 2 && getButtons1()[grillex][grilley].getForeground().equals(Color.red)) {
	  				 System.out.println("Vous avez déjà tiré ici ! ");
	  				 matchFound = false;
	  			}
	  			else if (Integer.valueOf(grillenumber) == 1 && !(getButtons()[grillex][grilley].getText().charAt(0) == getCurrentboat().getNom().toLowerCase().charAt(0)) && !moved) {
	  				System.out.println("Vous ne pouvez bouger le bateau que vous contrôler ! ");
	  			}
	  			else {
	  				setCaseIsadv(true);
	  			}
	  		}
	  			if (getButtons1()[grillex][grilley].getText().contains("-")) { 
	  				setbutton1(grillex,grilley,"*", Color.red);
	  				setbuttondummy(grillex,grilley,"*", Color.red);
	  				griddummy[grillex][grilley] = "*";
	  				}
	  			else {
	  				setbutton1(grillex,grilley,getButtons1()[grillex][grilley].getText(), Color.red);
	  				setbuttondummy(grillex,grilley,getButtons1()[grillex][grilley].getText(), Color.red);
	  				griddummy[grillex][grilley] = "x";
	  				}
	  			showUI();
		}

		private void showHelpConsole() {
			// TODO Auto-generated method stub
			new AideConsole();
		}

		private void setCaseClicked(String caseclicked) {
			// TODO Auto-generated method stub
			this.caseclicked = caseclicked;
		}

		@Override
		public void TirFusee() {
			// TODO Auto-generated method stub
			int grillex = 0;
			int grilley = 0;
			boolean matchFound = false;
			String entree = null;
			Scanner scan = new Scanner(System.in);
			setCaseIsadv(false);
			while(!isCaseIsadv()) {
				Pattern p = Pattern .compile("([1-2])\\s(1[0-5]|[1-9])\\s(1[0-5]|[1-9])$");
				System.out.println("Tir de fusée éclairante !");
				while(!matchFound) { 
					entree = scan.nextLine();
					Matcher m = p.matcher(entree);
					matchFound = m.find();
					if (!matchFound) {
						System.out.println("Mauvais format !");
					} 
				}
				setCaseClicked(entree);
				System.out.println("Case cliqué : " + getCaseclicked());
				String[] separated = getCaseclicked().split(" ");
				String grillenumber = separated[0];
				grillex = Integer.valueOf(separated[1]);
				grilley = Integer.valueOf(separated[2]);
				if (Integer.valueOf(grillenumber) == 1) {
				    System.out.println("Vous ne pouvez pas tirer sur vos alliés ! ");
				    matchFound = false;
				}
				else {
					setCaseIsadv(true);
				}
		}
		for(int i = grillex-2; i <= grillex+2; i++) {
			for(int y = grilley-2; y <= grilley+2; y++) {
				if(i > 0 && i < 15 && y > 0 && y < 15) {
					if(getButtons1()[i][y].getText() == "-") { 
						setbuttondummy(i, y, "*", Color.MAGENTA);
						griddummy[i][y] = "o";
					}
					else { 
						setbuttondummy(i, y, getButtons1()[i][y].getText(), Color.MAGENTA);
						griddummy[i][y] = getButtons1()[i][y].getText();
					}
			}
		 }
		}
		showUI();
		
		}

		@Override
		public void TirRandom() {
			// TODO Auto-generated method stub
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
				grid[grillex][grilley] = "*";
			}
			else {
				setbutton(grillex,grilley,getButtons()[grillex][grilley].getText(), Color.red);
				grid[grillex][grilley] = "x";
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
			// TODO Auto-generated method stub
			int grillex = 0;
			int grilley = 0;
			grillex = (int) (Math.random() * 15) + 1;
			grilley = (int) (Math.random() * 15) + 1;
			for(int i = grillex-2; i <= grillex+2; i++) {
				for(int y = grilley-2; y <= grilley+2; y++) {
					if(i > 0 && i < 15 && y > 0 && y < 15) {
						if(getButtons()[i][y].getText() == "-") { 
							setbutton(i, y, "*", Color.MAGENTA);
							grid[i][y] = "o";
						}
						else { 
							setbutton(i, y, getButtons()[i][y].getText(), Color.MAGENTA);
						}
				}
			 }
			}
		}

		@Override
		public String getCaseclicked() {
			// TODO Auto-generated method stub
			return this.caseclicked;
		}

		@Override
		public boolean isCaseIsadv() {
			// TODO Auto-generated method stub
			return this.caseIsadv;
		}

		@Override
		public void setCaseIsadv(boolean caseIsadv) {
			// TODO Auto-generated method stub
			this.caseIsadv = caseIsadv;
		}

		@Override
		public ABateau getCurrentboat() {
			// TODO Auto-generated method stub
			return this.currentboat;
		}

		@Override
		public void setCurrentboat(ABateau currentboat) {
			// TODO Auto-generated method stub
			this.currentboat = currentboat;
		}

		public String[][] getGrid() {
			// TODO Auto-generated method stub
			return grid;
		}

		public String[][] getGrid1() {
			return grid1;
		}

		public String[][] getGriddummy() {
			return griddummy;
		}
}
