package ru.etstudio.kuhmeyster.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.adapter.Pack;

public class OfferActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        Pack pack = getIntent().getParcelableExtra(Pack.class.getCanonicalName());
    }
}
