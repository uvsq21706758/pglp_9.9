package pglp_9.dessin;

import java.util.ArrayList;

public class GroupeForme extends Forme{
	
	 private ArrayList<Forme> formes;

	public GroupeForme(String nom) {
		super(nom);
		 formes = new ArrayList<Forme>();
	}

	@Override
	public void deplace(int x, int y) {
		 for (Forme forme : formes) {
	            forme.deplace(x, y);
	        }
		
	}

	@Override
	public void affiche() {
		  System.out.println("Groupe :");
	        for (Forme forme : formes) {
	            forme.affiche();
	        }
	}
	public void ajoutForme(Forme forme) {
		this.formes.add(forme);
	}
	
	public void suppForme(final Forme forme) {
	        formes.remove(forme);
	    }
	
	public ArrayList<Forme> getList() {
        return this.formes;
    }
}
