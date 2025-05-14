package com.planApi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String title;
    private String user;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
