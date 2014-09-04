package auxiliares;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import clasesDeNegocio.CriterioOrden;

public class CriterioRenderer extends JLabel implements ListCellRenderer<CriterioOrden> {
	private static final long serialVersionUID = -6298222774736936106L;
	//Sirve para mostrar la ID del criterio sin perder el criterio en si almacenado
		public CriterioRenderer() {
	        setOpaque(true);
	    }

		@Override
		public Component getListCellRendererComponent(
				JList<? extends CriterioOrden> lista, CriterioOrden criterio,
				int index, boolean isSelected, boolean cellHasFocus) {
			 	 
	        setText(criterio.quienSoy());
	 
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
