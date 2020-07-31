package com.ellencode.quickbook.controller;

import com.ellencode.quickbook.entities.companyinfo.CompanyInfoQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class CompanyController {

//    private static final org.slf4j.Logger log = com.intuit.ipp.util.Logger.getLogger();

    @Autowired
    CompanyInfoQuery companyInfo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCompany() {
        String result = "";
        try{
            result = companyInfo.queryCompanyInfo();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//    @ResponseBody



}
