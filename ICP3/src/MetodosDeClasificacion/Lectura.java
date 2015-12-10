package MetodosDeClasificacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class Lectura
{
	private File archivo;
	private FileReader fr;
	private BufferedReader br;
	private String n;

	private boolean exito;
	private boolean leidoCompletamente;
	public Lectura(String s)
	{
		this.n =s;
		exito=true;
		leidoCompletamente=false;
		 archivo = new File (s);
		/* try {
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			exito=false;
			System.err.println("Error al abrir el fichero  "+ s);
		}	*/
	}

	
	public boolean open() {
		try {
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			return true;
		} catch (FileNotFoundException e) {
			exito=false;
			System.err.println("Error al abrir el fichero  "+ n);
			return false;
		}	
	}

	
	public TipoDato leeLinea()
	{
		if(exito && !leidoCompletamente)
		{
			String linea;
			try {
				linea = br.readLine();
				if(linea==null || linea.isEmpty())
				{
					throw new IOException();
				}
				
				return parse(linea);
				
				
			} catch (IOException e) 
			{
				exito=false;
				leidoCompletamente=true;
				return null;
			}
			
		}
		return null;
	}
	
	public boolean isLeido(){
		return !leidoCompletamente;
	}
	
	public TipoDato parse(String s)
	{
		StringTokenizer st = new StringTokenizer(s,",");
		
		int num= st.countTokens();
		String tokens[] = new String[num];
		int i=0;
		while(st.hasMoreTokens())
		{
			tokens[i]= st.nextToken();
			i++;
		}
		
		double d[]  = new double[num-1];
		
		for(int j=0;j<4;j++)
		{
			d[j]= Double.valueOf(tokens[j]);
		}
		
		return new TipoDato(d,tokens[4]);
	}
	
	public void close(){
		try {
			fr.close();
			br.close();
		} catch (IOException e1) {
			System.err.println("Error al cerrar el fichero");
		}

	}
}
