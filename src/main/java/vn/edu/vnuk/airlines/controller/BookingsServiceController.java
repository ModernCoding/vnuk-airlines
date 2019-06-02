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

import vn.edu.vnuk.airlines.dao.BookingDao;
import vn.edu.vnuk.airlines.dao.BookingsServiceDao;
import vn.edu.vnuk.airlines.dao.ServiceDao;
import vn.edu.vnuk.airlines.model.BookingsService;
import vn.edu.vnuk.airlines.model.Price;

@Controller
public class BookingsServiceController {

	private BookingsServiceDao bookingsServiceDao;
	private BookingDao bookingDao;
	private ServiceDao serviceDao;

	@Autowired
	public void setBookingsServiceDao(BookingsServiceDao bookingsServiceDao) {
		this.bookingsServiceDao = bookingsServiceDao;
	}

	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	@Autowired
	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}
	


	@RequestMapping("/bookings-services")
    public String index(
		
    		@RequestParam(value="bookingId", required = false) String bookingId,
    		@RequestParam(value="serviceId", required = false) String serviceId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("bookingsServices", bookingsServiceDao.read(bookingId));
		model.addAttribute("bookingsServices", bookingsServiceDao.read(serviceId));
		
		if (bookingId != null) {
			model.addAttribute("booking", bookingDao.read(Long.parseLong(bookingId)));
		}
		
		if (serviceId != null) {
			model.addAttribute("service", serviceDao.read(Long.parseLong(serviceId)));
		}
		
		
        model.addAttribute("template", "bookings-service/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/bookings-services/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("bookingsService", bookingsServiceDao.read(id));
        model.addAttribute("template", "bookingsService/show");
        return "_layout";
    }
    
    
    @RequestMapping("/bookings-services/new")
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
    	
    	model.addAttribute("template", "bookings-service/new");
    	model.addAttribute("bookings", bookingDao.read(userId, flightId, priceId, paymentMethodId));
    	model.addAttribute("services", serviceDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/bookings-services/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		BookingsService bookingsService,
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
    	model.addAttribute("bookingsServices", bookingsServiceDao.read(id));
    	model.addAttribute("bookings", bookingDao.read(userId, flightId, priceId, paymentMethodId));
    	model.addAttribute("services", serviceDao.read());
        model.addAttribute("template", "bookings-service/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/bookings-services", method=RequestMethod.POST)
    public String create(
		
    	@Valid BookingsService bookingsService,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/bookings-services/new";
        }
        
        
        bookingsServiceDao.create(bookingsService);
        return "redirect:/bookings-services";
        
    }
    
    
    @RequestMapping(value="/bookings-services/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid BookingsService bookingsService,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/bookings-services/%s/edit", id);
        }
        
    	bookingsServiceDao.update(bookingsService);
        return backToShow ? String.format("redirect:/bookings-services/%s", id) : "redirect:/bookings-services";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/bookings-services/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	bookingsServiceDao.delete(id);
        response.setStatus(200);
    }
    
}
