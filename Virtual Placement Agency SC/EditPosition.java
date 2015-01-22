/*
 * Programmer: Bilawal Sheikh
 * File: EditPosition.java
 * Date: June 12, 2013
 * Course: ICS4U
 */


import javax.swing.*; 
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class EditPosition {
  JFrame frame;
  JPanel contentPane;
  JLabel positionTitle, notes, companyName, contactName, contactPhone, positionOpenedDate, positionClosedDate, reasonClosed, positionStatus;
  JTextField positionTitleInput, notesInput, contactNameInput, contactPhoneInput, positionOpenedDateInput, positionClosedDateInput, reasonClosedInput;
  JComboBox<String> companyNameInput,positionStatusInput;
  JButton saveButton, backButton;
  
  public int frameStatus;
  public String positionStatusResults;
  public String companyNameInputResults;
  File positionFile = new File("PositionFile.txt");
  public int eNum;
  
  PositionInformation p = new PositionInformation("", "", "", "", "", "", "", "", "");
  ArrayList<PositionInformation> posArrayList = new ArrayList<PositionInformation>();
  
  
  //Companies Variables For List
  File companyFile = new File("CompanyFile.txt");
  CompanyInformation c = new CompanyInformation("", "", "", "", "", "", "", "", "");
  ArrayList<CompanyInformation> compArrayList = new ArrayList<CompanyInformation>();
  ArrayList<String> compDisplayArrayList = new ArrayList<String>();
  public String displayName;
  
  /**
   * Opens Edit Position GUI
   * pre: what position to edit
   * post: New Frame is openeded up
   */
  
  public EditPosition(int editNumber) {
    frameStatus = 3;
    eNum = editNumber;    
    try {
      FileInputStream in = new FileInputStream(positionFile);
      ObjectInputStream readFile = new ObjectInputStream(in);
      
      while (((p = (PositionInformation)readFile.readObject()) != null)) {
        posArrayList.add(p);
      }
      readFile.close();
      in.close();
    } catch (FileNotFoundException e) {
      System.out.println("File could not be found.");
      System.err.println("FileNotFoundException: " + e.getMessage());
    } catch (IOException e) {
    } catch (ClassNotFoundException e) {
      System.out.println("Class could not be used to cast object.");
      System.err.println("ClassNotFoundException: "+ e.getMessage());
    }
    
    p = posArrayList.get(eNum);
    
    frame = new JFrame("Edit Position");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    contentPane = new JPanel();
    contentPane.setLayout(new GridLayout(0,2,20,20));
    contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    
    
    positionTitle = new JLabel("Position Title:");
    contentPane.add(positionTitle);
    positionTitleInput = new JTextField(p.getPositionTitle());
    contentPane.add(positionTitleInput);
    
    notes = new JLabel("Notes:");
    contentPane.add(notes);
    notesInput = new JTextField(p.getPositionNotes());
    contentPane.add(notesInput);
    
    companyName = new JLabel("Company Name:");
    contentPane.add(companyName);
    
    
    File companyFile = new File("CompanyFile.txt");
    try {
      FileInputStream in = new FileInputStream(companyFile);
      ObjectInputStream readFile = new ObjectInputStream(in);
      
      while (((c = (CompanyInformation)readFile.readObject()) != null)) {
        compArrayList.add(c);
        displayName = c.getCompanyName();
        compDisplayArrayList.add(displayName);
      }
      readFile.close();
      in.close();
    } catch (FileNotFoundException e) {
      System.out.println("File could not be found.");
      System.err.println("FileNotFoundException: " + e.getMessage());
    } catch (IOException e) {
    } catch (ClassNotFoundException e) {
      System.out.println("Class could not be used to cast object.");
      System.err.println("ClassNotFoundException: "+ e.getMessage());
    } 
    
    String compDisplayArray[] = new String[compDisplayArrayList.size()];
    compDisplayArray = compDisplayArrayList.toArray(compDisplayArray);
     
    companyNameInput = new JComboBox<String>(compDisplayArray);
    companyNameInput.setSelectedIndex(0);
    companyNameInput.addActionListener(new CompanyNameInputListener());
    contentPane.add(companyNameInput);
    
    
    contactName = new JLabel("Contact Name:");
    contentPane.add(contactName);
    contactNameInput = new JTextField(p.getPositionContactName());
    contentPane.add(contactNameInput);
    
    contactPhone = new JLabel("Contact Number:");
    contentPane.add(contactPhone);
    contactPhoneInput = new JTextField(p.getPositionContactPhone()); 
    contentPane.add(contactPhoneInput);
    
    positionStatus = new JLabel("Position Status:");
    contentPane.add(positionStatus);
    String[] positionStatusList = {" ","Active", "Closed"};
    positionStatusInput = new JComboBox<String>(positionStatusList);
    positionStatusInput.setSelectedIndex(0);
    positionStatusInput.addActionListener(new PositionStatusListener());
    contentPane.add(positionStatusInput);
    
    positionOpenedDate = new JLabel("Position Open Date:");
    contentPane.add(positionOpenedDate);
    positionOpenedDateInput = new JTextField(p.getPositionOpenDate());
    contentPane.add(positionOpenedDateInput);
    
    positionClosedDate = new JLabel("Position Close Date:");
    contentPane.add(positionClosedDate);
    positionClosedDateInput = new JTextField(p.getPositionCloseDate());
    contentPane.add(positionClosedDateInput);
    
    reasonClosed = new JLabel("Reason Closed:");
    contentPane.add(reasonClosed);
    reasonClosedInput = new JTextField(p.getPositionReasonClosed());
    contentPane.add(reasonClosedInput);
    
    backButton = new JButton("Back");
    backButton.setActionCommand("Back");
    backButton.addActionListener(new GoBackOrSaveListener());
    contentPane.add(backButton);
    
    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save");
    saveButton.addActionListener(new GoBackOrSaveListener());
    contentPane.add(saveButton);
    
    frame.setContentPane(contentPane);
    frame.pack();
    frame.setVisible(true);
    
  }
  
  class CompanyNameInputListener implements ActionListener {
    /**
     * Gets province from JComboBox
     * pre: none
     * post: got province
     */
    public void actionPerformed(ActionEvent event) {
      JComboBox comboBox = (JComboBox)event.getSource();
      String itemName = (String)comboBox.getSelectedItem();
      companyNameInputResults = itemName;
      
    }
  }
  
  class PositionStatusListener implements ActionListener {
    /**
     * gets Position Status
     * pre: none
     * post: got position Status
     */
    public void actionPerformed(ActionEvent event) {
      JComboBox comboBox = (JComboBox)event.getSource();
      String itemName = (String)comboBox.getSelectedItem();
      positionStatusResults = itemName;
    }
  }
  
  class GoBackOrSaveListener implements ActionListener {
    /**
     * Handles Button Click
     * pre: none
     * post: New Frame is openeded up
     */
    public void actionPerformed(ActionEvent event) {
      String eventName = event.getActionCommand();
      if (eventName.equals("Back")) {
        frameStatus = 3; 
        AddEditDelete goBack = new AddEditDelete(frameStatus);
        frame.setVisible(false);
      } else if (eventName.equals("Save")) {
        frameStatus = 3;
        
        String w1 = positionTitleInput.getText();
        String w2 = notesInput.getText();
        //companyNameInputResults
        String w3 = contactNameInput.getText();
        String w4 = contactPhoneInput.getText();
        //position status 
        String w5 = positionOpenedDateInput.getText();
        String w6 = positionClosedDateInput.getText();
        String w7 = reasonClosedInput.getText();
        frame.setVisible(false);
        
        posArrayList.remove(eNum);
        posArrayList.add(new PositionInformation(w1,w2,companyNameInputResults,w3,w4,positionStatusResults,w5,w6,w7));
        try {
          FileOutputStream out = new FileOutputStream(positionFile, false);
          ObjectOutputStream writeToFile = new ObjectOutputStream(out);
          
          for (int x = 0; x <posArrayList.size(); x++) {
            p = posArrayList.get(x);
            writeToFile.writeObject(p);
          }
          writeToFile.close();
          out.close();
          System.out.println("Data written to file");
        } catch (FileNotFoundException e) {
          System.out.println("File could not be found.");
          System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
          System.out.println("Problem with input/output.");
          System.err.println("IOException: " + e.getMessage());
        }
        AddEditDelete goBack = new AddEditDelete(frameStatus);
      }
    }
  }
}
