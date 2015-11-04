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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.adapter.Card;
import ru.etstudio.kuhmeyster.adapter.DishAdapter;
import ru.etstudio.kuhmeyster.adapter.IDishItemListener;
import ru.etstudio.kuhmeyster.db.entity.Dish;
import ru.etstudio.kuhmeyster.loader.DishesLoader;

public class DishesActivity extends AppCompatActivity implements View.OnClickListener,
        IDishItemListener, LoaderManager.LoaderCallbacks<List<Dish>> {

    private static final String LOG_TAG = DishesActivity.class.getName();

    private RecyclerView recycler;

    private DishAdapter adapter;

    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);

        initializeExtras();
        initializeComponents();

        getLoaderManager().initLoader(0, null, this).forceLoad();
        loading(true, 0);
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
        recycler.setAdapter(adapter);
        loading(false, data.size());
    }

    @Override
    public void onLoaderReset(Loader<List<Dish>> loader) {
        adapter.setData(null);
        recycler.setAdapter(adapter);
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

    private void loading(boolean start, int count) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        TextView resultText = (TextView) findViewById(R.id.result_text);
        if (start) {
            recycler.setVisibility(View.GONE);
            resultText.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            recycler.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            if (count <= 0) {
                resultText.setVisibility(View.VISIBLE);
            }
        }
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

        adapter = new DishAdapter(getApplicationContext(), this);
        recycler = (RecyclerView) findViewById(R.id.dishes);
        recycler.setHasFixedSize(true);
        recycler.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.attachToRecyclerView(recycler);
        fab.setOnClickListener(this);
    }
}
