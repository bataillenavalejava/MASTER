package Vue;

import java.awt.Color;

import javax.swing.JButton;

import modele.ABateau;
import modele.AJoueur;

public interface IGrille {

	void Tir();

	void TirFusee();

	void TirRandom();

	void TirFuseeRandom();

	ABateau getcJoueur();

	ABateau getdJoueur();

	ABateau getsJoueur();

	ABateau getCrJoueur();

	ABateau getcIA();

	ABateau getdIA();

	ABateau getsIA();

	ABateau getCrIA();

	String getCaseclicked();

	void setCaseclicked(String caseclicked);

	JButton[][] getButtons();

	JButton[][] getButtons1();

	JButton[][] getButtonsdummy();

	void setbutton(int i, int j, String string, Color color);

	void setbutton1(int i, int j, String string, Color color);

	void setbuttondummy(int i, int j, String string, Color color);

	void checkAliveBateau();

	void setWin(AJoueur j);

	void checkPossibleMove(ABateau aBateau, String string);

	void checkPossibleMoveRandom(ABateau aBateau, String string);

	boolean isCaseIsadv();

	void setCaseIsadv(boolean caseIsadv);

	ABateau getCurrentboat();

	void setCurrentboat(ABateau currentboat);

}