package com.donormanage.donormanagebw.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue
    private long donationid;

    private String note;
    private Integer amount;

    private Date donationdate;

    @ManyToOne
    @JoinColumn(name = "donorid")
    @JsonIgnoreProperties("donationlist")
    private Donor donor;

    public Donation() {
    }

    public Donation(String note, Integer amount, Date donationdate, Donor donor) {
        this.note = note;
        this.amount = amount;
        this.donationdate = donationdate;
        this.donor = donor;
    }

    public long getDonationid() {
        return donationid;
    }

    public void setDonationid(long donationid) {
        this.donationid = donationid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDonationdate() {
        return donationdate;
    }

    public void setDonationdate(Date donationdate) {
        this.donationdate = donationdate;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }
}
