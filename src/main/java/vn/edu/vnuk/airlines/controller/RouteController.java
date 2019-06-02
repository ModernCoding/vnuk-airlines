package vn.edu.vnuk.airlines.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vnuk.airlines.dao.AirportDao;
import vn.edu.vnuk.airlines.dao.RouteDao;
import vn.edu.vnuk.airlines.model.Airport;
import vn.edu.vnuk.airlines.model.Price;
import vn.edu.vnuk.airlines.model.Route;

@Controller
public class RouteController {

	@Autowired
	private AirportDao airportDao;	
	
	@Autowired
	private RouteDao routeDao;

	@RequestMapping("/routes")
    public String index(
		
		@RequestParam(value="takeOffAirportId", required = false) String takeOffAirportId,
		@RequestParam(value="landingAirportId", required = false) String landingAirportId,
		Model model,
		ServletRequest request

	) throws SQLException{
		
		Route route = new Route();
		if (takeOffAirportId != null)
			route.setTakeOffAirportId(Long.valueOf(takeOffAirportId));
		
		if (landingAirportId != null)
			route.setLandingAirportId(Long.valueOf(landingAirportId));
		
		model.addAttribute("routes", routeDao.read(route));
        model.addAttribute("template", "order/index");
        return "_layout";
	}
    
    @RequestMapping("/routes/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("route", routeDao.read(id));
        model.addAttribute("template", "route/show");
        return "_layout";
    }
    
    @RequestMapping("/routes/new")
    public String add(
    		
    	Route route,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "route/new");
    	model.addAttribute("airports", airportDao.read(new Airport()));
        return "_layout";
    }
    
    @RequestMapping("/routes/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Route route,
		Model model,
		ServletRequest request,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
		
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("backToShow", backToShow);
    	model.addAttribute("urlCompletion", backToShow ? String.format("/%s", id) : "");
    	model.addAttribute("routes", routeDao.read(id));
    	model.addAttribute("airports", airportDao.read());
        model.addAttribute("template", "route/edit");

        return "_layout";
    }
    
    @RequestMapping(value="/routes", method=RequestMethod.POST)
    public String create(		
    	@Valid Route route,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/routes/new";
        }
        
        routeDao.create(route);
        return "redirect:/orders";
    }
    
    @RequestMapping(value="/routes/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Route route,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/routes/%s/edit", id);
        }
        
    	routeDao.update(route);
        return backToShow ? String.format("redirect:/routes/%s", id) : "redirect:/routes";
    }
    
    //  delete with ajax
    @RequestMapping(value="/routes/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	routeDao.delete(id);
        response.setStatus(200);
    }
    
}
