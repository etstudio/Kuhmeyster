package ru.etstudio.kuhmeyster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.db.entity.Kind;

public class KindsAdapter extends BaseAdapter {

    private List<Kind> dataSet;

    private LayoutInflater inflater;

    public KindsAdapter(Context context, List<Kind> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataSet = data;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.kind_list_item, parent, false);
        }

        Kind kind = (Kind) getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.kindTitle);
        textView.setText(kind.getKind());

        return view;
    }
}
