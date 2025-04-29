package com.tracker.task.service;

import com.tracker.task.entity.Project;
import com.tracker.task.model.TaskRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.task.entity.Task;
import com.tracker.task.repository.TaskRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskrepository;

    @Autowired
    private ProjectService projectService;

    @Transactional
    public Task createTask(Long projectId, TaskRequestModel taskRequestModel) {
        Project project = projectService.findProjectById(projectId);
        if (project.getUser().getId() != 1111L) {
            throw new RuntimeException("You are not authorized to create a task for this project");
        }
        Task task = mapToTaskEntity(taskRequestModel);
        task.setProject(project);
        return taskrepository.save(task);
    }

    private Task mapToTaskEntity(TaskRequestModel taskRequestModel) {
        Task task = new Task();
        task.setTitle(taskRequestModel.getTitle());
        task.setDescription(taskRequestModel.getDescription());
        task.setStatus(taskRequestModel.getStatus().getValue());
        task.setCreatedDate(taskRequestModel.getCreatedDate());
        if (Objects.nonNull(taskRequestModel.getCompletionDate())) {
            task.setCompletionDate(taskRequestModel.getCompletionDate());
        }
        return task;
    }

    @Transactional
    public Task updateTask(Long id, TaskRequestModel taskData) {
        Task task = taskrepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getProject().getUser().getId() != 1111L) {
            throw new RuntimeException("You are not authorized to view this task");
        }
        task.setTitle(taskData.getTitle());
        task.setDescription(taskData.getDescription());
        task.setStatus(taskData.getStatus().getValue());
        if (Objects.nonNull(taskData.getCompletionDate())) {
            task.setCompletionDate(taskData.getCompletionDate());
        }
        return taskrepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = taskrepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getProject().getUser().getId() != 1111L) {
            throw new RuntimeException("You are not authorized to view this task");
        }
        taskrepository.deleteById(id);
    }

    @Transactional
    public Task getById(Long id) {
        Task task = taskrepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getProject().getUser().getId() != 1111L) {
            throw new RuntimeException("You are not authorized to view this task");
        }
        return task;
    }


}
