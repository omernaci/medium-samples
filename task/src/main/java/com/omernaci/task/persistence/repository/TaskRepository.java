package com.omernaci.task.persistence.repository;

import com.omernaci.task.persistence.entity.Task;
import com.omernaci.task.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findAllByStatus(TaskStatus status);

}
