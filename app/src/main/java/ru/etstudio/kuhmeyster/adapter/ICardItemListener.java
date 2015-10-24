package ru.etstudio.kuhmeyster.adapter;


import java.util.List;

public interface ICardItemListener {

    void onCardClick(Card card);

    void onCardLongClick(Card card);

    void onCardSelected(List<Card> cards);
}
