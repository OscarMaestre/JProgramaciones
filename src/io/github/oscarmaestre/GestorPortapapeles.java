package io.github.oscarmaestre;

import javafx.collections.ObservableList;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class GestorPortapapeles {

	/* Copia al portapapeles los String que estén seleccionados
	 * en el ListView
	 */
	public static void copiar(ListViewFixed<String> lista, String eol){
		Clipboard portapapeles=Clipboard.getSystemClipboard();
		ObservableList<String> cadenasSelec;
		cadenasSelec= lista.getSelectionModel().getSelectedItems();
		String cadena="";
		for (String cad: cadenasSelec){
			cadena = cadena + cad + eol;
			
		}
		System.out.println(cadena);
		ClipboardContent contenido=new ClipboardContent();
		contenido.putString(cadena);
		portapapeles.setContent(contenido);
		
	}

}
