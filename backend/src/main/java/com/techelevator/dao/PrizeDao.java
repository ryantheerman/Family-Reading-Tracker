package com.techelevator.dao;

import com.techelevator.model.Activity;
import com.techelevator.model.Prize;
import java.util.List;

public interface PrizeDao {
    //These are all methods that I thought of that we might need.
    //They do not need to stay or be used.

    List<Prize> listPrizes(Long UserId);

    Prize getPrizeById(Long prizeId);

    Prize createPrize(Prize prize);

    List<Prize> prizesByFamilyId(Long familyId);

    Prize updatePrize(Prize Prize, Long prizeId);





}
