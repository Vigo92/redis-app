package com.vigo.redis.service.impl;

import com.vigo.redis.model.entity.Customer;
import com.vigo.redis.repository.CustomerRepository;
import com.vigo.redis.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author : Obia Ugochukwu Vigo
 * email : ugochukwu.obia@teamapt.com
 * date : 19/10/2022
 **/

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Cacheable(value = "customerCache")
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
