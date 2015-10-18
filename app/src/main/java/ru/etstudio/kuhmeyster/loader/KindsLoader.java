package ru.etstudio.kuhmeyster.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import ru.etstudio.kuhmeyster.db.dao.KindDAO;
import ru.etstudio.kuhmeyster.db.entity.Kind;

public class KindsLoader extends AsyncTaskLoader<List<Kind>> {

    private List<Kind> kinds;

    private KindDAO kindDAO;

    public KindsLoader(Context context) {
        super(context);
        kindDAO = new KindDAO(context);
    }

    @Override
    public List<Kind> loadInBackground() {
        return kindDAO.getAll();
    }

    @Override
    public void deliverResult(List<Kind> data) {
        kinds = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (kinds != null) {
            deliverResult(kinds);
        }

        if (takeContentChanged() || kinds == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        if (kinds != null) {
            kinds = null;
        }
    }
}
