package com.anish.ShoppingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anish.ShoppingSite.model.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {

}
