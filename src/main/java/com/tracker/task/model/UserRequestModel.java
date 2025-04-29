package com.tracker.task.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String country;
}
