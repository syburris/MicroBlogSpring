package com.YoungMoney;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by stevenburris on 10/22/16.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
