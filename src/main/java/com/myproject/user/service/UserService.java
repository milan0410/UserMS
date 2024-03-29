package com.myproject.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.user.dto.BuyerDTO;
import com.myproject.user.dto.CartDTO;
import com.myproject.user.dto.LoginDTO;
import com.myproject.user.dto.MyKey;
import com.myproject.user.dto.SellerDTO;
import com.myproject.user.dto.WishlistDTO;
import com.myproject.user.entity.Buyer;
import com.myproject.user.entity.Cart;
import com.myproject.user.entity.Seller;
import com.myproject.user.entity.Wishlist;
import com.myproject.user.repository.BuyerRepository;
import com.myproject.user.repository.CartRepository;
import com.myproject.user.repository.SellerRepository;
import com.myproject.user.repository.WishlistRepository;
import com.myproject.user.validator.Validator;


@Service
public class UserService {

	@Autowired
	BuyerRepository buyerRepo;
	@Autowired
	SellerRepository sellerRepo;
	@Autowired
	CartRepository cartRepo;
	@Autowired
	WishlistRepository wishlistRepo;
	
	public void buyerRegistartion(BuyerDTO buyerDTO) throws Exception
	{
		Optional<Buyer> optBuyer=buyerRepo.findByPhoneNumber(buyerDTO.getPhoneNumber());
		Optional<Buyer> optBuyer1=buyerRepo.findByEmail(buyerDTO.getEmail());
		if(Validator.validate(buyerDTO)){
		if(!optBuyer.isPresent()&&!optBuyer1.isPresent()){
		Buyer buyer=buyerDTO.createEntity();
		buyerRepo.save(buyer);
	}
		else
			throw new Exception("Service.BUYER_ALREADY_PRESENT") ;
		}
	
    }
	public void sellerRegistartion(SellerDTO sellerDTO) throws Exception
	{
		Optional<Seller> optSeller=sellerRepo.findByPhoneNumber(sellerDTO.getPhoneNumber());
		Optional<Seller> optSeller1=sellerRepo.findByEmail(sellerDTO.getEmail());
		if(Validator.validate(sellerDTO)){
		if(!optSeller.isPresent()&&!optSeller1.isPresent()){
		Seller seller=sellerDTO.createEntity();
		sellerRepo.save(seller);
	}
		else
			throw new Exception("Service.SELLER_ALREADY_PRESENT") ;
		}	
    }
	
	public boolean sellerLogin(LoginDTO loginDTO)
	{
		Optional<Seller> optSeller=sellerRepo.findByEmail(loginDTO.getEmail());
		if(optSeller.isPresent())
		{
			if(loginDTO.getPassword().equals(optSeller.get().getPassword()))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean buyerLogin(LoginDTO loginDTO)
	{
		Optional<Buyer> optBuyer=buyerRepo.findByEmail(loginDTO.getEmail());
		if(optBuyer.isPresent())
		{
			if(loginDTO.getPassword().equals(optBuyer.get().getPassword()))
			{
				return true;
			}
		}
		return false;
	}
	public List<SellerDTO> getAllSellers()
	{
		List<Seller> sellers=sellerRepo.findAll();
		List<SellerDTO> sellersDTO=new ArrayList<>();
		for(Seller seller:sellers)
		{
			sellersDTO.add(SellerDTO.valueOf(seller));
			
		}
		return sellersDTO;
		
	}
	public List<BuyerDTO> getAllBuyers()
	{
		List<Buyer> buyers=buyerRepo.findAll();
		List<BuyerDTO> buyersDTO=new ArrayList<>();
		for(Buyer buyer:buyers)
		{
			buyersDTO.add(BuyerDTO.valueOf(buyer));
			
		}
		return buyersDTO;
		
	}
	public BuyerDTO getSepcificBuyer(int buyerId)
	{
		Optional<Buyer> optBuyer=buyerRepo.findById(buyerId);
		BuyerDTO buyerDTO=null;
		if(optBuyer.isPresent())
		{
			buyerDTO=BuyerDTO.valueOf(optBuyer.get());
		
		}
		return buyerDTO;
			
	}
	public SellerDTO getSepcificSeller(int sellerId)
	{
		Optional<Seller> optSeller=sellerRepo.findById(sellerId);
		SellerDTO sellerDTO=null;
		if(optSeller.isPresent())
		{
			sellerDTO=SellerDTO.valueOf(optSeller.get());
		
		}
		return sellerDTO;		
	}
	public boolean deleteSeller(int sellerId)
	{
		Optional<Seller> optSeller=sellerRepo.findById(sellerId);
		if(optSeller.isPresent())
		{    optSeller.get().setIsActive(0);
		     sellerRepo.save(optSeller.get());
			return true;
		
		}
		return false;
	}
	public boolean deleteBuyer(int buyerId)
	{
		Optional<Buyer> optBuyer=buyerRepo.findById(buyerId);
		if(optBuyer.isPresent())
		{    optBuyer.get().setIsActive(0);
		     buyerRepo.save(optBuyer.get());
			return true;
		
		}
		return false;
	}
	
	public void addToCart(CartDTO cartDTO) throws Exception
	{   MyKey myKey=new MyKey();
	    myKey.setBuyerId(cartDTO.getBuyerId());
	    myKey.setProdId(cartDTO.getProdId());
		Optional<Cart> optCart=cartRepo.findById(myKey);
		if(optCart.isPresent()!=true)
		{
		Cart cart=cartDTO.createEntity();
		cartRepo.save(cart);
		}
		else throw new Exception("Already Present!!");
	}
	
	public List<CartDTO> getBuyerCart(int buyerId) throws Exception
	{
		List<Cart> listCart=cartRepo.findAllByBuyerId(buyerId);
		List<CartDTO> buyerCart=new ArrayList<>();
		if(!listCart.isEmpty())
		{
		  for(Cart cart:listCart)
		  {
			  buyerCart.add(CartDTO.valueOf(cart));
		  }
		  return buyerCart;
		}
		else
			throw new Exception("No products found in cart!!");
	}
	public boolean deleteFromCart(MyKey myKey)
	{
		Optional<Cart> optCart=cartRepo.findById(myKey);
		if(optCart.isPresent())
		{
			cartRepo.deleteById(myKey);
			return true;
		
		}
		return false;
	}
	
	public boolean addToWishlist(WishlistDTO wishlistDTO)
	{  MyKey mk=new MyKey();
	   mk.setBuyerId(wishlistDTO.getBuyerId());
	   mk.setProdId(wishlistDTO.getProdId());
	   Optional<Wishlist> optWishlist=wishlistRepo.findById(mk);
	   if(!optWishlist.isPresent())
	   {
		   Wishlist wishlist=wishlistDTO.createEntity();
		   wishlistRepo.save(wishlist);
		   return true;
	   }
	   return false;
	}
	public boolean removeFromWishlist(MyKey myKey) {
		Optional<Wishlist> optWishlist=wishlistRepo.findById(myKey);
		if(optWishlist.isPresent()==true)
		{
			wishlistRepo.deleteById(myKey);
			return true;
		}
		return false;
	}
	
	public void addRewardPoints(int buyerId,double amount)
	{
		Optional<Buyer> optBuyer=buyerRepo.findById(buyerId);
		if(optBuyer.isPresent()==true)
		{
			int rewardPoints=optBuyer.get().getRewardPoints();
			optBuyer.get().setRewardPoints((int)amount/100);
			if((rewardPoints+amount/100)>=10000)
			{
				optBuyer.get().setIsPrivileged(1);
			}
			buyerRepo.save(optBuyer.get());
		}
	}
	
	public Integer getRewardPoints(int buyerId)
	{
		Optional<Buyer> optBuyer=buyerRepo.findById(buyerId);
		if(optBuyer.isPresent()==true)
		{
			return optBuyer.get().getRewardPoints();
		}
		else
			return 0;
	}
	
	
}
