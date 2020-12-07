package modele;

import Vue.IGrille;

public interface Ibateau {

	
	public void tirer(IGrille g) throws InterruptedException;
	public void tirerRandom(IGrille g) throws InterruptedException;
	public void setIsAlive(boolean isAlive);
	public int getSize();
	public String getNom();
	public boolean getStatus();
}
