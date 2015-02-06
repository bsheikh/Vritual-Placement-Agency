/*
 * Programmer: Bilawal Sheikh
 * File: EditApplicants.java
 */


import javax.swing.JComboBox;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class EditApplicant {
  JFrame frame;
  JPanel contentPane;
  JLabel name, address, city, province, postalCode, contactName, phone, email, notes;
  JTextField nameInput, addressInput, cityInput, postalCodeInput, contactNameInput, phoneInput, emailInput, notesInput;
  JComboBox provinceInput;
  JButton saveButton, backButton;
  public String provinceName;
  public int frameStatus;
  File applicantFile = new File("ApplicantFile.txt");
  public int eNum;
  ApplicantInformation c = new ApplicantInformation("", "", "", "", "", "", "", "", "");
  ArrayList<ApplicantInformation> appArrayList = new ArrayList<ApplicantInformation>();
  
  /**
     * Opens GUI for Applicants Edit
     * pre: Which Applicant To edit
     * post: New Frame is opened up
     */
  
  public EditApplicant(int editNumber) {
    frameStatus = 2;
    eNum = editNumber;
    try {
      FileInputStream in = new FileInputStream(applicantFile);
      ObjectInputStream readFile = new ObjectInputStream(in);
      
      while (((c = (ApplicantInformation)readFile.readObject()) != null)) {
        appArrayList.add(c);
      }
    } catch (FileNotFoundException e) {
      System.out.println("File could not be found.");
      System.err.println("FileNotFoundException: " + e.getMessage());
    } catch (IOException e) {
    } catch (ClassNotFoundException e) {
      System.out.println("Class could not be used to cast object.");
      System.err.println("ClassNotFoundException: "+ e.getMessage());
    }

    c = appArrayList.get(eNum);
    
    frame = new JFrame("Edit Applicant");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    contentPane = new JPanel();
    contentPane.setLayout(new GridLayout(0,2,20,20));
    contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    
    name = new JLabel("Applicant Name:");
    contentPane.add(name);
    nameInput = new JTextField(c.getApplicantName());
    contentPane.add(nameInput);
    
    address = new JLabel("Address:");
    contentPane.add(address);
    addressInput = new JTextField(c.getApplicantStreet());
    contentPane.add(addressInput);
    
    city = new JLabel("City:");
    contentPane.add(city);
    cityInput = new JTextField(c.getApplicantCity());
    contentPane.add(cityInput);
    
    province = new JLabel("Province(Please Click and Select):");
    contentPane.add(province);
    String[] provinceList = {" ","Ontario", "Quebec", "British Columbia", "Alberta", "Nova Scotia", "Manitoba", "Saskatchewan", "New Brunswick", "PEI", "Newfoundland and Labrador"};
    provinceInput = new JComboBox(provinceList);
    provinceInput.setSelectedIndex(0);
    provinceInput.addActionListener(new ProvinceListener());
    contentPane.add(provinceInput);
    
    postalCode = new JLabel("Postal Code:");
    contentPane.add(postalCode);
    postalCodeInput = new JTextField(c.getApplicantPostalCode());
    contentPane.add(postalCodeInput);
    
    contactName = new JLabel("Contact Name:");
    contentPane.add(contactName);
    contactNameInput = new JTextField(c.getApplicantContactName());
    contentPane.add(contactNameInput);
      
    phone = new JLabel("Phone:");
    contentPane.add(phone);
    phoneInput = new JTextField(c.getApplicantNumber());
    contentPane.add(phoneInput);
    
    email = new JLabel("Email:");
    contentPane.add(email);
    emailInput = new JTextField(c.getApplicantEmail());
    contentPane.add(emailInput);
    
    notes = new JLabel("Notes:");
    contentPane.add(notes);
    notesInput = new JTextField(c.getApplicantNotes());
    contentPane.add(notesInput);
    
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
  
  class ProvinceListener implements ActionListener {
    /**
     * Handles JComboBox
     * pre: none
     * post: get province
     */
    public void actionPerformed(ActionEvent event) {
      JComboBox comboBox = (JComboBox)event.getSource();
      String itemName = (String)comboBox.getSelectedItem();
      provinceName = itemName;
    }
  }
    
  class GoBackOrSaveListener implements ActionListener {
    /**
     * Handles Button Click
     * pre: none
     * post: either saves or goes back
     */
    public void actionPerformed(ActionEvent event) {
      String eventName = event.getActionCommand();
      if (eventName.equals("Back")) {
        frameStatus = 2; 
        AddEditDelete goBack = new AddEditDelete(frameStatus);
        frame.setVisible(false);
      } else if (eventName.equals("Save")) {
        frameStatus = 2;
        String w1 = nameInput.getText();
        String w2 = addressInput.getText();
        String w3 = cityInput.getText();
        // province
        String w4 = postalCodeInput.getText();
        String w5 = contactNameInput.getText();
        String w6 = phoneInput.getText();
        String w7 = emailInput.getText();
        String w8 = notesInput.getText();
        frame.setVisible(false);

        appArrayList.remove(eNum);
        appArrayList.add(new ApplicantInformation(w1,w2,w3,provinceName, w4,w5,w6,w7,w8));
        try {
          FileOutputStream out = new FileOutputStream(applicantFile, false);
          ObjectOutputStream writeToFile = new ObjectOutputStream(out);
          
          for (int x = 0; x <appArrayList.size(); x++) {
            c = appArrayList.get(x);
            writeToFile.writeObject(c);
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