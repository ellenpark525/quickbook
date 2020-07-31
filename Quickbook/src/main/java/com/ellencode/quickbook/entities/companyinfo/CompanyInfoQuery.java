package com.ellencode.quickbook.entities.companyinfo;

import com.alibaba.fastjson.JSON;
import com.ellencode.quickbook.qbo.DataServiceFactory;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.CompanyInfo;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.intuit.ipp.util.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.util.List;


/**
 * Demonstrates methods to query companyinfo data
 * 1. Query all records
 * 
 * @author dderose
 *
 */
@Configuration
public class CompanyInfoQuery {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
		
	public static void main(String[] args) {
		try {
			queryCompanyInfo();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}

	@Bean
	public static String queryCompanyInfo() throws FMSException, ParseException {

		String result = "";
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// get all companyinfo
			String sql = "select * from companyinfo";
			QueryResult queryResult = service.executeQuery(sql);
			if (!queryResult.getEntities().isEmpty() && queryResult.getEntities().size() > 0) {
				CompanyInfo companyInfo = (CompanyInfo) queryResult.getEntities().get(0);

				LOG.info("Companyinfo -> CompanyName: " + companyInfo.getCompanyName());
				result = JSON.toJSONString(companyInfo);
				System.out.println(result);
			}
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling executeQuery :: " + error.getMessage()));
			result = e.getMessage().toString();
		}

//		return JSON.toString(companyInfo.getCompanyName());
		return result;
	}
}
