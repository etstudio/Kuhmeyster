package ru.etstudio.kuhmeyster.ui.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.adapter.Card;
import ru.etstudio.kuhmeyster.adapter.DishAdapter;
import ru.etstudio.kuhmeyster.adapter.IDishItemListener;
import ru.etstudio.kuhmeyster.db.dao.DishDAO;
import ru.etstudio.kuhmeyster.db.entity.Dish;

public class DishesActivity extends AppCompatActivity implements View.OnClickListener,
        IDishItemListener {

    private static final String LOG_TAG = DishesActivity.class.getName();

    private DishDAO dishDAO;

    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        dishDAO = new DishDAO(getApplicationContext());
        initializeExtras();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add:
                //TODO start add dialog
                break;
        }
    }

    @Override
    public void onClick(Dish dish) {
        //TODO details dialog
    }

    private void initializeExtras() {
        card = getIntent().getParcelableExtra(Card.class.getCanonicalName());
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (card != null) {
            setTitle(card.getKind().getLabel());
        }

        RecyclerView recyclerMainMenu = (RecyclerView) findViewById(R.id.main_menu);
        recyclerMainMenu.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMainMenu.setLayoutManager(layoutManager);

        DishAdapter cardAdapter = new DishAdapter(this, dishDAO.getFor(card.getKind()));
        recyclerMainMenu.setAdapter(cardAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.attachToRecyclerView(recyclerMainMenu);
        fab.setOnClickListener(this);
    }
}
