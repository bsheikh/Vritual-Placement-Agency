/*
 * Programmer: Bilawal Sheikh
 * File: CompanyInformation.java
 */


import java.io.*;

public class CompanyInformation implements Serializable {
  public String name;
  public String street;
  public String city, province, postalCode, contactName, phoneNumber, email, notes;
  
  /**
   * Constructor
   * pre: multiple variables
   * post: variables are initialized
   */
  
  public CompanyInformation (String compName, String compStreet, String compCity, String compProvince, String compPostalCode, String compContactName, String compPhoneNumber, String compEmail, String compNotes){
    name = compName;
    street = compStreet;
    city = compCity;
    province = compProvince;
    postalCode = compPostalCode;
    contactName = compContactName;
    phoneNumber = compPhoneNumber;
    email = compEmail;
    notes = compNotes;
  }  
  
  public String getCompanyName() {
    return (name);
    
  }
  public String getCompanyStreet() {
    return(street);
  }
  public String getCompanyCity() {
    return(city);
  }
  public String getCompanyProvince() {
    return(province);
  }
  public String getCompanyPostalCode() {
    return(postalCode);
  }
  public String getCompanyContactName() {
    return(contactName);
  }
  public String getCompanyNumber() {
    return(phoneNumber);
  }
  public String getCompanyEmail() {
    return(email);
  }
  public String getCompanyNotes() {
    return(notes);
  }
}