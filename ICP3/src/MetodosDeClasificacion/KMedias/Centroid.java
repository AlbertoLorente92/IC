package MetodosDeClasificacion.KMedias;

public class Centroid
{
    private double mX = 0.0;
    private double mY = 0.0;
    private double mZ = 0.0;
    private double mK = 0.0;
    private String s;
    
       
    public Centroid(double newX, double newY,double newZ,double newK,String s)
    {
        this.mX = newX;
        this.mY = newY;
        this.mZ = newZ;
        this.mK = newK;
        this.s = s;
    }
    
    public void X(double newX)
    {
        this.mX = newX;
    }
    
    public double X()
    {
        return this.mX;
    }
    
    public void Y(double newY)
    {
        this.mY = newY;
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

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
}