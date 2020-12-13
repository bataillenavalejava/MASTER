package modele;

import Vue.IGrille;

public interface Ibateau {

	public String getNom();

	public int getSize();

	public boolean getStatus();

	public void setIsAlive(boolean isAlive);

	public void tirer(IGrille g) throws InterruptedException;

	public void tirerRandom(IGrille g) throws InterruptedException;
}
