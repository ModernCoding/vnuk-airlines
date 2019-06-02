package vn.edu.vnuk.airlines.controller;

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
import vn.edu.vnuk.airlines.model.Airport;
import vn.edu.vnuk.airlines.model.City;

@Controller
public class AirportController {

	private AirportDao airportDao;
	private CityDao cityDao;

	

	@Autowired
	public void setAirportDao(AirportDao airportDao) {
		this.airportDao = airportDao;
	}

	@Autowired
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}
	

	@RequestMapping("/airports")
    public String index(
		
		@RequestParam(value="cityId", required = false) String cityId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("airports", airportDao.read(cityId));
		
		if (cityId != null) {
			model.addAttribute("airport", cityDao.read(Long.parseLong(cityId)));
		}
		
        model.addAttribute("template", "airport/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/airports/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("airport", airportDao.read(id));
        model.addAttribute("template", "airport/show");
        return "_layout";
    }
    
    
    @RequestMapping("/airports/new")
    public String add(
    		
    	Airport airport,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "airport/new");
    	model.addAttribute("city", cityDao.read(countryId));
        return "_layout";
    }
    
    
    @RequestMapping("/airports/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Airport airport,
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
    	model.addAttribute("airports", cityDao.read(id));
    	model.addAttribute("cities", cityDao.read(countryId));
        model.addAttribute("template", "city/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/airports", method=RequestMethod.POST)
    public String create(
		
    	@Valid Airport airport,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/airports/new";
        }
        
        
        airportDao.create(airport);
        return "redirect:/cities";
        
    }
    
    
    @RequestMapping(value="/airports/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Airport airport,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/airports/%s/edit", id);
        }
        
    	airportDao.update(airport);
        return backToShow ? String.format("redirect:/airports/%s", id) : "redirect:/airports";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/airports/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	airportDao.delete(id);
        response.setStatus(200);
    }
    
}
