package auxiliares;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import clasesDeNegocio.Jugador;
import GUI.VentanaTheGrid;

public class RenderizarConAzul implements TableCellRenderer {
	private static final long serialVersionUID = 1L;
	
	private VentanaTheGrid ventana;
	private TableCellRenderer renderOriginal;
	
	public RenderizarConAzul(VentanaTheGrid caller, TableCellRenderer render)
	{	
		ventana = caller;
		renderOriginal = render;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = renderOriginal.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		String usuario = (String) table.getValueAt(row, 0);
		Jugador jugador = ventana.global.getJugadorByUsuario(usuario);
		c.setForeground(ventana.colorJugador(jugador));
		//c.setBackground(Color.BLUE);
		return c;
	}
	
}
