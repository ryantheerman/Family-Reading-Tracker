package com.techelevator.controller;

import com.techelevator.dao.JdbcActivityDao;
import com.techelevator.model.Activity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ActivityController {


    private JdbcActivityDao dao;

    public ActivityController(JdbcActivityDao dao) {
        this.dao = dao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/postActivity", method = RequestMethod.POST)
    public void addActivity(@RequestBody Activity activity) {

        try {
            dao.createActivity(activity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
