/*
 * 
 */
package Vue;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * The Class AideGraphique.
 */
public class AideGraphique {

	/**
	 * Instantiates a new aide graphique.
	 */
	public AideGraphique() {
		showImg();
	}

	/**
	 * Show img with default pdf opener.
	 */
	private void showImg() {
		File myFile = new File("AideGraphique.pdf");
		// TODO Auto-generated method stub
		try {
			Desktop.getDesktop().open(myFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
