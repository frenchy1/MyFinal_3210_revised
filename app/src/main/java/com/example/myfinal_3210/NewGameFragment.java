package com.example.myfinal_3210;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.Toast.makeText;


public class NewGameFragment extends Fragment {

    private TextView textViewPlayer1Label;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2Label;
    private TextView textViewPlayer2;

    Button aButtons[][] = new Button[4][4];

    private boolean playerXTurn = true;

    private int roundCount;

    // place to stash variables

    private SharedPreferences savedValues;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    private void getSharedPreferences(String savedValues, int modePrivate) {
    }

    @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState){
        View view = inflater.inflate (R.layout.fragment_newgame, container, false);

        textViewPlayer1Label = (TextView) view.findViewById(R.id.addPlayer1Label);
        textViewPlayer1 = (TextView) view.findViewById(R.id.player1);
        textViewPlayer2Label = (TextView) view.findViewById(R.id.addPlayer2Label);
        textViewPlayer2 =(TextView) view.findViewById(R.id.player2);

        //connect buttons to member variables

        aButtons[0][0] = view.findViewById(R.id.button00);
        aButtons[0][1] = view.findViewById(R.id.button01);
        aButtons[0][2] = view.findViewById(R.id.button02);
        aButtons[0][3] = view.findViewById(R.id.button03);
        aButtons[1][0] = view.findViewById(R.id.button10);
        aButtons[1][1] = view.findViewById(R.id.button11);
        aButtons[1][2] = view.findViewById(R.id.button12);
        aButtons[1][3] = view.findViewById(R.id.button13);
        aButtons[2][0] = view.findViewById(R.id.button20);
        aButtons[2][1] = view.findViewById(R.id.button21);
        aButtons[2][2] = view.findViewById(R.id.button22);
        aButtons[2][3] = view.findViewById(R.id.button23);
        aButtons[3][0] = view.findViewById(R.id.button30);
        aButtons[3][1] = view.findViewById(R.id.button31);
        aButtons[3][2] = view.findViewById(R.id.button32);
        aButtons[3][3] = view.findViewById(R.id.button33);

        // initialize SharedPreferences
        //this.savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        for (int nRow = 0; nRow < 4; nRow++) {
            for (int nCol = 0; nCol < 4; nCol++) {
                aButtons[nRow][nCol].setOnClickListener((View.OnClickListener) this);

            }
        }
        Button newGame = view.findViewById(R.id.ButtonReset);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                restartGame();
            }
        });
        return  view;
    }

    private void   restartGame(){

        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                aButtons[i][j].setText("");
                roundCount=0;

                playerXTurn = true;
            }
        }

    }


    //@Override
    public void onClick(View v) {
        Button btnClick = (Button)v;

        if (!(((Button)v).getText().toString().equals(""))){
            return;
        }

        if (playerXTurn){

            btnClick.setText("X");
        }

        else {

            btnClick.setText("O");
        }

        roundCount++;

        if (checkWin()){
            if (playerXTurn){
                playerXWins();
            }else{
                playerOWins();
            }
        }else if (roundCount == 16){
            draw();
        }else {
            playerXTurn = !playerXTurn;

        }
    }

    private boolean checkWin(){

        String[][] gameBoard = new String[4][4];

        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                gameBoard[i][j] = aButtons[i][j].getText().toString();
            }
        }

        for (int i=0;i<4;i++){
            if (gameBoard[i][0].equals(gameBoard[i][1])
                    & gameBoard[i][0].equals(gameBoard[i][2])
                    & gameBoard[i][0].equals(gameBoard[i][3])
                    & !gameBoard[i][0].equals("")){
                return  true;
            }
        }

        for (int i=0;i<4;i++){
            if (gameBoard[0][i].equals(gameBoard[1][i])
                    & gameBoard[0][i].equals(gameBoard[2][i])
                    & gameBoard[0][i].equals(gameBoard[3][i])
                    & !gameBoard[0][i].equals("")){
                return  true;
            }
        }

        if (gameBoard[0][0].equals(gameBoard[1][1])
                & gameBoard[0][0].equals(gameBoard[2][2])
                & gameBoard[0][0].equals(gameBoard[3][3])
                & !gameBoard[0][0].equals("")){
            return  true;
        }

        if (gameBoard[0][3].equals(gameBoard[1][2])
                & gameBoard[0][3].equals(gameBoard[2][1])
                & gameBoard[0][3].equals(gameBoard[3][0])
                & !gameBoard[0][2].equals("")){
            return  true;
        }

        return false;
    }

    private void  playerXWins(){

        makeText(this, "Player X Wins!", Toast.LENGTH_SHORT);

    }

    private void makeText(NewGameFragment newGameFragment, String s, int lengthShort) {
    }

    private void  playerOWins(){

        makeText(this, "Player O Wins!", Toast.LENGTH_SHORT);

    }
    private void  draw(){
        makeText(this, " It's a Draw!", Toast.LENGTH_SHORT);
        show();
    }

    private void show() {

    }
}


    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState ) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_newgame, container, false);
        this.getText() = (Spinner) view.findViewById(R.id.player1)
        return view;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_pickplayer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewGameFragment.this)
                        .navigate(R.id.action_HomeFragment_to_SelectionFragment);
            }
        });

    }

    public void restart() {
        this.updateDisplay();
        Toast.makeText(this.getContext(), "Restart Game", Toast.LENGTH_SHORT).show();
    }*/


