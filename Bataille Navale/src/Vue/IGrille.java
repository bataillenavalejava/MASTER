/*
 * 
 */
package Vue;

import java.awt.Color;

import javax.swing.JButton;

import modele.ABateau;
import modele.AJoueur;

/**
 * The Interface IGrille.
 */
public interface IGrille {

	/**
	 * Check alive bateau.
	 */
	void checkAliveBateau();

	/**
	 * Check possible move.
	 *
	 * @param aBateau the a bateau
	 * @param string  the string
	 */
	void checkPossibleMove(ABateau aBateau, String string);

	/**
	 * Check possible move random.
	 *
	 * @param aBateau the a bateau
	 * @param string  the string
	 */
	void checkPossibleMoveRandom(ABateau aBateau, String string);

	/**
	 * Gets the buttons.
	 *
	 * @return the buttons
	 */
	JButton[][] getButtons();

	/**
	 * Gets the buttons 1.
	 *
	 * @return the buttons 1
	 */
	JButton[][] getButtons1();

	/**
	 * Gets the buttonsdummy.
	 *
	 * @return the buttonsdummy
	 */
	JButton[][] getButtonsdummy();

	/**
	 * Gets the caseclicked.
	 *
	 * @return the caseclicked
	 */
	String getCaseclicked();

	/**
	 * Gets the c IA.
	 *
	 * @return the c IA
	 */
	ABateau getcIA();

	/**
	 * Gets the c joueur.
	 *
	 * @return the c joueur
	 */
	ABateau getcJoueur();

	/**
	 * Gets the cr IA.
	 *
	 * @return the cr IA
	 */
	ABateau getCrIA();

	/**
	 * Gets the cr joueur.
	 *
	 * @return the cr joueur
	 */
	ABateau getCrJoueur();

	/**
	 * Gets the currentboat.
	 *
	 * @return the currentboat
	 */
	ABateau getCurrentboat();

	/**
	 * Gets the d IA.
	 *
	 * @return the d IA
	 */
	ABateau getdIA();

	/**
	 * Gets the d joueur.
	 *
	 * @return the d joueur
	 */
	ABateau getdJoueur();

	/**
	 * Gets the s IA.
	 *
	 * @return the s IA
	 */
	ABateau getsIA();

	/**
	 * Gets the s joueur.
	 *
	 * @return the s joueur
	 */
	ABateau getsJoueur();

	/**
	 * Checks if is case isadv.
	 *
	 * @return true, if is case isadv
	 */
	boolean isCaseIsadv();

	/**
	 * Setbutton.
	 *
	 * @param i      the i
	 * @param j      the j
	 * @param string the string
	 * @param color  the color
	 */
	void setbutton(int i, int j, String string, Color color);

	/**
	 * Setbutton 1.
	 *
	 * @param i      the i
	 * @param j      the j
	 * @param string the string
	 * @param color  the color
	 */
	void setbutton1(int i, int j, String string, Color color);

	/**
	 * Setbuttondummy.
	 *
	 * @param i      the i
	 * @param j      the j
	 * @param string the string
	 * @param color  the color
	 */
	void setbuttondummy(int i, int j, String string, Color color);

	/**
	 * Sets the caseclicked.
	 *
	 * @param caseclicked the new caseclicked
	 */
	void setCaseclicked(String caseclicked);

	/**
	 * Sets the case isadv.
	 *
	 * @param caseIsadv the new case isadv
	 */
	void setCaseIsadv(boolean caseIsadv);

	/**
	 * Sets the currentboat.
	 *
	 * @param currentboat the new currentboat
	 */
	void setCurrentboat(ABateau currentboat);

	/**
	 * Sets the win.
	 *
	 * @param j the new win
	 */
	void setWin(AJoueur j);

	/**
	 * Tir.
	 */
	void Tir();

	/**
	 * Tir fusee.
	 */
	void TirFusee();

	/**
	 * Tir fusee random.
	 */
	void TirFuseeRandom();

	/**
	 * Tir random.
	 */
	void TirRandom();

}