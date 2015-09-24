package ru.etstudio.kuhmeyster.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.ui.common.Blur;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Card> dataSet;

    public RecyclerAdapter(List<Card> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_menu_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.getLabel().setText(dataSet.get(i).getLabel());
        viewHolder.getDishImage().setImageResource(dataSet.get(i).getImageId());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dishLabel;
        private ImageView dishImage;

        public ViewHolder(View itemView) {
            super(itemView);
            dishLabel = (TextView) itemView.findViewById(R.id.dish_type_text);
            dishImage = (ImageView) itemView.findViewById(R.id.dish_type_photo);
        }

        public TextView getLabel() {
            return dishLabel;
        }

        public ImageView getDishImage() {
            return dishImage;
        }
    }


}
