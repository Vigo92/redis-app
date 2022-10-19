package com.vigo.redis.controller;

import com.vigo.redis.model.entity.Customer;
import com.vigo.redis.model.entity.Item;
import com.vigo.redis.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Obia Ugochukwu Vigo
 * email : ugochukwu.obia@teamapt.com
 * date : 19/10/2022
 **/

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Customer> getItem(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
    }
}
