package com.nfd.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nfd.model.EmailPreference;
import com.nfd.service.EmailPreferenceService;

@RestController
public class EmailPreferenceController {

	    @Autowired
	    EmailPreferenceService emailService;  //Service which will do all data retrieval/manipulation work
	 
	     
	    //-------------------Retrieve All Users--------------------------------------------------------
	     
	    @RequestMapping(value = "/emailPreferences", method = RequestMethod.GET)
	    public ResponseEntity<List<EmailPreference>> getAllEmailPreferences() {
	        List<EmailPreference> preferences = emailService.getAllEmailPreferences();
	        if(preferences.isEmpty()){
	            return new ResponseEntity<List<EmailPreference>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<EmailPreference>>(preferences, HttpStatus.OK);
	    }
	 
	 
	    //-------------------Retrieve Single User--------------------------------------------------------
	     
	    @RequestMapping(value = "/emailPreference/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<EmailPreference> getEmailPreference(@PathVariable("id") long id) {
	        System.out.println("Fetching User with id " + id);
	        EmailPreference preference = emailService.getEmailPreference((int)id);
	        if (preference == null) {
	        	// TODO Add logs
	            // System.out.println("Preference with id " + id + " not found");
	            return new ResponseEntity<EmailPreference>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<EmailPreference>(preference, HttpStatus.OK);
	    }
	 
	     
	     
	    //-------------------Create a User--------------------------------------------------------
	     
	    @RequestMapping(value = "/emailPreference/", method = RequestMethod.POST)
	   
	    public ResponseEntity<Void> createPreference(@RequestBody EmailPreference preference, UriComponentsBuilder ucBuilder) { //@ModelAttribute EmailPreference preference,    UriComponentsBuilder ucBuilder) {
//	    	req.getHeaders().get
	    	System.out.println(preference);
	    	
	        System.out.println("Creating User " + preference.getEmailAddress());
	 
	        if (emailService.doesEmailPreferenceExist(preference)) {
	            System.out.println("Email preference with email address " + preference.getEmailAddress() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	 
	        emailService.addEmailPreference(preference);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/emailPreference/{id}").buildAndExpand(preference.getUserId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	        
	    }
	 
	    
	    //------------------- Update a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/emailPreference/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<EmailPreference> updatePreference(@PathVariable("id") long id, @RequestBody EmailPreference preference) {
	        System.out.println("Updating EmailPreference " + id);
	         
	        EmailPreference currentPreference = emailService.getEmailPreference((int) id);
	         
	        if (currentPreference==null) {
	            System.out.println("Preference with id " + id + " not found");
	            return new ResponseEntity<EmailPreference>(HttpStatus.NOT_FOUND);
	        }
	        
	        currentPreference.setEmailAddress(preference.getEmailAddress());
	        currentPreference.setOptin(preference.getOptin());
	         
	        emailService.updateEmailPreference(currentPreference);
	        return new ResponseEntity<EmailPreference>(currentPreference, HttpStatus.OK);
	    }
	 
	    //------------------- Delete a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/emailPreference/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<EmailPreference> deletePreference(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting Preference with id " + id);
	 
	        EmailPreference user = emailService.getEmailPreference((int) id);
	        if (user == null) {
	            System.out.println("Unable to delete preference with id " + id + " not found");
	            return new ResponseEntity<EmailPreference>(HttpStatus.NOT_FOUND);
	        }
	 
	        emailService.deleteEmailPreference((int) id);
	        return new ResponseEntity<EmailPreference>(HttpStatus.NO_CONTENT);
	    }
	 
	     /*
	    //------------------- Delete All Users --------------------------------------------------------
	     
	    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	    public ResponseEntity<User> deleteAllUsers() {
	        System.out.println("Deleting All Users");
	 
	        userService.deleteAllUsers();
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	    }
	 */
	}