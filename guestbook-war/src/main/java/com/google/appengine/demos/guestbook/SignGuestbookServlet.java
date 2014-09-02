package com.google.appengine.demos.guestbook;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.util.logging.Logger;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Collection;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SignGuestbookServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(SignGuestbookServlet.class.getName());

  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    String guestbookName = req.getParameter("guestbookName");
    Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);
    String content = req.getParameter("content");
    log.info("test123");

    Date date = new Date();
    Entity greeting = new Entity("Greeting", guestbookKey);
    greeting.setProperty("user", user);
    greeting.setProperty("date", date);
    greeting.setProperty("content", content);

    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);
        
    String msgBody = "sdfsdffsd";
    try {       
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("kris@premium-cloud-support.com", "Example.com Admin"));
      msg.addRecipient(Message.RecipientType.TO,
        new InternetAddress("kmuzyka@google.com", "Mr. User1"));
      msg.setSubject("Your Example.com account has been activated");
      msg.setText(msgBody);
      msg.setHeader("References", "zeteczka123");
      Transport.send(msg);
    } catch (AddressException e) {
      log.info("address");
    } catch (MessagingException e) {
      log.info("message");
    }
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(greeting);

    resp.sendRedirect("/guestbook.jsp?guestbookNam1e=" + guestbookName);
  }
}
