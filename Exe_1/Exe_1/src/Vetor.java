public class Vetor {
	 private static int TAMANHO = 10;
	 private static final int NOT_FOUND = -1;
	 private int v [] = new int[TAMANHO]; // Armazena os elementos
	 private int numElementos; // Informação sobre o número de elementos inseridos
	
	 public Vetor() {
		 this.numElementos = 0;
		 // Em JAVA a criação do vetor por si, já garante que todos os elementos do vetor serão zeros.
		 // Isto ocorre aqui para fins didáticos.
		 for(int i = 0; i < Vetor.TAMANHO ; i++) {
			 v[i] = 0;
		 }
	 }
	 
	 public int obtemTamanho() { 
		 return Vetor.TAMANHO; 
	 }
	 
	 private void extendArrayToV(int[] aux) {
		 for(int i = 0 ; i < aux.length; i++) {
			 this.v[i] = aux[i];
		 }
	 }
	 
	 public void insereNoFinal(int novoElemento) {
		 if((this.numElementos + 1) > Vetor.TAMANHO) {
			 Vetor.TAMANHO *= 2; 
			 int[] aux = this.v;
			 this.v = new int [Vetor.TAMANHO];
			 this.extendArrayToV(aux);
		 } else {
			 v[this.numElementos++] = novoElemento;	 
		 }
	 }
	 
	 public int[] removerElemento(int elemento) {
		 int cont = 0;
		 int[] result = null;
		 
		 for(int i = 0; i < Vetor.TAMANHO ; i++) {
			 if(this.v[i] == elemento) {
				 cont++;
				 this.v[i] = -1;
			 }
		}
		 
		 if(cont > 0) {
			 result = new int [cont];
			 for(int j = 0 ; j < cont; j++) {
				 result[j] = elemento;
			 }
			 // Fix array positions: 
			 
			 
			 
		 } 
		 
		 
		 return result;
	 }
	 
	 public int posicaoDe (int elemento) { 
		 for(int i = 0; i < Vetor.TAMANHO; i++) {
			 if(v[i] == elemento) 
				 return i;
		 }
		 return Vetor.NOT_FOUND;
	 }
	 
	 public void alteraEm (int pos, int novoValor) throws Exception {
		 // Se for uma posição válida
		 if(pos < Vetor.TAMANHO && pos >= 0) {
			 v[pos] = novoValor;
		 } else { 
			 throw new Exception("A posição '" + pos + "' não é válida !");
		 }
	 }
	 
	 // Not Implemented Yet
	 public int elementoDe (int pos) { 
		 return 0; 	
	 }
	 
	 public int elementoEm (int pos) { 
		 if(pos < this.numElementos) 
			 return v[pos];
		 else 
			 return NOT_FOUND;
	 }
	 
	 public void reverte() {
		 for(int i = 0, aux; i < this.numElementos / 2; i++) {
			 aux = v[i];
			 v[i] = v[this.numElementos - 1 - i];
			 v[this.numElementos - 1 - i] = aux;
		 }
	 }
	 
	 public void imprime() {
		 String str = "";
		 for(int i = 0 ; i < this.numElementos; i++) {
			 str+= v[i] + " ";
	  	 }
		 System.out.println(str);
	 }
}