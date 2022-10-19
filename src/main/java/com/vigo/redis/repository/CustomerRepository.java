package com.vigo.redis.repository;

import com.vigo.redis.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Obia Ugochukwu Vigo
 * email : ugochukwu.obia@teamapt.com
 * date : 19/10/2022
 **/
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
