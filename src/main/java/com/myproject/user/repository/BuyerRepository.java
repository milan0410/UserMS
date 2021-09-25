package com.myproject.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.user.entity.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Integer > {

	Optional<Buyer> findByPhoneNumber(String phoneNumber);
	Optional<Buyer> findByEmail(String email);

}
