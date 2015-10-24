package ru.etstudio.kuhmeyster.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.db.entity.Dish;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishHolder> implements View.OnClickListener {

    private static final String LOG_TAG = DishAdapter.class.getName();

    private List<Dish> dishes;

    private final IDishItemListener callback;

    public DishAdapter(IDishItemListener listener, List<Dish> dishes) {
        this.callback = listener;
        this.dishes = dishes;
    }

    @Override
    public DishHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        DishHolder dishHolder = new DishHolder(v);
        v.setTag(dishHolder);
        v.setOnClickListener(this);
        return dishHolder;
    }

    @Override
    public void onBindViewHolder(DishHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.bind(dish);
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    @Override
    public void onClick(View v) {
        DishHolder dishHolder = (DishHolder) v.getTag();
        if (dishHolder != null) {
            if (callback != null) {
                callback.onClick(dishes.get(dishHolder.getPos()));
            }
        }
    }

    public static class DishHolder extends RecyclerView.ViewHolder {

        TextView title;

        public DishHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.dish_title);
        }

        public void bind(Dish dish) {
            title.setText(dish.getTitle());
        }

        public int getPos() {
            return getAdapterPosition();
        }
    }
}
