package MetodosDeClasificacion.Bayes;

import MetodosDeClasificacion.Constantes;
import MetodosDeClasificacion.TipoDato;

public class Bayes {
	private int NUM_CLUSTERS;    // Total clusters.
    @SuppressWarnings("unused")
	private int TOTAL_DATA;      // Total data points.
    private TipoDato SAMPLES[];
	private Matriz[] matrizDeM;
	private Matriz[] matrizDeC; 
	private String[] s;
	
	public Bayes(int numCentros,TipoDato[] ejemplos,int tamaño){
		SAMPLES = ejemplos;
		NUM_CLUSTERS = numCentros;
		TOTAL_DATA = tamaño;
		matrizDeM = new Matriz[NUM_CLUSTERS];
		matrizDeC = new Matriz[NUM_CLUSTERS];
		
		for(int i=0;i< NUM_CLUSTERS;i++)
		{
			matrizDeM[i]= new Matriz(1,SAMPLES[0].getX().length);
			matrizDeC[i]= new Matriz(SAMPLES[0].getX().length,SAMPLES[0].getX().length);
		}
		
		s = new String[NUM_CLUSTERS];
		s[0] = SAMPLES[0].getS();
		s[1] = SAMPLES[50].getS();
		initialize();		
	}
	
	public boolean estaBien(TipoDato t){
		double[] prob = new double[NUM_CLUSTERS];
		
		for(int i = 0; i < NUM_CLUSTERS; i++){
			
			Matriz aux = new Matriz(1,t.getX().length);
    		for(int x = 0; x < t.getX().length; x++){
    			aux.setElemento(0, x, t.getX()[x]);
    		}
			
    		Matriz aux1 = aux.suma(matrizDeM[i].opuesta());
    		Matriz aux2 = matrizDeC[i].inversa2();
    		Matriz aux3 = aux1.transpuesta();
    		Matriz aux4 = aux1.producto(aux2);
    		Matriz aux5 = aux4.producto(aux3);
    		
    		double delante = 1.0/(2*Math.PI*matrizDeC[i].determinante());
    		double exponente = aux5.getElemento(0, 0)/2.0;
    		
    		
    		
    		prob[i] = Math.pow(delante, -exponente);
			//prob[i] = aux.suma(matrizDeM[i].opuesta()).producto(matrizDeC[i].inversa()).producto(aux.suma(matrizDeM[i].opuesta()).transpuesta()).getElemento(0,0);
		}
		
		
		int marca = 0;
    	double max = Double.MIN_VALUE;
    	for(int i = 0; i < NUM_CLUSTERS; i++){
    		if(prob[i]>max){
    			marca = i;
    			max = prob[i];
    		}
    	}
    	
    	if(this.s[marca].equals(t.getS())){
    		return true;
    	}else{
    		return false;
    	}
	}

	
	private Matriz dameM(TipoDato t){
		Matriz aux = new Matriz(1,t.getX().length);
		for(int x = 0; x < t.getX().length; x++){
			aux.setElemento(0, x, t.getX()[x]);
		}
		return aux;
	}
	
	private void initialize(){
		String s = SAMPLES[0].getS();
		int j = 0;
		
		for(int i = 0; i < Constantes.TOTAL_DATA; i++){
			
			if(!s.equalsIgnoreCase(SAMPLES[i].getS())){
				matrizDeM[j] = matrizDeM[j].producto(0.02);
				s = SAMPLES[i].getS();
				j++;
			}
			
			matrizDeM[j] = matrizDeM[j].suma(dameM(SAMPLES[i]));
		}
		matrizDeM[j] = matrizDeM[j].producto(0.02);
		
		s = SAMPLES[0].getS();
		j = 0;
		for(int i = 0; i < Constantes.TOTAL_DATA; i++){
			
			if(!s.equalsIgnoreCase(SAMPLES[i].getS())){
				matrizDeC[j] = matrizDeC[j].producto(0.02);
				s = SAMPLES[i].getS();
				j++;
			}
			
			Matriz aux = dameM(SAMPLES[i]);
			aux = aux.suma(matrizDeM[j].opuesta());
			matrizDeC[j] = matrizDeC[j].suma(aux.transpuesta().producto(aux));
		}
		matrizDeC[j] = matrizDeC[j].producto(0.02);
		
		
		/*for(int i = 0; i < 50; i++){
			matrizDeM[0] = matrizDeM[0].suma(dameM(SAMPLES[i]));
		}
		matrizDeM[0] = matrizDeM[0].producto(0.02);

		for(int i = 50; i < TOTAL_DATA; i++){
			matrizDeM[1] = matrizDeM[1].suma(dameM(SAMPLES[i]));
		}
		matrizDeM[1] = matrizDeM[1].producto(0.02);
		
		for(int i = 0; i < 50; i++){
			Matriz aux = dameM(SAMPLES[i]);
			aux = aux.suma(matrizDeM[0].opuesta());
			matrizDeC[0] = matrizDeC[0].suma(aux.transpuesta().producto(aux));
		}
		matrizDeC[0] = matrizDeC[0].producto(0.02);
		
		for(int i = 50; i < 100; i++){
			Matriz aux = dameM(SAMPLES[i]);	
			aux = aux.suma(matrizDeM[1].opuesta());
			matrizDeC[1] = matrizDeC[1].suma(aux.transpuesta().producto(aux));
		}
		matrizDeC[1] = matrizDeC[1].producto(0.02);*/
    }
}
