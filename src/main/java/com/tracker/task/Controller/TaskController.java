package com.tracker.task.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.task.Entity.Task;
import com.tracker.task.Service.TaskService;


@RestController

public class TaskController {
    @Autowired
     private  TaskService taskservice;

    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody Task task) {
         return ResponseEntity.status(HttpStatus.CREATED).body (this.taskservice.createTask(task));
    }
    

    @GetMapping("/tasks/{task_id}")
   public ResponseEntity<?> getById(@PathVariable Long id){
     Task userTask=this.taskservice.getById(id);
       if(userTask==null)
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("task with this id does not exist");
       else
       return ResponseEntity.ok(userTask);
       }


   

    @PutMapping(path="/tasks/{id}")
        public ResponseEntity<?>updateTask(@PathVariable Long id,@RequestBody Task updatedTask){
            Task foundTask=this.taskservice.getById(id);
            if(foundTask==null)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("task not exist");
    
            }
            else{
               
                return ResponseEntity.ok( this.taskservice.updateTask(id,updatedTask));
            }

       }

        
    


        @DeleteMapping("/tasks/{id}")
        public ResponseEntity<?> deleteTask(@PathVariable Long id){
         Task foundTask=this.taskservice.getById(id);
         if(foundTask==null)
         {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("task not exist");
 
         }
         else{
             this.taskservice.deleteTask(id);
             return ResponseEntity.noContent().build();
         }}
    }

