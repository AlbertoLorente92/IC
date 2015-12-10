package MetodosDeClasificacion.KMedias;

public class Data
{
    private double mX = 0;
    private double mY = 0;
    private double mZ = 0;
    private double mK = 0;
    private int mCluster = 0;
    
    public Data(double x, double y,double z,double k)
    {
        this.X(x);
        this.Y(y);
        this.Z(z);
        this.K(k);
    }
    
    public void X(double x)
    {
        this.mX = x;
    }
    
    public double X()
    {
        return this.mX;
    }
    
    public void Y(double y)
    {
        this.mY = y;
    }
    
    public double Y()
    {
        return this.mY;
    }
        
    public void Z(double z)
    {
        this.mZ = z;
    }
    
    public double Z()
    {
        return this.mZ;
    }
        
    public void K(double k)
    {
        this.mK = k;
    }
    
    public double K()
    {
        return this.mK;
    }
      
    public void cluster(int clusterNumber)
    {
        this.mCluster = clusterNumber;
    }
    
    public int cluster()
    {
        return this.mCluster;
    }
}