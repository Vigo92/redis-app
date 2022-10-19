package com.vigo.redis.service;

import com.vigo.redis.model.entity.Item;

/**
 * @author : Obia Ugochukwu Vigo
 * email : ugochukwu.obia@teamapt.com
 * date : 19/10/2022
 **/
public interface ItemService {

    Item getItem(Long id);
}
