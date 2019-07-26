/*
 * Created by Sujoy Datta. Copyright (c) 2019. All rights reserved.
 *
 * To the person who is reading this..
 * When you finally understand how this works, please do explain it to me too at sujoydatta26@gmail.com
 * P.S.: In case you are planning to use this without mentioning me, you will be met with mean judgemental looks and sarcastic comments.
 */

package com.morningstar.notabene.managers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeManager {

    public  static String getCurrentDateAsString(){
        Date currentDate = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");

        return df.format(currentDate);
    }

    public static Date getCurrentDateAsDate(){
        return new Date();
    }


    public static long getTimeStamp(){
        return System.currentTimeMillis();
    }
}
