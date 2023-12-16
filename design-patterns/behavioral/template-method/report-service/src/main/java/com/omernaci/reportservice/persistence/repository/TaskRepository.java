package com.omernaci.reportservice.persistence.repository;

import com.omernaci.reportservice.persistence.entity.Task;
import com.omernaci.reportservice.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findAllByStatus(TaskStatus status);

}
