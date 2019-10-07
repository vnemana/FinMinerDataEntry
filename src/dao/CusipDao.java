package dao;

import models.Cusip;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CusipDao {

    public List<Cusip> getBlankStocks() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "FundReportsPersistence");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cusip> query = em.createQuery(
                "select c from Cusip c where c.stock=''", Cusip.class);
        return query.getResultList();
    }

    public Cusip getCusipData(String cusipArg) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "FundReportsPersistence");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Cusip> query = em.createQuery(
                "select c from Cusip c where c.cusip= :cusip", Cusip.class)
                .setParameter("cusip", cusipArg);
        if (query.getResultList().size()==0) return null;
        return query.getResultList().get(0);
    }
}
