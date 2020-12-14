/*
 * 
 */
package modele;

/**
 * The Class JoueurHumain.
 */
public class JoueurHumain extends AJoueur {

	/**
	 * Instantiates a new joueur humain.
	 *
	 * @param cJoueur  the c joueur
	 * @param dJoueur  the d joueur
	 * @param crJoueur the cr joueur
	 * @param sJoueur  the s joueur
	 */
	public JoueurHumain(ABateau cJoueur, ABateau dJoueur, ABateau crJoueur, ABateau sJoueur) {
		// TODO Auto-generated constructor stub
		super(cJoueur, dJoueur, crJoueur, sJoueur, "Vous");
	}

}
