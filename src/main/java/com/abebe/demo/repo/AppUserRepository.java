package com.abebe.demo.repo;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<com.abebe.demo.model.AppUser, Long> {
   // AppUser findByUsername(String username);
    com.abebe.demo.model.AppUser findAppUserByUsername(String username);


}
