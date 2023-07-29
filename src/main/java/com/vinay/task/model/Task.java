package com.vinay.task.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Task {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "task_id")
	    private Long id;
	    @NotEmpty(message = "{task.name.not.empty}")
	    private String name;
	    @NotEmpty(message = "{task.description.not.empty}")
	    @Column(length = 1200)
	    @Size(max = 1200, message = "{task.description.size}")
	    private String description;
	    @NotNull(message = "{task.date.not.null}")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private LocalDate date;
	    private boolean isCompleted;
	    private String creatorName;
	    @ManyToOne
	    @JoinColumn(name = "OWNER_ID")
	    private User owner;

	    public long daysLeftUntilDeadline(LocalDate date) {
	        return ChronoUnit.DAYS.between(LocalDate.now(), date);
	    }
	    
	    public Task() {
	    }

	    public Task(@NotEmpty String name,
	                @NotEmpty @Size(max = 1200) String description,
	                @NotNull LocalDate date,
	                boolean isCompleted,
	                String creatorName) {
	        this.name = name;
	        this.description = description;
	        this.date = date;
	        this.isCompleted = isCompleted;
	        this.creatorName = creatorName;
	    }

	    public Task(@NotEmpty String name,
	                @NotEmpty @Size(max = 1200) String description,
	                @NotNull LocalDate date,
	                boolean isCompleted,
	                String creatorName,
	                User owner) {
	        this.name = name;
	        this.description = description;
	        this.date = date;
	        this.isCompleted = isCompleted;
	        this.creatorName = creatorName;
	        this.owner = owner;
	    }

}
