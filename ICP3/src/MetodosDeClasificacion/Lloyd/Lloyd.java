package MetodosDeClasificacion.Lloyd;

import MetodosDeClasificacion.TipoDato;

public class Lloyd {
	private int NUM_CLUSTERS;    // Total clusters.
    private int TOTAL_DATA;      // Total data points.
    private TipoDato SAMPLES[];
    private TipoDato[] centros;
    private int numIter;
    
	public Lloyd(int NUM_CLUSTERS,int TOTAL_DATA,TipoDato SAMPLES[],TipoDato[] centros){
    	this.NUM_CLUSTERS = NUM_CLUSTERS;
    	this.TOTAL_DATA = TOTAL_DATA;
    	this.SAMPLES = SAMPLES;
    	this.centros = centros;
    	numIter = 0;

        initialize();
    }
	
	public boolean estaBien(TipoDato t){
		double[] prob = new double[NUM_CLUSTERS];
		
		for(int i = 0; i < NUM_CLUSTERS; i++){	
			prob[i] = TipoDato.distEcuclidiana(t, centros[i]);
		}

		
		int marca = 0;
    	double max = Double.MAX_VALUE;
    	for(int i = 0; i < NUM_CLUSTERS; i++){
    		if(prob[i]<max){
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
	
	private double razon(){
		return 1.0/(1.0+Math.pow(numIter,3));
	}
	
	private void initialize(){
		double[] dist = new double[NUM_CLUSTERS];
		
    	for(int i = 0; i < TOTAL_DATA; i++){
    		
    		for(int j = 0; j < NUM_CLUSTERS; j++){
    			dist[j] = Math.pow(TipoDato.distEcuclidiana(SAMPLES[i], centros[j]),2);
    		}
    		
    		double min = Double.MAX_VALUE;
    		int marca = -1;
    		for(int j = 0; j < NUM_CLUSTERS; j++){
    			if(dist[j]<min){
    				min = dist[j];
    				marca = j;
    			}
    		}
    		
    		TipoDato aux = SAMPLES[i].resta(centros[marca]);
    		double r =  razon();
    		TipoDato mult = aux.multiplicacion(r);
    		TipoDato suma = centros[marca].suma(mult);
    		centros[marca] = new TipoDato(suma.getX(),centros[marca].getS());
    		numIter++;
    	} 
       	
    	
    }
}
