package com.donormanage.donormanagebw.repository;

import com.donormanage.donormanagebw.models.Donor;
import com.donormanage.donormanagebw.views.TotalDonationCount;
import com.donormanage.donormanagebw.views.TotalDonorCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface DonorRepository extends CrudRepository<Donor, Long> {

    @Transactional
    @Query(value = "SELECT count(*) as total from donors", nativeQuery = true)
    TotalDonorCount totalDonorCount();
}
