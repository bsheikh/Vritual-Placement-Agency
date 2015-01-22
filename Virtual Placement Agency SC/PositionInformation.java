/*
 * Programmer: Bilawal Sheikh
 * File: PositionInformation.java
 * Date: June 12, 2013
 * Course: ICS4U
 */

import java.io.*;

public class PositionInformation implements Serializable {
  public String title, notes, companyName, contactName, contactPhone,positionStatus,openDate,closeDate,reasonClosed;
  
  /**
   * Constructor
   * pre: multiple variables
   * post: variables are initialized
   */
  
  public PositionInformation(String positionTitleResults, String positionNotesResults, String companyNameResult, String contactNameResults, String contactPhoneResults, String positionStatusResults, String positionOpenedDateResults, String positionClosedDateResults, String reasonClosedResults) {
    title = positionTitleResults;
    notes = positionNotesResults;
    companyName = companyNameResult;
    contactName = contactNameResults;
    contactPhone = contactPhoneResults;
    positionStatus = positionStatusResults;
    openDate = positionOpenedDateResults;
    closeDate = positionClosedDateResults;
    reasonClosed = reasonClosedResults;
  }
  
  public String getPositionTitle() {
    return (title); 
  }
  public String getPositionNotes() {
    return(notes);
  }
  public String getPositionCompanyName() {
    return(companyName);
  }
  public String getPositionContactName() {
    return(contactName);
  }
  public String getPositionContactPhone() {
    return(contactPhone);
  }
  public String getPositionStatus() {
    return(positionStatus);
  }
  public String getPositionOpenDate() {
    return(openDate);
  }
  public String getPositionCloseDate() {
    return(closeDate);
  }
  public String getPositionReasonClosed() {
    return(reasonClosed);
  }
}

