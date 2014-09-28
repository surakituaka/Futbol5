package auxiliares;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import clasesDeNegocio.Partido;

public class PartidoRenderer extends JLabel implements ListCellRenderer<Partido> {
	private static final long serialVersionUID = -7902951354294469838L;

	//Sirve para mostrar la ID del partido sin perder el partido en si almacenado
			public PartidoRenderer() {
		        setOpaque(true);
		    }

			@Override
			public Component getListCellRendererComponent(
					JList<? extends Partido> lista, Partido partido,
					int index, boolean isSelected, boolean cellHasFocus) {
				 	 
		        setText(partido.getPartido_nombre());
		 
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
