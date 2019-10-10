package models;

import javax.persistence.*;

@Entity
@Table(name = "Holding", schema = "FundReports")
public class Holding {

    @Id
    @Column(name = "holdingId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int holdingId;
    public int getHoldingId() { return holdingId; }
    public void setHoldingId(int holdingId) { this.holdingId = holdingId; }

    @Basic
    @Column(name = "cusip")
    private String cusip;
    public String getCusip() { return cusip; }
    public void setCusip(String cusip) { this.cusip = cusip; }

    @Basic
    @Column(name = "stock")
    private String stock;
    public String getStock() { return stock; }
    public void setStock(String stock) { this.stock = stock; }

    @Basic
    @Column(name = "position")
    private double position;
    public double getPosition() { return position; }
    public void setPosition(double position) { this.position = position; }

    @Basic
    @Column(name = "numshares")
    private int numshares;
    public int getNumshares() { return numshares; }
    public void setNumshares(int numshares) { this.numshares = numshares; }

    @Basic
    @Column(name = "filingId")
    private int filingId;
    public int getFilingId() { return filingId; }
    public void setFilingId(int filingId) { this.filingId = filingId; }

    @Basic
    @Column(name = "fundId")
    private int fundId;
    public int getFundId() {return fundId;}
    public void setFundId(int fundId) {this.fundId = fundId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Holding that = (Holding) o;

        if (holdingId != that.holdingId) return false;
        if (Double.compare(that.position, position) != 0) return false;
        if (numshares != that.numshares) return false;
        if (cusip != null ? !cusip.equals(that.cusip) : that.cusip != null)
            return false;
        return stock != null ? stock.equals(that.stock) : that.stock == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = holdingId;
        result = 31 * result + (cusip != null ? cusip.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        temp = Double.doubleToLongBits(position);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + numshares;
        return result;
    }
}
