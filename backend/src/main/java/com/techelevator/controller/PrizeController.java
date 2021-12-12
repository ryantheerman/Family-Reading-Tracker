package com.techelevator.controller;

import com.techelevator.dao.JdbcBookDao;
import com.techelevator.dao.JdbcPrizeDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.model.Book;
import com.techelevator.model.Prize;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class PrizeController {

    private JdbcPrizeDao dao;
    private JdbcUserDao userDao;

    public PrizeController(JdbcPrizeDao dao, JdbcUserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getPrizes", method = RequestMethod.GET)
    public List<Prize> getPrizes(Principal user) {
        // System.out.println("prize name: " + prize.getPrizeName());
        try {
            User currentUser = userDao.findByUsername(user.getName());
            System.out.println(currentUser.getFamilyId());
            return dao.prizesByFamilyId(currentUser.getFamilyId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
