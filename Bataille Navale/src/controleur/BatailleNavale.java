package controleur;

import Vue.Fenetre;
import Vue.IGrille;
import modele.ABateau;
import modele.AJoueur;
import modele.Destroyer;
import modele.JoueurHumain;
import modele.JoueurIA;

public class BatailleNavale {

	private static boolean play = true;
	private static boolean jIAWin = false;
	private static boolean jJoueurWin = false;
	private static AJoueur jHumain = null;
	private static AJoueur jIA = null;

	public static boolean isPlay() {
		return play;
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 * start the game Naval Battle 
	 */
	public static void main(String[] args) throws InterruptedException {
		final Object sharedObject = new Object();
		Fenetre f = new Fenetre(sharedObject);
		while (true) {
			Thread.sleep(200);
			while (play) {
				boolean fenetreisSet = false;
				boolean fenetreisSetConsole = false;
				IGrille gr = f.getG();
				IGrille grc = f.getGC();
				jHumain = new JoueurHumain(gr.getcJoueur(), gr.getdJoueur(), gr.getCrJoueur(), gr.getsJoueur());
				jIA = new JoueurIA(gr.getcIA(), gr.getdIA(), gr.getCrIA(), gr.getsIA());
				while (!fenetreisSet && !fenetreisSetConsole) {
					fenetreisSet = f.isGrilleIsSet();
					fenetreisSetConsole = f.isGrilleConsoleIsSet();
					Thread.sleep(100);
				}
				if (fenetreisSet) {
					while (!jIAWin && !jJoueurWin) {
						if (fenetreisSet) {
							if (play) {
								gr = f.getG();
								jHumain.setAJouer(false);
								jIA.setAJouer(false);

								if (jHumain.getAJouer() == false
										&& ((Destroyer) jHumain.getDestroyer()).getMunEclair()) {
									f.setLabel("Lancer de fusée éclairante !");
									try {
										((Destroyer) jHumain.getDestroyer()).tirerFusee(gr);
										jHumain.setAJouer(true);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else if (jHumain.getAJouer() == false) {
									f.setLabel("Feu à volonté !");
									try {
										jHumain.nextBateau().tirer(gr);
										if (play) {
											jHumain.setAJouer(true);
											gr.checkAliveBateau();
											jJoueurWin = true;
											for (ABateau a : jIA.getBateau()) {
												if (a.getStatus() == true) {
													jJoueurWin = false;
												}
											}
											if (jJoueurWin) {
												play = false;
												gr.setWin(jHumain);
											}
										} else {
											break;
										}
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

								if (jIA.getAJouer() == false && ((Destroyer) jIA.getDestroyer()).getMunEclair()) {
									f.setLabel("Lancer de fusée éclairante !");
									try {
										((Destroyer) jIA.getDestroyer()).tirerFuseeRandom(gr);
										jIA.setAJouer(true);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else if (jIA.getAJouer() == false && play) {
									f.setLabel("Feu à volonté !");
									try {
										jIA.nextBateau().tirerRandom(gr);
										jIA.setAJouer(true);
										gr.checkAliveBateau();
										jIAWin = true;
										for (ABateau a : jHumain.getBateau()) {
											if (a.getStatus() == true) {
												jIAWin = false;
											}
										}
										if (jIAWin) {
											gr.setWin(jIA);
											play = false;
										}
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}

						jIA.resetMoveBoat();
						jHumain.resetMoveBoat();
					}
				} else if (fenetreisSetConsole) {
					while (!jIAWin && !jJoueurWin) {
						if (fenetreisSetConsole) {
							if (play) {
								grc = f.getGC();
								jHumain.setAJouer(false);
								jIA.setAJouer(false);
								if (jHumain.getAJouer() == false
										&& ((Destroyer) jHumain.getDestroyer()).getMunEclair()) {
									((Destroyer) jHumain.getDestroyer()).tirerFusee(grc);
									jHumain.setAJouer(true);
								} else if (jHumain.getAJouer() == false) {
									jHumain.nextBateau().tirer(grc);
									jHumain.setAJouer(true);
									grc.checkAliveBateau();
									jJoueurWin = true;
									for (ABateau a : jIA.getBateau()) {
										if (a.getStatus() == true) {
											jJoueurWin = false;
										}
									}
									if (jJoueurWin) {
										play = false;
										grc.setWin(jHumain);
									}
								}

								if (jIA.getAJouer() == false && ((Destroyer) jIA.getDestroyer()).getMunEclair()) {
									((Destroyer) jIA.getDestroyer()).tirerFuseeRandom(grc);
									jIA.setAJouer(true);
								} else if (jIA.getAJouer() == false) {
									jIA.nextBateau().tirerRandom(grc);
									jIA.setAJouer(true);
									grc.checkAliveBateau();
									jIAWin = true;
									for (ABateau a : jHumain.getBateau()) {
										if (a.getStatus() == true) {
											jIAWin = false;
										}
									}
									if (jIAWin) {
										grc.setWin(jIA);
										play = false;
									}
								}
							}
							jIA.resetMoveBoat();
							jHumain.resetMoveBoat();
							if (jIAWin) {
								grc.setWin(jIA);
							} else if (jJoueurWin) {
								grc.setWin(jHumain);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * reset game state
	 */
	public static void resetGameState() {
		jHumain.setAJouer(false);
		jIA.setAJouer(false);
		play = false;
		jIAWin = false;
		jJoueurWin = false;
		AJoueur jHumain = null;
		AJoueur jIA = null;
	}

	/**
	 * @param play : set the game play value to true or false
	 */
	public static void setPlay(boolean play) {
		BatailleNavale.play = play;
	}

}
