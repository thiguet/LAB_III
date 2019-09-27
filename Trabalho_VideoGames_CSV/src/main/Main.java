package main;

import javax.swing.JOptionPane;

import model.HashObj;
import model.Word;
import services.VGIndiceInvertido;
import util.StringHash;

public class Main {
	public static void main ( String args[] ) {
//		AVGCSVService vgCSVService = new VGCSVService();
//		vgCSVService.exportCSVToBin();
		VGIndiceInvertido indInvertido = 
				new VGIndiceInvertido(
						Config.VIDEO_GAMES_FILE_PATH,
						Config.WORDS_LIST_FILE_PATH,
						Config.HASH_FILE_PATH);
		
//		indInvertido.genWordList();
//		indInvertido.buildIndiceInvertido();
		
		indInvertido.searchStr(JOptionPane.showInputDialog("Digite o texto para buscar: "));		
	}
}
