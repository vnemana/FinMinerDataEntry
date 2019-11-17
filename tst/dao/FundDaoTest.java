package dao;

import models.Fund;

class FundDaoTest {

    @org.junit.jupiter.api.Test
    void createFund() {
        Fund fund = new Fund();
        fund.setFundName("TestFund");
        fund.setCik("TestCik");
        FundDao fundDao = new FundDao();
        fundDao.createFund(fund);
    }

    @org.junit.jupiter.api.Test
    void getFund() {
    }

    @org.junit.jupiter.api.Test
    void deleteFund() {
        FundDao fundDao = new FundDao();
        fundDao.deleteFundByCik("TestCik");
    }
}