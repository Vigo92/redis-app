package com.vigo.redis.service;

import com.vigo.redis.model.entity.Customer;

/**
 * @author : Obia Ugochukwu Vigo
 * email : ugochukwu.obia@teamapt.com
 * date : 19/10/2022
 **/
public interface CustomerService {


    Customer getCustomer(Long id);
}
