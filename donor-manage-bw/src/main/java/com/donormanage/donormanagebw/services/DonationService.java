package com.donormanage.donormanagebw.services;

import com.donormanage.donormanagebw.models.Donation;
import com.donormanage.donormanagebw.models.Donor;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DonationService {

    Donation findDonationById(long id);

    Donation update(Donation donation, long id);

    void delete(long id);

    Donation save(Donation donation);

    List<Donation> listDonationsByDonorId(long id);
}
