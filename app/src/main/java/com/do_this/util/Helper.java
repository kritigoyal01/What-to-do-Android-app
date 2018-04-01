package com.do_this.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateUtils;

import com.do_this.model.Count;
import com.do_this.model.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by a_man on 12-03-2018.
 */

public class Helper {

    public static void openPlayStoreIntent(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        } else {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static String timeString(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date startDate = simpleDateFormat.parse(date);
            return new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(startDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return "";
        }
    }

    public static CharSequence timeDiff(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date startDate = new Date();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            startDate = simpleDateFormat.parse(date);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return DateUtils.getRelativeTimeSpanString(startDate.getTime(), calendar.getTimeInMillis(), DateUtils.SECOND_IN_MILLIS);
    }

    public static ArrayList<Message> getMessages(SharedPreferenceUtil sharedPreferenceUtil) {
        ArrayList<Message> toReturn = new ArrayList<>();
        String savedInPrefs = sharedPreferenceUtil.getStringPreference(Constants.KEY_MESSAGES, null);
        if (savedInPrefs != null) {
            ArrayList<Message> messages = new Gson().fromJson(savedInPrefs, new TypeToken<ArrayList<Message>>() {
            }.getType());
            toReturn.addAll(messages);
        } else {
            toReturn.add(new Message("Go cook something please."));
            toReturn.add(new Message("Just walk away man..."));
            toReturn.add(new Message("Sit down buddy."));
            toReturn.add(new Message("You look good! now go buy a stupid dress to suit yourself"));
            toReturn.add(new Message("Ohh my love, you look bad, really bad! go wash your face."));
            toReturn.add(new Message("what? are you drooling? ewww, lick that off please."));
            toReturn.add(new Message("I have just called police and told'em about what you did last summer, RUN!"));
        }
        return toReturn;
    }

    public static Count getDisplayedCount(SharedPreferenceUtil sharedPreferenceUtil) {
        Count toReturn = new Count();
        String savedInPrefs = sharedPreferenceUtil.getStringPreference(Constants.KEY_DISPLAY_COUNT, null);
        if (savedInPrefs != null) {
            toReturn = new Gson().fromJson(savedInPrefs, new TypeToken<Count>() {
            }.getType());
        }
        return toReturn;
    }

    public static void setDisplayedCount(SharedPreferenceUtil sharedPreferenceUtil, Count displayedCount) {
        sharedPreferenceUtil.setStringPreference(Constants.KEY_DISPLAY_COUNT, new Gson().toJson(displayedCount, new TypeToken<Count>() {
        }.getType()));
    }

    public static void setMessages(SharedPreferenceUtil sharedPreferenceUtil, ArrayList<Message> messageList) {
        sharedPreferenceUtil.setStringPreference(Constants.KEY_MESSAGES, new Gson().toJson(messageList, new TypeToken<ArrayList<Message>>() {
        }.getType()));
    }
}
