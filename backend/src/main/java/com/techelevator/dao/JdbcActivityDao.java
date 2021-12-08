package com.techelevator.dao;

import com.techelevator.model.Activity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcActivityDao implements ActivityDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcActivityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Activity> findAllFinishedBooks() {
        return null;
    }
}
