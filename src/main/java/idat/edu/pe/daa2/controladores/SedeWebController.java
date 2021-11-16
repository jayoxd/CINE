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

import idat.edu.pe.daa2.jpa.modelo.Categoria;
import idat.edu.pe.daa2.jpa.modelo.Cine;
import idat.edu.pe.daa2.jpa.modelo.Sede;
import idat.edu.pe.daa2.jpa.servicios.CategoriaServicio;
import idat.edu.pe.daa2.jpa.servicios.CineServicio;
import idat.edu.pe.daa2.jpa.servicios.SedeServicio;


@Controller
@RequestMapping("/sede")
public class SedeWebController {
	@Autowired
	private SedeServicio servicio;
	
	@Autowired
	private  CineServicio cineServicio ;
	
	@Autowired
	private  CategoriaServicio categoriaServicio;

	@RequestMapping("/listarTodo")
	public String listarSede(Model model) {
		List<Sede> listaSede = servicio.buscarTodo();
		model.addAttribute("listaSede",listaSede);
		return "/moduloSede/listarTodo";
	}
	
	
	
	@RequestMapping("/nuevo")
	public String nuevaSala(Model model) {
	Sede	sedes  = new Sede();
	List<Cine> listarCine = cineServicio.buscarTodo();
	List<Categoria> listarCategoria = categoriaServicio.buscarTodo();

		model.addAttribute("sede", sedes);	
		model.addAttribute("cine", listarCine);	
		model.addAttribute("categoria", listarCategoria);


		return "/moduloSede/nuevaSede";
	}
	
	@RequestMapping(value = "/guardar" , method = RequestMethod.POST)
	public String crearSala(@ModelAttribute("sede") Sede sede) {
		 servicio.crear(sede);
	    return "redirect:/sede/listarTodo";
	}
	
	@RequestMapping(value = "/actualizar/{id}")
	public ModelAndView editarSala(@PathVariable(name = "id") int id,Model model) {
	    ModelAndView mav = new ModelAndView("/moduloSede/editarSede");
	    Sede sede = servicio.buscarPorID(id);
		List<Cine> listarCine = cineServicio.buscarTodo();
		List<Categoria> listarCategoria = categoriaServicio.buscarTodo();
		model.addAttribute("cine", listarCine);	
		model.addAttribute("categoria", listarCategoria);
		
	    mav.addObject("sede", sede);
	    return mav;
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminarSede(@PathVariable(name = "id") int id) {
		servicio.borrarPorID(id);
	    return "redirect:/sede/listarTodo";       
	}
}
