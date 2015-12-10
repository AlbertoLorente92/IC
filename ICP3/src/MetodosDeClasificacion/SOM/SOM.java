package MetodosDeClasificacion.SOM;

import MetodosDeClasificacion.TipoDato;

public class SOM {
	private int NUM_CLUSTERS;    // Total clusters.
    private int TOTAL_DATA;      // Total data points.
    private TipoDato SAMPLES[];
    private TipoDato[] centros;
    private double alphaInicial = 0.1;
    private double alphaFinal = 0.01, T = Math.pow(10,-5);
    private int numIter,maxIter;
    
	public SOM(int NUM_CLUSTERS,int TOTAL_DATA,TipoDato SAMPLES[],TipoDato[] centros,int maximoIteraciones){
    	this.NUM_CLUSTERS = NUM_CLUSTERS;
    	this.TOTAL_DATA = TOTAL_DATA;
    	this.SAMPLES = SAMPLES;
    	this.centros = centros;
    	numIter = 0;
    	maxIter = maximoIteraciones;
        initialize();
    }

	private double alphas(){
		return alphaInicial * Math.pow(alphaInicial/alphaFinal,numIter/maxIter);
	}
	
	private double kas(TipoDato t,TipoDato centro){
		double dist = TipoDato.distEcuclidiana(t, centro);
		
		double alfa = alphas();
		
		double num = Math.pow(dist,2);
		double den = 2 * Math.pow(alfa, 2);
		double res = -(num/den);
		
		double exp = Math.pow(Math.E, res);
		
		return exp;
	}
	
	public boolean estaBien(TipoDato t){
		double[] prob = new double[NUM_CLUSTERS];
		
		for(int i = 0; i < NUM_CLUSTERS; i++){	
			prob[i] = kas(t,centros[i]);
		}

		
		int marca = 0;
    	double max = Double.MIN_VALUE;
    	for(int i = 0; i < NUM_CLUSTERS; i++){
    		if(prob[i]>max){
    			marca = i;
    			max = prob[i];
    		}
    	}

    	
    	
    	if(centros[marca].getS().equals(t.getS())){
    		return true;
    	}else{
    		return false;
    	}
	}
	
	private void initialize(){

		
    	for(int i = 0; i < TOTAL_DATA; i++){
    		
    		for(int j = 0; j < NUM_CLUSTERS; j++){
    			double k = kas(SAMPLES[i],centros[j]);
    			
    			if(k>T){
    				centros[j] = centros[j].suma(SAMPLES[i].resta(centros[j]).multiplicacion(k));
    			}	
    		}
	
    		numIter++;
    	} 
       	
    	
    }
}
