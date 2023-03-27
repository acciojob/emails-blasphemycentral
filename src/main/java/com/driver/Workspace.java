package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId)
    {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting)
    {
        //add the meeting to calendar
        this.calendar.add(meeting);
    }

    public int findMaxMeetings()
    {
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        Collections.sort(calendar, (a,b) -> a.getEndTime().compareTo(b.getEndTime()));
        int count=  0;
        if(calendar.size()>0) count =1;
        LocalTime end = calendar.get(0).getEndTime();
        for(int i = 1;i<calendar.size();i++){
            if(calendar.get(i).getStartTime().compareTo(end)>0){
                count++;
                end = calendar.get(i).getEndTime();
            }
        }
        return count;
//        LocalTime[][] a = new LocalTime[calendar.size()][2];
//        int i=0;
//        for(Meeting m : calendar)
//        {
//            a[i][0] = m.getStartTime();
//            a[i][1] = m.getEndTime();
//            i++;
//        }
//        return findLongestChain(a);
    }
    public int findLongestChain(LocalTime[][] a)
    {
        sort(a);
        // System.out.println(Arrays.deepToString(a));
        int[] dp = new int[a.length];
        Arrays.fill(dp,1);

        for(int i=1; i<a.length; i++)
        {
            int max = 0;
            for(int j=i-1; j>=0; j--)
            {
                if(a[j][1].compareTo(a[i][0]) < 0)
                {
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
        }
        // System.out.println(Arrays.toString(dp));
        int max = 1;
        for(int i=0; i<dp.length; i++) max = Math.max(max, dp[i]);
        return max;
    }
    public void sort(LocalTime[][] a)
    {
        for(int i=0; i<a.length-1; i++)
        {
            int min = i;
            for(int j=i+1; j<a.length; j++)
            {
                if(a[j][0].compareTo(a[min][0]) < 0) min = j;
            }
            LocalTime temp0 = a[i][0];
            LocalTime temp1 = a[i][1];
            a[i][0] = a[min][0];
            a[i][1] = a[min][1];
            a[min][0] = temp0;
            a[min][1] = temp1;
        }
    }
}
