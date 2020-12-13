package Vue;

import java.awt.Color;

import javax.swing.JButton;

import modele.ABateau;
import modele.AJoueur;

public interface IGrille {

	void checkAliveBateau();

	void checkPossibleMove(ABateau aBateau, String string);

	void checkPossibleMoveRandom(ABateau aBateau, String string);

	JButton[][] getButtons();

	JButton[][] getButtons1();

	JButton[][] getButtonsdummy();

	String getCaseclicked();

	ABateau getcIA();

	ABateau getcJoueur();

	ABateau getCrIA();

	ABateau getCrJoueur();

	ABateau getCurrentboat();

	ABateau getdIA();

	ABateau getdJoueur();

	ABateau getsIA();

	ABateau getsJoueur();

	boolean isCaseIsadv();

	void setbutton(int i, int j, String string, Color color);

	void setbutton1(int i, int j, String string, Color color);

	void setbuttondummy(int i, int j, String string, Color color);

	void setCaseclicked(String caseclicked);

	void setCaseIsadv(boolean caseIsadv);

	void setCurrentboat(ABateau currentboat);

	void setWin(AJoueur j);

	void Tir();

	void TirFusee();

	void TirFuseeRandom();

	void TirRandom();

}