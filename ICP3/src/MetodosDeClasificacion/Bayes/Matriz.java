package MetodosDeClasificacion.Bayes;

/**
 * Representacion de matrices de numeros reales
 * El contenido de esta clase está creado exclusivamente con propósitos didácticos,
 * estos algoritmos no deben utilizarse en aplicaciones reales, ya que no tienen en cuenta
 * casos extremos y pueden introducir errores significativos al tratar con determinadas matrices.
 * 
 * @author Samuel Martin
 * @version 17/11/2009
 */
public class Matriz
{

    // Array de arrays de reales que almacenara los elementos de la Matriz. 
    private double[][] elementos;
    // Dimensiones de la matriz (numero de filas y de columnas)
    private int nFilas, nCols;

    /**
     * Crea una matriz a partir de sus elementos.
     * Cada uno de los elementos del contenido del array que se pasa como parametro 
     * se copia al contenido del campo que utilice (las modificaciones posteriores en el
     * contenido de elementos no afectan al contenido de la matriz)
     * @param elementos Elementos de la matriz en forma de array de dos dimensiones
     * Cada uno de los componentes de esta estructura (array de arrays) corresponde 
     * a un vector fila (m<sub>i</sub>) de la Matriz y, a su vez, cada uno de los componentes 
     * de un vector fila representa uno de los elementos (m<sub>ij</sub>) de la Matriz.
     */
    public Matriz(double[][] elementos)
    {
        nFilas = elementos.length;
        nCols = elementos[0].length;
        this.elementos = new double[nFilas][nCols];
         for(int i=0; i<nFilas; i++) {
             for(int j=0; j<nCols; j++) {
                 this.elementos[i][j] = elementos[i][j];
             }
         }
    }
    
    
    /**
     * Crea una Matriz rectangular rellena de ceros, dadas sus dimensiones.
     * @param nFilas Numero de filas de la matriz
     * @param nCols Numero de columnas de la matriz
     */
    public Matriz(int nFilas, int nCols) {
        this.nFilas = nFilas;
        this.nCols = nCols;
        elementos = new double[nFilas][nCols];
        //Todos se inicializara a 0.0
      
    }

    /**
     *  Crea una matriz cuadrada rellena de ceros.
     *  @param nDim Dimension de la matriz (numero de columnas, igual que el numero de filas).
     */
    public Matriz(int nDim) {
        this(nDim, nDim);
    }
    
    public int getnFilas() {
		return nFilas;
	}


	public void setnFilas(int nFilas) {
		this.nFilas = nFilas;
	}


	public int getnCols() {
		return nCols;
	}


	public void setnCols(int nCols) {
		this.nCols = nCols;
	}


	/**
     * Metodo accesor (getter). Obtiene un elemento de la matriz dadas sus coordenadas.
     * @param i Fila del elemento que al que se accede
     * @param j Columna del elemento al que se accede
     * @return Elemento situado en la fila i y columna j de la matriz (m<sub>ij</sub>).
     */
    public double getElemento(int i, int j) {
        return elementos[i][j];
    }
    
    
    /**
     * Metodo mutadodr (setter). Modifica el valor de un elemento de la matriz dadas sus coordenadas.
     * @param i Fila del elemento que cuyo valor se modifica
     * @param j Columna del elemento cuyo valor se modifica
     * @param d Nuevo valor que tomara el elemento situado en la fila i y columna j de la matriz (m<sub>ij</sub>).
     */    
    public void setElemento(int i, int j, double d) {
        elementos[i][j] = d;
    }
    

    /**
     * Metodo accesor (getter). Obtiene el valor de una fila de la matriz en forma de array.
     * @param i Fila a la que se accede
     * @return Fila i-esima de la matriz, en forma de array de reales. Devuelve una copia
     * estatica del contenido de la fila (modificaciones posteriores en la fila no afectaran
     * a los valores en el array devuelto ni viceversa).
     */
     public double[] getFila(int i) {
        double[] fila = new double[nCols]; // Creamos un nuevo array para almacenar la fila
        for (int j=0;  j<nCols; j++) {
            fila[j] = elementos[i][j];
        }
        return fila;
    }
    
    /**
     * Metodo accesor (getter). Obtiene el valor de una columna de la matriz en forma de array.
     * @param j Columna a la que se accede
     * @return Columna j-esima de la matriz, en forma de array de reales. Devuelve una copia
     * estatica del contenido de la columna (modificaciones posteriores en la columna no afectaran
     * a los valores en el array devuelto ni viceversa).
     */
    public double[] getColumna(int j) {
        double[] col = new double[nFilas]; // Creamos un nuevo array para almacenar la columna
        for (int i=0;  i<nFilas; i++) {
            col[i] = elementos[i][j];
        }
        return col;
    }

    
    /**    
     * Imprime una matriz por pantalla.
     * Imprime una fila en cada linea. En cada fila, imprime el borde izquierdo ("|"), 
     * luego cada uno de los elementos de la fila y, finalmente el borde derecho ("|").
     * La matriz aparece rodeada por sendas lineas en blanco arriba y abajo.
     * Por ejemplo:
     * <pre>
     * |+1.000 +2.000 +3.000 +4.000 |
     * |+5.000 +3.000 -2.000 +1.000 |
     * |+2.000 +7.000 +4.000 +3.250 |
     * |-2.000 -3.142 +7.300 +1.333 |
     * </pre>
     * @see #imprime2
     */
    public void imprime() {

        String borde = "|";
        
        System.out.println(); // Imprime una linea en blanco al principio
        for (int i=0; i<elementos.length; i++) {
            System.out.print(borde); // Imprime el borde izquierdo sin cambiar de linea
            double[] filai = elementos[i];
            for (int j=0; j<filai.length; j++) {
                imprimeElemento(elementos[i][j]); // Imprime el elemento i,j sin cambiar de linea
            }            
            System.out.print(borde); // Imprime el borde derecho
            System.out.println(); // Salta a una nueva linea
        }
        System.out.println(); // Imprime una linea en blanco al final
    }
    
    /**    
     * Imprime una matriz por pantalla.
     * Imprime una fila en cada linea. En cada fila, imprime el borde izquierdo ("|"), 
     * luego cada uno de los elementos de la fila y, finalmente el borde derecho ("|").
     * La matriz aparece rodeada por sendas lineas en blanco arriba y abajo.
     * Por ejemplo:
     * <pre>
     * |+1.000 +2.000 +3.000 +4.000 |
     * |+5.000 +3.000 -2.000 +1.000 |
     * |+2.000 +7.000 +4.000 +3.250 |
     * |-2.000 -3.142 +7.300 +1.333 |
     * </pre>
     * La implementacion de este metodo esta basada en el uso de un bucle for-each.
     * @see #imprime
     */    
    public void imprime2() {
        String borde = "|";
        
        System.out.println(); // Imprime una linea en blanco al principio
        for (double[] fila : elementos) {

            System.out.print(borde); // Imprime el borde izquierdo sin cambiar de linea
            for (double d : fila) {                
                imprimeElemento(d); // Imprime el elemento i,j sin cambiar de linea
            }
            System.out.print(borde); // Imprime el borde derecho
            System.out.println(); // Salta a una nueva linea            
        }
        System.out.println(); // Imprime una linea en blanco al final
    }    
    
    /** Imprime un numero real, formateado con 4 decimales, sin cambiar de linea.
     * Este metodo no deben entenderlo los alumnos, pero es necesario para que
     * todas las filas de la matriz queden uniformes.
     * @param d Numero que se imprimira
     */
    private void imprimeElemento(double d) {
        System.out.print( String.format("%1$+01.4g", d) + " ");
    }

 
    /**
     * Calcula el producto escalar de dos vectores.
     * Se puede suponer que ambos vectores tienen las mismas dimensiones.
     * El producto escalar de dos vectores viene dado por el sumatorio de los productos de sus componentes 
     * @param u Primer vector que se multiplicara.
     * @param v Segundo vector que se multiplicara.
     * @return producto escalar de u y v 
     */
    public double productoEscalar(double[] u, double[] v)/*throws Exception*/
    {
        double producto = 0;
        /*if(u.length != v.length)
        {
        	System.err.println(u.length +" "+ v.length);
        	throw new Exception();
        }*/
        for (int i=0; i<u.length; i++) {
            producto += u[i]*v[i];
        }
        return producto;
    }


    /**
     * Matriz transpuesta a la actual.
     * @return Matriz transpuesta a la actual. La transpuesta de una matriz M de f filas y c columnas
     * es otra matriz <sup>t</sup>M de c filas y f columnas, en donde <sup>t</sup>m<sub>i,j</sub> = m<sub>j,i</sub>.
     */
    public Matriz transpuesta() {
        Matriz resultado = new Matriz(this.nCols, this.nFilas); // Comenzamos creando una matriz vacía
        
        for (int i=0; i<nCols; i++) {
            for (int j=0; j<nFilas; j++) {
                resultado.elementos[i][j] = this.elementos[j][i]; // La vamos rellenando, cambiando el orden de los índices del array original
            }
        }
        /*for (int i=0; i<nFilas; i++) {
            for (int j=0; j<nCols; j++) {
                resultado.elementos[i][j] = this.elementos[j][i]; // La vamos rellenando, cambiando el orden de los índices del array original
            }
        }*/
        return resultado; // Una vez rellena, devolvemos la matriz
    }
    
    /** 
     * Suma de una matriz a la actual.
     * @param m Matriz que se sumará a la actual. 
     * Se puede suponer que tiene las mismas dimensiones que la actual.
     * @return Matriz resultante de sumar la matriz actual con la matriz m. La matriz suma tiene
     * las mismas dimensiones que la matriz actual y cada uno de sus elementos viene dado
     * por la suma del elemento respectivo de las dos matrices sumandas.
     */
    public Matriz suma(Matriz m) {
        Matriz resultado = new Matriz(this.nFilas, this.nCols); // Comenzamos creando una matriz vacía
        for (int i=0; i<nFilas; i++) {
            for (int j=0; j<nCols; j++) {
                // La vamos rellenando, con la suma de los elementos respectivos
                resultado.elementos[i][j] = this.elementos[i][j] + m.elementos[i][j]; 
            }
        }
        return resultado; // Una vez rellena, devolvemos la matriz
    }
    
    
    /**
     * Matriz opuesta a la actual
     * @return Matriz opuesta a la actual.
     * La matriz opuesta de una matriz M es otra matriz <sup>o</sup>M con las mismas dimensiones
     * y en donde <sup>o</sup>m</sub>i,j</sub> = -m<sub>i,j</sub>.
     */
    public Matriz opuesta() {
        Matriz resultado = new Matriz(this.nFilas, this.nCols);
        for (int i=0; i<nFilas; i++) {
            for (int j=0; j<nCols; j++) {
                resultado.elementos[i][j] = -this.elementos[i][j];
            }
        }
        return resultado;
    }
    
    
    /** 
     * Producto de una matriz por un número real
     * @param k Constante real por la que se multiplica la matriz
     * @return Producto de la matriz por la constante k. En la matriz resultado, 
     * todos los elementos de la matriz original se ven multiplicados por k
     */
    public Matriz producto(double k) {
        Matriz resultado = new Matriz(this.nFilas, this.nCols);
        for (int i=0; i<nFilas; i++) {
            for (int j=0; j<nCols; j++) {
                resultado.elementos[i][j] = k * this.elementos[i][j];
            }
        }
        return resultado;
    }
    
    
    /**
     * Producto por otra matriz por la derecha.
     * Si se multiplican dos matrices M·N, la matriz resultante P 
     * es una matriz que tiene tantas filas como M y tantas columnas como N, 
     * y donde cada componente es igual al producto escalar de la fila i de M por la columna j de N 
     * @param m Matriz por la que se multiplica la matriz actual
     * @return Matriz resultado de multiplicar la matriz actual por m por la derecha
     */
    public Matriz producto(Matriz m)  {
        Matriz resultado = new Matriz(this.nFilas, m.nCols);
        for(int i=0; i<this.nFilas; i++) {
            for(int j=0; j<m.nCols; j++) 
            {
                resultado.elementos[i][j] = productoEscalar( this.getFila(i), m.getColumna(j) );
            }
        }
        return resultado;
    }
    

    /**
     * Menor complementario de un elemento en la matriz.
     * @param ni Fila del elemento cuyo menor complementario se quiere obtener
     * @param nj Columna del elemento cuyo menor complementario se quiere obtener
     * @return Menor complementario del elemento ni, nj de la matriz original.
     * Se trata de una matriz igual que la original pero de la que se han eliminado la fila ni y la columna nj.
     */
     public Matriz complementaria(int ni, int nj) {
        if (nFilas > 0 && nCols > 0) {        
            Matriz resultado = new Matriz(nFilas-1, nCols-1);
            for (int i=0, ri=0; ri < nFilas-1; i++, ri++) {
                if (i== ni) {
                    i++;
                }
                for (int j=0, rj=0; rj< nCols-1; j++, rj++) {
                    if (j== nj) {
                        j++;
                    }
                    resultado.elementos[ri][rj] = this.elementos[i][j];
                }
            }
            return resultado;
        } else return null;
    }
    

    /**
     * Determinante de una matriz. Se supone la matriz cuadrada.
     * El determinante de una matriz se calcula siguiendo la regla de Laplace, según:
     * <ul>
     * <li>Si la matriz tiene dimensión 0 (0 filas por 0 columnas), el determinante vale 1.</li>
     * <li>Si la matriz tiene dimensión mayor que cero, el determinante se calcula mediante el método de los adjuntos.
     * El adjunto de un elemento es el producto de:
     *      <ul>
     *      <li>el determinante de la matriz complementaria del elemento, por</li>
     *      <li>por el valor del elemento</li>
     *      <li>por un factor de signo que vale (+1) si la paridad del índice de la fila y de la columna son iguales o (-1) si la paridad del índice de la fila y de la columna son distintos.</li>
     *      </ul>
     * El determinante de la matriz será igual al sumatorio de los adjuntos de los elementos de la primera fila.
     * </li>
     * </ul>
     * @return Determinante de la matriz dada.
     * @see <a href="http://en.wikipedia.org/wiki/Laplace_expansion">Expansión de Laplace</a>
     */
    public double determinante() {
        if (nFilas == 0 || nCols == 0) {
            return 1;
        } else {
            double resultado = 0;
            for (int i=0; i<nFilas; i++) {
                    int j=0;
                    int signo;
                    if ( (i+j)%2 == 0) {
                        signo = 1;
                    } else  {
                        signo = -1;
                    }
                    resultado += signo * elementos[i][j] * complementaria(i, j).determinante();
                }
            if(resultado!=0)
            	return resultado;
            else
            	return 0.0001;
        }
    }
    
    
    /**
     * Operación de intercambio de filas en una matriz.
     * Crea una nueva matriz, resultado de intercambiar dos filas en la matriz actual.
     * @param i1 Primera fila que se intercambiará
     * @param i2 Segunda fila que se intercambiará
     * @return Matriz igual a la original en todos sus elementos, salvo que los elementos de la
     * fila i1 aparecen cambiados por los de la i2 y viceversa.
     */
    public Matriz cambiaFilas(int i1, int i2)  {
        Matriz resultado = new Matriz(nFilas, nCols);
        for (int i=0; i<nFilas; i++) {
            for (int j=0; j<nCols; j++) {
                if (i == i1) {
                    resultado.elementos[i2][j] = this.elementos[i][j]; //Los elementos de la fila i1 la llevamos a la i2
                } else if (i == i2) {
                    resultado.elementos[i1][j] = this.elementos[i][j]; //Los elementos de la fila i2 la llevamos a la i1
                } else { 
                    resultado.elementos[i][j] = this.elementos[i][j]; //Los demás elementos los dejamos en el mismo lugar
                }
            }
        }        
        return resultado;
    }
    
    /** 
     * Operación de multiplicación de una fila por un número real.
     * Crea una nueva matriz, resultado de multiplicar una fila por un número dado.
     * @param i1 Fila que se multiplicará
     * @param k Número por el que se multiplicará la fila
     * @return Matriz igual a la original en todos sus elementos, salvo que los elementos de la
     * fila i1 aparecen multiplicados por la constante.
     */
    public Matriz multiplicaFila(int i1, double k) {
        Matriz resultado = new Matriz(nFilas, nCols);
        for (int i=0; i<nFilas; i++) {
            for (int j=0; j<nCols; j++) {
                if (i == i1) {
                    resultado.elementos[i1][j] = k * this.elementos[i][j]; //Los elementos de la fila i1 la multiplicamos por k
                } else {
                    resultado.elementos[i][j] = this.elementos[i][j]; // Los demás elementos los dejamos tal cual
                }
            }
        }
        return resultado;
    }
    
    /**
     * Operación de suma de una fila a otra.
     * Crea una nueva matriz, resultado de sumar una fila por un número real dado a otra fila.
     * @param i1 Fila que se modificará
     * @param i2 Fila que se sumará a i1
     * @param k Factor por el que se multiplica la fila i2 para sumarla a i1
     * @return Matriz igual a la original en todos sus elementos, salvo que los elementos de la fila i1
     * aparecen sumados con k veces el elemento respectivo de la fila i2.
     */
    public Matriz anadeFila(int i1, int i2, double k) {
        Matriz resultado = new Matriz(nFilas, nCols);
        for (int i=0; i<nFilas; i++) {
            for (int j=0; j<nCols; j++) {
                if (i == i1) {
                    // A los elementos de la fila i1 le sumamos k veces el elemento correspondiente de la fila i2
                    resultado.elementos[i1][j] = this.elementos[i][j] + k * this.elementos[i2][j]; 
                } else {
                    resultado.elementos[i][j] = this.elementos[i][j]; // Los demás elementos los dejamos tal cual
                }
            }
        }        
        return resultado;        
    }
        
    /**
     * Crea una matriz triangular superior a partir de la dada, a base de operaciones
     * elementales de fila utilizando el Método de Eliminación de Gauss.
     * Una matriz triangular superior es aquella en la que todos los elementos 
     * por debajo de la diagonal principal son iguales a cero.
     * Para crear la matriz triangular superior, se seguirá el método de Gauss ():
     * <ol>
     * <li>Se recorre la matriz por columnas.</li>
     * <li>Para cada columna j, se colocan ceros por debajo de la diagonal principal. Para ello:
     *      <ol>
     *      <li>Se recorre la columna por filas.</li>
     *      <li>Para cada fila i mayor que j (es decir, para cada elemento mij por debajo de la diagonal principal),
     *      se añade la fila j (es decir, la misma que la columna en la que nos encontremos) a la fila i, 
     *      multiplicada por –m<sub>ij</sub>/m<sub>jj</sub>. De este modo, conseguimos hacer 0 el elemento m<sub>ij</sub>.
     *      </li>
     *      </ol>
     * </li>
     * </ol>
     * @return Matriz triangular superior (con ceros por debajo de la diagonal principal) resultado
     * de llevar a cabo operaciones de fila a partir de la matriz dada.
     * @see <a href="http://en.wikipedia.org/wiki/Gaussian_elimination">Eliminación de Gauss</a>
     * @see #gaussJordanElim
     */
    public Matriz gaussElim() {
        Matriz resultado = this; // Empezamos con la matriz actual como resultado; 
        // no hay problema porque las operaciones de fila no modifican la original sino que devuelven matrices nuevas
        for (int j=0; j<nCols; j++) {
            for (int i=j+1; i<nFilas; i++) {
                resultado = resultado.anadeFila(i, j, -resultado.elementos[i][j] / resultado.elementos[j][j] );
            }
        }
        return resultado;     
    }
    
    /**
     * Crea una matriz en forma de filas en escalón reducida (RREF) a partir de la dada, 
     * a base de operaciones elementales de fila,
     * utilizando el Método de Eliminación de Gauss-Jordan.
     * Una matriz está en forma RREF si:
     * <ul>
     * <li>Todas las filas no nulas están por encima de las filas todo ceros</li>
     * <li>El coeficiente dominante de una fila 
     * (es decir, el primer elemento no nulo, también llamado pivote) está 
     * siempre a la derecha del coeficiente dominante de la fila superior.</li>
     * <li>Todo coeficiente dominante es igual a 1 y además es el único elemento no nulo de su columna.</li>
     * </ul>
     * El Método de Eliminación de Gauss-Jordan comienza aplicando el Método de Eliminación de Gauss.
     * A partir de ahí, se multiplica cada fila por el inverso del pivote para hacerla igual a la unidad. 
     * A continuación, para cada columna j se toma el último elemento i no nulo, 
     * y se hacen ceros todos los elementos por encima de él, sumando la fila del pivote a las demás filas,
     * multiplicándolas por la constante necesaria para anular el elemento 
     * (de forma semejante al Método de Eliminación de Gauss).
     * @see <a href="http://en.wikipedia.org/wiki/Gauss%E2%80%93Jordan_elimination">Método de Eliminación de Gauss-Jordan</a>
     * @see #gaussElim
     */
    public Matriz gaussJordanElim() {
        Matriz resultado = this.gaussElim(); // Empezamos con la matriz triangular superior como resultado
        for (int i=0; i<nFilas; i++) {
            resultado = resultado.multiplicaFila(i, 1.0/resultado.elementos[i][i]); // Hacemos los pivotes igual a la unidad
        }
        for (int j=0; j<nFilas; j++) {
            for (int i=0; i<j; i++) {
                  resultado = resultado.anadeFila(i, j, -resultado.elementos[i][j] / resultado.elementos[j][j] ); // Anulamos los elementos por encima de los pivotes (i<j)
            }
        }        
        return resultado;
    }
   
    public Matriz inversa2(){
    	return matrizAdjunta().producto(Math.pow(determinante(),-1));
    }
    
    public Matriz matrizAdjunta(){
        return matrizCofactores().transpuesta();
    }
     
    public Matriz matrizCofactores(){
        Matriz nm = new Matriz(elementos.length,elementos.length);
        for(int i=0;i<elementos.length;i++) {
            for(int j=0;j<elementos.length;j++) {
                Matriz det=new Matriz(elementos.length-1,elementos.length-1);
                double detValor;
                for(int k=0;k<elementos.length;k++) {
                    if(k!=i) {
                        for(int l=0;l<elementos.length;l++) {
                            if(l!=j) {
                            int indice1=k<i ? k : k-1 ;
                            int indice2=l<j ? l : l-1 ;
                            det.setElemento(indice1,indice2,elementos[k][l]);
                            }
                        }
                    }
                }
                detValor=det.determinante();
                nm.setElemento(i, j, detValor * (double)Math.pow(-1, i+j+2));
               // nm[i][j]=detValor * (double)Math.pow(-1, i+j+2);
            }
        }
        return nm;
    }
    /**
     * Matriz inversa a la dada
     * Para crear la matriz inversa a otra dada:
     * <ol>
     * <li>Concatenamos la matriz dada con la matriz identidad por la derecha</li>
     * <li>Aplicamos el Método de Eliminación de Gauss-Jordan {@link #gaussJordanElim} a la matriz resultante</li>
     * <li>Tomamos la parte derecha de la matriz resultante de aplicar el método anterior. Esta matriz es la inversa</li>
     * </ol>
     * @return Matriz inversa a la dada (es decir, aquella matriz m tal que this.producto(m) es igual a la matriz identidad).
     */
    public Matriz inversa() {
        // Primero creamos una nueva matriz que concatena la actual y la matriz identidad
        Matriz tmp = new Matriz(nFilas, 2*nCols);
        for (int i=0; i<nFilas; i++) {
            // Primera mitad, matriz original
            for (int j=0; j<nCols; j++) {
                tmp.elementos[i][j] = this.elementos[i][j];
            }
            // Segunda mitad, matriz identidad
            for (int j=nCols; j<2*nCols; j++) {
                if( i+nCols == j) {
                    tmp.elementos[i][j] = 1; // Valen 1 los elementos de la diagonal de la parte derecha
                } else {
                    tmp.elementos[i][j] = 0; // Valen 0 el resto de los elementos
                }
            }
        }
        // Segundo: aplicamos el método de eliminación de Gauss-Jordan
        tmp = tmp.gaussJordanElim();
        // Tercero: creamos una nueva matriz que equivale a la parte derecha de la matriz actual
        Matriz resultado = new Matriz(nFilas, nCols);
        for (int i=0; i<nFilas; i++) {
            for (int j=0; j<nCols; j++) {
                resultado.elementos[i][j] = tmp.elementos[i][j+nCols];
            }
        }
        return resultado;
    }
    
}
/*
public class Matriz {
	private double[][] m;
	private int x,y;
	
	Matriz(double[][] m,int x,int y){
		this.m = m;
		this.x = x;
		this.y = y;
	}
	
	public boolean sePuedenMultiplicar(Matriz a,Matriz b){
		return a.getY() == b.getX();
	}
	
	public Matriz multiplicar(Matriz a,Matriz b){
		double[][] aux = new double[a.getY()][b.getX()];
		
		double[][] mDeA = a.getM();
		double[][] mDeB = b.getM();
		
		for (int i = 0; i < rowsInA; i++) {
           for (int j = 0; j < columnsInB; j++) {
               for (int k = 0; k < columnsInA; k++) {
                   aux[i][j] = aux[i][j] + mDeA[i][k] * mDeB[k][j];
               }
           }
       }
		
		
	}
	
	public double[][] getM() {
		return m;
	}

	public void setM(double[][] m) {
		this.m = m;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}*/
