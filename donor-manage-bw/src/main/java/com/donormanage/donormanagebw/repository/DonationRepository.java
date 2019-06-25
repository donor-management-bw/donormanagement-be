package com.donormanage.donormanagebw.repository;

import com.donormanage.donormanagebw.models.Donation;
import org.springframework.data.repository.CrudRepository;

public interface DonationRepository extends CrudRepository<Donation, Long> {
}
