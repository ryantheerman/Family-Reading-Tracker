package com.techelevator.controller;

import com.techelevator.dao.JdbcBookDao;
import com.techelevator.dao.JdbcPrizeDao;
import com.techelevator.model.Book;
import com.techelevator.model.Prize;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PrizeController {

    private JdbcPrizeDao dao;

    public PrizeController(JdbcPrizeDao dao) {
        this.dao = dao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/postPrize", method = RequestMethod.POST)
    public void addPrize(@RequestBody Prize prize) {
        System.out.println("prize name: " + prize.getPrizeName());
        try {
            dao.createPrize(prize);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
