package com.ellencode.quickbook.controller;

import com.ellencode.quickbook.entities.account.AccountQuery;

import com.intuit.ipp.exception.FMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class AccountController {

//    private static final org.slf4j.Logger log = com.intuit.ipp.util.Logger.getLogger();

    @Autowired
    AccountQuery accountQuery;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getAccount() {
        String result = "";
        try{
            result = accountQuery.queryAccount();
        }catch(FMSException e) {
            e.printStackTrace();
        }
        return result;
    }
//    @ResponseBody



}
