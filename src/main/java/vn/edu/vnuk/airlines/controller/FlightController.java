package vn.edu.vnuk.airlines.controller;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import vn.edu.vnuk.airlines.dao.CityDao;
import vn.edu.vnuk.airlines.dao.DayDao;
import vn.edu.vnuk.airlines.dao.FlightDao;
import vn.edu.vnuk.airlines.dao.PlaneModelDao;
import vn.edu.vnuk.airlines.dao.RouteDao;
import vn.edu.vnuk.airlines.model.Airport;
import vn.edu.vnuk.airlines.model.Flight;
import vn.edu.vnuk.airlines.model.PlaneModel;
import vn.edu.vnuk.airlines.model.Route;

@Controller
public class FlightController {

	private FlightDao flightDao;
	private RouteDao routeDao;
	private DayDao dayDao;
	private PlaneModelDao planeModelDao;
	

	

	@Autowired
	public void setFlightDao(FlightDao airportDao) {
		this.flightDao = airportDao;
	}

	@Autowired
	public void setRouteDao(RouteDao routeDao) {
		this.routeDao = routeDao;
	}
	
	@Autowired
	public void setDayDao(DayDao dayDao) {
		this.dayDao = dayDao;
	}
	
	@Autowired
	public void setPlaneModelDao(PlaneModelDao planeModelDao) {
		this.planeModelDao = planeModelDao;
	}
	

	@RequestMapping("/flights")
    public String index(
		
		@RequestParam(value="routeId", required = false) String routeId,
		@RequestParam(value="dayId", required = false) String dayId,
		@RequestParam(value="planeModelId", required = false) String planeModelId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		Flight flight = new Flight();
			if (routeId != null)
				flight.setRouteId(Long.valueOf(routeId));
			
			if (dayId != null)
				flight.setDayId(Long.valueOf(dayId));
		
			if (planeModelId != null)
				flight.setPlaneModelId(Long.valueOf(planeModelId));
		
		model.addAttribute("flights", flightDao.read(routeId, dayId, planeModelId));
        model.addAttribute("template", "flight/index");
        return "_layout";
   
	}
    
    
	@RequestMapping("/flights/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("flight", flightDao.read(id));
        model.addAttribute("template", "flight/show");
        return "_layout";
    }
    
    @RequestMapping("/flights/new")
    public String add(
    		
		Flight flight,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "flight/new");
    	model.addAttribute("routes", routeDao.read(new Route()));
    	model.addAttribute("days", dayDao.read());
    	model.addAttribute("planeModels", planeModelDao.read(new PlaneModel()));
        return "_layout";
    }
    
    @RequestMapping("/flights/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Flight flight,
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
    	model.addAttribute("flight", flightDao.read(id));
    	model.addAttribute("routes", routeDao.read(new Route()));
    	model.addAttribute("days", dayDao.read());
    	model.addAttribute("planeModels", planeModelDao.read(new PlaneModel()));
        model.addAttribute("template", "product-pattern/edit");

        return "_layout";
    }
    
    @RequestMapping(value="/flights", method=RequestMethod.POST)
    public String create(		
    	@Valid Flight flight,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/flights/new";
        }
        
        flightDao.create(flight);
        return "redirect:/flights";
    }
    
    @RequestMapping(value="/flights/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Flight flight,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/flights/%s/edit", id);
        }
        
        flightDao.update(flight);
        return backToShow ? String.format("redirect:/flights/%s", id) : "redirect:/flights";
    }
    
    //  delete with ajax
    @RequestMapping(value="/flights/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	flightDao.delete(id);
        response.setStatus(200);
    }
}
