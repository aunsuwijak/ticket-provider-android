package com.kanoonth.ticketprovider.managers;

/**
 * Created by TAWEESOFT on 4/12/16 AD.
 */
public class Utility {
    public static String getMonthStr(int month) {
        switch(month) {
            case 0 :
                return "January";
            case 1 :
                return "February";
            case 2 :
                return "March";
            case 3 :
                return "April";
            case 4 :
                return "May";
            case 5 :
                return "June";
            case 6 :
                return "July";
            case 7 :
                return "August";
            case 8 :
                return "September";
            case 9 :
                return "October";
            case 10 :
                return "November";
            case 11 :
                return "December";
            default :
                return "";
        }
    }
}
