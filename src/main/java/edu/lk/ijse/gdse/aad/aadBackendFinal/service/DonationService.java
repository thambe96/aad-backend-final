package edu.lk.ijse.gdse.aad.aadBackendFinal.service;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.DonationDTO;

import java.util.List;

public interface DonationService {

    String createDonation(DonationDTO donationDTO);
    List<DonationDTO> getAllDonationsByUserId(int userId);

}
