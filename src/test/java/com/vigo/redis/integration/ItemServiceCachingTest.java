package com.vigo.redis.integration;

import com.vigo.redis.model.entity.Item;
import com.vigo.redis.repository.ItemRepository;
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
import org.springframework.cache.annotation.EnableCaching;
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
public class ItemServiceCachingTest {


    private static final Long ID = 2L;

    private static final String NAME = "Ugo";

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void givenRedisCaching_whenFindById_thenItemReturnedFromCache(){
        Item item = new Item(ID,NAME);
        given(itemRepository.findById(ID))
                .willReturn(Optional.of(item));

        Item item1 = itemService.getItem(ID);
        Item item2 = itemService.getItem(ID);

        assertThat(item1).isEqualTo(item);
        assertThat(item2).isEqualTo(item);

        verify(itemRepository,times(1)).findById(ID);
        assertThat(itemFromCache()).isEqualTo(item);
    }


    private Object itemFromCache(){
        return cacheManager.getCache("itemCache").get(ID).get();
    }


    @TestConfiguration
    static class TestRedisConfiguration{

        private final RedisServer redisServer;

        public TestRedisConfiguration(){
            this.redisServer = new RedisServer();
        }

        @PostConstruct
        public void startRedis(){
            this.redisServer.start();
        }

        @PreDestroy
        public void stopRedis(){
            this.redisServer.stop();
        }
    }
}





