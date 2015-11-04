package ru.etstudio.kuhmeyster.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.db.entity.Dish;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishHolder> implements View.OnClickListener {

    private static final String LOG_TAG = DishAdapter.class.getName();

    private Context context;

    private List<Dish> dishes;

    private final IDishItemListener callback;

    public DishAdapter(Context context, IDishItemListener listener) {
        this.context = context;
        this.callback = listener;
    }

    @Override
    public DishHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        DishHolder dishHolder = new DishHolder(v);
        v.setTag(dishHolder);
        v.setOnClickListener(this);
        return dishHolder;
    }

    @Override
    public void onBindViewHolder(DishHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.bind(context, dish);
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

    public void setData(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public static class DishHolder extends RecyclerView.ViewHolder {

        private TextView title;

        private TextView lastCooking;

        private ImageView dishType;

        private ImageView fish;

        public DishHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.dish_title);
            lastCooking = (TextView) itemView.findViewById(R.id.dish_last_cooking);
            dishType = (ImageView) itemView.findViewById(R.id.image_dish_type);
            fish = (ImageView) itemView.findViewById(R.id.image_contains_fish);
        }

        public void bind(Context context, Dish dish) {
            title.setText(dish.getTitle());
            if (dish.getLastCooking() != null) {
                lastCooking.setText(dish.getFormattedLastCooking());
            }
            fish.setImageDrawable(context.getResources().getDrawable(R.drawable.fish52));
            fish.setVisibility(View.GONE);

            Drawable type;
            if (dish.isLenten()) {
                type = context.getResources().getDrawable(R.drawable.vegan);
                if (dish.containsFish()) {
                    fish.setVisibility(View.VISIBLE);
                }
            } else {
                type = context.getResources().getDrawable(R.drawable.turkey7);
            }
            dishType.setImageDrawable(type);
        }

        public int getPos() {
            return getAdapterPosition();
        }
    }
}
