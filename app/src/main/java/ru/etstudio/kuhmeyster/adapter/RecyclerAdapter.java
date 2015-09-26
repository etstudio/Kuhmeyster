package ru.etstudio.kuhmeyster.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.db.dao.DishDAO;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private List<Card> dataSet;

    private DishDAO dishDAO;

    private Context context;

    private ICardItemListener itemListener;

    public RecyclerAdapter(Context context, List<Card> dataSet) {
        this.dataSet = dataSet;
        this.context = context;
        dishDAO = new DishDAO(context);
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_menu_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setTag(viewHolder);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.getLabel().setText(dataSet.get(i).getLabel());
        viewHolder.getDishImage().setImageDrawable(dataSet.get(i).getImage());
        Resources resources = context.getResources();

        if (dataSet.get(i).getType().equals(DishType.EVERYDAY)) {
            String text = MessageFormat.format(resources.getString(R.string.main_statistic),
                    dishDAO.getEverydayCount(), dishDAO.getEverydayLentenCount());
            viewHolder.getStatistic().setText(text);

        } else if (dataSet.get(i).getType().equals(DishType.CELEBRATORY)) {
            String text = MessageFormat.format(resources.getString(R.string.main_statistic),
                    dishDAO.getCelebratoryCount(), dishDAO.getCelebratoryLentenCount());
            viewHolder.getStatistic().setText(text);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onClick(View v) {
        if (itemListener != null) {
            ViewHolder viewHolder = (ViewHolder) v.getTag();
            if (viewHolder != null) {
                itemListener.onCardItemClick(dataSet.get(viewHolder.getAdapterPosition()));
            }
        }
    }

    public void setItemListener(ICardItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dishLabel;

        private TextView statistic;

        private ImageView dishImage;

        public ViewHolder(View itemView) {
            super(itemView);
            dishLabel = (TextView) itemView.findViewById(R.id.dish_type_text);
            statistic = (TextView) itemView.findViewById(R.id.statistic);
            dishImage = (ImageView) itemView.findViewById(R.id.dish_type_photo);
        }

        public TextView getLabel() {
            return dishLabel;
        }

        public ImageView getDishImage() {
            return dishImage;
        }

        public TextView getStatistic() {
            return statistic;
        }
    }


}
