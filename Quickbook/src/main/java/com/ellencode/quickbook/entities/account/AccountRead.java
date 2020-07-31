package com.ellencode.quickbook.entities.account;

import com.ellencode.quickbook.helper.AccountHelper;
import com.ellencode.quickbook.qbo.DataServiceFactory;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Account;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

import java.util.List;

/**
 * Demonstrates methods to read account data using account id
 * Note: We'll create an entity first and then read the same
 * 
 * @author dderose
 *
 */
public class AccountRead {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			getAccount();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void getAccount() throws FMSException {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add bank account with minimum mandatory fields
			Account account = AccountHelper.getBankAccountFields();
			Account savedAccount = service.add(account);
			LOG.info("Account with all fields created: " + savedAccount.getId());		

			// read using id
			Account accountOut = service.findById(savedAccount);
			LOG.info("Account name: " + accountOut.getFullyQualifiedName());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity findById:: " + error.getMessage()));
		}
		
	}
	
}
