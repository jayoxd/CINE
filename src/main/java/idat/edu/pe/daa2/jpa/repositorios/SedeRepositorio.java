package idat.edu.pe.daa2.jpa.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import idat.edu.pe.daa2.jpa.modelo.Sede;

public interface SedeRepositorio extends CrudRepository<Sede, Integer> {
	
	
	@Query(value = "SELECT s FROM Sede s WHERE s.nombre = ?1")
	public List<Sede> buscarSedePorNombre(String nombre);

	@Query(value = "SELECT s FROM  Sede s WHERE s.nombre like CONCAT(?1, '%')")
	public List<Sede> buscarSedeLikeNombre(String nombre);

}
