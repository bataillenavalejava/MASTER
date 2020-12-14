/*
 * 
 */
package modele;

import Vue.IGrille;

/**
 * The Interface Ibateau.
 */
public interface Ibateau {

	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom();

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize();

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public boolean getStatus();

	/**
	 * Sets the checks if is alive.
	 *
	 * @param isAlive the new checks if is alive
	 */
	public void setIsAlive(boolean isAlive);

	/**
	 * Tirer.
	 *
	 * @param g the g
	 * @throws InterruptedException the interrupted exception
	 */
	public void tirer(IGrille g) throws InterruptedException;

	/**
	 * Tirer random.
	 *
	 * @param g the g
	 * @throws InterruptedException the interrupted exception
	 */
	public void tirerRandom(IGrille g) throws InterruptedException;
}
