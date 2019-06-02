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

import vn.edu.vnuk.airlines.dao.PaymentMethodDao;
import vn.edu.vnuk.airlines.model.PaymentMethod;
import vn.edu.vnuk.airlines.model.PlaneManufacturer;

@Controller
public class PaymentMethodController {

	private PaymentMethodDao dao;
	
	@Autowired
	public void setPaymentMethodDao(PaymentMethodDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/payment-methods")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("paymentMethod", dao.read());
        model.addAttribute("template", "payment-method/index");
        return "_layout";
    }
    
    
    @RequestMapping("/payment-methods/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("paymentMethod", dao.read(id));
        model.addAttribute("template", "payment-method/show");
        return "_layout";
    }
    
    
    @RequestMapping("/payment-methods/new")
    public String add(PlaneManufacturer planeManufacturer, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "payment-method/new");
        return "_layout";
    }
    
    
    @RequestMapping("/payment-methods/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		PaymentMethod paymentMethod,
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
    	model.addAttribute("paymentMethod", dao.read(id));
        model.addAttribute("template", "payment-method/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/payment-methods", method=RequestMethod.POST)
    public String create(
		
    	@Valid PaymentMethod paymentMethod,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/payment-methods/new";
        }
        
        dao.create(paymentMethod);
        return "redirect:/payment-methods";
        
        
    }
    
    
    @RequestMapping(value="/payment-methods/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid PaymentMethod paymentMethod,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/payment-methods/%s/edit", id);
        }
        
        dao.update(paymentMethod);
        return backToShow ? String.format("redirect:/payment-methods/%s", id) : "redirect:/payment-methods";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/payment-methods/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
