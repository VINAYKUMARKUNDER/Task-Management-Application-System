package com.vinay.task.model;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class User {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private Long id;
	    @Email(message = "{user.email.not.valid}")
	    @NotEmpty(message = "{user.email.not.empty}")
	    @Column(unique = true)
	    private String email;
	    @NotEmpty(message = "{user.name.not.empty}")
	    private String name;
	    @NotEmpty(message = "{user.password.not.empty}")
	    @Size(min = 5, message = "{user.password.Size}")
	    private String password;
	    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'images/user.png'")
	    private String photo;
	    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
	    private List<Task> tasksOwned;

	    @ManyToMany(cascade = CascadeType.MERGE)
	
	    private List<Role> roles;

	    public List<Task> getTasksCompleted() {
	        return tasksOwned.stream()
	                .filter(Task::isCompleted)
	                .collect(Collectors.toList());
	    }

	    public List<Task> getTasksInProgress() {
	        return tasksOwned.stream()
	                .filter(task -> !task.isCompleted())
	                .collect(Collectors.toList());
	    }

	    public boolean isAdmin() {
	        String roleName = "ADMIN";
	        return roles.stream().map(Role::getRole).anyMatch(roleName::equals);
	    }
}
