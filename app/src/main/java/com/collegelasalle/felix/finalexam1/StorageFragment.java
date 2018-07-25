package com.collegelasalle.felix.finalexam1;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class StorageFragment extends Fragment {

    private final String requestFormat = "https://www.omdbapi.com/?apikey=%s&t=%s";
    private final String apiKey = "6a464315";
    private String request;

    public StorageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_storage, container, false);

        final EditText editTextName = rootView.findViewById(R.id.editTextName);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                request = String.format(requestFormat, apiKey,editTextName.getText().toString());

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                TextView textViewSaved = getActivity().findViewById(R.id.textViewSaved);



                if (sharedPref.getString(editTextName.getText().toString(), "") != ""){
                    TextView storageResult = getActivity().findViewById(R.id.storageResult);

                    storageResult.setText(sharedPref.getString(editTextName.getText().toString(), ""));
                    textViewSaved.setText("YES");
                } else {
                textViewSaved.setText("NO");
                    download();

            }
            }
        });

        return rootView;
    }


    private void download() {
        new DownloadMovieTask(this).execute(request);
    }

    public void parseJson(String json) {
        String title = "",year,genre;


        String text = "";
        try {

            JSONObject jsonObject = new JSONObject(json);
            title = jsonObject.getString("Title");
            year = jsonObject.getString("Year");
            genre = jsonObject.getString("Genre");
            text = String.format("Title: %s\nYear: %s\nGenre: %s", title, year, genre);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);


        sharedPref.edit().putString(title.toString(), text).commit();


        TextView storageResult = getActivity().findViewById(R.id.storageResult);


        storageResult.setText(text);
    }



}
