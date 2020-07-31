package com.ellencode.quickbook.entities.salesreceipt;

import com.ellencode.quickbook.helper.SalesReceiptHelper;
import com.ellencode.quickbook.qbo.DataServiceFactory;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.SalesReceipt;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

import java.text.ParseException;
import java.util.List;

/**
 * Demonstrates methods to void salesreceipt
 * Note: We'll create an entity first and then void the same
 * 
 * @author dderose
 *
 */
public class SalesReceiptVoid {
	
	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			voidSalesReceipt();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void voidSalesReceipt() throws FMSException, ParseException {
		
		try {
			DataService service = DataServiceFactory.getDataService();
			
			// create salesreceipt
			SalesReceipt salesreceipt = SalesReceiptHelper.getSalesReceiptFields(service);
			SalesReceipt addSalesReceipt = service.add(salesreceipt);
			LOG.info("SalesReceipt added : " + addSalesReceipt.getId());

			// void salesreceipt
			SalesReceipt voidedSalesReceipt = service.voidRequest(addSalesReceipt);		
			LOG.info("SalesReceipt voided : " + voidedSalesReceipt.getId() + " status ::: " + voidedSalesReceipt.getPrivateNote());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while voiding entity :: " + error.getMessage()));
		}
	}



}
