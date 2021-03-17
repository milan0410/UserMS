package com.infosys.user.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.user.dto.MyKey;
import com.infosys.user.entity.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart,MyKey>{
  List<Cart> findAllByBuyerId(int buyerId);
}
