package com.techelevator.dao;

import com.techelevator.model.Activity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcActivityDao implements ActivityDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcActivityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //list all activity based off user id
    @Override
    public List<Activity> findAllFinishedBooksByUserId(Long userId) {
        List<Activity> finishedBooks = new ArrayList<>();

        String sql = "SELECT activity_id, user_id, isbn, date_read, minutes_read, is_finished, media_type " +
                     "FROM activity " +
                     "WHERE user_id = ? AND is_finished = true;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            finishedBooks.add(mapRowToActivity(results));
        }
        return finishedBooks;
    }

    //list all activity based off family id
    @Override
    public List<Activity> findAllFinishedBooksByFamily(Long familyId) {
        List<Activity> finishedBooks = new ArrayList<>();

        String sql = "SELECT activity_id, user_id, isbn, date_read, minutes_read, is_finished, media_type " +
                     "FROM activity " +
                     "WHERE family_id = ? AND is_finished = true;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        while(results.next()) {
            finishedBooks.add(mapRowToActivity(results));
        }
        return finishedBooks;
    }

    @Override
    public List<Activity> activityByFamily(Long familyId) {
        List<Activity> bookActivity = new ArrayList<>();

        String sql = "SELECT activity_id, activity.user_id, isbn, date_read, minutes_read, is_finished, media_type " +
                "                     FROM activity JOIN users ON activity.user_id = users.user_id " +
                "                     WHERE family_id = ? AND minutes_read > 0;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        while(results.next()) {
            bookActivity.add(mapRowToActivity(results));
        }
        return bookActivity;
    }

    //all activity based off userid -- return list of Activity objects
    @Override
    public List<Activity> returnActivityForUser(Long UserId) {
        List<Activity> activityObject = new ArrayList<>();

        String sql = "SELECT activity_id, user_id, isbn, date_read, minutes_read, is_finished, media_type " +
                     "FROM activity " +
                     "WHERE user_id = ? AND (minutes_read > 0 OR is_finished = true);";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, UserId);
        while(results.next()){
            activityObject.add(mapRowToActivity(results));
        }
        return activityObject;
    }

    //create activity
    @Override
    public Activity createActivity(Activity activity) {

        String sql = "INSERT INTO activity(user_id, isbn, date_read, minutes_read, is_finished, media_type) " +
                     "VALUES(?, ?, ?, ?, ?, ?) " +
                     "RETURNING activity_id;";
        Long newActivityId = jdbcTemplate.queryForObject(sql, Long.class, activity.getUserId(), activity.getIsbn(),
                activity.getDateRead(), activity.getMinutesRead(), activity.getisFinished(), activity.getMediaType());

        activity.setActivityId(newActivityId);
        return activity;
    }

    private Activity mapRowToActivity(SqlRowSet rs) {
        Activity activity = new Activity();
        activity.setActivityId(rs.getLong("activity_id"));
        activity.setUserId(rs.getLong("user_id"));
        activity.setIsbn(rs.getString("isbn"));
        activity.setDateRead(rs.getDate("date_read").toLocalDate());
        activity.setMinutesRead(rs.getInt("minutes_read"));
        activity.setFinished(rs.getBoolean("is_finished"));
        activity.setMediaType(rs.getString("media_type"));
        return activity;
    }
}
