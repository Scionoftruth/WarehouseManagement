package com.wm.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.wm.enums.StatusEnum;
import com.wm.models.Customer;
import com.wm.models.Order;
import com.wm.models.Transaction;
import com.wm.repository.CustomerRepo;
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

public void sendMail(int orderNum){
//This should return a newly created invoice with all order details


    Transaction trans = tDao.getById(orderNum);
    Customer cust = cDao.findById(trans.getCustId());
    Order orde = oDao.findById(trans.getOrderId());

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
    String mess = "Hello:\nThis is your invoice for order: " + orde.getOrderId()+"\n";
// list all items in order
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

  Customer cust = cDao.findById(custId);
    Transaction trans = tDao.getByCustId(cust.getCustId());
  

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
    String mess = "Hello:\nYour current orders and status are: " + order.getOrderId()+"\n";
// list all transactions by customer ID, and the status if not resolved
// return a list

for (int i = 0; i < list.length(); i++) {
    StatusEnum status = list.getStatus();
    switch (status) {
        case StatusEnum.SUBMITTED:
        mess = (mess + list.getOrderId + " | " status.valueOf(StatusEnum, status));

    }
}

        message.setText(mess);
        Transport.send(message);
        System.out.println("Sent message successfully");
    } catch (MessagingException e) {
        System.out.println("Failed to send Email");
        e.printStackTrace();
    }
}

}
