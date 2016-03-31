package com.androidexamples.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.androidexamples.R;
import com.androidexamples.adapter.MyAdapter;
import com.androidexamples.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView myList = (RecyclerView) findViewById(R.id.recyclerList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        myList.setHasFixedSize(true);
        myList.setLayoutManager(layoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        List<UserInfo> mDataSet = new ArrayList<>();
        DBHandler dbHandler=new DBHandler(this);

        boolean count=dbHandler.isEmpty();
        if(count){
            Log.d("data is present","");
        }else {
            dbHandler.addContacts(new UserInfo("ABC", "1245678"));
            dbHandler.addContacts(new UserInfo("PQR", "5314554"));
            dbHandler.addContacts(new UserInfo("QRT", "7542415"));
            dbHandler.addContacts(new UserInfo("YTE", "3627845"));
        }

        MyAdapter myAdapter=new MyAdapter(mDataSet);
        mDataSet.clear();
        List<UserInfo> allContacts=dbHandler.getAllContacts();
            for(UserInfo userInfo:allContacts){
                Log.d("Name", userInfo.getName());
                Log.d("Phone Number",userInfo.getPhone_number());
                mDataSet.add(userInfo);
            }
        myList.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
