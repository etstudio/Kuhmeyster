package ru.etstudio.kuhmeyster.activity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.adapter.Card;
import ru.etstudio.kuhmeyster.adapter.DishType;
import ru.etstudio.kuhmeyster.adapter.ICardItemListener;
import ru.etstudio.kuhmeyster.adapter.RecyclerAdapter;

public class MainActivity extends AppCompatActivity implements ICardItemListener {

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
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getApplicationContext(), menuDataSet);
        recyclerAdapter.setItemListener(this);
        recyclerMainMenu.setAdapter(recyclerAdapter);
    }

    private List<Card> getMenu() {
        Resources resources = getResources();
        TypedArray images = resources.obtainTypedArray(R.array.images);

        List<Card> menu = new LinkedList<>();
        menu.add(new Card(DishType.EVERYDAY, images.getDrawable(0)));
        menu.add(new Card(DishType.CELEBRATORY, images.getDrawable(1)));

        return menu;
    }

    @Override
    public void onCardItemClick(Card card) {

    }
}
