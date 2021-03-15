package com.infosys.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.user.entity.Cart;
import com.infosys.user.entity.MyKey;
@Repository
public interface CartRepository extends JpaRepository<Cart,MyKey>{

}
