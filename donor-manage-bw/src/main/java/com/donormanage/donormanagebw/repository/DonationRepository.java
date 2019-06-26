package com.donormanage.donormanagebw.repository;

import com.donormanage.donormanagebw.models.Donation;
import com.donormanage.donormanagebw.views.TotalDonationAmount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DonationRepository extends CrudRepository<Donation, Long> {

    @Transactional
    @Modifying
    @Query(value = "SELECT * from donations WHERE donorid = :donorid", nativeQuery = true)
    List<Donation> findDonationsByDonorId(long donorid);

    @Transactional
    @Query(value = "SELECT sum(amount) total from donations", nativeQuery = true)
    TotalDonationAmount totalDonationsAmount();


        //find total donations made
    //find total amount of donated amount
}
