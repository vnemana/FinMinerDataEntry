import dao.CusipDao;
import models.Cusip;
import org.xml.sax.SAXException;
import util.FilingLinkInfo;
import web.Search13FResultsPage;
import web.SearchFundsForm;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main (String[] args) {
        String cik = args[0];
        if (cik != null) onCikSearchSubmit(cik);
//        CusipDao cusipDao = new CusipDao();
//        List<Cusip> cusips = cusipDao.getBlankStocks();
//        for (Cusip c: cusips) {
//            System.out.println(c.getCusip());
//        }
    }


    private static void onCikSearchSubmit(String cik) {
        String cikSearchUrl = "https://www.sec.gov/cgi-bin/browse-edgar?action=getcompany&" +
                "CIK="+ cik +"&owner=include&count=40&hidefilings=0&type" +
                "=13F-HR&count=100";
        try {
            Search13FResultsPage s = new Search13FResultsPage(cikSearchUrl);
            ArrayList<FilingLinkInfo> filing13FLinks = s.get13FFilingLinks();
            System.out.println("Filing Link: " + filing13FLinks.size());
            SearchFundsForm searchFundsForm = new SearchFundsForm();
            searchFundsForm.parseAndStoreFiling(filing13FLinks);

        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}

