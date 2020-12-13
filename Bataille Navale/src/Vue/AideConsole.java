package Vue;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AideConsole {
	public AideConsole() {
		showImg();
	}

	/**
	 *
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
