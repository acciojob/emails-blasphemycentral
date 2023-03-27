package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Gmail extends Email
{

    int inboxCapacity; //maximum number of mails inbox can store
    ArrayList<Mail> inbo;
    ArrayList<Mail> trash;
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity)
    {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbo = new ArrayList<>();
        this.trash = new ArrayList<>();
    }

    public Gmail()
    {
    }

    public void receiveMail(Date date, String sender, String message)
    {
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        Mail m = new Mail(date, sender, message);
        if (inbo.size() == inboxCapacity)
        {
            trash.add(inbo.get(0));
            inbo.remove(0);
        }
        inbo.add(m);
    }

    public void deleteMail(String message)
    {
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Mail m : inbo)
        {
            if(m.message.equals(message))
            {
                trash.add(m);
                inbo.remove(m);
            }
        }
    }

    public String findOldestMessage()
    {
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbo.size() == 0) return null;
        return inbo.get(0).message;
    }

    public String findLatestMessage()
    {
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbo.size() == 0) return null;
        return inbo.get(inbo.size()-1).message;
    }

    public int findMailsBetweenDates(Date start, Date end)
    {
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(Mail m : inbo)
        {
            if(m.date.compareTo(start)>=0 && end.compareTo(m.date)<=0)count++;
        }
        return count;
    }

    public int getInboxSize()
    {
        // Return number of mails in inbox
        return inbo.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash = new ArrayList<>();
    }

    public int getInboxCapacity()
    {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
    public void setInboxcapacity(int inboxCapacity){
        this.inboxCapacity = inboxCapacity;
    }
    class Mail
    {
        Date date;
        String sender;
        String message;

        public Mail(Date date, String sender, String message)
        {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }
    }
}
