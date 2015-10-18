package ru.etstudio.kuhmeyster.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.adapter.Card;
import ru.etstudio.kuhmeyster.adapter.Pack;
import ru.etstudio.kuhmeyster.db.entity.Kind;
import ru.etstudio.kuhmeyster.ui.fragment.KindsFragment;

public class KindActivity extends AppCompatActivity implements KindsFragment.onActionAcceptListener {

    private Card card;

    private Switch lentenSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinds);

        card = getIntent().getParcelableExtra(Card.class.getCanonicalName());

        initializeComponents(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initializeComponents(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lentenSwitch = (Switch) findViewById(R.id.switch_lenten);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.kindListFragment) == null) {
            fragmentManager.beginTransaction().add(R.id.listContainer, new KindsFragment()).commit();
        }
    }

    @Override
    public void onAccept(List<Kind> selected) {
        Intent intent = new Intent(getApplicationContext(), OfferActivity.class);
        Pack pack = new Pack();
        pack.setDishType(card.getDishType());
        pack.setLenten(lentenSwitch.isChecked());
        for (Kind kind : selected) {
            pack.addKindIds(kind.getId());
        }
        intent.putExtra(Pack.class.getCanonicalName(), pack);
        startActivity(intent);
    }
}
