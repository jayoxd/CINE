package idat.edu.pe.daa2.jpa.repositorios;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import idat.edu.pe.daa2.jpa.modelo.Cine;

public interface CineRepositorio extends CrudRepository<Cine, Integer> {
@Query(value = "SELECT c FROM Cine c WHERE c.nombre = ?1")
	public List<Cine> buscarCinesPorNombre(String nombre);
	
	@Query(value = "SELECT c FROM Cine c WHERE c.nombre like CONCAT(?1, '%')")
public List<Cine> buscarCinesLikeNombre(String nombre);
}
