package com.vigo.redis.service.impl;

import com.vigo.redis.model.entity.Item;
import com.vigo.redis.repository.ItemRepository;
import com.vigo.redis.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
public class ItemServiceImpl  implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    @Cacheable(value = "itemCache")
    public Item getItem(Long id) {
        return itemRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
