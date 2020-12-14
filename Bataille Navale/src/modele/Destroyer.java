/*
 * 
 */
package modele;

import Vue.IGrille;

/**
 * The Class Destroyer.
 */
public class Destroyer extends ABateau {

	/** The mun eclair. */
	private boolean munEclair;

	/**
	 * Instantiates a new destroyer.
	 *
	 * @param SharedObject the shared object
	 */
	public Destroyer(Object SharedObject) {
		super(SharedObject);
		this.taille = 3;
		this.name = "Destroyer";
		this.munEclair = true;
		this.impactrange = 1;
	}

	/**
	 * Gets the mun eclair.
	 *
	 * @return the mun eclair
	 */
	public boolean getMunEclair() {
		return munEclair;
	}

	/**
	 * Sets the mun eclair.
	 *
	 * @param munEclair the new mun eclair
	 */
	public void setMunEclair(boolean munEclair) {
		this.munEclair = munEclair;
	}

	/**
	 * Tirer fusee.
	 *
	 * @param gr the grid
	 * @throws InterruptedException the interrupted exception
	 */
	public void tirerFusee(IGrille gr) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Waiting for grille to complete...");
		gr.setCurrentboat(this);
		gr.TirFusee();
		this.munEclair = false;
	}

	/**
	 * Tirer fusee random.
	 *
	 * @param gr the grid
	 * @throws InterruptedException the interrupted exception
	 */
	public void tirerFuseeRandom(IGrille gr) throws InterruptedException {
		// TODO Auto-generated method stub
		gr.setCurrentboat(this);
		gr.TirFuseeRandom();
		this.munEclair = false;
	}

}
