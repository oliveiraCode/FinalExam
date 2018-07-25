package com.collegelasalle.felix.finalexam1;


import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements View.OnLongClickListener {

    public GameFragment() {
        // Required empty public constructor
    }

    String value1="";
    String value2="";
    int id = 0;
    Boolean newPlay = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        buttonRoll(rootView);
        textViewDrag(rootView);
        tokenOnLongClick(rootView);


        return rootView;
    }

    private void buttonRoll(View rootView) {
        Button btnRoll = rootView.findViewById(R.id.btnRoll);
        final TextView result1 = rootView.findViewById(R.id.result1);
        final TextView result2 = rootView.findViewById(R.id.result2);

        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int value1 = randomValues();
                int value2 = randomValues();

                while (value1 == value2){
                    value1 = randomValues();
                    value2 = randomValues();
                }

                result1.setText(String.valueOf(value1));
                result2.setText(String.valueOf(value2));

                calculatePoints();
            }
        });
    }


    private void tokenOnLongClick(View rootView) {

        for (int tokenIds : new int[]{
                R.id.token1,
                R.id.token2
        }){

            ImageView imageView = rootView.findViewById(tokenIds);
            imageView.setOnLongClickListener(this);
        }

    }


    @Override
    public boolean onLongClick(View view) {

        if (newPlay){
            restartGame();
        }

        ClipData data = ClipData.newPlainText("","");
        view.startDrag(data, new View.DragShadowBuilder(view), null, 0);

        return true;
    }

    private void textViewDrag(View rootView){
        for (final int tvIds : new int[] {
                R.id.textView1,
                R.id.textView2,
                R.id.textView3,
                R.id.textView4,
                R.id.textView5,
                R.id.textView6,
                R.id.textView7,
                R.id.textView8,
                R.id.textView9,
                R.id.textView10,
                R.id.textView11,
                R.id.textView12,
                R.id.textView13,
                R.id.textView14,
                R.id.textView15,
                R.id.textView16
        }) {

            rootView.findViewById(tvIds).setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {


                    if (dragEvent.getAction() == DragEvent.ACTION_DROP) {

                        TextView textView = getActivity().findViewById(tvIds);

                        textView.setTextColor(Color.GREEN);
                        newPlay = false;


                        if (value1 == ""){
                            value1 = textView.getText().toString();
                        } else if (value2 == ""){
                            value2 = textView.getText().toString();
                        }

                    }


                    return true;
                }
            });
        }
    }


    private void calculatePoints(){

        TextView result1 = getActivity().findViewById(R.id.result1);
        TextView result2 = getActivity().findViewById(R.id.result2);
        TextView score = getActivity().findViewById(R.id.score);

        if (value1.equals(result1.getText().toString()) && value2.equals(result2.getText().toString())){
            int result = Integer.valueOf(score.getText().toString())+100;
            score.setText(String.valueOf(result));
        } else

        if (value1.equals(result1.getText().toString()) || value2.equals(result2.getText().toString())){
            int result = Integer.valueOf(score.getText().toString())+25;
            score.setText(String.valueOf(result));
        } else {
            int result = Integer.valueOf(score.getText().toString())-5;
            score.setText(String.valueOf(result));
        }

        newPlay = true;

    }

    private int randomValues(){
        Random random = new Random();
        return (random.nextInt(16))+1;
    }

    private void restartGame(){

        value1 = "";
        value2 = "";

        TextView result1 = getActivity().findViewById(R.id.result1);
        TextView result2 = getActivity().findViewById(R.id.result2);
        result1.setText("00");
        result2.setText("00");


        for (final int tvIds : new int[]{
                R.id.textView1,
                R.id.textView2,
                R.id.textView3,
                R.id.textView4,
                R.id.textView5,
                R.id.textView6,
                R.id.textView7,
                R.id.textView8,
                R.id.textView9,
                R.id.textView10,
                R.id.textView11,
                R.id.textView12,
                R.id.textView13,
                R.id.textView14,
                R.id.textView15,
                R.id.textView16
        }) {
            TextView textView = getActivity().findViewById(tvIds);
            textView.setTextColor(Color.WHITE);
        }
    }

}
