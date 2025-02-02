package ArrayList;

import java.util.*;
public class Time
{
    private int hour;
    private int minute;
    private int second;
    private boolean am;

    public Time()
    {
        hour = 0;
        minute = 0;
        second = 0;
        am = true;

    }

    public Time(int hours, int minutes, int seconds, boolean meridiem)
    {
        hour = hours;
        minute = minutes;
        second = seconds;
        am = meridiem;

    }

    public int getHour()
    {
        return hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public int getSeconds()
    {
        return second;
    }

    public boolean getAM()
    {
        return am;
    }

    //write mutators for each attributes
    //set the attribute hour to the parameter hours.
    //We use this if we want to change the hour
    //like you got a new clock from the store.
    public void setHours(int hours)
    {
        this.hour = hours;
    }
    public void setMinutes(int minutes)
    {
        this.minute = minutes;
    }
    public void setSeconds(int seconds)
    {
        this.second = seconds;
    }
    public void setMeridiem(boolean meridiem)
    {
        this.am = meridiem;
    }

    //This is required for CodingRoom, optional in BlueJ
    public static void main(String[] args)
    {
        //declare a 10 element array of Time objects
        Time[] timeObjects = new Time[10];
//timeObjects[0] = new Time(1, 20, 30, true);
//        timeObjects[1] = {2, 30, 40, true};
//        timeObjects[2] = {3, 20, 30, true};
//        timeObjects[3] = {4, 30, 40, true};
//        timeObjects[4] = {5, 15, 20, true};
//        timeObjects[5] = {6, 20, 30, true};
//        timeObjects[6] = {11, 15, 20, true};
//        timeObjects[7] = {10, 20, 30, true;
//        timeObjects[8] = {9, 10, 45, true};
//        timeObjects[9] = {6, 15, 20, true};
       
        //initialize them with random time

    }
    }

