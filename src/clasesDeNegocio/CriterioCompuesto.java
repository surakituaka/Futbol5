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
	private Map<Inscripcion, ArrayList<Integer>> promedios = new HashMap<Inscripcion,ArrayList<Integer>>();
	
	public String quienSoy(){
		return "Compuesto";
	}
	
	public List<CriterioOrden> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<CriterioOrden> criterios) {
		this.criterios = criterios;
	}

	public Map<Inscripcion, ArrayList<Integer>> getPromedios() {
		return promedios;
	}

	public void setPromedios(Map<Inscripcion, ArrayList<Integer>> promedios) {
		this.promedios = promedios;
	}

	@Override
	public void ordenar(Partido partido) {
		
		for (Inscripcion inscripcion : partido.getInscripciones()) {
			promedios.put(inscripcion, new ArrayList<Integer>());
		}
		
		for (CriterioOrden criterio : this.getCriterios()) {
			Collections.sort(partido.getInscripciones(), criterio);
			for (Inscripcion inscripcion : partido.getInscripciones()) {
				promedios.get(inscripcion).add(partido.getInscripciones().indexOf(inscripcion)+1);
			}
			
		}
	}

	@Override
	public int compare(Inscripcion inscripcion1, Inscripcion inscripcion2) {
		
		Double j1 = new Double(0), j2 = new Double(0);
		for (int i = 0; i < promedios.get(inscripcion2).size(); i++) {
			j1+=promedios.get(inscripcion1).get(i);
			j2+=promedios.get(inscripcion2).get(i);
		}
		j1 = j1/promedios.get(inscripcion1).size();
		j2 = j2/promedios.get(inscripcion2).size();
		
		return j1.compareTo(j2);
	}

	
	public CriterioCompuesto(CriterioOrden[] criterios){
		super();
		for (CriterioOrden criterio : criterios) {
			getCriterios().add(criterio);
		}
	}

	
	@Override
	public Comparator<Inscripcion> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Inscripcion> thenComparing(Comparator<? super Inscripcion> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<Inscripcion> thenComparing(
			Function<? super Inscripcion, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<Inscripcion> thenComparing(
			Function<? super Inscripcion, ? extends U> arg0,
			Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Inscripcion> thenComparingDouble(
			ToDoubleFunction<? super Inscripcion> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Inscripcion> thenComparingInt(
			ToIntFunction<? super Inscripcion> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Inscripcion> thenComparingLong(
			ToLongFunction<? super Inscripcion> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
