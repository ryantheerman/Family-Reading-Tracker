package com.techelevator.dao;

import com.techelevator.model.Prize;
import java.util.List;

public interface PrizeDao {
    //These are all methods that I thought of that we might need.
    //They do not need to stay or be used.

    List<Prize> listPrizes();

    Prize getPrizeById(Long prizeId);

    Prize createPrize(Prize prize);




}
