package edu.lk.ijse.gdse.aad.aadBackendFinal.repo;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);
}
