/*
 * 
 */
package modele;

/**
 * The Class SousMarin.
 */
public class SousMarin extends ABateau {

	/**
	 * Instantiates a new sous marin.
	 *
	 * @param SharedObject the shared object
	 */
	public SousMarin(Object SharedObject) {

		super(SharedObject);
		this.taille = 1;
		this.name = "Submarine";
		this.impactrange = 0;
	}

}
