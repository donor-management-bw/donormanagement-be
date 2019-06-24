package com.donormanage.donormanagebw.services;

import com.donormanage.donormanagebw.models.Donation;
import com.donormanage.donormanagebw.models.Donor;
import com.donormanage.donormanagebw.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service(value = "donorService")
public class DonorServiceImpl implements DonorService {

    @Autowired
    DonorRepository donorrepos;

    @Override
    public List<Donor> findAll(Pageable pageable) {
        List<Donor> list = new ArrayList<>();
        donorrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Donor findDonorById(long id) throws EntityNotFoundException {
        return donorrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Override
    public Donor update(Donor donor, long id) {

        Donor currentDonor = donorrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if(currentDonor != null)
        {
            if(id == currentDonor.getDonorid())
            {
                if(donor.getDname() != null)
                {
                    currentDonor.setDname(donor.getDname());
                }
                if(donor.getDemail() != null)
                {
                    currentDonor.setDemail(donor.getDemail());
                }
                if(donor.getDphone() != null)
                {
                    currentDonor.setDphone(donor.getDphone());
                }
                if(donor.getDaddress() != null)
                {
                    currentDonor.setDaddress(donor.getDaddress());
                }
                return donorrepos.save(currentDonor);
            } else
            {
                throw new EntityNotFoundException((Long.toString(id) + " Not Current Donor"));
            }
        } else
        {
            throw new EntityNotFoundException();
        }

    }

    @Override
    public void delete(long id) {
        if(donorrepos.findById(id).isPresent())
        {
            donorrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Donor save(Donor donor) {
        Donor newDonor = new Donor();
        newDonor.setDname(donor.getDname());
        newDonor.setDemail(donor.getDemail());
        newDonor.setDphone(donor.getDphone());
        newDonor.setDaddress(donor.getDaddress());

        return donorrepos.save(newDonor);
    }

}
