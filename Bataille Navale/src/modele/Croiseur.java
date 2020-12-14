/*
 * 
 */
package modele;

/**
 * The Class Croiseur.
 */
public class Croiseur extends ABateau {

	/**
	 * Instantiates a new croiseur.
	 *
	 * @param SharedObject the shared object
	 */
	public Croiseur(Object SharedObject) {
		super(SharedObject);
		this.taille = 5;
		this.name = "Cruiser";
		this.impactrange = 0;
	}

}
