/*
 * Programmer: Bilawal Sheikh
 * File: Positions.java
 */


/** Positions
  * User selects positions to view through a list of position names
  * Details: position title, notes, company name, contact name, contact phone,
  *          position status (active/closed), position opened date, position closed 
  *          date and reason closed
  * NOTES: Companies name should be on a list and displayed from the company class
  * Must be able to add/edit/delete
  */


import javax.swing.*; 
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Positions {
  
  JFrame frame;
  JPanel contentPane;
  JLabel positionTitle, notes, companyName, contactName, contactPhone, positionOpenedDate, positionClosedDate, reasonClosed, positionStatus;
  JTextField positionTitleInput, notesInput, contactNameInput, contactPhoneInput, positionOpenedDateInput, positionClosedDateInput, reasonClosedInput;
  JComboBox positionStatusInput, companyNameInput;
  JButton saveButton, backButton;
 
  
  public int frameStatus;
  public String positionStatusResults;
  File positionFile = new File("PositionFile.txt");
  
  PositionInformation p = new PositionInformation("", "", "", "", "", "", "", "", "");
  ArrayList<PositionInformation> posArrayList = new ArrayList<PositionInformation>();
  
  
  //COMPANY NAME VARIABLES
  CompanyInformation c = new CompanyInformation("", "", "", "", "", "", "", "", "");
  ArrayList<CompanyInformation> compArrayList = new ArrayList<CompanyInformation>();
  ArrayList<String> compDisplayArrayList = new ArrayList<String>();
  public String displayName;
  public String companyNameResults;
  
  /**
   * Opens Position GUI
   * pre: none
   * post: New Frame is openeded up
   */
  
  public Positions(int frameStatus) {
    frameStatus = 3;
    
    File positionFile = new File("PositionFile.txt");
    
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
    
    frame = new JFrame("Add Position");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    contentPane = new JPanel();
    contentPane.setLayout(new GridLayout(0,2,20,20));
    contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    
    
    positionTitle = new JLabel("Position Title:");
    contentPane.add(positionTitle);
    positionTitleInput = new JTextField(15);
    contentPane.add(positionTitleInput);
    
    notes = new JLabel("Notes:");
    contentPane.add(notes);
    notesInput = new JTextField(15);
    contentPane.add(notesInput);
    
    
    companyName = new JLabel("Company Name:");
    contentPane.add(companyName);
    
    
    
    
    File companyFile = new File("CompanyFile.txt");
    try {
      FileInputStream in = new FileInputStream(companyFile);
      ObjectInputStream readFile = new ObjectInputStream(in);
      
      while (((c = (CompanyInformation)readFile.readObject()) != null)) {
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
    
    companyNameInput = new JComboBox(compDisplayArray);
    companyNameInput.setSelectedIndex(0);
    companyNameInput.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
    companyNameInput.addActionListener(new SelectCompanyInput());
    contentPane.add(companyNameInput);
    
    
    contactName = new JLabel("Contact Name:");
    contentPane.add(contactName);
    contactNameInput = new JTextField(15);
    contentPane.add(contactNameInput);
    
    contactPhone = new JLabel("Contact Number:");
    contentPane.add(contactPhone);
    contactPhoneInput = new JTextField(15); 
    contentPane.add(contactPhoneInput);
    
    positionStatus = new JLabel("Position Status:");
    contentPane.add(positionStatus);
    String[] positionStatusList = {" ","Active", "Closed"};
    positionStatusInput = new JComboBox(positionStatusList);
    positionStatusInput.setSelectedIndex(0);
    positionStatusInput.addActionListener(new PositionStatusListener());
    contentPane.add(positionStatusInput);
    
    positionOpenedDate = new JLabel("Position Open Date:");
    contentPane.add(positionOpenedDate);
    positionOpenedDateInput = new JTextField(15);
    contentPane.add(positionOpenedDateInput);
    
    positionClosedDate = new JLabel("Position Close Date:");
    contentPane.add(positionClosedDate);
    positionClosedDateInput = new JTextField(15);
    contentPane.add(positionClosedDateInput);
    
    reasonClosed = new JLabel("Reason Closed:");
    contentPane.add(reasonClosed);
    reasonClosedInput = new JTextField(15);
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
  
  class PositionStatusListener implements ActionListener {
    /**
     * Gets position status from JComboBox
     * pre: none
     * post: gets position
     */
    public void actionPerformed(ActionEvent event) {
      JComboBox comboBox = (JComboBox)event.getSource();
      String itemName = (String)comboBox.getSelectedItem();
      positionStatusResults = itemName;
    }
  }
  
  class SelectCompanyInput implements ActionListener {
    /**
     * gets Company from JComboBox
     * pre: none
     * post: Gets Company Relatinship
     */
    public void actionPerformed(ActionEvent event) {
      JComboBox comboBox = (JComboBox)event.getSource();
      String itemName = (String)comboBox.getSelectedItem();
      companyNameResults = itemName;
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
        //company name options
        String w3 = contactNameInput.getText();
        String w4 = contactPhoneInput.getText();
        //position status 
        String w5 = positionOpenedDateInput.getText();
        String w6 = positionClosedDateInput.getText();
        String w7 = reasonClosedInput.getText();
        frame.setVisible(false);
        
        
        posArrayList.add(new PositionInformation(w1,w2,companyNameResults,w3,w4,positionStatusResults,w5,w6,w7));
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
