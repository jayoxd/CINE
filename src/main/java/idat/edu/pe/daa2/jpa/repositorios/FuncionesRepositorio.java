package idat.edu.pe.daa2.jpa.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import idat.edu.pe.daa2.jpa.modelo.Funciones;

public interface FuncionesRepositorio extends CrudRepository<Funciones, Integer> {
	
	
	@Query(value = "SELECT f FROM Funciones f WHERE f.precio = ?1")
	public List<Funciones> buscarFuncionesPorPrecio(String precio);

	@Query(value = "SELECT f FROM Funciones f WHERE f.precio like CONCAT(?1, '%')")
	public List<Funciones> buscarFuncionesLikePrecio(String precio);
}
