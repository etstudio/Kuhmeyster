package ru.etstudio.kuhmeyster.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.adapter.KindsAdapter;
import ru.etstudio.kuhmeyster.db.dao.KindDAO;
import ru.etstudio.kuhmeyster.db.entity.Kind;

public class KindsFragment extends ListFragment implements View.OnClickListener {

    public interface onActionAcceptListener {
        void onAccept(List<Kind> selected);
    }

    private onActionAcceptListener actionListener;

    private KindsAdapter kindsAdapter;

    private List<Kind> selectedKinds;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context context = getActivity().getApplicationContext();
        KindDAO kindDAO = new KindDAO(context);
        kindsAdapter = new KindsAdapter(context, kindDAO.getAll());
        setListAdapter(kindsAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                actionListener = (onActionAcceptListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement onActionAcceptListener");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.kinds_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.attachToListView(getListView());
        fab.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        SparseBooleanArray sba = l.getCheckedItemPositions();

        initializeSelectedKindList();

        for (int i = 0; i < sba.size(); i++) {
            int key = sba.keyAt(i);
            if (sba.get(key)) {
                selectedKinds.add((Kind) kindsAdapter.getItem(key));
            }
        }
    }

    private void initializeSelectedKindList() {
        if (selectedKinds == null) {
            selectedKinds = new ArrayList<>();
        } else {
            selectedKinds.clear();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (actionListener != null) {
                    actionListener.onAccept(selectedKinds);
                }
                break;

        }
    }
}
