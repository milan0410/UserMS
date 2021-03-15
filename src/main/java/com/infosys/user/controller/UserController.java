package com.infosys.user.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.infosys.user.dto.BuyerDTO;
import com.infosys.user.dto.CartDTO;
import com.infosys.user.dto.LoginDTO;
import com.infosys.user.dto.ProductDTO;
import com.infosys.user.dto.SellerDTO;
import com.infosys.user.dto.WishlistDTO;
import com.infosys.user.entity.MyKey;
import com.infosys.user.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	Environment environment;
	
	@PostMapping(value="/seller/register", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String registerSeller(@RequestBody SellerDTO sellerDTO)
	{
	  try {
		userService.sellerRegistartion(sellerDTO);
		String success="Seller registration is successful";
		//ResponseEntity<String> response= new ResponseEntity<String>(success,HttpStatus.OK);
		return success;
	  }catch(Exception e)
	  {
		  return environment.getProperty(e.getMessage()); 
		  //ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
	  }
	}
	@PostMapping(value="/buyer/register", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTO buyerDTO)
	{
	  try {
		userService.buyerRegistartion(buyerDTO);
		String success="Buyer registration is successful";
		ResponseEntity<String> response= new ResponseEntity<String>(success,HttpStatus.OK);
		return response;
	  }catch(Exception e)
	  {
		  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
	  }
	}
	@PostMapping(value="/seller/login",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sellerLogin(@RequestBody LoginDTO loginDTO)
	{
		try
		{
			if(userService.sellerLogin(loginDTO))
			{
				String success="Seller Login Successful";
				ResponseEntity<String> response= new ResponseEntity<String>(success,HttpStatus.OK);
				return response;
			}
			else
			{
				throw new Exception("Invalid Login Credentials");
			}
			
		}catch(Exception e)
		{
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
		}
	}

	@PostMapping(value="/buyer/login",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> buyerLogin(@RequestBody LoginDTO loginDTO)
	{
		try
		{
			if(userService.buyerLogin(loginDTO))
			{
				String success="Buyer Login Successful";
				ResponseEntity<String> response= new ResponseEntity<String>(success,HttpStatus.OK);
				return response;
			}
			else
			{
				throw new Exception("Invalid Login Credentials");
			}
			
		}catch(Exception e)
		{
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
		}
	}
	
	@GetMapping(value="/buyer",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuyerDTO>> allBuyersDetails()
	{
		 List<BuyerDTO> buyersList=userService.getAllBuyers();
		 ResponseEntity<List<BuyerDTO>> response=new ResponseEntity<List<BuyerDTO>>(buyersList,HttpStatus.OK);
		 return response;
	}
	
	@GetMapping(value="/seller",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SellerDTO>> allSellersDetails()
	{
		 List<SellerDTO> sellersList=userService.getAllSellers();
		 ResponseEntity<List<SellerDTO>> response=new ResponseEntity<List<SellerDTO>>(sellersList,HttpStatus.OK);
		 return response;
	}
	
	@GetMapping(value="/buyer/{buyerId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyerDTO> getSepcificBuyerDetails(@PathVariable int buyerId)
	{  try {
		 BuyerDTO buyer=userService.getSepcificBuyer(buyerId);
		 if(buyer!=null)
		 {
		 ResponseEntity<BuyerDTO> response=new ResponseEntity<BuyerDTO>(buyer,HttpStatus.OK);
		 return response;
		 }
		 else
		 {
			 throw new Exception("Buyer Not Found!");
		 }
	}catch(Exception e)
	{
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
	}

	}
	@GetMapping(value="/seller/{sellerId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellerDTO> getSepcificSellerDetails(@PathVariable int sellerId)
	{  try {
		 SellerDTO seller=userService.getSepcificSeller(sellerId);
		 if(seller!=null)
		 {
		 ResponseEntity<SellerDTO> response=new ResponseEntity<SellerDTO>(seller,HttpStatus.OK);
		 return response;
		 }
		 else
		 {
			 throw new Exception("Seller Not Found!");
		 }
	}catch(Exception e)
	{
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
	}

	}
	
	@DeleteMapping(value="/seller/{sellerId}")
	public ResponseEntity<String> deleteSeller(@PathVariable int sellerId)
	{  try {
	
		 if(userService.deleteSeller(sellerId))
		 { String success="Deleted Successfully !!";
		 
		 ResponseEntity<String> response=new ResponseEntity<String>(success,HttpStatus.OK);
		 return response;
		 }
		 else
		 {
			 throw new Exception("Seller Not Found!");
		 }
	}catch(Exception e)
	{
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
	}

	}
	@DeleteMapping(value="/buyer/{buyerId}")
	public ResponseEntity<String> deleteBuyer(@PathVariable int buyerId)
	{  try {
	
		 if(userService.deleteBuyer(buyerId))
		 { String success="Deleted Successfully !!";
		 ResponseEntity<String> response=new ResponseEntity<String>(success,HttpStatus.OK);
		 return response;
		 }
		 else
		 {
			 throw new Exception("Buyer Not Found!");
		 }
	}catch(Exception e)
	{
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
	}

	}
	
	@PostMapping(value="/cart/add",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO)
	{   try {
		userService.addToCart(cartDTO);
		String success="Product Added to cart successfully!!!";
		ResponseEntity<String> response= new ResponseEntity<String>(success,HttpStatus.OK);
		return response;
	        }catch(Exception e){
	        	   String failed="Product cannot be added to cart!!";
	        	 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,failed,e);
	                           }
	}
	@DeleteMapping(value="/cart/remove")
	public ResponseEntity<String> deleteFromCart(@RequestBody MyKey myKey)
	{
		try {
		 if(userService.deleteFromCart(myKey))
		 { String success="Deleted Successfully !!";
		 ResponseEntity<String> response=new ResponseEntity<String>(success,HttpStatus.OK);
		 return response;
		 }
		 else
		 {
			 throw new Exception("Not Found!");
		 }
	}catch(Exception e)
	{
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
	}
   }

	@PostMapping(value="/wishlist/add",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToWishlist(@RequestBody WishlistDTO wishlistDTO)
	{
		try
		{
			if(userService.addToWishlist(wishlistDTO))
			{
			String success="Product Added to wishlist successfully!!!";
			ResponseEntity<String> response=new ResponseEntity<String>(success,HttpStatus.OK);
			 return response;
			} 
			 else
			 {
				 throw new Exception("Product already present in Wishlist!");
			 }
		}catch(Exception e)
		{
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
		}
	   }
	@PostMapping(value="/seller/{sellerId}/product/add",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String addProduct(@RequestBody ProductDTO productDTO)
	{   try {
		
		String url="http://localhost:8400/api/product/add/";
		String response=new RestTemplate().postForObject(url, productDTO,String.class);
		return response;
	}catch(Exception e)
	{
		return e.getMessage();
	}
	}
	@DeleteMapping(value="/seller/delete/product/{prodId}")
	public String deleteProduct(@PathVariable Integer prodId)
	{
		try {
			
		String url="http://localhost:8400//api/product/delete/";
		new RestTemplate().delete(url+prodId);
		String response="Deleted Successfully";
		return response;
		}catch(Exception e)
		{
			return e.getMessage();
		}
	}
			
	}
	
