package ui;

import javax.swing.JOptionPane;

import model.Medico;
import service.MedicoService;

public class MedicosUI {
	MedicosUI() {};

	public static Medico cadastrarMedico() {
		String nomeMedico 	  = JOptionPane.showInputDialog(null, "Insira o nome do Médico: ");
		String telefoneMedico = JOptionPane.showInputDialog(null, "Insira o telefone do Médico: ");

		Medico aux = new Medico(1, nomeMedico);
		aux.setTelefone(telefoneMedico);

		MedicoService medService;

		return aux;
	}
}
