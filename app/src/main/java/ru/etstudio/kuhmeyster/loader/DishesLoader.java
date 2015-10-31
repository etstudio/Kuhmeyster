package ru.etstudio.kuhmeyster.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import ru.etstudio.kuhmeyster.db.dao.DishDAO;
import ru.etstudio.kuhmeyster.db.entity.Dish;
import ru.etstudio.kuhmeyster.db.entity.Kind;

public class DishesLoader extends AsyncTaskLoader<List<Dish>> {

    private DishDAO dishDAO;

    private Kind kind;

    public DishesLoader(Context context) {
        super(context);
    }

    public DishesLoader(Context context, Kind kind) {
        this(context);
        this.kind = kind;
        dishDAO = new DishDAO(context);
    }

    @Override
    public List<Dish> loadInBackground() {
        return dishDAO.getFor(kind);
    }
}
