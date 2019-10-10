package dao;

import models.Fund;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class FundDao {
    public int createFund(Fund fund) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "FundReportsPersistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em. persist(fund);
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
