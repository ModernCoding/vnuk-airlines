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

import vn.edu.vnuk.airlines.dao.CityDao;
import vn.edu.vnuk.airlines.dao.CountryDao;
import vn.edu.vnuk.airlines.model.City;


@Controller
public class CityController {

	private CityDao cityDao;
	private CountryDao countryDao;

	@Autowired
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	@Autowired
	public void setcoutryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	

	@RequestMapping("/cities")
    public String index(
		
		@RequestParam(value="countryId", required = false) String countryId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("cities", cityDao.read(countryId));
		
		if (countryId != null) {
			model.addAttribute("planeManufacturer", cityDao.read(Long.parseLong(countryId)));
		}
		
        model.addAttribute("template", "city/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/cities/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("city", cityDao.read(id));
        model.addAttribute("template", "cities/show");
        return "_layout";
    }
    
    
    @RequestMapping("/cities/new")
    public String add(
    		
		City city,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "city/new");
    	model.addAttribute("country", countryDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/cities/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		City city,
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
    	model.addAttribute("cities", cityDao.read(id));
    	model.addAttribute("counties", countryDao.read());
        model.addAttribute("template", "city/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/cities", method=RequestMethod.POST)
    public String create(
		
    	@Valid City city,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/cities/new";
        }
        
        
        cityDao.create(city);
        return "redirect:/cities";
        
    }
    
    
    @RequestMapping(value="/cities/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid City city,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/cities/%s/edit", id);
        }
        
        cityDao.update(city);
        return backToShow ? String.format("redirect:/cities/%s", id) : "redirect:/cities";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/cities/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	cityDao.delete(id);
        response.setStatus(200);
    }
    
}
