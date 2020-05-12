package pglp_9.dessin;

public abstract class Forme {
	
	String nom;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Forme(String nom) {
		this.nom=nom;
	}
	
	public abstract void deplace(int x,int y);
	
	public abstract void affiche();

}
