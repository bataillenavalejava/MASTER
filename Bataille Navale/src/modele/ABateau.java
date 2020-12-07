package modele;

import Vue.IGrille;


public abstract class ABateau implements Ibateau {

	protected boolean IsAlive;
	protected boolean aBouger;
	protected int taille;
	protected String name;
	protected final Object SharedObject;
	
	public ABateau(Object obj) { 
		this.IsAlive = true;
		SharedObject = obj;
	}
	
	

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
		 
		public void tirerRandom(IGrille gr) throws InterruptedException { 
			gr.setCurrentboat(this);
			gr.TirRandom();
		}

	public void setIsAlive(boolean isAlive) {
			this.IsAlive = isAlive;
		}

	public int getSize() { 
		return this.taille;
	}
	
	public boolean isABouger() {
		return aBouger;
	}

	public void setABouger(boolean aBouger) {
		this.aBouger = aBouger;
	}

	public String getNom() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	public boolean getStatus() { 
		return this.IsAlive;
	}

}
