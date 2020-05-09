package pglp_9.dessin;

public abstract class DAO<T> {
	
	public abstract T create(T object);
   
    public abstract T find(String id);
       
    public abstract T update(T object);
   
    public abstract void delete(T object);
}
