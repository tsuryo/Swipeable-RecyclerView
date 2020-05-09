package com.tsuryo.swipeablervexample;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mList;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createList();

        final SwipeableRecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);

        rv.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
                Snackbar.make(rv,
                        "Item " + position + " Marked As Read",
                        Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSwipedRight(int position) {
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
                Snackbar.make(rv,
                        "Item " + position + " Removed",
                        Snackbar.LENGTH_LONG).show();
            }
        });

        /*rv.setRightBg(R.color.blue);
        rv.setRightImage(R.drawable.ic_v);
        rv.setRightText("Right Text");

        rv.setLeftBg(R.color.red);
        rv.setLeftImage(R.drawable.ic_trash);
        rv.setLeftText("Left Text");

        rv.setTextSize(62);
        rv.setTextColor(R.color.white);*/
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

    private void createList() {
        mList = new ArrayList<>();
        //  generate example list
        for (int i = 0; i < 20; i++) {
            mList.add("Item - " + i);
        }
        mAdapter = new Adapter(mList);
    }

}
