package com.vinay.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinay.task.model.Role;




public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	 Role findByRole(String user);

}
