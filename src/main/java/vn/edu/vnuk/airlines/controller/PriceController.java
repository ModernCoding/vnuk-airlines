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

import vn.edu.vnuk.airlines.dao.ClassesDao;
import vn.edu.vnuk.airlines.dao.FlightDao;
import vn.edu.vnuk.airlines.dao.PriceDao;
import vn.edu.vnuk.airlines.dao.PriceTypeDao;
import vn.edu.vnuk.airlines.model.Booking;
import vn.edu.vnuk.airlines.model.Price;

@Controller
public class PriceController {

	private PriceDao priceDao;
	private FlightDao flightDao;
	private ClassesDao classesDao;
	private PriceTypeDao priceTypeDao;

	@Autowired
	public void setPriceDao(PriceDao priceDao) {
		this.priceDao = priceDao;
	}

	@Autowired
	public void setFlightDao(FlightDao flightDao) {
		this.flightDao = flightDao;
	}
	
	
	@Autowired
	public void setClassesDao(ClassesDao classesDao) {
		this.classesDao = classesDao;
	}
	
	@Autowired
	public void setPriceTypeDao(PriceTypeDao priceTypeDao) {
		this.priceTypeDao = priceTypeDao;
	}


	@RequestMapping("/prices")
    public String index(
		
    		@RequestParam(value="flightId", required = false) String flightId,
    		@RequestParam(value="classId", required = false) String classId,
    		@RequestParam(value="priceTypeId", required = false) String priceTypeId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("prices", priceDao.read(flightId));
		model.addAttribute("prices", priceDao.read(classId));
		model.addAttribute("prices", priceDao.read(priceTypeId));
		
		if (flightId != null) {
			model.addAttribute("flight", priceDao.read(Long.parseLong(flightId)));
		}
		
		if (classId != null) {
			model.addAttribute("class", priceDao.read(Long.parseLong(classId)));
		}
		
		if (priceTypeId != null) {
			model.addAttribute("price", priceTypeDao.read(Long.parseLong(priceTypeId)));
		}
		
		
        model.addAttribute("template", "price/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/prices/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("price", priceDao.read(id));
        model.addAttribute("template", "price/show");
        return "_layout";
    }
    
    
    @RequestMapping("/prices/new")
    public String add(
    		
    	Price price,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) { 
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "price/new");
    	model.addAttribute("flights", flightDao.read(routeId, dayId, planeModelId));
    	model.addAttribute("classes", classesDao.read());
    	model.addAttribute("priceTypes", priceTypeDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/prices/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Price price,
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
    	model.addAttribute("prices", priceDao.read(id));
    	model.addAttribute("flights", flightDao.read(routeId, dayId, planeModelId));
    	model.addAttribute("classes", classesDao.read());
    	model.addAttribute("priceTypes", priceTypeDao.read());
        model.addAttribute("template", "plane-model/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/prices", method=RequestMethod.POST)
    public String create(
		
    	@Valid Price price,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/prices/new";
        }
        
        
        priceDao.create(price);
        return "redirect:/prices";
        
    }
    
    
    @RequestMapping(value="/prices/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Price price,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/prices/%s/edit", id);
        }
        
    	priceDao.update(price);
        return backToShow ? String.format("redirect:/prices/%s", id) : "redirect:/prices";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/prices/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	priceDao.delete(id);
        response.setStatus(200);
    }
    
}
