package MetodosDeClasificacion.KMedias;

import java.util.ArrayList;

import MetodosDeClasificacion.TipoDato;

public class KMeans_Ex4a
{    
	private int NUM_CLUSTERS;    // Total clusters.
    private int TOTAL_DATA;      // Total data points.
    private TipoDato SAMPLES[];
	
    private ArrayList<Data> dataSet = new ArrayList<Data>();
    private ArrayList<Centroid> centroids = new ArrayList<Centroid>();
    
    private void initialize()
    {
    	int i = 0;
    	boolean cambio;
    	String s;
    	double x1,y1,z1,k1;

    	for(int j = 0; j < NUM_CLUSTERS; j++){
    		s = SAMPLES[i].getS();
    		cambio = false;
    		x1=y1=z1=k1=0;
    		
    		while(i<TOTAL_DATA && !cambio){
        		if(SAMPLES[i].getS().equals(s)){
        			x1 = x1 + SAMPLES[i].getX()[0];
        			y1 = y1 + SAMPLES[i].getX()[1];
        			z1 = z1 + SAMPLES[i].getX()[2];
        			k1 = k1 + SAMPLES[i].getX()[3];
        			i++;
        		}else{
        			cambio = true;
        		}
        	}
    		
    		centroids.add(new Centroid(x1/(double)i,y1/(double)i,z1/(double)i,k1/(double)i,s));
    	}

    }
    
    private void kMeanCluster()
    {
        final double bigNumber = Math.pow(10, 10);    // some big number that's sure to be larger than our data range.
        double minimum = bigNumber;                   // The minimum value to beat. 
        double distance = 0.0;                        // The current minimum value.
        int sampleNumber = 0;
        int cluster = 0;
        boolean isStillMoving = true;
        Data newData = null;
        
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(dataSet.size() < TOTAL_DATA)
        {
            newData = new Data(SAMPLES[sampleNumber].getX()[0],SAMPLES[sampleNumber].getX()[1],SAMPLES[sampleNumber].getX()[2],SAMPLES[sampleNumber].getX()[3]);
            dataSet.add(newData);
            minimum = bigNumber;
            for(int i = 0; i < NUM_CLUSTERS; i++)
            {
                distance = dist(newData, centroids.get(i));
                if(distance < minimum){
                    minimum = distance;
                    cluster = i;
                }
            }
            newData.cluster(cluster);
            
            // calculate new centroids.
            for(int i = 0; i < NUM_CLUSTERS; i++)
            {
                int totalX = 0;
                int totalY = 0;
                int totalZ = 0;
                int totalK = 0;
                int totalInCluster = 0;
                for(int j = 0; j < dataSet.size(); j++)
                {
                    if(dataSet.get(j).cluster() == i){
                        totalX += dataSet.get(j).X();
                        totalY += dataSet.get(j).Y();
                        totalZ += dataSet.get(j).Z();
                        totalK += dataSet.get(j).K();
                        totalInCluster++;
                    }
                }
                if(totalInCluster > 0){
                    centroids.get(i).X(totalX / totalInCluster);
                    centroids.get(i).Y(totalY / totalInCluster);
                    centroids.get(i).Z(totalZ / totalInCluster);
                    centroids.get(i).K(totalK / totalInCluster);
                }
            }
            sampleNumber++;
        }
        
        // Now, keep shifting centroids until equilibrium occurs.
        while(isStillMoving)
        {
            // calculate new centroids.
            for(int i = 0; i < NUM_CLUSTERS; i++)
            {
                int totalX = 0;
                int totalY = 0;
                int totalZ = 0;
                int totalK = 0;
                int totalInCluster = 0;
                for(int j = 0; j < dataSet.size(); j++)
                {
                    if(dataSet.get(j).cluster() == i){
                        totalX += dataSet.get(j).X();
                        totalY += dataSet.get(j).Y();
                        totalZ += dataSet.get(j).Z();
                        totalK += dataSet.get(j).K();
                        totalInCluster++;
                    }
                }
                if(totalInCluster > 0){
                    centroids.get(i).X(totalX / totalInCluster);
                    centroids.get(i).Y(totalY / totalInCluster);
                    centroids.get(i).Z(totalZ / totalInCluster);
                    centroids.get(i).K(totalK / totalInCluster);
                }
            }
            
            // Assign all data to the new centroids
            isStillMoving = false;
            
            for(int i = 0; i < dataSet.size(); i++)
            {
                Data tempData = dataSet.get(i);
                minimum = bigNumber;
                for(int j = 0; j < NUM_CLUSTERS; j++)
                {
                    distance = dist(tempData, centroids.get(j));
                    if(distance < minimum){
                        minimum = distance;
                        cluster = j;
                    }
                }
                tempData.cluster(cluster);
                if(tempData.cluster() != cluster){
                    tempData.cluster(cluster);
                    isStillMoving = true;
                }
            }
        }
    }
    
    /**
     * // Calculate Euclidean distance.
     * @param d - Data object.
     * @param c - Centroid object.
     * @return - double value.
     */
    private double dist(Data d, Centroid c)
    {
        return Math.sqrt(Math.pow((c.Y() - d.Y()), 2) + Math.pow((c.X() - d.X()) , 2) + Math.pow((c.Z() - d.Z()) , 2) + Math.pow((c.K() - d.K()) , 2));
    }
    
    public void mostrar(){
    	// Print out clustering results.
        for(int i = 0; i < NUM_CLUSTERS; i++)
        {
            System.out.println("Cluster " + i + " includes:");
            for(int j = 0; j < TOTAL_DATA; j++)
            {
                if(dataSet.get(j).cluster() == i){
                    System.out.println("     (" + dataSet.get(j).X() + ", " + dataSet.get(j).Y() + ", " + dataSet.get(j).Z() + ", " + dataSet.get(j).K() +")");
                }
                
            } // j
            System.out.println();
        } // i
        
        // Print out centroid results.
        System.out.println("Centroids finalized at:");
        for(int i = 0; i < NUM_CLUSTERS; i++)
        {
            System.out.println("     (" + centroids.get(i).X() + ", " + centroids.get(i).Y() + ", " + centroids.get(i).Z() + ", " + centroids.get(i).K() +")");
        }
        System.out.print("\n");
    	
    }

    public TipoDato[] centros(){
    	TipoDato[] c = new TipoDato[2];
    	double[] x = new double[4];
    	String s = "";
    	x[0] = centroids.get(0).X();
    	x[1] = centroids.get(0).Y();
    	x[2] = centroids.get(0).Z();
    	x[3] = centroids.get(0).K();
    	c[0] = new TipoDato(x,s);
    	x = new double[4];
    	x[0] = centroids.get(1).X();
    	x[1] = centroids.get(1).Y();
    	x[2] = centroids.get(1).Z();
    	x[3] = centroids.get(1).K();
    	c[1] = new TipoDato(x,s);

    	return c;
    }
    
    public boolean estaBien(TipoDato t){
    	double[] nums = new double[NUM_CLUSTERS];
    	double[] probs = new double[NUM_CLUSTERS];
    	
    	for(int i = 0; i < NUM_CLUSTERS; i++){
    		nums[i] = Math.pow( dist(new Data(t.getX()[0],t.getX()[1],t.getX()[2],t.getX()[3]),this.centroids.get(i)), -1);
    		
    	}
    	
    	double denominador = 0;
    	
    	for(int i = 0; i < NUM_CLUSTERS; i++){
    		denominador +=  Math.pow( dist(new Data(t.getX()[0],t.getX()[1],t.getX()[2],t.getX()[3]),this.centroids.get(i)), -1);
    	}
    	
    	for(int i = 0; i < NUM_CLUSTERS; i++){
    		probs[i] =  nums[i] / denominador;
    	}
    	
    	int marca = 0;
    	double max = Double.MIN_VALUE;
    	for(int i = 0; i < NUM_CLUSTERS; i++){
    		if(probs[i]>max){
    			marca = i;
    			max = probs[i];
    		}
    	}
    	
    	if(centroids.get(marca).getS().equals(t.getS())){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public KMeans_Ex4a(int NUM_CLUSTERS,int TOTAL_DATA,TipoDato SAMPLES[])
    {
    	this.NUM_CLUSTERS = NUM_CLUSTERS;
    	this.TOTAL_DATA = TOTAL_DATA;
    	this.SAMPLES = SAMPLES;
        initialize();
        kMeanCluster();
    }
}