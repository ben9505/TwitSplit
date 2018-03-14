package com.example.ben.twitsplit;

import android.content.Context;
import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ben on 12/03/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class PresenterUtilsTest {

    @Mock
    PresenterView view;

    @Mock
    PresenterUtils presenterUtils;

    @Mock
    Context context;

    @Before
    public void setUp() throws Exception {
        presenterUtils = new PresenterUtils(view);
    }

    @Test
    public void splitMessage() throws Exception {
        when(view.getMessage()).thenReturn("I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.");
        presenterUtils.sendMessage(context);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1/2 I can't believe Tweeter now supports chunking");
        strings.add("2/2 my messages, so I don't have to do it myself.");
        verify(view).onSendMultiMessage(strings);
    }

    @Test
    public void splitMessageCase2() throws Exception {
        when(view.getMessage()).thenReturn("I can't believe Tweeter now supports chunking");
        presenterUtils.sendMessage(context);
        verify(view).onSendMessage("I can't believe Tweeter now supports chunking");
    }

    @Test
    public void splitMessageCase3() throws Exception {
        when(view.getMessage()).thenReturn("I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself. I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself. I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself. I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself. I can't believe Tweeter now supports chunking chunking chunking");
        presenterUtils.sendMessage(context);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1/11 I can't believe Tweeter now supports");
        strings.add("2/11 chunking my messages, so I don't have to do");
        strings.add("3/11 it myself. I can't believe Tweeter now");
        strings.add("4/11 supports chunking my messages, so I don't");
        strings.add("5/11 have to do it myself. I can't believe");
        strings.add("6/11 Tweeter now supports chunking my messages,");
        strings.add("7/11 so I don't have to do it myself. I can't");
        strings.add("8/11 believe Tweeter now supports chunking my");
        strings.add("9/11 messages, so I don't have to do it myself. I");
        strings.add("10/11 can't believe Tweeter now supports chunking");
        strings.add("11/11 chunking chunking");
        verify(view).onSendMultiMessage(strings);
    }

    @Test
    public void splitMessageCase4() throws Exception {
        when(view.getMessage()).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        presenterUtils.sendMessage(context);
    }

}