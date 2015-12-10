package MetodosDeClasificacion.presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import MetodosDeClasificacion.*;
import MetodosDeClasificacion.Bayes.Bayes;
import MetodosDeClasificacion.KMedias.KMeans_Ex4a;
import MetodosDeClasificacion.Lloyd.Lloyd;
import MetodosDeClasificacion.SOM.SOM;

public class Ventana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIrisclasestxt;
	private JTextField txtTestiristxt;
	private JTextArea textFieldBayes;
	private JTextArea textFieldKMedias;
	private JTextArea textFieldLLoyd;
	private JTextArea textFieldSOM;
	private JButton btnProbar;
	private JLabel lblMtodoDeClasificacin;
	private JLabel lblMtodoDeClasificacin_1;
	private JLabel lblMtodoDeClasificacin_2;
	private JLabel lblMtodoDeClasificacin_3;
	private JLabel lblFicheroCarga;
	private JLabel lblFicheroTest;
	private JButton btnCargar;
	private Bayes b;
	private Lloyd ll;
	private KMeans_Ex4a k;
	private SOM s;
	private static boolean cargado;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		cargado = false;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 lblFicheroCarga = new JLabel("Fichero Carga");
		lblFicheroCarga.setBounds(39, 35, 137, 14);
		contentPane.add(lblFicheroCarga);
		
		 lblFicheroTest = new JLabel("Fichero Test");
		lblFicheroTest.setBounds(39, 140, 111, 14);
		contentPane.add(lblFicheroTest);
		
		 btnCargar = new JButton("Cargar");
		btnCargar.setBounds(184, 59, 89, 23);
		contentPane.add(btnCargar);
		
		txtIrisclasestxt = new JTextField();
		txtIrisclasestxt.setText("Iris2Clases.txt");
		txtIrisclasestxt.setBounds(39, 60, 111, 20);
		contentPane.add(txtIrisclasestxt);
		txtIrisclasestxt.setColumns(10);
		
		txtTestiristxt = new JTextField();
		txtTestiristxt.setText("TestIris01.txt");
		txtTestiristxt.setBounds(39, 175, 111, 20);
		contentPane.add(txtTestiristxt);
		txtTestiristxt.setColumns(10);
		
		 btnProbar = new JButton("Probar");
		btnProbar.setBounds(184, 174, 89, 23);
		contentPane.add(btnProbar);
		
		 lblMtodoDeClasificacin = new JLabel("M\u00E9todo de Clasificaci\u00F3n de Bayes");
		lblMtodoDeClasificacin.setBounds(425, 47, 293, 14);
		contentPane.add(lblMtodoDeClasificacin);
		
		 lblMtodoDeClasificacin_1 = new JLabel("M\u00E9todo de clasificaci\u00F3n de K-Medias");
		lblMtodoDeClasificacin_1.setBounds(425, 165, 293, 14);
		contentPane.add(lblMtodoDeClasificacin_1);
		
		 lblMtodoDeClasificacin_2 = new JLabel("M\u00E9todo de clasificaci\u00F3n de LLoyd");
		lblMtodoDeClasificacin_2.setBounds(425, 255, 293, 14);
		contentPane.add(lblMtodoDeClasificacin_2);
		
		 lblMtodoDeClasificacin_3 = new JLabel("M\u00E9todo de clasificaci\u00F3n de SOM");
		lblMtodoDeClasificacin_3.setBounds(425, 382, 293, 14);
		contentPane.add(lblMtodoDeClasificacin_3);
		
		textFieldBayes = new JTextArea();
		textFieldBayes.setEditable(false);
		textFieldBayes.setBounds(425, 85, 293, 52);
		contentPane.add(textFieldBayes);
		textFieldBayes.setColumns(10);
		
		textFieldKMedias = new JTextArea();
		textFieldKMedias.setEditable(false);
		textFieldKMedias.setBounds(425, 190, 293, 54);
		contentPane.add(textFieldKMedias);
		textFieldKMedias.setColumns(10);
		
		textFieldLLoyd = new JTextArea();
		textFieldLLoyd.setEditable(false);
		textFieldLLoyd.setBounds(425, 290, 293, 54);
		contentPane.add(textFieldLLoyd);
		textFieldLLoyd.setColumns(10);
		
		textFieldSOM = new JTextArea();
		textFieldSOM.setEditable(false);
		textFieldSOM.setBounds(425, 407, 293, 61);
		contentPane.add(textFieldSOM);
		textFieldSOM.setColumns(10);
		
		ponListeners();
	}
	
	
	private void ponListeners()
	{
		btnCargar.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TipoDato SAMPLES[] = new TipoDato[Constantes.TOTAL_DATA];
				Lectura l = new Lectura(txtIrisclasestxt.getText());
				
				if(!l.open()){
					JOptionPane.showMessageDialog(null, "Error, no se encuentra el fichero" , "ERROR" ,1);
				}else{
					cargado = true;
					JOptionPane.showMessageDialog(null, "Fichero cargado con éxito" , "" ,1);// el tipo de mensaje, creo que 1 es informativo
					for(int i = 0; i < Constantes.TOTAL_DATA; i++){
						SAMPLES[i] = l.leeLinea();
					}
					l.close();
					
					ll = new Lloyd(Constantes.NUM_CLUSTERS,Constantes.TOTAL_DATA,SAMPLES,Constantes.centros);
					s = new SOM(Constantes.NUM_CLUSTERS,Constantes.TOTAL_DATA,SAMPLES,Constantes.centros,Constantes.NUM_ITER_MAX);
					k = new KMeans_Ex4a(Constantes.NUM_CLUSTERS, Constantes.TOTAL_DATA, SAMPLES);
					b = new Bayes(Constantes.NUM_CLUSTERS, SAMPLES,Constantes.TOTAL_DATA);
				}
			}}
		);	
		
		btnProbar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Lectura l = new Lectura(txtTestiristxt.getText());
			
				if(!l.open()){
					JOptionPane.showMessageDialog(null, "Error, no se encuentra el fichero" , "ERROR" ,1);
					textFieldBayes.setText("");
					textFieldKMedias.setText("");
					textFieldSOM.setText("");
					textFieldLLoyd.setText("");
				}else{
					if(!cargado){
						JOptionPane.showMessageDialog(null, "Error, no se ha cargado un fichero anteriormente" , "ERROR" ,1);
					}else{
						TipoDato t = l.leeLinea();
						l.close();
						
						String sb;// se peude usar la misma variable?
						if(b.estaBien(t)){
							sb="El fichero test contiene: "+ t.toString() +"\n"+
									"Se clasifica en la clase " + t.getS() + "\n"+
									"COINCIDE con la muestra del fichero";
							//falla porque son text field,no text areas. si pero lo hace todo en una linea
						}else{
							sb="El fichero test contiene: "+ t.toString() +"\n"+
									"Se clasifica en la clase " + t.getS() + "\n"+
									"NO COINCIDE con la muestra del fichero";
						}				
						textFieldBayes.setText(sb);
						
						
						if(k.estaBien(t)){
							sb="El fichero test contiene: "+ t.toString() +"\n"+
									"Se clasifica en la clase " + t.getS() + "\n"+
									"COINCIDE con la muestra del fichero";
						}else{
							sb="El fichero test contiene: "+ t.toString() +"\n"+
									"Se clasifica en la clase " + t.getS() + "\n"+
									"NO COINCIDE con la muestra del fichero";
						}				
						textFieldKMedias.setText(sb);
						
						
						if(s.estaBien(t)){
							sb="El fichero test contiene: "+ t.toString() +"\n"+
									"Se clasifica en la clase " + t.getS() + "\n"+
									"COINCIDE con la muestra del fichero";
						}else{
							sb="El fichero test contiene: "+ t.toString() +"\n"+
									"Se clasifica en la clase " + t.getS() + "\n"+
									"NO COINCIDE con la muestra del fichero";
						}	
						textFieldSOM.setText(sb);
						
						
						if(ll.estaBien(t)){
							sb="El fichero test contiene: "+ t.toString() +"\n"+
									"Se clasifica en la clase " + t.getS() + "\n"+
									"COINCIDE con la muestra del fichero";
						}else{
							sb="El fichero test contiene: "+ t.toString() +"\n"+
									"Se clasifica en la clase " + t.getS() + "\n"+
									"NO COINCIDE con la muestra del fichero";
						}
						textFieldLLoyd.setText(sb);
					}
				}
				
			}
		});
	}
}

