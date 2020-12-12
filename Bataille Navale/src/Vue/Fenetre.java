package Vue;


import controleur.BatailleNavale;
import modele.Destroyer;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Vue.Grille;

public class Fenetre { 
	
	@SuppressWarnings("unused")
	private CardLayout cardL;
	private static Grille g ;
	private static GrilleConsole g1;
    private static final Dimension ddefault = new Dimension(1400,800);
    private static final Dimension dmin = new Dimension(1400,800);
    private boolean grilleIsSet = false; 
    private boolean grilleConsoleIsSet = false;
    private JLabel label = new JLabel("A vous de jouer !");
	private static Object SharedObject;
	private JFrame frame1;
	public Fenetre(Object obj) { 
		this.affichage();	
		this.cardL = new CardLayout();
		Fenetre.SharedObject = obj;
		g = new Grille(SharedObject);
		g1 = new GrilleConsole(SharedObject);
		frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
	
	public void affichage(){
	{
		/* Création des composants */
		frame1= new JFrame("Fenetre");
		JPanel panel1= new JPanel();
				/* Bar de menu */
		JMenuBar menu_bar1 = new JMenuBar();
				/* différents menus */
		JMenu menu1 = new JMenu("Jeu");
		JMenu menu2 = new JMenu("Options");
				/* differents choix de chaque menu */
		JMenuItem demarrer = new JMenuItem("Démarrer");
		JMenuItem quitter = new JMenuItem("Quitter");
		JMenuItem save = new JMenuItem("Sauvegarder");
		JMenuItem chargersave = new JMenuItem("Charger une sauvegarde");
		JMenuItem aide = new JMenuItem("Aide");

		/* Ajout de composants aux conteneurs  */
		label.setEnabled(false);
		panel1.add(label);
		frame1.getContentPane().add(panel1,"South");
				/* Ajouter les choix au menu  */
		menu1.add(demarrer);
		menu1.add(quitter);
		menu2.add(save);
		menu2.add(chargersave);
		menu2.add(aide);
				/* Ajouter les menu sur la bar de menu */
		menu_bar1.add(menu1);
		menu_bar1.add(menu2);
				/* Ajouter la bar du menu à la frame */
		frame1.setJMenuBar(menu_bar1);

		/* Action réaliser par l'ihm */
		
				/* clic sur le choix Démarrer du menu */
		demarrer.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String[] options = new String[] {"Console", "Graphique"};
						int response = JOptionPane.showOptionDialog(null, "Voulez-vous lancer en mode Console ou Graphique ?", "Lancer une nouvelle partie",
						        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						        null, options, options[0]);
						if(response == 1) {	
							if (grilleIsSet == false) { 
								frame1.add(g.getJframe());
						    	g.CreateandShowUI();
						    	g.AddBateauJoueur();
						    	g.AddBateauIA();
						    	grilleIsSet = true;
							}
							else { 
							int reply = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir lancer une nouvelle partie ?", "Lancer une nouvelle partie", JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
					        		   BatailleNavale.setPlay(false);
									    frame1.remove(g.getJframe());
										g = new Grille(SharedObject);	
										frame1.add(g.getJframe());
								    	g.CreateandShowUI();
								    	g.AddBateauJoueur();
								    	g.AddBateauIA();
							} 
					} 
				}
						if(response == 0) {	
							if (grilleConsoleIsSet == false) { 
						    	g1.CreateandShowUI();
						    	g1.AddBateauJoueur();
						    	g1.AddBateauIA();
						    	g1.showUI();
						    	grilleConsoleIsSet = true;
							}
							else { 
							int reply = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir lancer une nouvelle partie ?", "Lancer une nouvelle partie", JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
					        		   BatailleNavale.setPlay(false);
										g1 = new GrilleConsole(SharedObject);	
								    	g1.CreateandShowUI();
								    	g1.AddBateauJoueur();
								    	g1.AddBateauIA();
							} 
					} 
				}
			}
		});
				/* clic sur le choix Quitter du menu */
		quitter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int reply = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir quitter ?", "Quitter le jeu", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
				    int reply1 = JOptionPane.showConfirmDialog(null, "Voulez vous sauvegarder avant de quitter ?", "Quitter le jeu", JOptionPane.YES_NO_OPTION);
				    if (reply1 == JOptionPane.YES_OPTION) {
				    	SaveGrille();
					    JOptionPane.showMessageDialog(null, "Sauvegarde réussi. ");
						frame1.dispose();
						System.gc();
				    } else { 
				    	JOptionPane.showMessageDialog(null, "A bientot ! ");
						frame1.dispose();
						System.gc();
				    }
				}
			}
		});
		/* clic sur le choix Sauvegarder du menu */
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int reply = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir sauvegarder votre partie ? Cela écrasera toute sauvegarde", "Sauvegarder votre partie", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					SaveGrille();
				}
			} 
		});
		
		/* clic sur le choix Charger du menu */
		chargersave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int reply = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir charger la partie ? Cela écrasera votre partie actuelle", "Charger une nouvelle partie", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
			    	chargerSave();
				    	}
				    }
		});
		/* clic sur le choix Quitter du menu */
		aide.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new AideGraphique();
			}
		});
		
		frame1.pack();
		frame1.setSize(ddefault);
        frame1.setMinimumSize(dmin);
		frame1.setSize(200,200);
		frame1.setVisible(true);
		
	}	
 }
	
	public void SaveGrille() {
		try {

	      File myObj = new File("save.txt");
	      if (myObj.createNewFile()) {
	        System.out.println("File created: " + myObj.getName());
	      } else {
	        System.out.println("File already exists, proceeding ...");
	      }
	      FileWriter myWriter = new FileWriter("save.txt");
	 for(int i = 1; i <= 15; i++) {
			for(int y = 1; y <= 15; y++) {
					myWriter.write(g.getButtons()[i][y].getText());
					myWriter.write(' ');
					String color = Integer.toString(g.getButtons()[i][y].getForeground().getRGB());
					myWriter.write(color);
					//System.out.println(Integer.parseInt(color));
					myWriter.write(' ');
					myWriter.write(String.valueOf(i));
					myWriter.write(' ');
					myWriter.write(String.valueOf(y));
					myWriter.write(System.lineSeparator());	

			}
		}
	 
	 for(int i = 1; i <= 15; i++) {
			for(int y = 1; y <= 15; y++) {
		myWriter.write(g.getButtons1()[i][y].getText());
		myWriter.write(' ');
		String color1 = Integer.toString(g.getButtons1()[i][y].getForeground().getRGB());
		//System.out.println();
		myWriter.write(color1);	
		myWriter.write(' ');
		myWriter.write(String.valueOf(i));
		myWriter.write(' ');
		myWriter.write(String.valueOf(y));
		
		myWriter.write(System.lineSeparator());	
			}
		}

	 for(int i = 1; i <= 15; i++) {
			for(int y = 1; y <= 15; y++) {
		myWriter.write(g.getButtonsdummy()[i][y].getText());
		myWriter.write(' ');
		String color2 = Integer.toString(g.getButtonsdummy()[i][y].getForeground().getRGB());
		//System.out.println();
		myWriter.write(color2);	
		myWriter.write(' ');
		myWriter.write(String.valueOf(i));
		myWriter.write(' ');
		myWriter.write(String.valueOf(y));
		
		myWriter.write(System.lineSeparator());	
			}
		}
	 
	 	// get all player ship informations
	 	myWriter.write(g.getcJoueur().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g.getcJoueur().isABouger()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g.getCrJoueur().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g.getCrJoueur().isABouger()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g.getdJoueur().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g.getdJoueur().isABouger()));
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(((Destroyer) g.getdJoueur()).getMunEclair()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g.getsJoueur().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g.getCrJoueur().isABouger()));
	 	
	 	// get all IA ship informations
		myWriter.write(System.lineSeparator());	

	 	myWriter.write(g.getcIA().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g.getcIA().isABouger()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g.getCrIA().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g.getCrIA().isABouger()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g.getdIA().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g.getdIA().isABouger()));
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(((Destroyer) g.getdIA()).getMunEclair()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g.getsIA().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g.getCrIA().isABouger()));
	 	
		myWriter.close(); 
	} catch (FileNotFoundException e1) {
	// TODO Auto-generated catch block		
	e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	
	public static void SaveGrilleConsole() {
		try {

	      File myObj = new File("save.txt");
	      if (myObj.createNewFile()) {
	        System.out.println("File created: " + myObj.getName());
	      } else {
	        System.out.println("File already exists, proceeding ...");
	      }
	      FileWriter myWriter = new FileWriter("save.txt");
	 for(int i = 1; i <= 15; i++) {
			for(int y = 1; y <= 15; y++) {
					myWriter.write(g1.getButtons()[i][y].getText());
					myWriter.write(' ');
					String color = Integer.toString(g1.getButtons()[i][y].getForeground().getRGB());
					myWriter.write(color);
					//System.out.println(Integer.parseInt(color));
					myWriter.write(' ');
					myWriter.write(String.valueOf(i));
					myWriter.write(' ');
					myWriter.write(String.valueOf(y));
					myWriter.write(System.lineSeparator());	

			}
		}
	 
	 for(int i = 1; i <= 15; i++) {
			for(int y = 1; y <= 15; y++) {
		myWriter.write(g1.getButtons1()[i][y].getText());
		myWriter.write(' ');
		String color1 = Integer.toString(g1.getButtons1()[i][y].getForeground().getRGB());
		//System.out.println();
		myWriter.write(color1);	
		myWriter.write(' ');
		myWriter.write(String.valueOf(i));
		myWriter.write(' ');
		myWriter.write(String.valueOf(y));
		
		myWriter.write(System.lineSeparator());	
			}
		}

	 for(int i = 1; i <= 15; i++) {
			for(int y = 1; y <= 15; y++) {
		myWriter.write(g1.getButtonsdummy()[i][y].getText());
		myWriter.write(' ');
		String color2 = Integer.toString(g1.getButtonsdummy()[i][y].getForeground().getRGB());
		//System.out.println();
		myWriter.write(color2);	
		myWriter.write(' ');
		myWriter.write(String.valueOf(i));
		myWriter.write(' ');
		myWriter.write(String.valueOf(y));
		
		myWriter.write(System.lineSeparator());	
			}
		}
	 
	 	// get all player ship informations
	 	myWriter.write(g1.getcJoueur().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g1.getcJoueur().isABouger()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g1.getCrJoueur().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g1.getCrJoueur().isABouger()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g1.getdJoueur().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g1.getdJoueur().isABouger()));
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(((Destroyer) g1.getdJoueur()).getMunEclair()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g1.getsJoueur().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g1.getCrJoueur().isABouger()));
	 	
	 	// get all IA ship informations
		myWriter.write(System.lineSeparator());	

	 	myWriter.write(g1.getcIA().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g1.getcIA().isABouger()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g1.getCrIA().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g1.getCrIA().isABouger()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g1.getdIA().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g1.getdIA().isABouger()));
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(((Destroyer) g1.getdIA()).getMunEclair()));
	 	
		myWriter.write(System.lineSeparator());	
		
	 	myWriter.write(g1.getsIA().getNom());
	 	myWriter.write(' ');
	 	myWriter.write(String.valueOf(g1.getCrIA().isABouger()));
	 	
		myWriter.close(); 
	} catch (FileNotFoundException e1) {
	// TODO Auto-generated catch block		
	e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	
	
	public IGrille getG() {
		return g;
	}

	public boolean isGrilleIsSet() {
		// TODO Auto-generated method stub
		return grilleIsSet;
	}

	public void setLabel(String string) {
		// TODO Auto-generated method stub
		label.setText(string);
	}

	public JFrame getPanel() {
		// TODO Auto-generated method stub
		return this.frame1;
	}

	public GrilleConsole getGC() {
		// TODO Auto-generated method stub
		return g1;
	}

	public boolean isGrilleConsoleIsSet() {
		// TODO Auto-generated method stub
		return grilleConsoleIsSet;
	}

	public static void chargerSave() {
		// TODO Auto-generated method stub
		g.setCaseclicked(null);
  	  synchronized(SharedObject){
  		  SharedObject.notifyAll();
  	  }

	    BufferedReader lecteurAvecBuffer = null;
	    String ligne;
	    try
	      {
		lecteurAvecBuffer = new BufferedReader(new FileReader("save.txt"));
	      }
	    catch(FileNotFoundException exc)
	      {
		System.out.println("Erreur d'ouverture");
	      }
	    int linenumber =1; 
	    try {
			while ((ligne = lecteurAvecBuffer.readLine()) != null) {
				if(linenumber<226) {
			       	String[] separated = ligne.split("\\s+");
			       	String grilletext = separated[0];
			        String color = separated[1];
			        String x = separated[2];
			        String y = separated[3];
			        Color c = new Color(Integer.parseInt(color));
			        g.setbutton(Integer.valueOf(x),Integer.valueOf(y),grilletext,c);
			     }
				else if(linenumber<451) {
		        	String[] separated = ligne.split(" ");
		        	String grilletext = separated[0];
		        	String color = separated[1];
		        	String x = separated[2];
		        	String y = separated[3];
		        	Color c = new Color(Integer.parseInt(color));
		        	g.setbutton1(Integer.valueOf(x),Integer.valueOf(y),grilletext,c);
				} else if (linenumber<676) {
		        	String[] separated = ligne.split(" ");
		        	String grilletext = separated[0];
		        	String color = separated[1];
		        	String x = separated[2];
		        	String y = separated[3];
		        	Color c = new Color(Integer.parseInt(color));
		        	g.setbuttondummy(Integer.valueOf(x),Integer.valueOf(y),grilletext,c);
				} else {
					String[] separated = ligne.split(" ");
		        	String shipname = separated[0];
		        	String aBouger = separated[1];
					if(shipname == "Battleship" && linenumber<680) {
						g.getcJoueur().setABouger(Boolean.valueOf(aBouger));
					}
					else if(shipname == "Cruiser" && linenumber<680) {
						g.getCrJoueur().setABouger(Boolean.valueOf(aBouger));
					}
					else if(shipname == "Destroyer" && linenumber<680) {
						g.getdJoueur().setABouger(Boolean.valueOf(aBouger));
			        	String aTirerfusee = separated[2];
						((Destroyer) g.getdJoueur()).setMunEclair(Boolean.valueOf(aTirerfusee));
					}
					else if(shipname == "Submarine " && linenumber<680) {
						g.getCrJoueur().setABouger(Boolean.valueOf(aBouger));
					}
					if(shipname == "Battleship" && linenumber<684) {
						g.getcIA().setABouger(Boolean.valueOf(aBouger));
					}
					else if(shipname == "Cruiser" && linenumber<684) {
						g.getCrIA().setABouger(Boolean.valueOf(aBouger));
					}
					else if(shipname == "Destroyer" && linenumber<684) {
						g.getdIA().setABouger(Boolean.valueOf(aBouger));
			        	String aTirerfusee = separated[2];
						((Destroyer) g.getdIA()).setMunEclair(Boolean.valueOf(aTirerfusee));
					}
					else if(shipname == "Submarine " && linenumber<684) {
						g.getsIA().setABouger(Boolean.valueOf(aBouger));
					}
				}
				linenumber++;
				
			}
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			lecteurAvecBuffer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void chargerSaveConsole() {
		// TODO Auto-generated method stub
  	  synchronized(SharedObject){
  		  SharedObject.notifyAll();
  	  }

	    BufferedReader lecteurAvecBuffer = null;
	    String ligne;
	    try
	      {
		lecteurAvecBuffer = new BufferedReader(new FileReader("save.txt"));
	      }
	    catch(FileNotFoundException exc)
	      {
		System.out.println("Sauvegarde corrompue.");
	      }
	    int linenumber =1; 
	    try {
			while ((ligne = lecteurAvecBuffer.readLine()) != null) {
				if(linenumber<226) {
			       	String[] separated = ligne.split("\\s+");
			       	String grilletext = separated[0];
			        String color = separated[1];
			        String x = separated[2];
			        String y = separated[3];
			        Color c = new Color(Integer.parseInt(color));
			        g1.setbutton(Integer.valueOf(x),Integer.valueOf(y),grilletext,c);
			        g1.getGrid()[Integer.valueOf(x)][Integer.valueOf(y)] = grilletext;
			     }
				else if(linenumber<451) {
		        	String[] separated = ligne.split(" ");
		        	String grilletext = separated[0];
		        	String color = separated[1];
		        	String x = separated[2];
		        	String y = separated[3];
		        	Color c = new Color(Integer.parseInt(color));
		        	g1.setbutton1(Integer.valueOf(x),Integer.valueOf(y),grilletext,c);
		        	g1.getGrid1()[Integer.valueOf(x)][Integer.valueOf(y)] = grilletext;
				} else if (linenumber<676) {
		        	String[] separated = ligne.split(" ");
		        	String grilletext = separated[0];
		        	String color = separated[1];
		        	String x = separated[2];
		        	String y = separated[3];
		        	Color c = new Color(Integer.parseInt(color));
		        	g1.setbuttondummy(Integer.valueOf(x),Integer.valueOf(y),grilletext,c);
		        	g1.getGriddummy()[Integer.valueOf(x)][Integer.valueOf(y)] = grilletext;
				} else {
					String[] separated = ligne.split(" ");
		        	String shipname = separated[0];
		        	String aBouger = separated[1];
					if(shipname == "Battleship" && linenumber<680) {
						g1.getcJoueur().setABouger(Boolean.valueOf(aBouger));
					}
					else if(shipname == "Cruiser" && linenumber<680) {
						g1.getCrJoueur().setABouger(Boolean.valueOf(aBouger));
					}
					else if(shipname == "Destroyer" && linenumber<680) {
						g1.getdJoueur().setABouger(Boolean.valueOf(aBouger));
			        	String aTirerfusee = separated[2];
						((Destroyer) g1.getdJoueur()).setMunEclair(Boolean.valueOf(aTirerfusee));
					}
					else if(shipname == "Submarine " && linenumber<680) {
						g1.getCrJoueur().setABouger(Boolean.valueOf(aBouger));
					}
					if(shipname == "Battleship" && linenumber<684) {
						g1.getcIA().setABouger(Boolean.valueOf(aBouger));
					}
					else if(shipname == "Cruiser" && linenumber<684) {
						g1.getCrIA().setABouger(Boolean.valueOf(aBouger));
					}
					else if(shipname == "Destroyer" && linenumber<684) {
						g1.getdIA().setABouger(Boolean.valueOf(aBouger));
			        	String aTirerfusee = separated[2];
						((Destroyer) g1.getdIA()).setMunEclair(Boolean.valueOf(aTirerfusee));
					}
					else if(shipname == "Submarine " && linenumber<684) {
						g1.getsIA().setABouger(Boolean.valueOf(aBouger));
					}
				}
				linenumber++;
				
			}
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			lecteurAvecBuffer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}