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
import idat.edu.pe.daa2.jpa.modelo.Sala;
import idat.edu.pe.daa2.jpa.modelo.Sede;
import idat.edu.pe.daa2.jpa.servicios.SalaServicio;
import idat.edu.pe.daa2.jpa.servicios.SedeServicio;


@Controller
@RequestMapping("/sala")

public class SalaWebController {


	
	@Autowired
	private SalaServicio servicio;
	
	@Autowired
	private SedeServicio sedeServicio;

	@RequestMapping("/listarTodo")
	public String listarSala(Model model) {
		List<Sala> listaSala = servicio.buscarTodo();
		model.addAttribute("listaSala",listaSala);
		return "/moduloSala/listarTodo";
	}
	
	
	
	@RequestMapping("/nuevo")
	public String nuevaSala(Model model) {
	Sala	salas  = new Sala();
	List<Sede> listarSede = sedeServicio.buscarTodo();

		model.addAttribute("sala", salas);	
		model.addAttribute("sede", listarSede);


		return "/moduloSala/nuevaSala";
	}
	
	@RequestMapping(value = "/guardar" , method = RequestMethod.POST)
	public String crearSala(@ModelAttribute("sala") Sala sala) {
		 servicio.crear(sala);
	    return "redirect:/sala/listarTodo";
	}
	
	@RequestMapping(value = "/actualizar/{id}")
	public ModelAndView editarSala(@PathVariable(name = "id") int id,Model model) {
	    ModelAndView mav = new ModelAndView("/moduloSala/editarSala");
	    Sala sala = servicio.buscarPorID(id);
		List<Sede> listarSede = sedeServicio.buscarTodo();
		model .addAttribute("sede", listarSede);
		
	    mav.addObject("sala", sala);
	    return mav;
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminarFunciones(@PathVariable(name = "id") int id) {
		servicio.borrarPorID(id);
	    return "redirect:/sala/listarTodo";       
	}
}
