package com.techelevator.dao;

import com.techelevator.model.Activity;

import java.util.List;

public interface ActivityDao {

    //more methods can be added here. more of a placeholder for now.

    List<Activity> findAllFinishedBooksByUserId(Long userId);

    Activity createActivity(Activity activity);

    List<Activity> findAllFinishedBooksByFamily(Long familyId);

    List<Activity> returnActivityForUser(Long UserId);

    List<Activity> activityByFamily(Long familyId);

}
