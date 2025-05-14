package com.planApi.respository;

import com.planApi.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Schedule> scheduleRowMapper = (rs, rowNum) -> new Schedule(
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("user"),
            rs.getString("password"),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("updated_date").toLocalDateTime()
    );

    @Override
    public void save(Schedule schedule) {
        String sql = "INSERT INTO plan_schema (title, user, password, created_date, updated_date) VALUES (?, ?, ?, NOW(), NOW())";
        jdbcTemplate.update(sql, schedule.getTitle(), schedule.getUser(), schedule.getPassword());
    }

    @Override
    public Schedule findById(Long id) {
        String sql = "SELECT * FROM plan_schema WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, scheduleRowMapper, id);
    }

    @Override
    public List<Schedule> findAll(String user, String updatedDate) {
        StringBuilder sql = new StringBuilder("SELECT * FROM plan_schema WHERE 1=1");
        if (user != null && !user.isEmpty()) {
            sql.append(" AND user = '").append(user).append("'");
        }
        if (updatedDate != null && !updatedDate.isEmpty()) {
            sql.append(" AND DATE(updated_date) = '").append(updatedDate).append("'");
        }
        sql.append(" ORDER BY updated_date DESC");
        return jdbcTemplate.query(sql.toString(), scheduleRowMapper);
    }

    @Override
    public void update(Long id, String title, String user) {
        String sql = "UPDATE plan_schema SET title = ?, user = ?, updated_date = NOW() WHERE user_id = ?";
        jdbcTemplate.update(sql, title, user, id);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM plan_schema WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public String getPasswordById(Long id) {
        String sql = "SELECT password FROM plan_schema WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
}

