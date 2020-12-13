package modele;

import Vue.IGrille;

public abstract class ABateau implements Ibateau {

	protected boolean IsAlive;
	protected boolean aBouger;
	protected int taille;
	protected String name;
	protected final Object SharedObject;
	protected int impactrange;

	public ABateau(Object obj) {
		this.IsAlive = true;
		SharedObject = obj;
	}

	public int getImpactrange() {
		return impactrange;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int getSize() {
		return this.taille;
	}

	@Override
	public boolean getStatus() {
		return this.IsAlive;
	}

	public boolean isABouger() {
		return aBouger;
	}

	public void setABouger(boolean aBouger) {
		this.aBouger = aBouger;
	}

	@Override
	public void setIsAlive(boolean isAlive) {
		this.IsAlive = isAlive;
	}

	@Override
	public void tirer(IGrille gr) throws InterruptedException {
		System.out.println("En attente de tir pour le " + this.getNom());
		gr.setCurrentboat(this);
		try {
			gr.Tir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void tirerRandom(IGrille gr) throws InterruptedException {
		gr.setCurrentboat(this);
		gr.TirRandom();
	}

}
