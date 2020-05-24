package pglp_9.dessin;


import java.util.ArrayList;

/**
 * classe abstraite DAO
 *
 * @param <T> type 
 */
public abstract class DAO<T> {
	
	/**
	 * rajouter au dao
	 * @param object l'objet inserer
	 * @return creation
	 * @throws SQLException exception
	 */
	public abstract T create(T object);
   
    /**
     * chercher un objet
     * @param id l'id de l'objet
     * @return l'objet recherché
     */
    public abstract T find(String id);
       
    /**
     * modifier 
     * @param object
     * @return
     * @throws SQLException
     */
    public abstract T update(T object);
   
    /**
     * supprimer
     * @param object
     * @throws SQLException
     */
    public abstract void delete(T object);
    
    /**
     * @return les élements
     */
    public abstract ArrayList<T> show();
  
}

