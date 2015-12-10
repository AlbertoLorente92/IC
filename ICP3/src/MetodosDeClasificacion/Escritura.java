package MetodosDeClasificacion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Escritura {
	private File archivo;
	private FileWriter fr;

	public Escritura(TipoDato t,String s)
	{

		 archivo = new File (s);
		
		try {
			fr = new FileWriter (archivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fr.write(t.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
