package MetodosDeClasificacion;

public class TipoDato {

	private double x[]; 
	private String s;
	
	public TipoDato(double[] x, String s) {
		super();
		this.x = x;
		this.s = s;
	}
	
	public TipoDato(TipoDato tipoDato) {
		x = tipoDato.getX();
		s = tipoDato.getS();
		// TODO Auto-generated constructor stub
	}

	static public double distEcuclidiana(TipoDato a,TipoDato b){
		double retorno=0;
		
		for(int i=0;i<a.getX().length;i++){
			retorno+=Math.pow(a.getX()[i]- b.getX()[i],2);
		}
		
		return Math.sqrt(retorno);
	}
	
	public TipoDato suma(TipoDato a){
		double[] x  = new double[a.getX().length];
		
		for(int i=0;i<a.getX().length;i++){
			x[i] = a.getX()[i] + this.x[i];
		}
		
		return new TipoDato(x,s);
	}
	
	public TipoDato resta(TipoDato a){
		double[] x  = new double[a.getX().length];
		
		for(int i=0;i<a.getX().length;i++){
			x[i] = this.x[i] - a.getX()[i];
		}
		
		return new TipoDato(x,s);
	}
	
	public TipoDato multiplicacion(double b){
		double[] x  = new double[this.x.length];
		
		for(int i=0;i< this.x.length;i++){
			x[i] = this.x[i] * b;
		}
		
		return new TipoDato(x,s);
	}
	
	public double[] getX() {
		return x;
	}
	
	public void setX(double[] x) {
		this.x = x;
	}
	
	public String getS() {
		return s;
	}
	
	public void setS(String s) {
		this.s = s;
	}
	
	public String toString(){
		return (x[0] + "," +  x[1] + "," + x[2] + "," + x[3] + "," + s);
	}
	
}
