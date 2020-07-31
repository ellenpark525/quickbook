package com.ellencode.quickbook.controller;

import com.ellencode.quickbook.entities.invoice.InvoiceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InvoiceController {

//    private static final org.slf4j.Logger log = com.intuit.ipp.util.Logger.getLogger();

    @Autowired
    InvoiceQuery invoiceQuery;

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public String getInvoice() {
        String result = "";
        try{
            result = invoiceQuery.queryInvoice();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//    @ResponseBody



}
