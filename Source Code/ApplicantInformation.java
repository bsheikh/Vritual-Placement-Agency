/*
 * Programmer: Bilawal Sheikh
 * File: ApplicantInformation.java
 */


import java.io.*;

public class ApplicantInformation implements Serializable {
  public String name, street, city, province, postalCode, contactName, phoneNumber, email, notes;
  
  /**
   * Constructor
   * pre: multiple variables
   * post: variables are initialized
   */
  
  public ApplicantInformation(String appName, String appStreet, String appCity, String appProvince, String appPostalCode, String appContactName, String appPhoneNumber, String appEmail, String appNotes) {
    name = appName;
    street = appStreet;
    city = appCity;
    province = appProvince;
    postalCode = appPostalCode;
    contactName = appContactName;
    phoneNumber = appPhoneNumber;
    email = appEmail;
    notes = appNotes;
  }
  public String getApplicantName() {
    return (name);
    
  }
  public String getApplicantStreet() {
    return(street);
  }
  public String getApplicantCity() {
    return(city);
  }
  public String getApplicantProvince() {
    return(province);
  }
  public String getApplicantPostalCode() {
    return(postalCode);
  }
  public String getApplicantContactName() {
    return(contactName);
  }
  public String getApplicantNumber() {
    return(phoneNumber);
  }
  public String getApplicantEmail() {
    return(email);
  }
  public String getApplicantNotes() {
    return(notes);
  }
}
