package com.planApi.controller;

import com.planApi.dto.PasswordCkDto;
import com.planApi.dto.*;
import com.planApi.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class PlanApiController {

    private final ScheduleService scheduleService;

    public PlanApiController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto request) {
        ScheduleResponseDto created = scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String updatedDate) {
        return ResponseEntity.ok(scheduleService.getSchedules(user, updatedDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto request) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody PasswordCkDto passwordDto) {
        scheduleService.deleteSchedule(id, passwordDto.getPassword());
        return ResponseEntity.noContent().build();
    }
}
