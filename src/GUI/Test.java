package GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Test extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1107310967197575029L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	
	public void CargarStrech(JLabel imagen, String ruta){
        ImageIcon iconoImagen = new ImageIcon(getClass().getResource(ruta));
        int imagenX=iconoImagen.getIconWidth();
        int imagenY=iconoImagen.getIconHeight();
        int imagenHeight=imagen.getBounds().height;
        int imagenWidth=imagenHeight*imagenX/imagenY;
        imagen.setBounds(imagen.getBounds().x,imagen.getBounds().y,imagenWidth,imagenHeight);
		imagen.setIcon(new ImageIcon(iconoImagen.getImage().getScaledInstance(imagenWidth,imagenHeight,Image.SCALE_SMOOTH)));
	}
	
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		String ruta="/img/MSN1.png";
		contentPane.setLayout(null);
		JLabel imagen = new JLabel();
		imagen.setBounds(116, 41, 70, 78);
		CargarStrech(imagen, ruta);		
		contentPane.add(imagen);
	}
}
