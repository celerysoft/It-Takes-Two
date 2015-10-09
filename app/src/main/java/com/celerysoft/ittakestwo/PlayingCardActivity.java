package com.celerysoft.ittakestwo;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.celerysoft.ittakestwo.models.Card;
import com.celerysoft.ittakestwo.models.CardMatchingGame;
import com.celerysoft.ittakestwo.models.PlayingCard;
import com.celerysoft.ittakestwo.models.PlayingDeck;

import java.util.ArrayList;

public class PlayingCardActivity extends Activity {
    /** Log tag **/
    private final String LOG_TAG = this.getClass().getSimpleName();

    private final String GAME_STATE_CARDS = "gameStateCards";
    private final String GAME_STATE_SCORE = "gameStateScore";
    private final int SCORE_MISSING = 99999;
    private final String CARDS_MISSING = "cardsMissing";

    private final int CARD_COUNT = 16;

    private boolean isNeededAutoAdjustForScreen = true;

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
        game = new CardMatchingGame(cardButtons.size(), new PlayingDeck());
        isNeededAutoAdjustForScreen = true;
    }

    /**
     * adjust cards horizontal gap and vertical gap for portrait screen
     */
    private void autoAdjustForPortraitScreen() {

        //adjust horizontal gap
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        float horizontalMargin = getResources().getDimension(R.dimen.activity_horizontal_margin);

        GridLayout.LayoutParams cardlayoutParams = (GridLayout.LayoutParams) card01.getLayoutParams();
        int cardLayoutWidth = cardlayoutParams.width;
        int cardLayoutHorizontalMargin = (int) ((screenWidth - 2 * horizontalMargin - 4 * cardLayoutWidth) / 3);

        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card02.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card03.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        //adjust vertical gap
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        float verticalMargin = getResources().getDimension(R.dimen.activity_vertical_margin);

        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int buttonHeight = btnRestartGame.getHeight();

        cardlayoutParams = (GridLayout.LayoutParams) card04.getLayoutParams();
        int cardLayoutHeight = cardlayoutParams.height;

        int cardLayoutVerticalMargin = (int) ((screenHeight - statusBarHeight - 3 * verticalMargin - buttonHeight - 4 * cardLayoutHeight) / 3);

        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card08.getLayoutParams();
        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card12.getLayoutParams();
        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);
    }

    /**
     * adjust cards horizontal gap and vertical gap for landscape screen
     */
    private void autoAdjustForLandscapeScreen() {

        //adjust horizontal gap
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        float horizontalMargin = getResources().getDimension(R.dimen.activity_horizontal_margin);

        GridLayout.LayoutParams cardlayoutParams = (GridLayout.LayoutParams) card01.getLayoutParams();
        int cardLayoutWidth = cardlayoutParams.width;
        int cardLayoutHorizontalMargin = (int) ((screenWidth - 2 * horizontalMargin - 8 * cardLayoutWidth) / 7);
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card02.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card03.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card04.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card05.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card06.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card07.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        //adjust vertical gap
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        float verticalMargin = getResources().getDimension(R.dimen.activity_vertical_margin);

        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int buttonHeight = btnRestartGame.getHeight();

        cardlayoutParams = (GridLayout.LayoutParams) card09.getLayoutParams();
        int cardLayoutHeight = cardlayoutParams.height;

        int cardLayoutVerticalMargin = (int) ((screenHeight - statusBarHeight - 3 * verticalMargin - buttonHeight - 2 * cardLayoutHeight) / 1);

        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);
        card09.setLayoutParams(cardlayoutParams);
    }

    /**
     * when all the widget create finished adjust gap of cards.
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            if (isNeededAutoAdjustForScreen) {
                if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    autoAdjustForPortraitScreen();
                } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    autoAdjustForLandscapeScreen();
                }

                isNeededAutoAdjustForScreen = false;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_playing_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onCreateView() {

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

        btnRestartGame.setOnClickListener(onBtnClickListener);
        btnShareScore.setOnClickListener(onBtnClickListener);
    }

    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.playingcard_btn_restart:
                    onRestartBtnClick();
                    break;
                case R.id.playingcard_btn_share:
                    onShareScoreBtnClick();
                    break;
                default:
                    break;
            }
        }
    };

    private void onRestartBtnClick() {
        game = new CardMatchingGame(cardButtons.size(), new PlayingDeck());
        updateUi();
    }

    private void onShareScoreBtnClick() {
        game.restart();
        updateUi();
    }

    /** card buttons onClickListener **/
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

    /** update UI when touch the cards **/
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
        if (card.isMatched()) {
            cardButton.setBackgroundColor(0xAAFFFFFF);
            return;
        }
        if (card.isChosen()) {
            cardButton.setBackgroundColor(0xFFFFFFFF);
        } else {
            cardButton.setBackgroundResource(R.drawable.cardback);
        }
        tvScroe.setText(getString(R.string.playingcard_score) + game.getScore());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        int score = game.getScore();
        outState.putInt(GAME_STATE_SCORE, score);

        String saveGame = "";
        ArrayList<Card> cards = game.getCards();
        int cardCount = cards.size();
        for (int i = 0; i < cardCount; ++i) {
            Card card = cards.get(i);
            if (card instanceof PlayingCard) {
                saveGame += ((PlayingCard) card).getSuit() + "@" + ((PlayingCard) card).getRank() +  "@" + card.isMatched() +  "@" + card.isChosen() + "#";
            } else {
                Log.e(LOG_TAG, "Sava game error: card content is abnormal. WTF, how she do it?");
            }

        }
        saveGame = saveGame.substring(0, saveGame.length() - 1);
        outState.putString(GAME_STATE_CARDS, saveGame);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            int score = savedInstanceState.getInt(GAME_STATE_SCORE, SCORE_MISSING);
            if (score == SCORE_MISSING) {
                Log.e(LOG_TAG, "Restore game error: game score is missing!!!");
                return;
            }

            String saveGame = savedInstanceState.getString(GAME_STATE_CARDS, CARDS_MISSING);
            if (saveGame.equals(CARDS_MISSING)) {
                Log.e(LOG_TAG, "Restore game error: cards data is missing!!!");
                return;
            } else {
                ArrayList<Card> restoreCards = new ArrayList<>();
                String[] cardStrings = saveGame.split("#");
                int cardCount = cardStrings.length;
                if (cardCount != CARD_COUNT) {
                    Log.e(LOG_TAG, "Restore game error: card count is abnormal!!!");
                    return;
                }
                for (int i = 0; i < cardCount; ++i) {
                    String cardContent = cardStrings[i];
                    String[] cardContents = cardContent.split("@");
                    if (cardContents.length != 4) {
                        Log.e(LOG_TAG, "Restore game error: card content is lost!!!");
                        return;
                    }
                    PlayingCard card = new PlayingCard();
                    card.setSuit(cardContents[0]);
                    card.setRank(Integer.parseInt(cardContents[1]));
                    card.setMatched(Boolean.parseBoolean(cardContents[2]));
                    card.setChosen(Boolean.parseBoolean(cardContents[3]));
                    restoreCards.add(card);
                }
                this.game = new CardMatchingGame(restoreCards, score);
                updateUi();
            }

            super.onRestoreInstanceState(savedInstanceState);
        }
    }


}
