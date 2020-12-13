package modele;

public class Croiseur extends ABateau {

	public Croiseur(Object SharedObject) {
		super(SharedObject);
		this.taille = 5;
		this.name = "Cruiser";
		this.impactrange = 0;
	}

}
