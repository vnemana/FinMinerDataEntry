package dao;

import models.Fund;

import static org.junit.jupiter.api.Assertions.*;

class FundDaoTest {

    @org.junit.jupiter.api.Test
    void createFund() {
        Fund fund = new Fund();
        fund.setFundName("TestFund");
        FundDao fundDao = new FundDao();
        fundDao.createFund(fund);
    }

    @org.junit.jupiter.api.Test
    void getFund() {
    }
}