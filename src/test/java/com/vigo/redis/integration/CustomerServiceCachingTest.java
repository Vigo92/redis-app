package com.vigo.redis.integration;

import com.vigo.redis.model.entity.Customer;
import com.vigo.redis.repository.CustomerRepository;
import com.vigo.redis.service.CustomerService;
import com.vigo.redis.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author : Obia Ugochukwu Vigo
 * email : ugochukwu.obia@teamapt.com
 * date : 19/10/2022
 **/

@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(classes = {CacheAutoConfiguration.class, RedisAutoConfiguration.class})
@EnableCaching
@SpringBootTest
public class CustomerServiceCachingTest {

    private static final Long ID = 1L;
    private static final String NAME = "Vigo";

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void givenRedisCaching_whenFindById_thenItemReturnedFromCache(){
        Customer customer = new Customer(ID,NAME);
        given(customerRepository.findById(ID))
                .willReturn(Optional.of(customer));

        Customer customer1 = customerService.getCustomer(ID);
        Customer customer2 = customerService.getCustomer(ID);

        assertThat(customer1).isEqualTo(customer);
        assertThat(customer2).isEqualTo(customer);

        verify(customerRepository,times(1)).findById(ID);
        assertThat(customerFromCache()).isEqualTo(customer);
    }

    private Object customerFromCache(){
        return cacheManager.getCache("customerCache").get(ID).get();
    }



    @TestConfiguration
    static class  EmbeddedRedisConfiguration{

        private final RedisServer redisServer;

        public EmbeddedRedisConfiguration (){
            this.redisServer = new RedisServer();
        }

        @PostConstruct
        public void startRedis(){
            redisServer.start();
        }

        @PreDestroy
        public void stopRedis(){
            this.redisServer.stop();
        }
    }
}
