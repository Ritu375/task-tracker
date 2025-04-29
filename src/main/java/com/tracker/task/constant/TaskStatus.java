package com.tracker.task.constant;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TaskStatus {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    private String value;

}
