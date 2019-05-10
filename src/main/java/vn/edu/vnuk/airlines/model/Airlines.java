/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnuk.airlines.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author michel
 */
public class Airlines {
    private Long id;
    
    @NotNull
    @Size(min = 5, message="Description must contain at least 5 characters")
    private String pilotName;
    
    
    
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date dateOfCompletion;
    


    
}
