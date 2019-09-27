package util;

import java.util.List;

import model.HashObj;

public interface IHashTable<E, F> {
	public int getUsedSize();
	public int getMaxHashTableSize();
	public F hash(E data);
	
	public void insert(E obj) throws Exception;
	public HashObj remove(E obj) throws Exception;
	public boolean exists(E obj) throws Exception;
	public E find(E obj) throws Exception;
	public List<E> getAll() throws Exception;		
}
