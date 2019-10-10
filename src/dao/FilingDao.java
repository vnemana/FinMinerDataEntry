package dao;

import models.Filing;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.List;

public class FilingDao {
    public int createFiling(Filing filing) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "FundReportsPersistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(filing);
        em.getTransaction().commit();

        TypedQuery<Filing> query = em.createQuery("select f from Filing f where f.filingType=" +
                ":filing_type and f.filingDate = :filing_date and f.fundId = :fund_id", Filing.class)
                .setParameter("filing_type", filing.getFilingType())
                .setParameter("filing_date", filing.getFilingDate())
                .setParameter("fund_id", filing.getFundId());

        List<Filing> filings = query.getResultList();
        //TODO: If list > 1 then throw exception.
        em.clear();
        emf.close();
        return filings.get(0).getFilingId();
    }

    public Filing getFiling(int fundId, Date filingDate, String filingType) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "FundReportsPersistence");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Filing> query = em.createQuery("select f from Filing f where f.filingType=" +
                ":filing_type and f.filingDate = :filing_date and f.fundId = :fund_id", Filing.class)
                .setParameter("filing_type", filingType)
                .setParameter("filing_date", filingDate)
                .setParameter("fund_id", fundId);

        List<Filing> filings = query.getResultList();
        em.clear();
        emf.close();
        if (filings.size() == 0) return null;
        //TODO: If list > 1 then throw exception.
        return filings.get(0);

    }
}
