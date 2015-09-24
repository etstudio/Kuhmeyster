package ru.etstudio.kuhmeyster.activity;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.adapter.RecyclerAdapter;
import ru.etstudio.kuhmeyster.adapter.Card;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeComponents() {
        RecyclerView recyclerMainMenu = (RecyclerView) findViewById(R.id.main_menu_recycler);
        recyclerMainMenu.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMainMenu.setLayoutManager(layoutManager);
        List<Card> menuDataSet = getMenu();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(menuDataSet);
        recyclerMainMenu.setAdapter(recyclerAdapter);
    }

    private List<Card> getMenu() {
        Resources resources = getResources();
        String[] mainMenuItems = resources.getStringArray(R.array.main_type_menu);
        TypedArray images = resources.obtainTypedArray(R.array.images);
        List<Card> menu = new LinkedList<>();
        for (int i = 0; i < mainMenuItems.length; i++) {
            menu.add(new Card(mainMenuItems[i], images.getResourceId(i, -1)));
        }
        return menu;
    }


}
