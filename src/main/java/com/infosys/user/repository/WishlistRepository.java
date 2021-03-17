package com.infosys.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.user.dto.MyKey;
import com.infosys.user.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist,MyKey>{


}
