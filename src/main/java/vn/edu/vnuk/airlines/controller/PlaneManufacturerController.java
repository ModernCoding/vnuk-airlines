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

import vn.edu.vnuk.airlines.dao.PlaneManufacturerDao;
import vn.edu.vnuk.airlines.model.PlaneManufacturer;

@Controller
public class PlaneManufacturerController {
	
	private PlaneManufacturerDao dao;
	
	@Autowired
	public void setPlaneManufacturerDao(PlaneManufacturerDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/plane-manufacturers")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("planeManufacturers", dao.read());
        model.addAttribute("template", "plane-manufacturer/index");
        return "_layout";
    }
    
    
    @RequestMapping("/plane-manufacturers/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("planeManufacturer", dao.read(id));
        model.addAttribute("template", "plane-manufacturer/show");
        return "_layout";
    }
    
    
    @RequestMapping("/plane-manufacturers/new")
    public String add(PlaneManufacturer planeManufacturer, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "plane-manufacturer/new");
        return "_layout";
    }
    
    
    @RequestMapping("/plane-manufacturers/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		PlaneManufacturer planeManufacturer,
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
    	model.addAttribute("planeManufacturer", dao.read(id));
        model.addAttribute("template", "plane-manufacturer/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/plane-manufacturers", method=RequestMethod.POST)
    public String create(
		
    	@Valid PlaneManufacturer planeManufacturer,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/plane-manufacturers/new";
        }
        
        dao.create(planeManufacturer);
        return "redirect:/plane-manufacturers";
        
        
    }
    
    
    @RequestMapping(value="/plane-manufacturers/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid PlaneManufacturer planeManufacturer,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/plane-manufacturers/%s/edit", id);
        }
        
        dao.update(planeManufacturer);
        return backToShow ? String.format("redirect:/plane-manufacturers/%s", id) : "redirect:/plane-manufacturers";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/plane-manufacturers/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
