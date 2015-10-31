package ru.etstudio.kuhmeyster.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.adapter.Card;
import ru.etstudio.kuhmeyster.adapter.CardAdapter;
import ru.etstudio.kuhmeyster.adapter.CardChoiceMode;
import ru.etstudio.kuhmeyster.adapter.ICardItemListener;
import ru.etstudio.kuhmeyster.db.dao.DishDAO;
import ru.etstudio.kuhmeyster.db.dao.KindDAO;
import ru.etstudio.kuhmeyster.db.entity.Kind;

public class MainActivity extends AppCompatActivity implements ICardItemListener, View.OnClickListener {

    private static final String LOG_TAG = MainActivity.class.getName();

    private KindDAO kindDAO;

    private DishDAO dishDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kindDAO = new KindDAO(getApplicationContext());
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

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCardClick(Card card) {
        System.out.print(card.getKind().getLabel());
    }

    @Override
    public void onCardLongClick(Card card) {
        if (card != null) {
            Intent dishesIntent = new Intent(getApplicationContext(), DishesActivity.class);
            dishesIntent.putExtra(Card.class.getCanonicalName(), card);
            startActivity(dishesIntent);
        }
    }

    @Override
    public void onCardSelected(List<Card> cards) {

    }

    @Override
    public void onClick(View v) {

    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerMainMenu = (RecyclerView) findViewById(R.id.main_menu);
        recyclerMainMenu.setHasFixedSize(true);
        recyclerMainMenu.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerMainMenu.setLayoutManager(layoutManager);

        List<Card> menuDataSet = getCards();
        CardAdapter cardAdapter = new CardAdapter(this, menuDataSet);
        cardAdapter.setChoiceMode(CardChoiceMode.MULTISELECT);
        recyclerMainMenu.setAdapter(cardAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_next);
        fab.attachToRecyclerView(recyclerMainMenu);
        fab.setOnClickListener(this);
    }

    private List<Card> getCards() {
        Resources resources = getResources();
        TypedArray images = resources.obtainTypedArray(R.array.images);

        List<Card> menu = new LinkedList<>();
        List<Kind> kinds = kindDAO.getAll();
        for (Kind kind : kinds) {
            Card card = Card.newBulder()
                    .setKind(kind)
                    .setImage(null)
                    .setDishCount(dishDAO.getCountForKind(kind))
                    .build();
            menu.add(card);
        }
        return menu;
    }
}
