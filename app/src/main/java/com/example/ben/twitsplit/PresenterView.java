package com.example.ben.twitsplit;

import java.util.ArrayList;

/**
 * Created by ben on 13/03/2018.
 */

public interface PresenterView {
    String getMessage();
    void setMessage(String s);
    void onSendMessage(String msg);
    void onSendMultiMessage(ArrayList<String> arrMsg);
}
