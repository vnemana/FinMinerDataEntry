package dao;

import models.Filing;
import models.Fund;
import models.Holding;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class HoldingDao {
    public void storeFilingData(ArrayList<Holding> holdingArray, Fund fund, Filing filing) {
        //If Fund already exists, then retrieve fund id from fund table
        int fundId = 0;
        int filingId = 0;
        FundDao fundDao = new FundDao();
        Fund lclFund = fundDao.getFund(fund.getFundName());
        if (lclFund == null) {
            fundId = fundDao.createFund(fund);
        }
        else fundId = lclFund.getFundId();

        filing.setFundId(fundId);

        FilingDao filingDao = new FilingDao();
        Filing lclFiling = filingDao.getFiling(filing.getFundId(), filing.getFilingDate(),
                filing.getFilingType());
        if (lclFiling == null) {
            filingId = filingDao.createFiling(filing);
        }
        else filingId = lclFiling.getFilingId();

        for (int ii = 0; ii < holdingArray.size(); ii++) {
            Holding holding = holdingArray.get(ii);

            holding.setFundId(fundId);
            holding.setFilingId(filingId);
            if (!HoldingDao.getHolding (fundId, filingId, holding.getCusip()))
                createHolding(holding);
        }
    }

    private static boolean getHolding(int fundId, int filingId, String cusip) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "FundReportsPersistence");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Holding> query = em.createQuery("select h from Holding h where h.fundId=" +
                ":fund_id and h.filingId = :filing_id and h.cusip = :cusip", Holding.class)
                .setParameter("fund_id", fundId)
                .setParameter("filing_id", filingId)
                .setParameter("cusip", cusip);

        List<Holding> holdings = query.getResultList();
        if (holdings.size() > 0) return true;
        return false;
    }

    public void createHolding(Holding holding) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "FundReportsPersistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(holding);
        em.getTransaction().commit();
        em.clear();
        emf.close();
    }
}
