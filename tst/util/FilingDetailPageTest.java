package util;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class FilingDetailPageTest {

    @Test
    void getCik() {
        try {
            URL url = new URL("https://www.sec.gov/Archives/edgar/data/1067983/000095012319008356/0000950123-19-008356-index.htm");
            FilingDetailPage filingDetailPage = new FilingDetailPage(url);
            String cik = filingDetailPage.getCik();
            assertEquals(cik, "0001067983");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getFilingType() {
        try {
            URL url = new URL("https://www.sec.gov/Archives/edgar/data/1036250/000103625019000003/0001036250-19-000003-index.htm");
            FilingDetailPage filingDetailPage = new FilingDetailPage(url);
            String filingType = filingDetailPage.getFilingType();
            assertEquals(filingType, "13F-HR");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}