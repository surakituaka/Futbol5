package clasesDeNegocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class CriterioCompuesto extends CriterioOrden{
	
	private List<CriterioOrden> criterios = new ArrayList<CriterioOrden>();
	private Map<Jugador, ArrayList<Integer>> promedios = new HashMap<Jugador,ArrayList<Integer>>();
	
	public String quienSoy(){
		return "Compuesto";
	}
	
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
		
		Double j1 = new Double(0), j2 = new Double(0);
		for (int i = 0; i < promedios.get(jugador2).size(); i++) {
			j1+=promedios.get(jugador1).get(i);
			j2+=promedios.get(jugador2).get(i);
		}
		j1 = j1/promedios.get(jugador1).size();
		j2 = j2/promedios.get(jugador2).size();
		
		return j1.compareTo(j2);
	}

	
	public CriterioCompuesto(CriterioOrden[] criterios){
		super();
		for (CriterioOrden criterio : criterios) {
			getCriterios().add(criterio);
		}
	}

	
	@Override
	public Comparator<Jugador> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Jugador> thenComparing(Comparator<? super Jugador> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<Jugador> thenComparing(
			Function<? super Jugador, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<Jugador> thenComparing(
			Function<? super Jugador, ? extends U> arg0,
			Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Jugador> thenComparingDouble(
			ToDoubleFunction<? super Jugador> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Jugador> thenComparingInt(
			ToIntFunction<? super Jugador> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Jugador> thenComparingLong(
			ToLongFunction<? super Jugador> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
