package Modèle;

public abstract class ABateau implements Ibateau{

	protected boolean IsAlive;
	protected int taille;
	protected String name;
	
	public ABateau() { 
		this.IsAlive = true;
	}
	
	public void tirer() { 
		
	}
	
	public int getSize() { 
		return this.taille;
	}

	public String getNom() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
