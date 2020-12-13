package modele;

import java.util.ArrayList;

public abstract class AJoueur {
	private ArrayList<ABateau> bateau = new ArrayList<ABateau>();
	private boolean aJouer;
	private String name;
	private int pointeur = 1;

	public AJoueur(ABateau cJoueur, ABateau dJoueur, ABateau crJoueur, ABateau sJoueur, String name) {
		this.bateau.add(cJoueur);
		this.bateau.add(dJoueur);
		this.bateau.add(crJoueur);
		this.bateau.add(sJoueur);
		this.aJouer = false;
		this.name = name;
	}

	public boolean getAJouer() {
		// TODO Auto-generated method stub
		return aJouer;
	}

	public ArrayList<ABateau> getBateau() {
		return bateau;
	}

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

	public String getName() {
		return name;
	}

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

	public void resetMoveBoat() {
		for (ABateau b : bateau) {
			b.aBouger = false;
		}
	}

	public void setAJouer(boolean b) {
		// TODO Auto-generated method stub
		this.aJouer = b;
	}
}
