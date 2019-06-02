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
import vn.edu.vnuk.airlines.dao.PlaneModelDao;
import vn.edu.vnuk.airlines.model.PlaneModel;

@Controller
public class PlaneModelController {

	private PlaneModelDao planeModelDao;
	private PlaneManufacturerDao planeManufacturerDao;

	@Autowired
	public void setPlaneModelDao(PlaneModelDao planeModelDao) {
		this.planeModelDao = planeModelDao;
	}

	@Autowired
	public void setPlaneManufacturerDao(PlaneManufacturerDao planeManufacturerDao) {
		this.planeManufacturerDao = planeManufacturerDao;
	}

	

	@RequestMapping("/plane-models")
    public String index(
		
		@RequestParam(value="planeManufacturerId", required = false) String planeManufacturerId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("planeModels", planeModelDao.read(planeManufacturerId));
		
		if (planeManufacturerId != null) {
			model.addAttribute("planeManufacturer", planeManufacturerDao.read(Long.parseLong(planeManufacturerId)));
		}
		
        model.addAttribute("template", "plane-model/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/plane-models/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("planeModel", planeModelDao.read(id));
        model.addAttribute("template", "plane-model/show");
        return "_layout";
    }
    
    
    @RequestMapping("/plane-models/new")
    public String add(
    		
		PlaneModel planeModel,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "plane-model/new");
    	model.addAttribute("planeManufacturers", planeManufacturerDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/plane-models/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		PlaneModel planeModel,
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
    	model.addAttribute("planeModels", planeModelDao.read(id));
    	model.addAttribute("planeManufacturers", planeManufacturerDao.read());
        model.addAttribute("template", "plane-model/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/plane-models", method=RequestMethod.POST)
    public String create(
		
    	@Valid PlaneModel planeModel,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/plane-models/new";
        }
        
        
        planeModelDao.create(planeModel);
        return "redirect:/plane-models";
        
    }
    
    
    @RequestMapping(value="/plane-models/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid PlaneModel planeModel,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/plane-models/%s/edit", id);
        }
        
        planeModelDao.update(planeModel);
        return backToShow ? String.format("redirect:/plane-models/%s", id) : "redirect:/plane-models";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/plane-models/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	planeModelDao.delete(id);
        response.setStatus(200);
    }
    
}
