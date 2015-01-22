/*
 * Programmer: Bilawal Sheikh
 * File: AddEditDelete.java
 * Date: June 12, 2013
 * Course: ICS4U
 */

import javax.swing.*; 
import java.awt.event.*;
import javax.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.Arrays;

public class AddEditDelete {
  
  // GUI VARIABLES
  JFrame frame;
  JPanel contentPane;
  JLabel instructions, blank1, blank2;
  JButton add, edit, delete, back;

  //COMPANY VARIABLES
  JLabel compName, compStreet, compCity, compProvince, compContactName, compPostalCode, compPhoneNumber, compEmail, compNotes;
  JComboBox<String> companyList;
  CompanyInformation c = new CompanyInformation("", "", "", "", "", "", "", "", "");
  ArrayList<CompanyInformation> compArrayList = new ArrayList<CompanyInformation>();
  ArrayList<String> compDisplayArrayList = new ArrayList<String>();
  File companyFile = new File("CompanyFile.txt");
  
  //APPLICANTS VARIABLES
  JLabel appName, appStreet, appCity, appProvince, appContactName, appPostalCode, appPhoneNumber, appEmail, appNotes;

  ApplicantInformation a = new ApplicantInformation("", "", "", "", "", "", "", "", "");
  ArrayList<ApplicantInformation> appArrayList = new ArrayList<ApplicantInformation>();
  ArrayList<String> appDisplayArrayList = new ArrayList<String>();
  File applicantFile = new File("ApplicantFile.txt");
  
  //POSITION VARIABLES
  JLabel positionTitle, notes, companyName, contactName, contactPhone, positionStatus, positionOpenedDate, positionClosedDate, reasonClosed;

  PositionInformation p = new PositionInformation("", "", "", "", "", "", "", "", "");
  ArrayList<PositionInformation> posArrayList = new ArrayList<PositionInformation>();
  ArrayList<String> posDisplayArrayList = new ArrayList<String>();
  File positionFile = new File("PositionFile.txt");
  

  // STATEMENTS FALSE
  public boolean company = false;
  public boolean applicants = false;
  public boolean positions = false;
  
  //GENERAL VARIABLES
  public String displayName;
  public int editNum;
  public int frameStatus;

  /**
   * Constructor
   * pre: Which frame to open
   * post: GUI is opened
   */
  
  public AddEditDelete(int frameStatus) {
    if (frameStatus == 1) {
      company = true;
      applicants = false;
      positions = false;
      frame = new JFrame("ADD/EDIT/DELETE Companies");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      contentPane = new JPanel();
      contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
      contentPane.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
      
      instructions = new JLabel("Select a Company:");
      instructions.setAlignmentX(JLabel.LEFT_ALIGNMENT);
      instructions.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
      contentPane.add(instructions);
      

      File companyFile = new File("CompanyFile.txt");
      try {
        FileInputStream in = new FileInputStream(companyFile);
        ObjectInputStream readFile = new ObjectInputStream(in);
        
        while (((c = (CompanyInformation)readFile.readObject()) != null)) {
          compArrayList.add(c);
          System.out.println(c.getCompanyName());
          displayName = c.getCompanyName();
          compDisplayArrayList.add(displayName);
        }
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
      
      companyList = new JComboBox<String>(compDisplayArray);
      companyList.setSelectedIndex(0);
      companyList.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
      companyList.addActionListener(new SelectCompany());
      contentPane.add(companyList);
      
      blank1 = new JLabel(" ");
      contentPane.add(blank1);
      
      compName = new JLabel(" ");
      contentPane.add(compName);
      compStreet = new JLabel(" ");
      contentPane.add(compStreet);
      compCity = new JLabel(" ");
      contentPane.add(compCity);
      compPostalCode = new JLabel(" ");
      contentPane.add(compPostalCode);
      compContactName = new JLabel(" ");
      compProvince = new JLabel(" ");
      contentPane.add(compProvince);
      contentPane.add(compContactName);
      compPhoneNumber = new JLabel(" ");
      contentPane.add(compPhoneNumber);
      compEmail = new JLabel(" ");
      contentPane.add(compEmail);
      compNotes = new JLabel(" ");
      contentPane.add(compNotes);
      
      blank2 = new JLabel(" ");
      contentPane.add(blank2);
      
      add = new JButton("Add");
      add.setActionCommand("Add");
      add.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(add);
      
      edit = new JButton("Edit");
      edit.setActionCommand("Edit");
      edit.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(edit);
      
      delete = new JButton("Delete");
      delete.setActionCommand("Delete");
      delete.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(delete);
      
      back = new JButton("Back");
      back.setActionCommand("Back");
      back.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(back);
      
      frame.setContentPane(contentPane);
      frame.pack();
      frame.setVisible(true);
      
    } else if (frameStatus == 2) {
      company = false;
      applicants = true;
      positions = false;
      
      frame = new JFrame("ADD/EDIT/DELETE Applicants");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      contentPane = new JPanel();
      contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
      contentPane.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
      
      instructions = new JLabel("Select a Applicant:");
      instructions.setAlignmentX(JLabel.LEFT_ALIGNMENT);
      instructions.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
      contentPane.add(instructions);
      

      File applicantFile = new File("ApplicantFile.txt");
      try {
        FileInputStream in = new FileInputStream(applicantFile);
        ObjectInputStream readFile = new ObjectInputStream(in);
        
        while (((a = (ApplicantInformation)readFile.readObject()) != null)) {
          appArrayList.add(a);
          System.out.println(a.getApplicantName());
          displayName = a.getApplicantName();
          appDisplayArrayList.add(displayName);
        }
      } catch (FileNotFoundException e) {
        System.out.println("File could not be found.");
        System.err.println("FileNotFoundException: " + e.getMessage());
      } catch (IOException e) {
      } catch (ClassNotFoundException e) {
        System.out.println("Class could not be used to cast object.");
        System.err.println("ClassNotFoundException: "+ e.getMessage());
      } 
      
      String appDisplayArray[] = new String[appDisplayArrayList.size()];
      appDisplayArray = appDisplayArrayList.toArray(appDisplayArray);
      
      companyList = new JComboBox<String>(appDisplayArray);
      companyList.setSelectedIndex(0);
      companyList.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
      companyList.addActionListener(new SelectCompany());
      contentPane.add(companyList);
      
      blank1 = new JLabel(" ");
      contentPane.add(blank1);
      
      appName = new JLabel(" ");
      contentPane.add(appName);
      appStreet = new JLabel(" ");
      contentPane.add(appStreet);
      appCity = new JLabel(" ");
      contentPane.add(appCity);
      appPostalCode = new JLabel(" ");
      contentPane.add(appPostalCode);
      appContactName = new JLabel(" ");
      appProvince = new JLabel(" ");
      contentPane.add(appProvince);
      contentPane.add(appContactName);
      appPhoneNumber = new JLabel(" ");
      contentPane.add(appPhoneNumber);
      appEmail = new JLabel(" ");
      contentPane.add(appEmail);
      appNotes = new JLabel(" ");
      contentPane.add(appNotes);
      
      blank2 = new JLabel(" ");
      contentPane.add(blank2);
      
      add = new JButton("Add");
      add.setActionCommand("Add");
      add.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(add);
      
      edit = new JButton("Edit");
      edit.setActionCommand("Edit");
      edit.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(edit);
      
      delete = new JButton("Delete");
      delete.setActionCommand("Delete");
      delete.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(delete);
      
      back = new JButton("Back");
      back.setActionCommand("Back");
      back.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(back);
      
      frame.setContentPane(contentPane);
      frame.pack();
      frame.setVisible(true);

    } else if (frameStatus == 3) {
      company = false;
      applicants = false;
      positions = true;
      
      frame = new JFrame("ADD/EDIT/DELETE Positions");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      contentPane = new JPanel();
      contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
      contentPane.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
      
      instructions = new JLabel("Select a Position:");
      instructions.setAlignmentX(JLabel.LEFT_ALIGNMENT);
      instructions.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
      contentPane.add(instructions);
      

      File positionFile = new File("PositionFile.txt");
      try {
        FileInputStream in = new FileInputStream(positionFile);
        ObjectInputStream readFile = new ObjectInputStream(in);
        
        while (((p = (PositionInformation)readFile.readObject()) != null)) {
          posArrayList.add(p);
          displayName = p.getPositionTitle();
          posDisplayArrayList.add(displayName);
        }
      } catch (FileNotFoundException e) {
        System.out.println("File could not be found.");
        System.err.println("FileNotFoundException: " + e.getMessage());
      } catch (IOException e) {
      } catch (ClassNotFoundException e) {
        System.out.println("Class could not be used to cast object.");
        System.err.println("ClassNotFoundException: "+ e.getMessage());
      } 
      
      String posDisplayArray[] = new String[posDisplayArrayList.size()];
      posDisplayArray = posDisplayArrayList.toArray(posDisplayArray);
      companyList = new JComboBox<String>(posDisplayArray);
      companyList.setSelectedIndex(0);
      companyList.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
      companyList.addActionListener(new SelectCompany());
      contentPane.add(companyList);
      
      blank1 = new JLabel(" ");
      contentPane.add(blank1);
      
      positionTitle = new JLabel(" ");
      contentPane.add(positionTitle);
      notes = new JLabel(" ");
      contentPane.add(notes);
      companyName = new JLabel(" ");
      contentPane.add(companyName);
      contactName = new JLabel(" ");
      contentPane.add(contactName);
      contactPhone = new JLabel(" ");
      contentPane.add(contactPhone);
      positionStatus = new JLabel(" ");
      contentPane.add(positionStatus);
      positionOpenedDate = new JLabel(" ");
      contentPane.add(positionOpenedDate);
      positionClosedDate = new JLabel(" ");
      contentPane.add(positionClosedDate);
      reasonClosed = new JLabel(" ");
      contentPane.add(reasonClosed);
            
      blank2 = new JLabel(" ");
      contentPane.add(blank2);
      
      add = new JButton("Add");
      add.setActionCommand("Add");
      add.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(add);
      
      edit = new JButton("Edit");
      edit.setActionCommand("Edit");
      edit.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(edit);
      
      delete = new JButton("Delete");
      delete.setActionCommand("Delete");
      delete.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(delete);
      
      back = new JButton("Back");
      back.setActionCommand("Back");
      back.addActionListener(new AddEditDeleteBackListener());
      contentPane.add(back);
      
      frame.setContentPane(contentPane);
      frame.pack();
      frame.setVisible(true);
      
    }
  }
  

  
  class AddEditDeleteBackListener implements ActionListener {
    
    /**
     * Handles Button Click
     * pre: none
     * post: New Frame is openeded up
     */
    
    public void actionPerformed(ActionEvent event) {
      String eventName = event.getActionCommand();
      
      if(eventName.equals("Add")) {
        if (company) {
          Companies loadComp = new Companies(frameStatus);
          frame.setVisible(false);
        } else if (applicants) {
          Applicants loadApp = new Applicants(frameStatus);
          frame.setVisible(false);
        } else if (positions) {
          Positions loadPos = new Positions(frameStatus);
          frame.setVisible(false);
        }
      } else if (eventName.equals("Edit")) {
        if (company) {
          EditCompany e = new EditCompany(editNum);
          frame.setVisible(false);
        } else if (applicants) {
          EditApplicant e = new EditApplicant(editNum);
          frame.setVisible(false);
        } else if (positions) {
          EditPosition g = new EditPosition(editNum);
          frame.setVisible(false);
        }
      } else if (eventName.equals("Delete")) {
        if (company) {
          compArrayList.remove(editNum);
          
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
          frameStatus = 1;
          AddEditDelete goBack = new AddEditDelete(frameStatus);
          frame.setVisible(false);
          
        } else if (applicants) {
          appArrayList.remove(editNum);
          
          try {
            FileOutputStream out = new FileOutputStream(applicantFile, false);
            ObjectOutputStream writeToFile = new ObjectOutputStream(out);
            
            for (int x = 0; x <appArrayList.size(); x++) {
              a = appArrayList.get(x);
              writeToFile.writeObject(a);
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
          frameStatus = 2;
          AddEditDelete goBack = new AddEditDelete(frameStatus);
          frame.setVisible(false);

        } else if (positions) {
          posArrayList.remove(editNum);
          
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
          frameStatus = 3;
          AddEditDelete goBack = new AddEditDelete(frameStatus);
          frame.setVisible(false);
        }
      } else if (eventName.equals("Back")) {
        VirtualPlacementAgency back = new VirtualPlacementAgency();
        frame.setVisible(false);
      }
    }
  }
  
  /**
   * Selects which file to open
   * pre: none
   * post: Opens The App/Comp/Pos Info
   */
  
  class SelectCompany implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      JComboBox comboBox = (JComboBox)event.getSource();
      String itemName = (String) comboBox.getSelectedItem();
      editNum = comboBox.getSelectedIndex();
      
      if (company) {

        c = compArrayList.get(editNum);
        
        compName.setText("Company Name: " +c.getCompanyName());
        compStreet.setText("Address: " +c.getCompanyStreet());
        compCity.setText("City: "+c.getCompanyCity());
        compProvince.setText("Province: "+c.getCompanyProvince());
        compPostalCode.setText("Postal Code: "+c.getCompanyPostalCode());
        compContactName.setText("Contact Name: "+c.getCompanyContactName());
        compPhoneNumber.setText("Phone #: "+c.getCompanyNumber());
        compEmail.setText("Email: " +c.getCompanyEmail());
        compNotes.setText("Notes: "+c.getCompanyNotes());
        
      } else if (applicants) {
      
        a = appArrayList.get(editNum);
        
        appName.setText("Applicant Name: "+a.getApplicantName());
        appStreet.setText("Address: "+a.getApplicantStreet());
        appCity.setText("City: "+a.getApplicantCity());
        appProvince.setText("Province: "+a.getApplicantProvince());
        appPostalCode.setText("Postal Code: "+a.getApplicantPostalCode());
        appContactName.setText("Contact Name: "+a.getApplicantContactName());
        appPhoneNumber.setText("Phone #: "+a.getApplicantNumber());
        appEmail.setText("Email: "+a.getApplicantEmail());
        appNotes.setText("Notes: "+a.getApplicantNotes());
        
      } else if (positions) {
        
        p = posArrayList.get(editNum);
        
        positionTitle.setText("Position Title: "+p.getPositionTitle());
        notes.setText("Notes: " +p.getPositionNotes());
        companyName.setText("Company Name: "+p.getPositionCompanyName());
        contactName.setText("Contact Name: "+p.getPositionContactName());
        contactPhone.setText("Contact Phone: "+p.getPositionContactPhone());
        positionStatus.setText("Position Status: "+p.getPositionStatus());
        positionOpenedDate.setText("Position Open Date: "+p.getPositionOpenDate());
        positionClosedDate.setText("Position Closed Date: "+ p.getPositionCloseDate());
        reasonClosed.setText("Reason Closed: "+p.getPositionReasonClosed());
        
      }
    }
  }
}