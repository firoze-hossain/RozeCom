package com.roze.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.roze.common.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
