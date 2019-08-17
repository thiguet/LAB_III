import java.io.IOException;

import ui.Menu;

public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//		String fileName = "paciente.dat";
		//		int qtdPacientes = 20;
		//		
		//		Paciente[] paciente = new Paciente[20];
		//		
		//		for (int i = 0; i < qtdPacientes ; i++ ) {
		//			paciente[i] = new Paciente((i + 1), "Alice " + (i+1));
		//		}
		//		
		//		SaveFileSerializable escrita = new SaveFileSerializable();
		//		
		//		escrita.openFile(fileName);
		//		
		//		for (int i = 0; i < qtdPacientes ; i++ ) {
		//			escrita.setData(paciente[i]);
		//		}
		//		
		//		escrita.closeFile();
		//		
		//		ReadFileSerializable leitura = new ReadFileSerializable();
		//		
		//		leitura.openFile(fileName);
		//		
		//		Paciente aux;
		//		while ((aux = (Paciente)leitura.getData()) != null) {
		//			System.out.println( aux.getCodigo() +" - "+ aux.getNome() );
		//		}
		//		
		//		leitura.closeFile();

		String option = Menu.displayMenu();

		switch(option) {
		case "1": 
			break;
		default:  
			break;
		}

	}
}