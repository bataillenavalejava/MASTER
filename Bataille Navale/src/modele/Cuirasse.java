/*
 * 
 */
package modele;

/**
 * The Class Cuirasse.
 */
public class Cuirasse extends ABateau {

	/**
	 * Instantiates a new cuirasse.
	 *
	 * @param SharedObject the shared object
	 */
	public Cuirasse(Object SharedObject) {
		super(SharedObject);
		this.taille = 7;
		this.name = "Battleship";
		this.impactrange = 2;
	}
}
