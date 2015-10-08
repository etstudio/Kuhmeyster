package ru.etstudio.kuhmeyster.activity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.adapter.Card;
import ru.etstudio.kuhmeyster.adapter.DishType;
import ru.etstudio.kuhmeyster.adapter.ICardItemListener;
import ru.etstudio.kuhmeyster.adapter.RecyclerAdapter;
import ru.etstudio.kuhmeyster.db.dao.DishDAO;

public class MainActivity extends AppCompatActivity implements ICardItemListener {

    private DishDAO dishDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dishDAO = new DishDAO(getApplicationContext());
        initializeComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerMainMenu = (RecyclerView) findViewById(R.id.main_menu_recycler);
        recyclerMainMenu.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMainMenu.setLayoutManager(layoutManager);

        List<Card> menuDataSet = getCards();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(menuDataSet);
        recyclerAdapter.setItemListener(this);
        recyclerMainMenu.setAdapter(recyclerAdapter);
    }

    private List<Card> getCards() {
        Resources resources = getResources();
        TypedArray images = resources.obtainTypedArray(R.array.images);

        List<Card> menu = new LinkedList<>();

        Card card = new Card(DishType.EVERYDAY, images.getDrawable(0));
        card.setEverydayCount(dishDAO.getEverydayCount());
        card.setEverydayLentenCount(dishDAO.getEverydayLentenCount());
        menu.add(card);

        card = new Card(DishType.CELEBRATORY, images.getDrawable(1));
        card.setCelebratoryCount(dishDAO.getCelebratoryCount());
        card.setCelebratoryLentenCount(dishDAO.getCelebratoryLentenCount());
        menu.add(card);

        return menu;
    }

    @Override
    public void onCardItemClick(Card card) {
        Toast.makeText(this, card.getLabel(), Toast.LENGTH_SHORT).show();
    }
}
