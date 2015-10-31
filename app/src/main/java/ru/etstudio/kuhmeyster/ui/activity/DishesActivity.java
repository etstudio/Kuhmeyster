package ru.etstudio.kuhmeyster.ui.activity;


import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.adapter.Card;
import ru.etstudio.kuhmeyster.adapter.DishAdapter;
import ru.etstudio.kuhmeyster.adapter.IDishItemListener;
import ru.etstudio.kuhmeyster.db.dao.DishDAO;
import ru.etstudio.kuhmeyster.db.entity.Dish;
import ru.etstudio.kuhmeyster.loader.DishesLoader;

public class DishesActivity extends AppCompatActivity implements View.OnClickListener,
        IDishItemListener, LoaderManager.LoaderCallbacks<List<Dish>> {

    private static final String LOG_TAG = DishesActivity.class.getName();

    private DishAdapter adapter;

    private RecyclerView recyclerDishes;

    private DishDAO dishDAO;

    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        dishDAO = new DishDAO(getApplicationContext());

        initializeExtras();
        initializeComponents();

        getLoaderManager().initLoader(7, null, this);
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
    public Loader<List<Dish>> onCreateLoader(int id, Bundle args) {
        return new DishesLoader(getApplicationContext(), card.getKind());
    }

    @Override
    public void onLoadFinished(Loader<List<Dish>> loader, List<Dish> data) {
        adapter.setData(data);
        recyclerDishes.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Dish>> loader) {
        adapter.setData(null);
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

        adapter = new DishAdapter(this);
        recyclerDishes = (RecyclerView) findViewById(R.id.dishes);
        recyclerDishes.setAdapter(adapter);
        recyclerDishes.setHasFixedSize(true);
        recyclerDishes.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerDishes.setLayoutManager(layoutManager);
        recyclerDishes.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.attachToRecyclerView(recyclerDishes);
        fab.setOnClickListener(this);
    }
}
