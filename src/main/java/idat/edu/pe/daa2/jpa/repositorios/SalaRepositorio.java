package idat.edu.pe.daa2.jpa.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import idat.edu.pe.daa2.jpa.modelo.Sala;

public interface SalaRepositorio extends CrudRepository<Sala, Integer> {
	
	@Query(value = "SELECT s FROM Sala s WHERE s.nombre = ?1")
	public List<Sala> buscarSalaPorNombre(String nombre);

	@Query(value = "SELECT s FROM Sala s WHERE s.nombre like CONCAT(?1, '%')")
	public List<Sala> buscarSalaLikeNombre(String nombre);

}
