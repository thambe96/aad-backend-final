package edu.lk.ijse.gdse.aad.aadBackendFinal.service.impl;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.DonationDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.Donation;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentReqStatus;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentRequest;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.DonationRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.TreatmentRequestRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.DonationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepo donationRepo;
    private final ModelMapper modelMapper;
    private final TreatmentRequestRepo treatmentRequestRepo;


    @Transactional
    @Override
    public String createDonation(DonationDTO donationDTO) {

        //Handle Exceptions here

        //Transaction


        TreatmentRequest treatmentRequest = treatmentRequestRepo.findById(
                                                donationDTO.getTreatmentRequest().
                                                getRequestId()
                                                ).orElse(null);

        if(treatmentRequest != null) {
            double collectedAmount = treatmentRequest.getCollectedAmount();
            double donatedAmount = donationDTO.getAmount();

            collectedAmount += donatedAmount;

            treatmentRequest.setCollectedAmount(collectedAmount);

            if (collectedAmount >= donatedAmount) {
                treatmentRequest.setRequestStatus(TreatmentReqStatus.CLOSED);
            }

            treatmentRequestRepo.save(treatmentRequest);
            donationRepo.save(modelMapper.map(donationDTO, Donation.class));
            return "Donation Recorded Successfully";
        }


        return "Failed to record the donation";


    }

    @Override
    public List<DonationDTO> getAllDonationsByUserId(int userId) {

        List<Donation> donations = donationRepo.findByDonorId(userId);

        List<DonationDTO> donationDTOs = new ArrayList<>();

        for (Donation donation : donations) {
            DonationDTO donationDTO = new DonationDTO(
                    donation.getDonationId(),
                    donation.getAmount(),
                    donation.getDate(),
                    donation.getDonator(),
                    donation.getTreatmentRequest()

            );
            donationDTOs.add(donationDTO);
        }


        return donationDTOs;
    }


}
