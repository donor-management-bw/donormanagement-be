package com.donormanage.donormanagebw.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "donors")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long donorid;

    @Column(nullable = false,
            unique = true)
    private String dname;

    @Column(nullable = false)
    private String demail;

    private String dphone;
    private String daddress;

    @OneToMany(mappedBy = "donor",
                cascade = CascadeType.ALL)
    @JsonIgnoreProperties("donor")
    private List<Donation> donationlist = new ArrayList<>();



    public Donor() {
    }

    public Donor(String dname, String demail, String dphone, String daddress, List<Donation> donationlist) {
        this.dname = dname;
        this.demail = demail;
        this.dphone = dphone;
        this.daddress = daddress;
        this.donationlist = donationlist;
    }

    public long getDonorid() {
        return donorid;
    }

    public void setDonorid(long donorid) {
        this.donorid = donorid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDemail() {
        return demail;
    }

    public void setDemail(String demail) {
        this.demail = demail;
    }

    public String getDphone() {
        return dphone;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }

    public String getDaddress() {
        return daddress;
    }

    public void setDaddress(String daddress) {
        this.daddress = daddress;
    }

    public List<Donation> getDonationlist() {
        return donationlist;
    }

    public void setDonationlist(List<Donation> donationList) {
        this.donationlist = donationList;
    }
}
