package com.celerysoft.ittakestwo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.celerysoft.ittakestwo.modules.Card;
import com.celerysoft.ittakestwo.modules.CardMatchingGame;
import com.celerysoft.ittakestwo.modules.PlayingCard;
import com.celerysoft.ittakestwo.modules.PlayingDeck;

import java.util.ArrayList;

public class PlayingCardActivity extends Activity {

    private GridLayout cardContainer;
    private Button card00;
    private Button card01;
    private Button card02;
    private Button card03;
    private Button card04;
    private Button card05;
    private Button card06;
    private Button card07;
    private Button card08;
    private Button card09;
    private Button card10;
    private Button card11;
    private Button card12;
    private Button card13;
    private Button card14;
    private Button card15;
    private ArrayList<Button> cardButtons = new ArrayList<>();

    private Button btnRestartGame;
    private Button btnShareScore;
    private TextView tvScroe;

    private CardMatchingGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_card);

        onCreateView();
        onCreateListener();
        autoAdjustForScreen();
        game = new CardMatchingGame(cardButtons.size(), new PlayingDeck());
    }

    /**
     * adjust cards horizontal gap and vertical gap
     */
    private void autoAdjustForScreen() {
        //adjust horizontal gap
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        float margin = getResources().getDimension(R.dimen.activity_horizontal_margin);

        GridLayout.LayoutParams cardlayoutParams = (GridLayout.LayoutParams) card01.getLayoutParams();
        int cardLayoutWidth = cardlayoutParams.width;
        int cardLayoutMarginLeft = (int) ((screenWidth - 2 * margin - 4 * cardLayoutWidth) / 3);
        cardlayoutParams.setMargins(cardLayoutMarginLeft, 0, 0, 0);
        card01.setLayoutParams(cardlayoutParams);
        cardlayoutParams = (GridLayout.LayoutParams) card02.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutMarginLeft, 0, 0, 0);
        card02.setLayoutParams(cardlayoutParams);
        cardlayoutParams = (GridLayout.LayoutParams) card03.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutMarginLeft, 0, 0, 0);
        card03.setLayoutParams(cardlayoutParams);

        //TODO adjust vertical gap
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_playing_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onCreateView() {
        cardContainer = (GridLayout) findViewById(R.id.playingcard_gridlayout);

        card00 = (Button) findViewById(R.id.card00);
        card01 = (Button) findViewById(R.id.card01);
        card02 = (Button) findViewById(R.id.card02);
        card03 = (Button) findViewById(R.id.card03);
        card04 = (Button) findViewById(R.id.card04);
        card05 = (Button) findViewById(R.id.card05);
        card06 = (Button) findViewById(R.id.card06);
        card07 = (Button) findViewById(R.id.card07);
        card08 = (Button) findViewById(R.id.card08);
        card09 = (Button) findViewById(R.id.card09);
        card10 = (Button) findViewById(R.id.card10);
        card11 = (Button) findViewById(R.id.card11);
        card12 = (Button) findViewById(R.id.card12);
        card13 = (Button) findViewById(R.id.card13);
        card14 = (Button) findViewById(R.id.card14);
        card15 = (Button) findViewById(R.id.card15);
        cardButtons.add(card00);
        cardButtons.add(card01);
        cardButtons.add(card02);
        cardButtons.add(card03);
        cardButtons.add(card04);
        cardButtons.add(card05);
        cardButtons.add(card06);
        cardButtons.add(card07);
        cardButtons.add(card08);
        cardButtons.add(card09);
        cardButtons.add(card10);
        cardButtons.add(card11);
        cardButtons.add(card12);
        cardButtons.add(card13);
        cardButtons.add(card14);
        cardButtons.add(card15);

        btnRestartGame = (Button) findViewById(R.id.playingcard_btn_restart);
        btnShareScore = (Button) findViewById(R.id.playingcard_btn_share);
        tvScroe = (TextView) findViewById(R.id.playingcard_tv_score);
    }

    private void onCreateListener() {
        card00.setOnClickListener(onCardClickListener);
        card01.setOnClickListener(onCardClickListener);
        card02.setOnClickListener(onCardClickListener);
        card03.setOnClickListener(onCardClickListener);
        card04.setOnClickListener(onCardClickListener);
        card05.setOnClickListener(onCardClickListener);
        card06.setOnClickListener(onCardClickListener);
        card07.setOnClickListener(onCardClickListener);
        card08.setOnClickListener(onCardClickListener);
        card09.setOnClickListener(onCardClickListener);
        card10.setOnClickListener(onCardClickListener);
        card11.setOnClickListener(onCardClickListener);
        card12.setOnClickListener(onCardClickListener);
        card13.setOnClickListener(onCardClickListener);
        card14.setOnClickListener(onCardClickListener);
        card15.setOnClickListener(onCardClickListener);
    }

    private View.OnClickListener onCardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view instanceof Button) {
                Button card = (Button) view;
                int chosenButtonIndex = cardButtons.indexOf(card);
                game.chooseCardAtIndex(chosenButtonIndex);
                updateUi();
            }
        }
    };

    private void updateUi() {
        for (Button cardButton : cardButtons) {
            int cardButtonIndex = cardButtons.indexOf(cardButton);
            Card card = game.cardAtIndex(cardButtonIndex);
            setTextForCard(cardButton, card);
            setBackGroundForCard(cardButton, card);
        }
    }

    private void setTextForCard(Button cardButton, Card card) {
        cardButton.setText(card.isChosen() ? card.getContents() : "");
        if (card instanceof PlayingCard) {
            String suit = ((PlayingCard) card).getSuit();
            if (suit.equals("♥") || suit.equals("♦")) {
                cardButton.setTextColor(Color.RED);
            } else if (suit.equals("♠") || suit.equals("♣")) {
                cardButton.setTextColor(Color.BLACK);
            }
        }

    }

    private void setBackGroundForCard(Button cardButton, Card card) {
        if (card.isChosen()) {
            cardButton.setBackgroundColor(0xFFFFFFFF);
        } else {
            cardButton.setBackgroundResource(R.drawable.cardback);
        }
        tvScroe.setText(getString(R.string.playingcard_score) + game.getScore());
    }
}
