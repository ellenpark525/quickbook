package com.ellencode.quickbook.entities.salesreceipt;

import com.alibaba.fastjson.JSON;
import com.ellencode.quickbook.helper.SalesReceiptHelper;
import com.ellencode.quickbook.qbo.DataServiceFactory;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.SalesReceipt;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.intuit.ipp.util.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.util.List;

/**
 * Demonstrates methods to query salesreceipt data
 * 1. Query all records
 * 2. Query by id, note we'll add the entity first and then query
 * 
 * @author dderose
 *
 */
@Configuration
public class SalesReceiptQuery {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
		
	public static void main(String[] args) {
		try {
			querySalesReceipt();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}

	@Bean
	public static String querySalesReceipt() throws FMSException, ParseException {

		String result = "";
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// get all salesreceipt
			String sql = "select * from salesreceipt";
			QueryResult queryResult = service.executeQuery(sql);
			int count = queryResult.getEntities().size();
			
			LOG.info("Total number of salesreceipts: " + count);

			// add salesreceipt
			SalesReceipt salesreceipt = SalesReceiptHelper.getSalesReceiptFields(service);
			SalesReceipt savedSalesReceipt = service.add(salesreceipt);
			LOG.info("SalesReceipt created: " + savedSalesReceipt.getId() + " ::salesreceipt doc num: " + savedSalesReceipt.getDocNumber());

			// get salesreceipt data based on id
			sql = "select * from salesreceipt where id = '" + savedSalesReceipt.getId() + "'"; 
			queryResult = service.executeQuery(sql);
			salesreceipt = (SalesReceipt) queryResult.getEntities().get(0);
			LOG.info("SalesReceipt amount : " + salesreceipt.getTotalAmt());

			result = JSON.toJSONString(salesreceipt);
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling executeQuery :: " + error.getMessage()));
		}

		return result;
	}
}
