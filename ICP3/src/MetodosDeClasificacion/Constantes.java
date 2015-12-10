package MetodosDeClasificacion;

public class Constantes {
	public final static int NUM_CLUSTERS = 2;    
	public final static int TOTAL_DATA = 100;
	public final static String carga = "Iris2Clases.txt";
	public final static String[] test = {"TestIris01.txt","TestIris02.txt","TestIris03.txt"};
	private static double[] x = {4.0,3.0,1.0,0.0};
	private static double[] y = {5.0,2.0,3.0,1.0};	
	public final static int NUM_ITER_MAX = 1000;
	public final static TipoDato[] centros = {new TipoDato(x, "Iris-setosa"),new TipoDato(y, "Iris-versicolor")};;
	
}
