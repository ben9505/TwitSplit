package com.example.ben.twitsplit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PresenterView {

    PresenterUtils presenterUtils;
    MsgAdapter msgAdapter;
    ArrayList<String> strings = new ArrayList<>();

    EditText edMessage;
    RecyclerView rclListMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edMessage = (EditText) findViewById(R.id.edMessage);
        rclListMessage = (RecyclerView) findViewById(R.id.rclListMessage);
        presenterUtils = new PresenterUtils(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        msgAdapter = new MsgAdapter(this,strings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(false);
        rclListMessage.setLayoutManager(linearLayoutManager);
        rclListMessage.setHasFixedSize(false);
        rclListMessage.setAdapter(msgAdapter);

    }

    @Override
    public String getMessage() {
        return edMessage.getText().toString().trim();
    }

    @Override
    public void setMessage(String s) {
        edMessage.setText(s);
    }

    @Override
    public void onSendMessage(String msg) {
        strings.add(msg);
        msgAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSendMultiMessage(ArrayList<String> arrMsg) {
        for (int i=0; i < arrMsg.size(); i++) {
            strings.add(arrMsg.get(i));
        }
        msgAdapter.notifyDataSetChanged();
    }

    public void onClickSend(View view) {
        presenterUtils.sendMessage(this);
    }
}
