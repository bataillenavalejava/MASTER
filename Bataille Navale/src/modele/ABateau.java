/*
 * 
 */
package modele;

import Vue.IGrille;

// TODO: Auto-generated Javadoc
/**
 * The Class ABateau.
 */
public abstract class ABateau implements Ibateau {

	/** The Is alive. */
	protected boolean IsAlive;

	/** The a bouger. */
	protected boolean aBouger;

	/** The taille. */
	protected int taille;

	/** The name. */
	protected String name;

	/** The Shared object. */
	protected final Object SharedObject;

	/** The impactrange. */
	protected int impactrange;

	/**
	 * Instantiates a new a bateau.
	 *
	 * @param obj constructor of the boats
	 */
	public ABateau(Object obj) {
		this.IsAlive = true;
		SharedObject = obj;
	}

	/**
	 * Gets the impactrange.
	 *
	 * @return the impactrange
	 */
	public int getImpactrange() {
		return impactrange;
	}

	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.name;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	@Override
	public int getSize() {
		return this.taille;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@Override
	public boolean getStatus() {
		return this.IsAlive;
	}

	/**
	 * Checks if is a bouger.
	 *
	 * @return true, if is a bouger
	 */
	public boolean isABouger() {
		return aBouger;
	}

	/**
	 * Sets the a bouger.
	 *
	 * @param aBouger the new a bouger
	 */
	public void setABouger(boolean aBouger) {
		this.aBouger = aBouger;
	}

	/**
	 * Sets the checks if is alive.
	 *
	 * @param isAlive the new checks if is alive
	 */
	@Override
	public void setIsAlive(boolean isAlive) {
		this.IsAlive = isAlive;
	}

	/**
	 * Tirer.
	 *
	 * @param gr the grid
	 * @throws InterruptedException the interrupted exception
	 */
	@Override
	public void tirer(IGrille gr) throws InterruptedException {
		System.out.println("En attente de tir pour le " + this.getNom());
		gr.setCurrentboat(this);
		try {
			gr.Tir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Tirer random.
	 *
	 * @param gr the grid
	 * @throws InterruptedException the interrupted exception
	 */
	@Override
	public void tirerRandom(IGrille gr) throws InterruptedException {
		gr.setCurrentboat(this);
		gr.TirRandom();
	}

}
