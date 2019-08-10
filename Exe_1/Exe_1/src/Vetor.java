public class Vetor {
	 public static final int TAMANHO = 10;
	 private static final int NOT_FOUND = -1;
	 private int v [] = new int[TAMANHO]; // Armazena os elementos
	 private int numElementos; // Informa��o sobre o n�mero de elementos inseridos
	
	 public Vetor() {
		 this.numElementos = 0;
		 // Em JAVA a cria��o do vetor por si, j� garante que todos os elementos do vetor ser�o zeros.
		 // Isto ocorre aqui para fins did�ticos.
		 for(int i = 0; i < Vetor.TAMANHO ; i++) {
			 v[i] = 0;
		 }
	 }
	 
	 public int obtemTamanho() { 
		 return Vetor.TAMANHO; 
	 }
	 
	 public void insereNoFinal(int novoElemento) {
		 v[this.numElementos++] = novoElemento;
	 }
	 
	 public int posicaoDe (int elemento) { 
		 for(int i = 0; i < Vetor.TAMANHO; i++) {
			 if(v[i] == elemento) 
				 return i;
		 }
		 return Vetor.NOT_FOUND;
	 }
	 
	 public void alteraEm (int pos, int novoValor) throws Exception {
		 // Se for uma posi��o v�lida
		 if(pos < Vetor.TAMANHO && pos >= 0) {
			 v[pos] = novoValor;
		 } else { 
			 throw new Exception("A posi��o '" + pos + "' n�o � v�lida !");
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