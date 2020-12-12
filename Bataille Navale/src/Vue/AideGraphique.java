package Vue;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AideGraphique {
	public AideGraphique() {
		showImg();
	}
	/**
	 * 
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
