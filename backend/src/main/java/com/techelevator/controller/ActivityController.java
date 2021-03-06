package com.techelevator.controller;

import com.techelevator.dao.JdbcActivityDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.model.Activity;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class ActivityController {


    private JdbcActivityDao dao;
    private JdbcUserDao userDao;

    public ActivityController(JdbcActivityDao dao, JdbcUserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/postActivity/{id}", method = RequestMethod.POST)
    public void addActivity(@RequestBody Activity activity, @PathVariable Long id) {
        activity.setDateRead(LocalDate.now());
        activity.setUserId(id);

        try {
            dao.createActivity(activity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getActivityByFam", method = RequestMethod.GET)
    public List<Activity> getActivityByFam(Principal user){
        User currentUser = userDao.findByUsername(user.getName());
        return dao.activityByFamily(currentUser.getFamilyId());
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getActivityById/{id}", method = RequestMethod.GET)
    public List<Activity> getActivityById(@PathVariable Long id){
        return dao.returnActivityForUser(id);
    }
}
