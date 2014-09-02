package com.google.appengine.demos.guestbook;

import java.io.IOException;
import javax.servlet.http.*;
import java.com.google.appengine.api.mail.BounceNotification;
import java.com.google.appengine.api.mail.BounceNotificationParser;

public class BounceHandlerServlet extends HttpServlet {
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp)
            throws IOException {
    try {
      BounceNotification bounce = BounceNotificationParser.parse(req);
    } catch (AddressException e) {
      bounce.getOriginal().getFrom();
  
    }
       BounceNotification bounce = BounceNotificationParser.parse(req);
       // bounce.getOriginal().getFrom() 
       // bounce.getOriginal().getTo() 
       // bounce.getOriginal().getSubject() 
       // bounce.getOriginal().getText() 
       // bounce.getNotification().getFrom() 
       // bounce.getNotification().getTo() 
       // bounce.getNotification().getSubject() 
       // bounce.getNotification().getText() 
   }
}
