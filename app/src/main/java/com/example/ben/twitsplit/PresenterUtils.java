package com.example.ben.twitsplit;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ben on 12/03/2018.
 */

public class PresenterUtils {

    PresenterView presenterView;

    public PresenterUtils(PresenterView presenterView) {
        this.presenterView = presenterView;
    }

    ArrayList<String> arrMsg = new ArrayList<>();

    private final int LIMIT_CHARACTER_LENGTH = 50;
    int numberAddMoreChar = 4;

    public void sendMessage(Context context) {
        String msg = presenterView.getMessage();
        boolean isError = false;
        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(context,context.getString(R.string.error_empty),Toast.LENGTH_SHORT).show();
            return;
        }

        if (msg.length() < 50) {
            Debug.i(msg);
            presenterView.onSendMessage(msg);
        } else {
            arrMsg.clear();
            splitMessage(msg);
            ArrayList<String> arr = new ArrayList<>();
            for (int i=0; i < arrMsg.size(); i++) {
                int j = i+1;
                String line = j + "/" + arrMsg.size();
                String text = line + " " +arrMsg.get(i);
                if (text.length() >= LIMIT_CHARACTER_LENGTH) {
                    isError = true;
                    numberAddMoreChar = numberAddMoreChar + 1;
                    break;
                }
                arr.add(text);
                Debug.i(text);
            }
            if (!isError) {
                presenterView.onSendMultiMessage(arr);
            }
        }
        if (isError) {
            sendMessage(context);
        } else {
            numberAddMoreChar = 4;
            presenterView.setMessage("");
        }
    }

    private void splitMessage(String msg) {
        if (msg.length() < 50) {
            arrMsg.add(msg);
        } else {
            String[] arrStrings = msg.split(" ");
            if (arrStrings.length==1) {
                String s = slipForLongString(msg);
                arrMsg.add(s);
                String rightString = msg.substring(s.length()).trim();
                splitMessage(rightString);
            } else {
                String s = arrayToStringLimitCharacters(arrStrings);
                arrMsg.add(s);
                String rightString = msg.substring(s.length()).trim();
                splitMessage(rightString);
            }

        }
    }

    private String arrayToStringLimitCharacters(String[] arr) {
        StringBuilder builder = new StringBuilder();
        for(String s : arr) {
            int length = builder.toString().length() + s.length();
            if (length < (LIMIT_CHARACTER_LENGTH - numberAddMoreChar)) {
                builder.append(s + " ");
            } else {
                break;
            }
        }
        return builder.toString().trim();
    }

    private String slipForLongString(String msg) {
        String s = msg;
        if (msg.length() >= (LIMIT_CHARACTER_LENGTH - numberAddMoreChar)) {
            s = msg.substring(0,LIMIT_CHARACTER_LENGTH - numberAddMoreChar -1);
        }
        return s;
    }

}
