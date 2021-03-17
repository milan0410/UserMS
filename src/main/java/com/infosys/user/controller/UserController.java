package com.infosys.user.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.infosys.user.dto.MyKey;
import com.infosys.user.dto.OrderDetailsDTO;
import com.infosys.user.dto.ProductDTO;
import com.infosys.user.dto.ProductsOrderedDTO;
import com.infosys.user.dto.SellerDTO;
import com.infosys.user.dto.WishlistDTO;
import com.infosys.user.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	Environment environment;
	@Value("${product.uri}")
	String productUri;
	@Value("${order.uri}")
	String orderUri;
	
	@PostMapping(value="/seller/register", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerSeller(@RequestBody SellerDTO sellerDTO)
	{
	  try {
		userService.sellerRegistartion(sellerDTO);
		String success="Seller registration is successful";
		return new ResponseEntity<String>(success,HttpStatus.OK);
	  }catch(Exception e)
	  {
		  return new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST); 

	  }
	}
	@PostMapping(value="/buyer/register", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTO buyerDTO)
	{
	  try {
		userService.buyerRegistartion(buyerDTO);
		String success="Buyer registration is successful";
		return new ResponseEntity<String>(success,HttpStatus.OK);
	  }catch(Exception e)
	  {
		  return new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
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
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
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
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
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
		 new RestTemplate().delete(productUri+"/removeProducts/"+sellerId);
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
		 return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}

	}
	
	@PostMapping(value="/cart/add",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO)
	{   try { 
		ProductDTO product=new RestTemplate().getForObject(productUri+"/Id/"+cartDTO.getProdId(),ProductDTO.class);
		if(product!=null&&product.getStock()>=cartDTO.getQuantity())
		{
		userService.addToCart(cartDTO);
		String success="Product Added to cart successfully!!!";
		return new ResponseEntity<String>(success,HttpStatus.OK);
		}
		else
			throw new Exception("The required quantity is not available");
	        }catch(Exception e){
	        	  
	        	 return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
	                           }
	}
		
	@GetMapping(value="/cart/product/{buyerId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Integer> getBuyerCartProduct(@PathVariable int buyerId)
	{
		try
		{    List<CartDTO> listCart=userService.getBuyerCart(buyerId);
			List<Integer> productList=new ArrayList<>();
			for(CartDTO cart:listCart)
			{
				productList.add(cart.getProdId());
			}
			return productList;
		}catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
		}
	}
	
	@GetMapping(value="/cart/product/quantity/{buyerId}/{prodId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer getBuyerCartQuantity(@PathVariable int buyerId,@PathVariable int prodId)
	{
		try
		{    List<CartDTO> listCart=userService.getBuyerCart(buyerId);
			for(CartDTO cart:listCart)
			{ 
				if(cart.getProdId()==prodId)
					return cart.getQuantity();
			}
			return 0;
		}catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
		}
	}
	
	@DeleteMapping(value="/cart/remove")
	public ResponseEntity<String> deleteFromCart(@RequestBody MyKey myKey)
	{
		try {
		 if(userService.deleteFromCart(myKey))
		 { String success="Deleted Successfully !!";
		 return new ResponseEntity<String>(success,HttpStatus.OK);
		 }
		 else
		 {
			 throw new Exception("Not Found!");
		 }
	}catch(Exception e)
	{
		 return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
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
			return new ResponseEntity<String>(success,HttpStatus.OK);
			} 
			 else
			 {
				 throw new Exception("Product already present in Wishlist!");
			 }
		}catch(Exception e)
		{
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	   }
	
	@DeleteMapping(value="/wishlist/remove",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeFromWishlist(@RequestBody MyKey myKey)
	{
		try {
			if(userService.removeFromWishlist(myKey))
			{
				return new ResponseEntity<String>("Removed Successfully!!",HttpStatus.OK);
			}
			else
			{
				throw new Exception("No such product exist in wishlist");
			}
		}catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/wishlistToCart/{quantity}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> wishlistToCart(@PathVariable int quantity,@RequestBody MyKey myKey)
	{ 
		try {
	    ProductDTO product=new RestTemplate().getForObject(productUri+"/Id/"+myKey.getProdId(),ProductDTO.class);
		CartDTO cartDTO=new CartDTO();
		cartDTO.setBuyerId(myKey.getBuyerId());
		cartDTO.setProdId(myKey.getProdId());
		cartDTO.setQuantity(quantity);
		if(product!=null&&userService.removeFromWishlist(myKey)==true&&product.getStock()>=quantity)
		{
		userService.addToCart(cartDTO);
		return new ResponseEntity<String>("Successfully moved from wishlist to cart!!",HttpStatus.OK);
		}
		else
			throw new Exception("Unable to move from Wishlist to Cart!!");
		}catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value="/seller/products/add",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String addProduct(@RequestBody ProductDTO productDTO)
	{   try {
		
		String response=new RestTemplate().postForObject(productUri, productDTO,String.class);
		return response;
	}catch(Exception e)
	{
		return e.getMessage();
	}
	}
	@DeleteMapping(value="/seller/products/delete/{prodId}")
	public String deleteProduct(@PathVariable Integer prodId)
	{
		try {
		new RestTemplate().delete(productUri+"delete/"+prodId);
		String response="Deleted Successfully";
		return response;
		}catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
	@PostMapping(value="/user/orderUpdate",consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean orderUpdate(@RequestBody OrderDetailsDTO orderDetails)
	{    System.out.println("Inside OrderUpdate Before reward");
		userService.addRewardPoints(orderDetails.getBuyerId(),orderDetails.getAmount());
		 System.out.println("Inside OrderUpdate after reward");
		List<ProductsOrderedDTO> productsOrdered=orderDetails.getProductsOrdered();
		 System.out.println("Inside OrderUpdate Before put"+productsOrdered);
		new RestTemplate().postForObject(productUri+"/reduceStock", productsOrdered,Boolean.class);
		 System.out.println("Inside OrderUpdate after put");
		return true;
		
	}
	
	@GetMapping(value="/rewardPoints/{buyerId}")
	public Integer getRewardPoints(@PathVariable Integer buyerId)
	{   System.out.println("In Contoller");
		return userService.getRewardPoints(buyerId);
	}
			
	}
	
