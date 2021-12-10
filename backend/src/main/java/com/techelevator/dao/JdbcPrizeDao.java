package com.techelevator.dao;

import com.techelevator.model.Activity;
import com.techelevator.model.Prize;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcPrizeDao implements PrizeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //get list of prizes by family id
    @Override
    public List<Prize> prizesByFamilyId(Long familyId) {
        List<Prize> prizesByFam = new ArrayList<>();

        String sql = "SELECT prize_id, prize_name, description, milestone, family_id, " +
                     "max_prizes, start_date, end_date, is_active " +
                     "FROM prizes " +
                     "WHERE family_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        while(results.next()) {
            prizesByFam.add(mapRowToPrize(results));
        }
        return prizesByFam;
    }



    //all prizes by userId
    @Override
    public List<Prize> listPrizes(Long UserId) {
        List<Prize> prizesByUserId = new ArrayList<>();

        String sql = "SELECT prizes.prize_id, prize_name, description, milestone, prizes.family_id, " +
                     "max_prizes, start_date, end_date, is_active n" +
                     "FROM prizes " +
                     "JOIN prize_user ON prizes.prize_id = prize_user.prize_id " +
                     "JOIN users ON users.user_id = prize_user.user_id " +
                     "WHERE users.user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, UserId);
        while(results.next()) {
            prizesByUserId.add(mapRowToPrize(results));
        }
        return prizesByUserId;
    }

    @Override
    public Prize getPrizeById(Long prizeId) {

        String sql = "SELECT prizes.prize_id, prize_name, description, milestone, family_id, " +
                     "max_prizes, start_date, end_date, is_active " +
                     "FROM prizes " +
                     "JOIN prize_user ON prize_user.prize_id = prizes.prize_id " +
                     "WHERE prizes.prize_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, prizeId);

        return mapRowToPrize(result);
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

    private Prize mapRowToPrize(SqlRowSet rs) {
        Prize prize = new Prize();
        prize.setPrizeId(rs.getLong("prize_id"));
        prize.setPrizeName(rs.getString("prize_name"));
        prize.setPrizeDescription(rs.getString("description"));
        prize.setMilestone(rs.getInt("milestone"));
        prize.setFamilyId(rs.getLong("family_id"));
        prize.setMaxPrizes(rs.getInt("max_prizes"));
        prize.setStartDate(rs.getDate("start_date").toLocalDate());
        prize.setEndDate(rs.getDate("end_date").toLocalDate());
        prize.setActive(rs.getBoolean("is_active"));
        return prize;
    }
}
;
