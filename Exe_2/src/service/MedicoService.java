package service;

import java.io.IOException;

import model.Medico;
import persistence.ReadFileSerializable;

public class MedicoService {
	MedicoService() {}

	public int getNextCode() {
		ReadFileSerializable leitura = new ReadFileSerializable();
		int nextCode = 0;
		try {
			leitura.openFile("medico.dat");

			Medico aux, 
				   med = null;
			
			while ((aux = (Medico)leitura.getData()) != null) {
				med = aux;
			}
			
			nextCode = (med.getCodigo() + 1);
		
			if(nextCode == 0) {
//				"Um erro inesperado ocorreu !"
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return nextCode;
	}
}
