package com.tracker.task.model;

import com.tracker.task.constant.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestModel {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private TaskStatus status;


    private Timestamp createdDate;

    private Timestamp completionDate;

}
