package club;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriterioCompuesto extends CriterioOrden{
	
	private List<CriterioOrden> criterios = new ArrayList<CriterioOrden>();
	private Map<Jugador, ArrayList<Integer>> promedios = new HashMap<Jugador,ArrayList<Integer>>();
	
	public List<CriterioOrden> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<CriterioOrden> criterios) {
		this.criterios = criterios;
	}

	public Map<Jugador, ArrayList<Integer>> getPromedios() {
		return promedios;
	}

	public void setPromedios(Map<Jugador, ArrayList<Integer>> promedios) {
		this.promedios = promedios;
	}

	@Override
	public void ordenar(Partido partido) {
		
		for (Jugador jugador : partido.getJugadores()) {
			promedios.put(jugador, new ArrayList<Integer>());
		}
		
		for (CriterioOrden criterio : this.getCriterios()) {
			Collections.sort(partido.getJugadores(), criterio);
			for (Jugador jugador : partido.getJugadores()) {
				promedios.get(jugador).add(partido.getJugadores().indexOf(jugador)+1);
			}
			
		}
	}

	@Override
	public int compare(Jugador jugador1, Jugador jugador2) {
		
		double j1 = 0, j2 = 0;
		for (int i = 0; i < promedios.get(jugador2).size(); i++) {
			j1+=promedios.get(jugador1).get(i);
			j2+=promedios.get(jugador2).get(i);
		}
		j1 = j1/promedios.get(jugador1).size();
		j2 = j2/promedios.get(jugador2).size();
		if(j1>j2){
			return 1;
		}
		if(j1<j2){
			return -1;
		}
		return 0;
	}

	
	public CriterioCompuesto(CriterioOrden[] criterios){
		super();
		for (CriterioOrden criterio : criterios) {
			getCriterios().add(criterio);
		}
	}
	
	
}
