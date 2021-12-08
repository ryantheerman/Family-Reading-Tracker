package com.techelevator.dao;

import com.techelevator.model.Prize;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcPrizeDao implements PrizeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Prize> listPrizes() {
        return null;
    }

    @Override
    public Prize getPrizeById(Long prizeId) {
        return null;
    }
}
