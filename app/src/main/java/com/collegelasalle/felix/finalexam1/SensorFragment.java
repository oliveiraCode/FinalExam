package com.collegelasalle.felix.finalexam1;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SensorFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;

    public SensorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_sensor, container, false);


        RadioGroup radioGroup = rootView.findViewById(R.id.radioGroup);

        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int RadioButtonSelected) {

                switch (RadioButtonSelected){
                    case R.id.radioButtonTemperature:
                        getTemperature();
                        break;
                    case R.id.radioButtonLight:
                        getLight();
                        break;
                    case R.id.radioButtonPressure:
                        getPressure();
                        break;
                }
            }
        });



        return rootView;

    }

    private void getLight(){

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void getPressure(){

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void getTemperature(){

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        TextView sensorResult = getActivity().findViewById(R.id.sensorResult);

        System.out.println(sensorEvent.values[0]);

        sensorResult.setText(String.valueOf(sensorEvent.values[0]));

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
