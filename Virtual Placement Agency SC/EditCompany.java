/*
 * Programmer: Bilawal Sheikh
 * File: EditCompany.java
 */


/** Comapnies
  * User selects which company profile to open up
  * Details: company name, address(street, city, state/province, postal code), contact name
  *          phone, email and notes
  * NOTES: Provines should be selected from a drop down menu
  * Must be able to add/delete/edit
  */

import javax.swing.*; 
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class EditCompany {
  JFrame frame;
  JPanel contentPane;
  JLabel companyName, address, city, province, postalCode, contactName, phone, email, notes;
  JTextField companyNameInput, addressInput, cityInput, postalCodeInput, contactNameInput, phoneInput, emailInput, notesInput;
  JComboBox provinceInput;
  JButton saveButton, backButton;
  public String provinceName;
  public int frameStatus;
  File companyFile = new File("CompanyFile.txt");
  public int eNum;
  CompanyInformation c = new CompanyInformation("", "", "", "", "", "", "", "", "");
  ArrayList<CompanyInformation> compArrayList = new ArrayList<CompanyInformation>();

  /**
     * Opens Company Edit GUI
     * pre: which company to edit
     * post: New Frame is openeded up
     */
  
  public EditCompany(int editNumber) {
    frameStatus = 1;
    eNum = editNumber;
    try {
      FileInputStream in = new FileInputStream(companyFile);
      ObjectInputStream readFile = new ObjectInputStream(in);
      
      while (((c = (CompanyInformation)readFile.readObject()) != null)) {
        compArrayList.add(c);
        System.out.println(c.getCompanyName());
      }
    } catch (FileNotFoundException e) {
      System.out.println("File could not be found.");
      System.err.println("FileNotFoundException: " + e.getMessage());
    } catch (IOException e) {
    } catch (ClassNotFoundException e) {
      System.out.println("Class could not be used to cast object.");
      System.err.println("ClassNotFoundException: "+ e.getMessage());
    }

    c = compArrayList.get(eNum);
    
    frame = new JFrame("Edit Company");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    contentPane = new JPanel();
    contentPane.setLayout(new GridLayout(0,2,20,20));
    contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    
    companyName = new JLabel("Company Name:");
    contentPane.add(companyName);
    companyNameInput = new JTextField(c.getCompanyName());
    contentPane.add(companyNameInput);
    
    address = new JLabel("Address:");
    contentPane.add(address);
    addressInput = new JTextField(c.getCompanyStreet());
    contentPane.add(addressInput);
    
    city = new JLabel("City:");
    contentPane.add(city);
    cityInput = new JTextField(c.getCompanyCity());
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
    postalCodeInput = new JTextField(c.getCompanyPostalCode());
    contentPane.add(postalCodeInput);
    
    contactName = new JLabel("Contact Name:");
    contentPane.add(contactName);
    contactNameInput = new JTextField(c.getCompanyContactName());
    contentPane.add(contactNameInput);
      
    phone = new JLabel("Phone:");
    contentPane.add(phone);
    phoneInput = new JTextField(c.getCompanyNumber());
    contentPane.add(phoneInput);
    
    email = new JLabel("Email:");
    contentPane.add(email);
    emailInput = new JTextField(c.getCompanyEmail());
    contentPane.add(emailInput);
    
    notes = new JLabel("Notes:");
    contentPane.add(notes);
    notesInput = new JTextField(c.getCompanyNotes());
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
     * Gets province from user
     * pre: none
     * post: got province
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
     * post: New Frame is openeded up
     */
    public void actionPerformed(ActionEvent event) {
      String eventName = event.getActionCommand();
      if (eventName.equals("Back")) {
        frameStatus = 1; 
        AddEditDelete goBack = new AddEditDelete(frameStatus);
        frame.setVisible(false);
      } else if (eventName.equals("Save")) {
        frameStatus = 1;
        String w1 = companyNameInput.getText();
        String w2 = addressInput.getText();
        String w3 = cityInput.getText();
        // province
        String w4 = postalCodeInput.getText();
        String w5 = contactNameInput.getText();
        String w6 = phoneInput.getText();
        String w7 = emailInput.getText();
        String w8 = notesInput.getText();
        frame.setVisible(false);

        compArrayList.remove(eNum);
        compArrayList.add(new CompanyInformation(w1,w2,w3,provinceName, w4,w5,w6,w7,w8));
        try {
          FileOutputStream out = new FileOutputStream(companyFile, false);
          ObjectOutputStream writeToFile = new ObjectOutputStream(out);
          
          for (int x = 0; x <compArrayList.size(); x++) {
            c = compArrayList.get(x);
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