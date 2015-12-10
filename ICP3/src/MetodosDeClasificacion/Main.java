package MetodosDeClasificacion;

import MetodosDeClasificacion.Bayes.Bayes;
import MetodosDeClasificacion.KMedias.KMeans_Ex4a;
import MetodosDeClasificacion.SOM.SOM;

public class Main {
	public static void main(String[] args)
	{

		TipoDato SAMPLES[] = new TipoDato[Constantes.TOTAL_DATA];
		Lectura l = new Lectura(Constantes.carga);
		l.open();
		for(int i = 0; i < Constantes.TOTAL_DATA; i++){			
			SAMPLES[i] = l.leeLinea();
		}
		l.close();
		String ini = "TestIris";
		String fin = ".txt";		
		
		
		SOM s = new SOM(Constantes.NUM_CLUSTERS,Constantes.TOTAL_DATA,SAMPLES,Constantes.centros,Constantes.NUM_ITER_MAX);
		KMeans_Ex4a k = new KMeans_Ex4a(Constantes.NUM_CLUSTERS, Constantes.TOTAL_DATA, SAMPLES);
		Bayes b = new Bayes(Constantes.NUM_CLUSTERS, SAMPLES,Constantes.TOTAL_DATA);
		
		for(int i = 0; i < Constantes.TOTAL_DATA; i++){
			l = new Lectura(ini.concat(Integer.toString(i)).concat(fin));	
			l.open();
			TipoDato x1 = l.leeLinea();
			if(b.estaBien(x1) && k.estaBien(x1) && s.estaBien(x1))
				System.out.println(x1.getS());
			else
				System.out.println("Error con el fichero " + ini.concat(Integer.toString(i)).concat(fin) + " clasificacion Bayes");
			l.close();	
		}

	}
}
