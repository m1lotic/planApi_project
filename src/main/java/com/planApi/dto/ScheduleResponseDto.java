package com.planApi.dto;

import com.planApi.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String user;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static ScheduleResponseDto from(Schedule entity) {
        return new ScheduleResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getUser(),
                entity.getCreatedDate(),
                entity.getUpdatedDate()
        );
    }
}
