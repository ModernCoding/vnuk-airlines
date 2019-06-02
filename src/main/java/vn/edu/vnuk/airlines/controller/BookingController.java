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

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;

import vn.edu.vnuk.airlines.dao.BookingDao;
import vn.edu.vnuk.airlines.dao.FlightDao;
import vn.edu.vnuk.airlines.dao.PaymentMethodDao;
import vn.edu.vnuk.airlines.dao.PriceDao;
import vn.edu.vnuk.airlines.dao.UserDao;
import vn.edu.vnuk.airlines.model.Booking;
import vn.edu.vnuk.airlines.model.IdentificationType;
import vn.edu.vnuk.airlines.model.PlaneModel;
import vn.edu.vnuk.airlines.model.User;

@Controller
public class BookingController {

	private BookingDao bookingDao;
	private UserDao userDao;
	private FlightDao flightDao;
	private PriceDao priceDao;
	private PaymentMethodDao paymentMethodDao;

	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setFlightDao(FlightDao flightDao) {
		this.flightDao = flightDao;
	}
	
	@Autowired
	public void setPriceDao(PriceDao priceDao) {
		this.priceDao = priceDao;
	}
	
	@Autowired
	public void setPaymentMethodDao(PaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}


	@RequestMapping("/users")
    public String index(
		
    		@RequestParam(value="userId", required = false) String userId,
    		@RequestParam(value="flightId", required = false) String flightId,
    		@RequestParam(value="priceId", required = false) String priceId,
    		@RequestParam(value="paymentMethodId", required = false) String paymentMethodId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("bookings", bookingDao.read(userId));
		model.addAttribute("bookings", bookingDao.read(flightId));
		model.addAttribute("bookings", bookingDao.read(priceId));
		model.addAttribute("bookings", bookingDao.read(paymentMethodId));
		
		if (userId != null) {
			model.addAttribute("user", bookingDao.read(Long.parseLong(userId)));
		}
		
		if (flightId != null) {
			model.addAttribute("flight", bookingDao.read(Long.parseLong(flightId)));
		}
		
		if (priceId != null) {
			model.addAttribute("price", bookingDao.read(Long.parseLong(priceId)));
		}
		
		if (paymentMethodId != null) {
			model.addAttribute("paymentMethod", bookingDao.read(Long.parseLong(paymentMethodId)));
		}
		
        model.addAttribute("template", "booking/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/bookings/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("booking", bookingDao.read(id));
        model.addAttribute("template", "booking/show");
        return "_layout";
    }
    
    
    @RequestMapping("/bookings/new")
    public String add(
    		
		Booking booking,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) { 
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "booking/new");
    	model.addAttribute("users", userDao.read(userTypeId, identificationTypeId));
    	model.addAttribute("flights", flightDao.read(routeId, dayId, planeModelId));
    	model.addAttribute("prices", priceDao.read(flightId, classId, priceTypeId));
    	model.addAttribute("paymentMethods", paymentMethodDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/bookings/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Booking booking,
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
    	model.addAttribute("bookings", bookingDao.read(id));
    	model.addAttribute("users", userDao.read(userTypeId, identificationTypeId));
    	model.addAttribute("flights", flightDao.read(routeId, dayId, planeModelId));
    	model.addAttribute("prices", priceDao.read(flightId, classId, priceTypeId));
    	model.addAttribute("paymentMethods", paymentMethodDao.read());
        model.addAttribute("template", "plane-model/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/bookings", method=RequestMethod.POST)
    public String create(
		
    	@Valid Booking booking,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/bookings/new";
        }
        
        
        bookingDao.create(booking);
        return "redirect:/bookings";
        
    }
    
    
    @RequestMapping(value="/bookings/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Booking booking,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/bookings/%s/edit", id);
        }
        
        bookingDao.update(booking);
        return backToShow ? String.format("redirect:/bookings/%s", id) : "redirect:/bookings";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/bookings/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	bookingDao.delete(id);
        response.setStatus(200);
    }
    
}
