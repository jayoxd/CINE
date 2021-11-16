package idat.edu.pe.daa2.jpa.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import idat.edu.pe.daa2.jpa.modelo.Categoria;

public interface CategoriaRepositorio extends CrudRepository<Categoria, Integer> {
	
	
	@Query(value = "SELECT c FROM Categoria c WHERE c.codigo = ?1")
	public List<Categoria> buscarCategoriaPorCodigo(String codigo);
	
	@Query(value = "SELECT c FROM Categoria c WHERE c.codigo like CONCAT(?1, '%')")
public List<Categoria> buscarCategoriaLikeCodigo(String codigo);

}
