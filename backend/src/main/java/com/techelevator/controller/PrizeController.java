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
        try {
            dao.createPrize(prize);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //update prize controller
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/editPrize", method = RequestMethod.PUT)
    public void updatePrize(@RequestBody Prize prize) {
      dao.updatePrize(prize);
    }

    //delete prize?
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/deletePrize", method = RequestMethod.DELETE)
    public void deletePrize(@RequestBody Prize prize) {
        dao.deletePrize(prize);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getPrizes", method = RequestMethod.GET)
    public List<Prize> getPrizes(Principal user) {
        try {
            User currentUser = userDao.findByUsername(user.getName());
            return dao.prizesByFamilyId(currentUser.getFamilyId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
