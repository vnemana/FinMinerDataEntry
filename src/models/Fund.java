package models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Fund", schema = "FundReports")
public class Fund {
    @Id
    @Column(name = "fundId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fundId;
    public int getFundId() {
        return fundId;
    }
    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    @Basic
    @Column(name = "fundName")
    private String fundName;
    public String getFundName() {
        return fundName;
    }
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Basic
    @Column(name = "cik")
    private String cik;
    public String getCik() {
        return cik;
    }
    public void setCik(String cik) {
        this.cik = cik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fund that = (Fund) o;

        if (fundId != that.fundId) return false;
        return fundName != null ? fundName.equals(that.fundName) : that.fundName == null;
    }

    @Override
    public int hashCode() {
        int result = fundId;
        result = 31 * result + (fundName != null ? fundName.hashCode() : 0);
        return result;
    }
}
