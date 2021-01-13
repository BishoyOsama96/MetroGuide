package com.bishoyosama.metroguide;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StartFragment extends Fragment implements View.OnClickListener, TextToSpeech.OnInitListener {
    public ArrayList<String> line1 = new ArrayList<>(Arrays.asList(
            "New El-Marg", "El-Marg", "Ezbet El-Nakhl", "Ain Shams",
            "El-Matareyya", "Helmeyet El-Zaitoun", "Hadayeq El-Zaitoun"
            , "Saray El-Qobba", "Hammamat ElQobba", "Kobri El-Qobba",
            "Manshiet El-Sadr", "El-Demerdash", "Ghamra", "Al-Shohadaa",
            "Orabi", "Nasser", "Sadat", "Saad Zaghloul", "Al-Sayeda Zeinab",
            "El-Malek El-Saleh", "Mar Girgis", "El-Zahraa'", "Dar El-Salam",
            "Hadayek El-Maadi", "Maadi", "Sakanat El-Maadi", "Tora El-Balad",
            "Kozzika", "Tora El-Asmant", "El-Maasara", "Hadayek Helwan",
            "Wadi Hof", "Helwan University", "Ain Helwan", "Helwan"));
    public ArrayList<String> line2 = new ArrayList<>(Arrays.asList(
            "Shubra El-Kheima", "Kolleyyet El-Zeraa", "Mezallat", "Khalafawy",
            "St.Teresa", "Rod El-Farag", "Masarra", "Al-Shohadaa", "Attaba",
            "Mohamed Naguib", "Sadat", "Opera", "Dokki", "El Bohoth", "Cairo University"
            , "Faisal", "Giza", "Omm El-Masryeen", "Sakiat Mekky", "El-Mounib"));
    public ArrayList<String> line3 = new ArrayList<>(Arrays.asList("Airport", "Ahmed Galal",
            "Adly Mansour", "El Haykestep", "Omar Ibn El-Khattab", "Qobaa", "Hesham Barakat",
            "El-Nozha", "Nadi El-Shams", "Alf Maskan", "Heliopolis Square", "Haroun", "Al-Ahram",
            "Koleyet El-Banat", "Stadium", "Fair Zone", "Abbassiya", "Abdou Pasha", "El-Geish",
            "Bab El-Shaaria", "Attaba", "Nasser", "Maspero", "Zamalek", "Kit Kat", "Sudan", "Imbaba",
            "El-Bohy", "El-Kawmeya Al-Arabiya", "Ring Road", "Rod El-Farag", "El-Tawfikeya",
            "Wadi El-Nil", "Gamaat El Dowal Al-Arabiya", "Bulaq El-Dakroor", "Cairo University"));
    ArrayList<String> items = new ArrayList<>();
    Spinner StartPoint, Direction;
    Button show;
    Station s = new Station();
    TextToSpeech tts;


    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        StartPoint = view.findViewById(R.id.StartPoint);
        Direction = view.findViewById(R.id.Direction);
        show = view.findViewById(R.id.show);
        show.setOnClickListener(this);
        tts = new TextToSpeech(getActivity(), this);
        items.clear();
        Collections.addAll(items, "Choose Station",
                "Line1", "New El-Marg", "El-Marg", "Ezbet El-Nakhl", "Ain Shams",
                "El-Matareyya", "Helmeyet El-Zaitoun", "Hadayeq El-Zaitoun"
                , "Saray El-Qobba", "Hammamat ElQobba", "Kobri El-Qobba",
                "Manshiet El-Sadr", "El-Demerdash", "Ghamra", "Al-Shohadaa",
                "Orabi", "Nasser", "Sadat", "Saad Zaghloul", "Al-Sayeda Zeinab",
                "El-Malek El-Saleh", "Mar Girgis", "El-Zahraa'", "Dar El-Salam",
                "Hadayek El-Maadi", "Maadi", "Sakanat El-Maadi", "Tora El-Balad",
                "Kozzika", "Tora El-Asmant", "El-Maasara", "Hadayek Helwan",
                "Wadi Hof", "Helwan University", "Ain Helwan", "Helwan",
                "Line2", "Shubra El-Kheima", "Kolleyyet El-Zeraa", "Mezallat", "Khalafawy",
                "St.Teresa", "Rod El-Farag", "Masarra", "Al-Shohadaa", "Attaba",
                "Mohamed Naguib", "Sadat", "Opera", "Dokki", "El Bohoth", "Cairo University"
                , "Faisal", "Giza", "Omm El-Masryeen", "Sakiat Mekky", "El-Mounib",
                "Line3", "Airport", "Ahmed Galal", "Adly Mansour", "El Haykestep", "Omar Ibn El-Khattab",
                "Qobaa", "Hesham Barakat", "El-Nozha", "Nadi El-Shams", "Alf Maskan", "Heliopolis Square",
                "Haroun", "Al-Ahram", "Koleyet El-Banat", "Stadium", "Fair Zone", "Abbassiya", "Abdou Pasha",
                "El-Geish", "Bab El-Shaaria", "Attaba", "Nasser", "Maspero", "Zamalek", "Kit Kat", "Sudan",
                "Imbaba", "El-Bohy", "El-Kawmeya Al-Arabiya", "Ring Road", "Rod El-Farag", "El-Tawfikeya",
                "Wadi El-Nil", "Gamaat El Dowal Al-Arabiya", "Bulaq El-Dakroor", "Cairo University");
        //step 2 bind items on spinner
        //spinner->adapter->items
        //fill items, pass items to adapter,pass adapter to spinner
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        ArrayAdapter adapterr = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, items);
        StartPoint.setAdapter(adapterr);
        Direction.setAdapter(adapterr);

        StartPoint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s.start = StartPoint.getSelectedItem().toString();
//                s.end = Direction.getSelectedItem().toString();
                EventBus.getDefault().post(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Direction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                s.start=StartPoint.getSelectedItem().toString();
                s.end=Direction.getSelectedItem().toString();
                EventBus.getDefault().post(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//      s.start=StartPoint.getSelectedItem().toString();
//      s.end=Direction.getSelectedItem().toString();

        return view;
    }

    private FragmentManager getSupportFragmentManager() {
        return null;
    }

    @Override
    public void onClick(View view) {
        String startSt = StartPoint.getSelectedItem().toString();
        String EndSt = Direction.getSelectedItem().toString();

//A
        if (EndSt.equals("Choose Station") || startSt.equals("Choose Station")
                || startSt.equals("Line1") || startSt.equals("Line2") || startSt.equals("Line3")
                || EndSt.equals("Line1") || EndSt.equals("Line2") || EndSt.equals("Line3")
                || EndSt.equals(startSt)
        ) {
            if (startSt.equals("Choose Station")) {
                tts.speak("please select correct Start&End Station", TextToSpeech.QUEUE_FLUSH, null, null);
                YoYo.with(Techniques.Shake).duration(500).repeat(1).playOn(StartPoint);
            }
            if (EndSt.equals("Choose Station")) {
                tts.speak("please select correct Start&End Station", TextToSpeech.QUEUE_FLUSH, null, null);
                YoYo.with(Techniques.Shake).duration(500).repeat(1).playOn(Direction);
                return;
            }
            YoYo.with(Techniques.Shake).duration(500).repeat(1).playOn(StartPoint);
            YoYo.with(Techniques.Shake).duration(500).repeat(1).playOn(Direction);
        } else {
            s.gotoresfragment = true;
            EventBus.getDefault().post(s);
//
        }


    }
    private void getParentFragmentManager() {

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setSpeechRate(0.7f);
            tts.setPitch(.9f);
        } else {
            Toast.makeText(getActivity(), "feature not supported", Toast.LENGTH_SHORT).show();
        }
    }

    static class Station {
        public String start, end;
        public Boolean gotoresfragment = false;
    }
}