package com.ellencode.quickbook.controller;

import com.ellencode.quickbook.entities.salesreceipt.SalesReceiptQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SalesReceiptController {

//    private static final org.slf4j.Logger log = com.intuit.ipp.util.Logger.getLogger();

    @Autowired
    SalesReceiptQuery salesReceiptQuery;

    @RequestMapping(value = "/salesReceipt", method = RequestMethod.GET)
    public String getInvoice() {
        String result = "";
        try{
            result = salesReceiptQuery.querySalesReceipt();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//    @ResponseBody



}
