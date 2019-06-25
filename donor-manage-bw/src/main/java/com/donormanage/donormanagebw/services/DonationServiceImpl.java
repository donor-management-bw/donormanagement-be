package com.donormanage.donormanagebw.services;

import com.donormanage.donormanagebw.models.Donation;
import com.donormanage.donormanagebw.repository.DonationRepository;
import com.donormanage.donormanagebw.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "donationService")
public class DonationServiceImpl implements DonationService{

    @Autowired
    DonationRepository donationrepos;

    @Override
    public Donation findDonationById(long id) {
        return donationrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Override
    public Donation update(Donation donation, long id) {

        Donation currentDonation = donationrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if(currentDonation != null)
        {
            if(id == currentDonation.getDonationid())
            {
                if(donation.getAmount() != null)
                {
                    currentDonation.setAmount(donation.getAmount());
                }
                if(donation.getNote() != null)
                {
                    currentDonation.setNote(donation.getNote());
                }
                if(donation.getDonor() != null)
                {
                    currentDonation.setDonor(donation.getDonor());
                }
                return donationrepos.save(currentDonation);

            } else
            {
                throw new EntityNotFoundException((Long.toString(id) + " Not Current Donation with this id"));
            }
        } else
        {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void delete(long id) {
        donationrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        donationrepos.deleteById(id);
    }

    @Transactional
    @Override
    public Donation save(Donation donation) {
        return donationrepos.save(donation);
    }

    @Transactional
    @Override
    public List<Donation> listDonationsByDonorId(long id)
    {
        return donationrepos.findDonationsByDonorId(id);
    }

}
