package edu.lk.ijse.gdse.aad.aadBackendFinal.repo;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
