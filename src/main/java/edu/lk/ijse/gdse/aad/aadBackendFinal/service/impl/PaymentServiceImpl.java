package edu.lk.ijse.gdse.aad.aadBackendFinal.service.impl;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.DonationDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.Payment;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentRequest;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.User;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.DonationRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.PaymentRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.TreatmentRequestRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.UserRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.DonationService;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepo paymentRepo;


    // newly added


    private final DonationRepo donationRepo;
    private final UserRepo userRepo;
    private final DonationService donationService;
    private final TreatmentRequestRepo treatmentRequestRepo;


    @Override
    public Map<String, Object> createPaymentIntent(Long amountCents, String currency, int requestId, int donorUserId) {

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amountCents);
        params.put("currency", currency);
        params.put("automatic_payment_methods", Map.of("enabled", true)); // let stripe decide card/others

        // optional: attach metadata to PaymentIntent for later verification

       /*
        Map<String, String> metadata = new HashMap<>();
        metadata.put("request_id", String.valueOf(requestId));
        if (donorUserId != null) metadata.put("donor_user_id", String.valueOf(donorUserId));
        params.put("metadata", metadata);

        */

        PaymentIntent intent = null;
        try {
            intent = PaymentIntent.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }



        // persist a Payment record
        Payment payment = new Payment();
        payment.setPaymentIntentId(intent.getId());
        payment.setAmount(amountCents/100);
        payment.setCurrency(currency);
        payment.setStatus(intent.getStatus()); // "requires_payment_method" etc
        payment.setCreatedAt(LocalDateTime.now());
        paymentRepo.save(payment);

        Map<String, Object> resp = new HashMap<>();
        resp.put("clientSecret", intent.getClientSecret());
        resp.put("paymentIntentId", intent.getId());



        // Newly added

        Payment lastPaymentRecord = paymentRepo.getLastPayment();


        User donator = userRepo.findById(donorUserId).orElse(null);
        TreatmentRequest treatmentRequest =treatmentRequestRepo.findById(requestId).orElse(null);

        DonationDTO donationDTO = new DonationDTO(amountCents/100, donator, treatmentRequest);
        donationDTO.setPayment(lastPaymentRecord);


        donationService.createDonation(donationDTO);





//        DonationDTO donationDTO = new DonationDTO()


        return resp;

    }
}
