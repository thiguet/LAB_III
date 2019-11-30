
public class Solution {
	private int exchange;
	private int[] weights;

	public Solution() {}
	
	public Solution(int exchange, int[] weights) {
		this.exchange = exchange;
		this.weights = weights;		
	}

	public int getValues() {
		return exchange;
	}

	public void setValues(int values) {
		this.exchange = values;
	}

	public int[] getWeights() {
		return weights;
	}

	public void setWeights(int[] weights) {
		this.weights = weights;
	}
	
	private void firstCase(int[][] matrizDinamica) {
		int j = 0;

		// O peso é maior que o troco em análise nesta iteração.
		for(int i = 0 ; i < matrizDinamica.length ; i++) {		
			if((i-1) >= 0) { 
				matrizDinamica[i][j] = (i / this.weights[j]); // Use o número de moedas atual.
			} else {
				matrizDinamica[i][j] = 0; // Não existe solução.
			}
		}
	}
	
	public int getResult() {
		int [][] matrizDinamica = new int [this.exchange + 1][this.weights.length];
		
		for(int aux = 0; aux < matrizDinamica.length; aux++) {
			matrizDinamica[aux] = new int [this.weights.length];
		}
		
		this.firstCase(matrizDinamica);
				
		// Percorre cada um dos limites
		for(int j = 1; j < this.weights.length; j++) {		
			// Percorre de 0 a 10
			for(int i = 0 ; i < matrizDinamica.length; i++) {
				int lastIndex = (i - this.weights[j] < 0) ? 0 : i - this.weights[j];
				
				if(minimize(matrizDinamica, i, j, lastIndex)) {
					int resp =  (i / this.weights[j]);
					if((i % this.weights[j]) != 0) {
						resp += (matrizDinamica[i % this.weights[j]][j]);
					}
					matrizDinamica[i][j] = resp; // Use a nova resposta.
				} else {
					matrizDinamica[i][j] = ((int) matrizDinamica[i][j-1]); // Use a solução anterior.
				}
			}
		}
		System.out.print("\nPesos:   Indices: ");
		for(int j = 0 ; j < matrizDinamica.length; j++) {
			System.out.print(j + " ");
		}	
		
		System.out.print("\n");
		for(int j = 0 ; j < this.weights.length; j++) {
			System.out.print("\n" + this.weights[j]+ "                ");
			for(int i = 0 ; i < matrizDinamica.length; i++) {
				System.out.print(" ");
				System.out.print(matrizDinamica[i][j]);
			}
		}
		
		return 1;
	}
	
	public boolean minimize(int [][] matriz, int i, int j, int lastIndex) {	
		int a = (i / this.weights[j]); // Nova resposta otimizada.
		int b = ((int) matriz[i][j-1]);							// Resposta anterior.
		
		if((i % this.weights[j]) != 0) {
			a += (matriz[i % this.weights[j]][j]);
		}
		
		if(a == 0) 
			return false;
		
		return a < b;
	} 
}
