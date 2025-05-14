package com.planApi.service;

import com.planApi.dto.ScheduleRequestDto;
import com.planApi.dto.ScheduleResponseDto;
import com.planApi.entity.Schedule;
import com.planApi.exception.PasswordMismatchException;
import com.planApi.respository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(null, dto.getTitle(), dto.getUser(), dto.getPassword(), null, null);
        repository.save(schedule);
        return ScheduleResponseDto.from(schedule);
    }

    public List<ScheduleResponseDto> getSchedules(String user, String updatedDate) {
        return repository.findAll(user, updatedDate).stream()
                .map(ScheduleResponseDto::from)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto getScheduleById(Long id) {
        return ScheduleResponseDto.from(repository.findById(id));
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        String dbPassword = repository.getPasswordById(id);
        if (!dbPassword.equals(dto.getPassword())) {
            throw new PasswordMismatchException();
        }
        repository.update(id, dto.getTitle(), dto.getUser());
        return getScheduleById(id);
    }

    public void deleteSchedule(Long id, String password) {
        String dbPassword = repository.getPasswordById(id);
        if (!dbPassword.equals(password)) {
            throw new PasswordMismatchException();
        }
        repository.delete(id);
    }
}
