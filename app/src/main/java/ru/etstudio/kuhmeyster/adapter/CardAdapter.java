package ru.etstudio.kuhmeyster.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import ru.etstudio.kuhmeyster.R;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private static final String LOG_TAG = CardAdapter.class.getName();

    private List<Card> cards;

    private ICardItemListener callback;

    private CardChoiceMode mode;

    private SparseBooleanArray selected = new SparseBooleanArray();

    public CardAdapter(ICardItemListener listener, List<Card> cards) {
        this.callback = listener;
        this.cards = cards;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        CardHolder cardHolder = new CardHolder(v);
        v.setTag(cardHolder);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        return cardHolder;
    }

    @Override
    public void onBindViewHolder(CardHolder cardHolder, int i) {
        Card card = cards.get(i);
        cardHolder.bind(card);
        cardHolder.setSelected(selected.get(i));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public void onClick(View v) {
        CardHolder cardHolder = (CardHolder) v.getTag();
        if (cardHolder != null) {
            if (mode == CardChoiceMode.MULTISELECT) {
                selected.put(cardHolder.getPos(), cardHolder.select());
                if (callback != null) {
                    callback.onCardClick(cards.get(cardHolder.getPos()));
                    callback.onCardSelected(getSelectedCards());
                }
            }

            if (mode == CardChoiceMode.SINGLE) {
                if (callback != null) {
                    callback.onCardClick(cards.get(cardHolder.getPos()));
                }
            }

        }
    }

    @Override
    public boolean onLongClick(View v) {
        CardHolder cardHolder = (CardHolder) v.getTag();
        if (cardHolder != null) {
            if (callback != null) {
                callback.onCardLongClick(cards.get(cardHolder.getPos()));
            }
        }

        return true;
    }

    public List<Card> getSelectedCards() {
        List<Card> selectedCards = new ArrayList<>();
        int size = selected.size();

        try {
            for (int i = 0; i < size; i++) {
                int key = selected.keyAt(i);
                boolean isSelected = selected.get(key);
                if (isSelected) {
                    Card card = cards.get(key);
                    if (card != null) {
                        selectedCards.add(card);
                    }
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return selectedCards;
    }

    public void setChoiceMode(CardChoiceMode mode) {
        this.mode = mode;
    }

    public static class CardHolder extends RecyclerView.ViewHolder {

        private TextView kindLabel;

        private TextView statisticLabel;

        private ImageView selectImage;

        private boolean selected = false;

        private String statisticTemplate;

        public CardHolder(View itemView) {
            super(itemView);
            kindLabel = (TextView) itemView.findViewById(R.id.kind_label);
            statisticLabel = (TextView) itemView.findViewById(R.id.statistic_text);
            selectImage = (ImageView) itemView.findViewById(R.id.card_selector);
            statisticTemplate = itemView.getContext().getString(R.string.main_statistic);
        }

        public void bind(Card card) {
            kindLabel.setText(card.getKind().getLabel());
            String statisticText = MessageFormat.format(statisticTemplate, card.getDishCount());
            statisticLabel.setText(statisticText);
        }

        public boolean select() {
            if (selected) {
                selectImage.setImageResource(android.R.drawable.button_onoff_indicator_off);
                selected = false;
            } else {
                selectImage.setImageResource(android.R.drawable.button_onoff_indicator_on);
                selected = true;
            }
            return selected;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean value) {
            selected = !value;
            select();
        }

        public int getPos() {
            return getAdapterPosition();
        }
    }


}
