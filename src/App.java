import dao.CusipDao;
import models.Cusip;

import java.util.List;

public class App {

    public static void main (String[] args) {
        CusipDao cusipDao = new CusipDao();
        List<Cusip> cusips = cusipDao.getBlankStocks();
        for (Cusip c: cusips) {
            System.out.println(c.getCusip());
        }
    }
}

