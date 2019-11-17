package dao;

import models.Fund;

import javax.persistence.*;
import java.util.List;

public class FundDao {
    public int createFund(Fund fund) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "FundReportsPersistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(fund);
        em.getTransaction().commit();

        TypedQuery<Fund> query = em.createQuery("select f from Fund f where f.fundName=" +
                ":fund_name", Fund.class)
                .setParameter("fund_name", fund.getFundName());

        List<Fund> funds = query.getResultList();
        em.clear();
        emf.close();
        //TODO: If list > 1 then throw exception.
        return funds.get(0).getFundId();
    }

    public void updateCikForFund(int fundId, String cik) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory
                ("FundReportsPersistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("UPDATE Fund f set f.cik = " +
                ":cik_name where f.fundId = :fund_id");
        query.setParameter("cik_name", cik);
        query.setParameter("fund_id", fundId);

        query.executeUpdate();

        em.getTransaction().commit();
        em.clear();
        emf.close();
    }

    public void deleteFundByCik(String cik) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory
                ("FundReportsPersistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("DELETE from Fund f where f.cik = " +
                ":cik_name");
        query.setParameter("cik_name", cik).executeUpdate();

        em.getTransaction().commit();
        em.clear();
        emf.close();
    }

    public Fund getFund(String fundName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "FundReportsPersistence");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Fund> query = em.createQuery("select f from Fund f where f.fundName=" +
                ":fund_name", Fund.class)
                .setParameter("fund_name", fundName);

        List<Fund> funds = query.getResultList();
        em.clear();
        emf.close();

        if (funds.size() == 0) return null;
        //TODO: If list > 1 then throw exception.
        return funds.get(0);
    }
}
