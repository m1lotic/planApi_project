package com.planApi.respository;

import com.planApi.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    void save(Schedule schedule);
    Schedule findById(Long id);
    List<Schedule> findAll(String user, String updatedDate);
    void update(Long id, String title, String user);
    void delete(Long id);
    String getPasswordById(Long id);
}

