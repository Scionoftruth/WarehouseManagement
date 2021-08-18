package com.wm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.wm.enums.StatusEnum;
import com.wm.models.Customer;
import com.wm.models.Item;
import com.wm.models.Order;
import com.wm.models.Transaction;
import com.wm.repository.CustomerRepo;
import com.wm.repository.ItemRepo;
import com.wm.repository.OrderRepo;
import com.wm.repository.TransactionRepo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailService {

private String to;
private String from;
private String host;
private Properties properties;
private Session session;

private OrderRepo oDao;
private TransactionRepo tDao;
private CustomerRepo cDao;
private ItemRepo iDao;
private TransactionService tServ;

public void sendTestMail() {
    
    to = "rpengler@ymail.com";
    from = "web@gmail.com";
    host = "localhost";
    properties = System.getProperties();
    properties.setProperty("mail.stmp.host", host);
    session = Session.getDefaultInstance(properties);

    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("This is a subject");
        message.setText("This is actual message");
        Transport.send(message);
        System.out.println("Sent message successfully");
    } catch (MessagingException e) {
        System.out.println("Failed to send Email");
        e.printStackTrace();
    }
}

public void sendMail(int transNum, int custId){
//This should return a specific invoice with all order details


    Transaction trans = tDao.findByTransId(transNum); // fetch the specfic transaction
    Customer cust = cDao.findByCustId(custId); // to get cutomer email
    

    to = cust.getEmail();
    from = "web@gmail.com";
    host = "localhost";
    properties = System.getProperties();
    properties.setProperty("mail.stmp.host", host);
    session = Session.getDefaultInstance(properties);

    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Thank you for your order");
        String mess = "Hello:\nThis is your invoice for order: " + transNum +"\n\n";

        List<Order> orderQuery = new ArrayList<Order>();
        orderQuery = oDao.findByTransId(transNum);
// check in the controller if the ID is valid
 //   if (orderQuery == null) {
 //       message.setText("Sorry, but we did not find an order matching the ID: " + transNum);
  //  }
// add method for grabbing all items of specific order

        for (int i = 0; i < orderQuery.size(); i++) {
            Item itemId = orderQuery.get(i).getItemId();
            float price =  (itemId.getItemPrice() * itemId.getInvQuantity());
            mess = (mess + "Item: " + itemId + " " + itemId.getItemName() + "\nQuantity: " + itemId.getInvQuantity() 
            + "\nPrice: " + price + "\n\n");
        }

        message.setText(mess);
        Transport.send(message);
        System.out.println("Sent message successfully");
    } catch (MessagingException e) {
        System.out.println("Failed to send Email");
        e.printStackTrace();
    }

}
public void sendInvMail(int custId){
//This should return a list of unresolved invoices

    Customer cust = cDao.findByCustId(custId);
    List<Transaction> transList = tServ.getUnresolvedByCustomer(custId);

    to = cust.getEmail();
    from = "web@gmail.com";
    host = "localhost";
    properties = System.getProperties();
    properties.setProperty("mail.stmp.host", host);
    session = Session.getDefaultInstance(properties);

    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Here are your pending orders");
        String mess = "Hello:\nYour current orders and status are: \n\n";

    //add method for returning all unresolved orders of specific customer
        if (transList == null) {
            mess = (mess + "You have no pending orders with us. \n");
        } else {
            for (int i = 0; i < transList.size(); i++) {
                Transaction trans = transList.get(i);
                String status = trans.getStatus().toString();
                mess = (mess + "Order ID: " + trans.getTransId() + " Status: " + status + "\n");
            }
        }

        mess = (mess + "\nIf you do not see your expected order here, that means the package is on its way.");

        message.setText(mess);
        Transport.send(message);
        System.out.println("Sent message successfully");
        } catch (MessagingException e) {
        System.out.println("Failed to send Email");
        e.printStackTrace();
        }
    }
}
