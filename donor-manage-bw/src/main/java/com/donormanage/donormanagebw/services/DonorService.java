package com.donormanage.donormanagebw.services;

import com.donormanage.donormanagebw.models.Donor;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DonorService {

    List<Donor> findAll(Pageable pageable);

    Donor findDonorById(long id);

    Donor update(Donor donor, long id);

    void delete(long id);

    Donor save(Donor donor);

}
