package com.myproject.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.user.dto.MyKey;
import com.myproject.user.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist,MyKey>{


}
