package web;

import dao.HoldingDao;
import models.Filing;
import models.Fund;
import models.Holding;
import org.xml.sax.SAXException;
import util.FilingDetailPage;
import util.FilingLinkInfo;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchFundsForm {
    private static final String searchUrl = "https://www.sec.gov/cgi-bin/browse-edgar?company=";
    private static final String searchSite = "https://www.sec.gov";
    private static final String search13fParam = "&type=13F-HR&count=100";

    public void parseAndStoreFiling(ArrayList<FilingLinkInfo> filing13FLinks) throws IOException, ParserConfigurationException, SAXException {
        System.out.println(filing13FLinks.size());
        if (!filing13FLinks.isEmpty()) {
            for (FilingLinkInfo f: filing13FLinks) {
                URL filingDetailURL = new URL(f.getLink());
                FilingDetailPage filingDetailPage = new
                        FilingDetailPage(filingDetailURL);
                String rawFilingURLString = filingDetailPage
                        .getRawFiling();
                if (rawFilingURLString != null) {
                    URL rawFilingURL = new URL(rawFilingURLString);

                    HashMap<String, Holding> holdingRecords
                            = filingDetailPage.parseRawFiling(rawFilingURL);
                    ArrayList<Holding>
                            holdingRecordArrayList = new
                            ArrayList<>();
                    for (Map.Entry<String, Holding> pair : holdingRecords.entrySet()) {
                        holdingRecordArrayList.add(pair
                                .getValue());
                    }

                    Fund fund = new Fund();
                    fund.setFundName(filingDetailPage.getFundName());
                    Filing filing = new Filing();
                    filing.setFilingDate(Date.valueOf(filingDetailPage.getFilingDate()));
                    filing.setReportDate(Date.valueOf(filingDetailPage.getReportDate()));
                    filing.setFilingType(filingDetailPage.getFilingType());
                    HoldingDao holdingDao = new HoldingDao();
                    holdingDao.storeFilingData(holdingRecordArrayList, fund, filing);
                    //holding.StoreFilingData(holdingRecordArrayList,filing, fund);
                    System.out.println("Completed " + fund.getFundName() + " " +
                            filing.getFilingDate() + " " +
                            filing.getReportDate());
                }
            }
        }
    }
}
