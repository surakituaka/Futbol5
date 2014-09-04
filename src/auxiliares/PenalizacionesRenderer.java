package auxiliares;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import clasesDeNegocio.Penalizacion;

public class PenalizacionesRenderer extends JLabel implements ListCellRenderer<Penalizacion> {
	private static final long serialVersionUID = 1788821167387850586L;

	//Sirve para mostrar el mensaje de penalizaciones en listas sin perder la penalizacion
			public PenalizacionesRenderer() {
		        setOpaque(true);
		    }

			@Override
			public Component getListCellRendererComponent(
					JList<? extends Penalizacion> lista, Penalizacion penalizacion,
					int index, boolean isSelected, boolean cellHasFocus) {
				 	 
		        setText(penalizacion.getMotivo());
		 
		        if (isSelected) {
		            setBackground(lista.getSelectionBackground());
		            setForeground(lista.getSelectionForeground());
		        } else {
		            setBackground(lista.getBackground());
		            setForeground(lista.getForeground());
		        }
		 
		        return this;
			}
}
