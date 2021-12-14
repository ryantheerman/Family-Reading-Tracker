package com.techelevator.dao;

import com.techelevator.model.Activity;
import com.techelevator.model.Prize;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JdbcPrizeDao implements PrizeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void updatePrize(Prize Prize) {

        String sql = "UPDATE prizes SET prize_name = ?, description = ?, milestone = ?, family_id = ?, max_prizes = ?, " +
                     "start_date = ?, end_date = ? " +
                     "WHERE prize_id = ?";
        String sql2 = "UPDATE prizes SET is_active = true WHERE CURRENT_DATE BETWEEN start_date AND end_date OR (CURRENT_DATE = start_date OR CURRENT_DATE = end_date);" +
                "UPDATE prizes SET is_active = false WHERE CURRENT_DATE NOT BETWEEN start_date AND end_date AND (CURRENT_DATE != start_date AND CURRENT_DATE != end_date);";

        jdbcTemplate.update(sql, Prize.getPrizeName(), Prize.getPrizeDescription(),
        Prize.getMilestone(), Prize.getFamilyId(), Prize.getMaxPrizes(), Prize.getStartDate(), Prize.getEndDate(), Prize.getPrizeId());

        jdbcTemplate.update(sql2);


    }

    // delete prize

    @Override
    public void deletePrize(Prize prize) {
        String sql = "DELETE FROM prizes WHERE prize_id = ?;";
        jdbcTemplate.update(sql, prize.getPrizeId());

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
                     "max_prizes, start_date, end_date, is_active " +
                     "FROM prizes " +
                     "JOIN prize_user ON prizes.prize_id = prize_user.prize_id " +
                     "JOIN users ON users.user_id = prize_user.user_id " +
                     "WHERE users.user_id = ?;";
        String sql2 = "UPDATE prizes SET is_active = true WHERE CURRENT_DATE BETWEEN start_date AND end_date OR (CURRENT_DATE = start_date OR CURRENT_DATE = end_date);" +
                "UPDATE prizes SET is_active = false WHERE CURRENT_DATE NOT BETWEEN start_date AND end_date AND (CURRENT_DATE != start_date AND CURRENT_DATE != end_date);";

        jdbcTemplate.update(sql2);

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

        String sql = "INSERT INTO prizes (prize_name, description, milestone, family_id, max_prizes, start_date, end_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING prize_id;" +
                "UPDATE prizes SET is_active = true WHERE CURRENT_DATE BETWEEN start_date AND end_date OR (CURRENT_DATE = start_date OR CURRENT_DATE = end_date);";

        Long newId = jdbcTemplate.queryForObject(sql, Long.class, prize.getPrizeName(), prize.getPrizeDescription(),
            prize.getMilestone(), prize.getFamilyId(), prize.getMaxPrizes(), prize.getStartDate(), prize.getEndDate());

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
        prize.setIsActive(rs.getBoolean("is_active"));
        System.out.println(prize.getIsActive());
        return prize;
    }
}
;
