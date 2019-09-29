package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.HashObj;

public class StringHash implements IHashTable<HashObj, Integer> {
	private int m;
	private HashObj[] vetor;
	private List<HashObj> areaAux;
	
	@SuppressWarnings("unused")
	private StringHash() {}
	
	public StringHash(int m) {
		this.m = m;
		this.vetor   = new HashObj[this.m];
		this.areaAux = new ArrayList<HashObj>();
	}
		
	@Override
	public int getUsedSize() {
		int i = 0;
		
		for(HashObj aux : this.vetor)
			if(aux != null)
				i++;
		
		return this.areaAux.size() + i;
	}

	@Override
	public int getMaxHashTableSize() {
		return m;
	}

	@Override
	public Integer hash(HashObj data) {
		int aux, 
			tot = 0;
		String hashObjStr = data.getStr();
		
		for(int i = 0; i < hashObjStr.length(); i++) {
			aux = (int) (hashObjStr.charAt(i) * Math.pow(i+1, 2));
			tot += aux;
		}
	
	 	return tot % m;
	}

	// Espera-se que a função de espalhamento seja boa, 
	// caso contrário gastará-se muita memória desnecessariamente, 
	// sem garantir uma busca eficiente, devido à alta taxa de colisões.
	@Override
	public void insert(HashObj obj) throws Exception {
		String str = obj.getStr();
		if(!str.isEmpty()) {
			int pos = this.hash(obj);
			
			if(this.vetor[pos] == null) {
				this.vetor[pos] = obj;
			} else {
				this.areaAux.add(obj);
			}
		} else {
			throw new Exception("Esta palavra não é válida para ser inserida na tabela hash!");
		}	
	}

	@Override
	public HashObj remove(HashObj obj) throws Exception {
		HashObj hashAux = this.vetor[this.hash(obj)];
		
		if(hashAux == null) {
			Iterator<HashObj> it = this.areaAux.iterator();
			
			while(it.hasNext()) {
				hashAux = it.next();
				if(hashAux.getStr().equals(obj.getStr())) {
					it.remove();
					break;
				}
			}
			return hashAux;
		}
		
		return hashAux;
	}

	@Override
	public boolean exists(HashObj obj) throws Exception {
		HashObj aux = this.vetor[this.hash(obj)];
		
		if(aux == null) {
			aux = this.areaAux
						.stream()
						.filter(hObj -> this.hash(hObj).equals(this.hash(obj)) && 
										hObj.getStr().equals(obj.getStr()))
						.findFirst()
						.orElse(null);
			
			return aux != null;
		} else {
			if(!aux.getStr().equals(obj.getStr())) {
				aux = this.areaAux
						.stream()
						.filter(hObj -> this.hash(hObj).equals(this.hash(obj)) && 
										hObj.getStr().equals(obj.getStr()))
						.findFirst()
						.orElse(null);
				return aux != null;
			} else {
				return true;
			}
		}
	}

	@Override
	public HashObj find(HashObj obj) throws Exception {
		HashObj aux = this.vetor[this.hash(obj)];
		
		if(aux == null) {
			aux = this.areaAux
						.stream()
						.filter(hObj -> hObj.getStr().equals(obj.getStr()))
						.findFirst()
						.orElse(null);
			
			return aux;
		} else {
			if(aux.getStr().equals(obj.getStr()))
				return aux;
			else 
				return this.areaAux
						.stream()
						.filter(hObj -> this.hash(hObj).equals(this.hash(obj)) && 
										hObj.getStr().equals(obj.getStr()))
						.findFirst()
						.orElse(null);
		}
	}

	@Override
	public List<HashObj> getAll() throws Exception {
		List<HashObj> all = 
				this.areaAux.subList(0, this.areaAux.size());
		
		for(HashObj aux : this.vetor)
			if(aux != null)
				all.add(aux);
				
		return all;
	}	
}
