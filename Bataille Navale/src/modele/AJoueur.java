/*
 * 
 */
package modele;

import java.util.ArrayList;

/**
 * The Class AJoueur.
 */
public abstract class AJoueur {

	/** The bateau. */
	private ArrayList<ABateau> bateau = new ArrayList<ABateau>();

	/** The a jouer. */
	private boolean aJouer;

	/** The name. */
	private String name;

	/** The pointeur. */
	private int pointeur = 1;

	/**
	 * Instantiates a new joueur.
	 *
	 * @param cJoueur  the c joueur
	 * @param dJoueur  the d joueur
	 * @param crJoueur the cr joueur
	 * @param sJoueur  the s joueur
	 * @param name     the name
	 */
	public AJoueur(ABateau cJoueur, ABateau dJoueur, ABateau crJoueur, ABateau sJoueur, String name) {
		this.bateau.add(cJoueur);
		this.bateau.add(dJoueur);
		this.bateau.add(crJoueur);
		this.bateau.add(sJoueur);
		this.aJouer = false;
		this.name = name;
	}

	/**
	 * Gets the a jouer.
	 *
	 * @return the a jouer
	 */
	public boolean getAJouer() {
		// TODO Auto-generated method stub
		return aJouer;
	}

	/**
	 * Gets the bateau.
	 *
	 * @return the bateau
	 */
	public ArrayList<ABateau> getBateau() {
		return bateau;
	}

	/**
	 * Gets the bateauby name.
	 *
	 * @param s the s
	 * @return the bateauby name
	 */
	public ABateau getBateaubyName(String s) {
		// TODO Auto-generated method stub
		ABateau b = null;
		for (ABateau a : getBateau()) {
			if (a.getNom() == s) {
				b = a;
			}
		}
		return b;
	}

	/**
	 * Gets the destroyer.
	 *
	 * @return the destroyer
	 */
	public ABateau getDestroyer() {
		// TODO Auto-generated method stub
		for (int i = 0; i < bateau.size(); i++) {
			if (bateau.get(i).getNom() == "Destroyer") {
				return bateau.get(i);
			}
		}
		;
		return null;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Next bateau.
	 *
	 * @return the a bateau
	 */
	public ABateau nextBateau() {
		// TODO Auto-generated method stub
		pointeur++;
		if (pointeur == bateau.size()) {
			pointeur = 0;
		}
		while (!bateau.get(pointeur).IsAlive) {
			pointeur++;
			if (pointeur == bateau.size()) {
				pointeur = 0;
			}
		}
		return bateau.get(pointeur);
	}

	/**
	 * Reset move boat.
	 */
	public void resetMoveBoat() {
		for (ABateau b : bateau) {
			b.aBouger = false;
		}
	}

	/**
	 * Sets the a jouer.
	 *
	 * @param b the new a jouer
	 */
	public void setAJouer(boolean b) {
		// TODO Auto-generated method stub
		this.aJouer = b;
	}
}
