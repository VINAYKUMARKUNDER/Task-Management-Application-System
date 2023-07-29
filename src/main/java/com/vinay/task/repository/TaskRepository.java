package com.vinay.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinay.task.model.Task;
import com.vinay.task.model.User;




public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task> findByOwnerOrderByDateDesc(User user);

}
