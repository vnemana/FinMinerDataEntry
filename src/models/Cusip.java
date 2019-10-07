package models;

import javax.persistence.*;

@Entity
@Table(name = "Cusip", schema = "FundReports")
public class Cusip {
    private String cusip;
    private String stock;
    private String companyName;

    @Id
    @Column(name = "cusip")
    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    @Basic
    @Column(name = "stock")
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cusip that = (Cusip) o;

        if (cusip != null ? !cusip.equals(that.cusip) : that.cusip != null)
            return false;
        if (stock != null ? !stock.equals(that.stock) : that.stock != null)
            return false;
        return companyName != null ? companyName.equals(that.companyName) : that.companyName == null;
    }

    @Override
    public int hashCode() {
        int result = cusip != null ? cusip.hashCode() : 0;
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        return result;
    }
}
