package com.myproject.user.validator;

import com.myproject.user.dto.BuyerDTO;
import com.myproject.user.dto.SellerDTO;

public class Validator {
	
	   public static boolean validate(BuyerDTO buyerDTO) throws Exception {

	        if(validateName(buyerDTO.getName())!=true)
	        {
	            throw new Exception("Validator.INVALID_NAME");
	                 }
	        
	        if(validateEmail(buyerDTO.getEmail())!=true)
	                   {
	                      throw new Exception("Validator.INVALID_EMAIL");
	                   }

	        if(validatePhoneNumber(buyerDTO.getPhoneNumber())!=true)
	                   {
	                      throw new Exception("Validator.INVALID_PHONE");
	                   }
	        if(validatePassword(buyerDTO.getPassword())!=true)
	                   {
	                      throw new Exception("Validator.INVALID_PASSWORD");
	                    }
	        else
	        	return true;

	    }
	   
	   public static boolean validate(SellerDTO sellerDTO) throws Exception {

	        if(!validateName(sellerDTO.getName()))
	        {
	            throw new Exception("Validator.INVALID_NAME");
	                 }
	                 if(!validateEmail(sellerDTO.getEmail()))
	                   {
	                      throw new Exception("Validator.INVALID_EMAIL");
	                   }

	                    if(!validatePhoneNumber(sellerDTO.getPhoneNumber()))
	                   {
	                      throw new Exception("Validator.INVALID_PHONE");
	                   }
	                 if(!validatePassword(sellerDTO.getPassword()))
	                   {
	                      throw new Exception("Validator.INVALID_PASSWORD");
	                    }
	                 else
	                	 return true;

	    }	   

	    public static Boolean validateName(String name) {
	            String regex="^[A-Za-z]+([\\s]*[A-Za-z]+)*$";                 

	            if(name!=null&&name.matches(regex))
	                return true;
	              else
	        return false;
	              }

	      public static Boolean validateEmail(String email) {
	              String regex1="^[a-z0-9_\\.]+@[a-z]+\\.[a-z]+$";
	            if(email!=null&&email.matches(regex1))
	                return true;
	              else
	        return false;  
	              }
	      public static Boolean validatePhoneNumber(String phoneNumber){
	                 String regex2= "[0-9]{10}";
	              if(phoneNumber!=null&&phoneNumber.matches(regex2))
	                return true;
	              else
	        return false;
	               }
	    public static Boolean validatePassword(String password){         
	    	String regex3="(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[-!@#(\\$)%(\\^)&(\\*)]).{7,20}$";
	            if(password!=null&&password.matches(regex3))
	                return true;
	              else
	        return false;
	                
	    }
}
