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


    @Override
    public Prize createPrize(Prize prize) {
        System.out.println("prize description: " + prize.getPrizeDescription());
        String sql = "INSERT INTO prizes (prize_name, description, milestone, max_prizes, start_date, end_date) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING prize_id";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, prize.getPrizeName(), prize.getPrizeDescription(),
            prize.getMilestone(), prize.getMaxPrizes(), prize.getStartDate(), prize.getEndDate());

        prize.setPrizeId(newId);
        return prize;
    }
}
