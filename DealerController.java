package com.groupthree.incentivesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groupthree.incentivesystem.entities.Dealer;
import com.groupthree.incentivesystem.entities.Deals;
import com.groupthree.incentivesystem.services.DealerService;

@RestController
public class DealerController {

	@Autowired
	DealerService dealerService;
	
	// for dealer login
	@PostMapping("/dealer/login")
	public String dealerLogin(@RequestParam int dId, @RequestParam String dPass) {
		if (dealerService.validator(dId, dPass)) {
			if (dealerService.dealerLogin(dId, dPass))
				return "Dealer Login Successful!";
			else
				return "Wrong credentials.";
		} else
			return "Validation failed!";
	}
	
	// for dealer registration
	@PostMapping("/dealer/register")
	public Dealer dealerRegistration(@RequestParam String dName, @RequestParam long dContact, 
			@RequestParam String dPass) {
		if(dealerService.validator(dName, dContact, dPass)) {
			return dealerService.dealerRegistration(dName, dContact, dPass);
		}
		else
			return new Dealer();
	}
	
	// for creating deals
	@PostMapping("/dealer/logged/createDeals")
	public Deals createDeals(@RequestParam String dealModel, @RequestParam String incentiveRange) {
		if(dealerService.validator(dealModel) && dealerService.incentiveValidator(incentiveRange) ){
			return dealerService.createDeals(dealModel, incentiveRange);
		}
		else
			return new Deals();
	}
	
	// for redefining deals
	@PostMapping("/dealer/logged/redefineDeals")
	public Deals redefineDeals(String dealModel, String incentiveRange) {
		if(dealerService.validator(dealModel) && dealerService.incentiveValidator(incentiveRange)) {
			return dealerService.redefineDeals(dealModel, incentiveRange);
		}
		else
			return new Deals();
	}
	
	// for deleting deals
	@PostMapping("/dealer/logged/deleteDeals")
	public String deleteDeals(String dealModel) {
		if(dealerService.validator(dealModel)) {
			return dealerService.deleteDeals(dealModel);
		}
		else
			return "Deal not Deleted";
	}
	
	
}
