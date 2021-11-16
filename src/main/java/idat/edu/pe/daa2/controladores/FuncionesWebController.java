package idat.edu.pe.daa2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import idat.edu.pe.daa2.jpa.modelo.Funciones;
import idat.edu.pe.daa2.jpa.modelo.Pelicula;
import idat.edu.pe.daa2.jpa.modelo.Sala;
import idat.edu.pe.daa2.jpa.servicios.FuncionesServicio;
import idat.edu.pe.daa2.jpa.servicios.PeliculasServicio;
import idat.edu.pe.daa2.jpa.servicios.SalaServicio;

@Controller
@RequestMapping("/funciones")
public class FuncionesWebController {

	@Autowired
	private FuncionesServicio servicio;
	
	
	@Autowired
	private PeliculasServicio peliculaServicio;
	
	@Autowired
	private SalaServicio salaServicios;

	@RequestMapping("/listarTodo")
	public String listarFunciones(Model model) {
		List<Funciones> listaFunciones = servicio.buscarTodo();
		model.addAttribute("listaFunciones",listaFunciones);
		return "/moduloFunciones/listarTodo";
	}
	
	
	
	@RequestMapping("/nuevo")
	public String nuevaFunciones(Model model) {
	Funciones	funciones  = new Funciones();
	List<Pelicula> listadoPeliculas = peliculaServicio.buscarTodo();
	List<Sala> listarSalas = salaServicios.buscarTodo();

		model.addAttribute("funciones", funciones);	
		model.addAttribute("peliculas", listadoPeliculas);
		model.addAttribute("salas", listarSalas);		


		return "/moduloFunciones/nuevaFunciones";
	}
	
	@RequestMapping(value = "/guardar" , method = RequestMethod.POST)
	public String crearFunciones(@ModelAttribute("funciones") Funciones funciones) {
		 servicio.crear(funciones);
	    return "redirect:/funciones/listarTodo";
	}
	
	@RequestMapping(value = "/actualizar/{id}")
	public ModelAndView editarFunciones(@PathVariable(name = "id") int id,Model model) {
	    ModelAndView mav = new ModelAndView("/moduloFunciones/editarFunciones");
	    Funciones funciones = servicio.buscarPorID(id);
	    List<Pelicula> listadoPeliculas = peliculaServicio.buscarTodo();
		List<Sala> listarSalas = salaServicios.buscarTodo();
		model .addAttribute("peliculas", listadoPeliculas);
		model.addAttribute("salas", listarSalas);	
		
	    mav.addObject("funciones", funciones);
	    return mav;
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminarFunciones(@PathVariable(name = "id") int id) {
		servicio.borrarPorID(id);
	    return "redirect:/funciones/listarTodo";       
	}
}