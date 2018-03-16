package com.abebe.demo.repo;

import org.springframework.data.repository.CrudRepository;

public interface AppRoleRepository extends CrudRepository<com.abebe.demo.model.AppRole, Long> {
    com.abebe.demo.model.AppRole findAppRoleByRoleName(String roleName);

    com.abebe.demo.model.AppRole findByUsers(com.abebe.demo.model.AppUser user);
}
