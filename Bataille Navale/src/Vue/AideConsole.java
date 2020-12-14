/*
 * 
 */
package Vue;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * The Class AideConsole.
 */
public class AideConsole {

	/**
	 * Instantiates a new aide console.
	 */
	public AideConsole() {
		showImg();
	}

	/**
	 * Show img with default pdf opener.
	 */
	private void showImg() {
		File myFile = new File("AideConsole.pdf");
		// TODO Auto-generated method stub
		try {
			Desktop.getDesktop().open(myFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
