package com.ellencode.quickbook.entities.invoice;

import com.alibaba.fastjson.JSON;
import com.ellencode.quickbook.helper.InvoiceHelper;
import com.ellencode.quickbook.qbo.DataServiceFactory;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.intuit.ipp.util.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.util.List;

/**
 * Demonstrates methods to query invoice 
 * 1. Query all records
 * 2. Query by id, note we'll add the entity first and then query
 * 
 * @author dderose
 *
 */
@Configuration
public class InvoiceQuery {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			queryInvoice();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	@Bean
	public static String queryInvoice() throws FMSException, ParseException {
		String result = "";
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// get all invoices
			String sql = "select * from invoice";
			QueryResult queryResult = service.executeQuery(sql);
			int count = queryResult.getEntities().size();
			
			LOG.info("Total number of invoices: " + count);

			// add invoice
			Invoice invoice = InvoiceHelper.getInvoiceFields(service);
			Invoice savedInvoice = service.add(invoice);
			LOG.info("Invoice created: " + savedInvoice.getId() + " ::invoice doc num: " + savedInvoice.getDocNumber());

			// get invoice  based on id
			sql = "select * from invoice where id = '" + savedInvoice.getId() + "'"; 
			queryResult = service.executeQuery(sql);
			invoice = (Invoice)queryResult.getEntities().get(0);
			LOG.info("Invoice doc num : " + invoice.getDocNumber());

			result = JSON.toJSONString(invoice);
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling executeQuery :: " + error.getMessage()));
		}

		return result;
		
	}
}
