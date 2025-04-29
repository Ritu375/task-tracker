package com.tracker.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.task.Entity.Task;
import com.tracker.task.Repository.TaskRepository;

@Service
public class TaskService {

    @Autowired

    private TaskRepository taskrepository;
    
    public Task createTask(Task task) {
        task.setCreatedDate(java.time.LocalDate.now());
        return taskrepository.save(task);
    }

    public Task updateTask(Long id, Task taskData) {
        Task task = taskrepository.findById(id).orElseThrow();
        task.setTitle(taskData.getTitle());
        task.setDescription(taskData.getDescription());
        task.setStatus(taskData.getStatus());
        task.setCompletionDate(taskData.getCompletionDate());
        return taskrepository.save(task);
    }

    public void deleteTask(Long id) {
        taskrepository.deleteById(id);
    }

    public Task getById(Long id) {
        return taskrepository.findById(id).orElse(null);
    }


        public Iterable<Task> getAll(){
        
            return this.taskrepository.findAll();
    }

   
    
}
