package vn.edu.vnuk.airlines.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import vn.edu.vnuk.airlines.model.Country;

public class ClassController {

	private ClassesDao dao;
	
	@Autowired
	public void setDayDao(ClassesDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/classes")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("classes", dao.read());
        model.addAttribute("template", "class/index");
        return "_layout";
    }
    
    
    @RequestMapping("/classes/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("class", dao.read(id));
        model.addAttribute("template", "class/show");
        return "_layout";
    }
    
    
    @RequestMapping("/classes/new")
    public String add(Country country, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "class/new");
        return "_layout";
    }
    
    
    @SuppressWarnings("rawtypes")
	@RequestMapping("/classes/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Class classes,
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
    	model.addAttribute("class", dao.read(id));
        model.addAttribute("template", "day/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/classes", method=RequestMethod.POST)
    public String create(
		
    	@Valid vn.edu.vnuk.airlines.model.Class classes,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/classes/new";
        }
        
        dao.create(classes);
        return "redirect:/classes";
        
        
    }
    
    
    @RequestMapping(value="/classes/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid vn.edu.vnuk.airlines.model.Class classes,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/classes/%s/edit", id);
        }
        
        dao.update(classes);
        return backToShow ? String.format("redirect:/classes/%s", id) : "redirect:/classes";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/classes/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
