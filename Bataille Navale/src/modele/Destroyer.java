package modele;

import Vue.IGrille;

public class Destroyer extends ABateau {

	private boolean munEclair;

	public Destroyer(Object SharedObject) {
		super(SharedObject);
		this.taille = 3;
		this.name = "Destroyer";
		this.munEclair = true;
		this.impactrange = 1;
	}

	public boolean getMunEclair() {
		return munEclair;
	}

	public void setMunEclair(boolean munEclair) {
		this.munEclair = munEclair;
	}

	public void tirerFusee(IGrille gr) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Waiting for grille to complete...");
		gr.setCurrentboat(this);
		gr.TirFusee();
		this.munEclair = false;
	}

	public void tirerFuseeRandom(IGrille gr) throws InterruptedException {
		// TODO Auto-generated method stub
		gr.setCurrentboat(this);
		gr.TirFuseeRandom();
		this.munEclair = false;
	}

}
