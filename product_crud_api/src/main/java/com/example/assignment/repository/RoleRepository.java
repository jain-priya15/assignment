package com.example.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assignment.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
