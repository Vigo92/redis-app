package com.vigo.redis.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author : Obia Ugochukwu Vigo
 * email : ugochukwu.obia@teamapt.com
 * date : 19/10/2022
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

        @Id
        private Long id;

        private String name;
}
