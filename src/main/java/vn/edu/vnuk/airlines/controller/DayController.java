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

import vn.edu.vnuk.airlines.dao.DayDao;
import vn.edu.vnuk.airlines.model.Country;
import vn.edu.vnuk.airlines.model.Day;

@Controller
public class DayController {

	private DayDao dao;
	
	@Autowired
	public void setDayDao(DayDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/days")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("day", dao.read());
        model.addAttribute("template", "day/index");
        return "_layout";
    }
    
    
    @RequestMapping("/days/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("day", dao.read(id));
        model.addAttribute("template", "day/show");
        return "_layout";
    }
    
    
    @RequestMapping("/days/new")
    public String add(Country country, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "day/new");
        return "_layout";
    }
    
    
    @RequestMapping("/days/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Day day,
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
    	model.addAttribute("day", dao.read(id));
        model.addAttribute("template", "day/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/days", method=RequestMethod.POST)
    public String create(
		
    	@Valid Day day,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/days/new";
        }
        
        dao.create(day);
        return "redirect:/days";
        
        
    }
    
    
    @RequestMapping(value="/days/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Day day,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/days/%s/edit", id);
        }
        
        dao.update(day);
        return backToShow ? String.format("redirect:/days/%s", id) : "redirect:/days";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/days/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
