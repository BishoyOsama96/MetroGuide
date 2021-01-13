package com.bishoyosama.metroguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ResultsFragment extends Fragment {
    private byte price;
    byte u, v;
    int R1,R2,nostation,extime,wtime=4;
    byte t = 0;
    public ArrayList<String> line1=new ArrayList<>(Arrays.asList(
            "New El-Marg","El-Marg","Ezbet El-Nakhl","Ain Shams",
            "El-Matareyya","Helmeyet El-Zaitoun","Hadayeq El-Zaitoun"
            ,"Saray El-Qobba","Hammamat ElQobba","Kobri El-Qobba",
            "Manshiet El-Sadr","El-Demerdash","Ghamra","Al-Shohadaa",
            "Orabi","Nasser","Sadat","Saad Zaghloul","Al-Sayeda Zeinab",
            "El-Malek El-Saleh","Mar Girgis","El-Zahraa'","Dar El-Salam",
            "Hadayek El-Maadi","Maadi","Sakanat El-Maadi","Tora El-Balad",
            "Kozzika","Tora El-Asmant","El-Maasara","Hadayek Helwan",
            "Wadi Hof","Helwan University","Ain Helwan","Helwan"));
    public ArrayList<String> line2=new ArrayList<>(Arrays.asList(
            "Shubra El-Kheima","Kolleyyet El-Zeraa","Mezallat","Khalafawy",
            "St.Teresa","Rod El-Farag","Masarra","Al-Shohadaa","Attaba",
            "Mohamed Naguib","Sadat","Opera","Dokki","El Bohoth","Cairo University"
            ,"Faisal","Giza","Omm El-Masryeen","Sakiat Mekky","El-Mounib"));
    public ArrayList<String> line3=new ArrayList<>(Arrays.asList("Airport","Ahmed Galal",
            "Adly Mansour","El Haykestep","Omar Ibn El-Khattab", "Qobaa","Hesham Barakat",
            "El-Nozha","Nadi El-Shams","Alf Maskan","Heliopolis Square", "Haroun","Al-Ahram",
            "Koleyet El-Banat", "Stadium","Fair Zone","Abbassiya", "Abdou Pasha","El-Geish",
            "Bab El-Shaaria","Attaba","Nasser","Maspero", "Zamalek", "Kit Kat","Sudan","Imbaba",
            "El-Bohy","El-Kawmeya Al-Arabiya","Ring Road","Rod El-Farag","El-Tawfikeya",
            "Wadi El-Nil","Gamaat El Dowal Al-Arabiya","Bulaq El-Dakroor","Cairo University"));
    TextView Route,StationCount,SNtv,TTtv,TimeView,TPtv,TicketPrice;
    String s,d;


    public ResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_results, container, false);
        Route=view.findViewById(R.id.Route);
        Route.setMovementMethod(new ScrollingMovementMethod());
        StationCount=view.findViewById(R.id.StationCount);
        SNtv=view.findViewById(R.id.SNtv);
        TTtv=view.findViewById(R.id.TTtv);
        TimeView=view.findViewById(R.id.TimeView);
        TPtv=view.findViewById(R.id.TPtv);
        TicketPrice=view.findViewById(R.id.TicketPrice);


        if(getActivity() instanceof MainActivity){
            if (getArguments()!=null){
            s=getArguments().getString("start");

            d=getArguments().getString("end");
            sent(s,d);
            }
        }



        // Inflate the layout for this fragment
        return view;
    }


    public  byte ticket(int Count){
        if (Count >= 1 && Count <= 9) {
            this.price= 5;
        } else if (Count > 9 && Count <= 16) {
            this.price = 7;}else if (Count > 16 && Count <= 39) {
            this.price = 10;}
        return this.price;
    }

    public void sent(String start,String destination){

        if(line1.contains(start) && line1.contains(destination))
        {
            u= (byte) line1.indexOf(start);
            v= (byte) line1.indexOf(destination);
            nostation= ((Math.max(u,v))-(Math.min(u,v)));
            t=ticket(nostation);
            extime= wtime*(nostation);
            // System.out.println("your path contains of "+nostation+" Stations");
            StationCount.setText(""+(nostation));
            TicketPrice.setText(""+t+" Egp");
            //  System.out.println("expected time is "+extime+"m");
            TimeView.setText(""+extime+" min");
            if(u>v){
                List rev = new ArrayList(line1.subList(Math.min(u,v),Math.max(u,v)+1));
                Collections.reverse(rev);
                // System.out.println("you should walk in the direction of El-Marg");
                Route.append("You should walk in the direction of El-Marg \n");
                // System.out.println("your rout is "+rev);
                Route.append("\nyour rout is:\n"+rev);
            }

            else {
                // System.out.println("you should walk in the direction of Helwan");
                Route.append("you should walk in the direction of Helwan \n");
                // System.out.println("your rout is "+line1.subList(Math.min(u,v),Math.max(u,v)+1));
                Route.append("\nyour rout is:\n "+line1.subList(Math.min(u,v),Math.max(u,v)+1).toString());

            }
            //    System.out.println("your ticket will cost: "+t+" pounds");

        }
        if(line2.contains(start) && line2.contains(destination))
        {
            u= (byte) line2.indexOf(start);
            v= (byte) line2.indexOf(destination);
            nostation= ((Math.max(u,v)+1)-(Math.min(u,v)));
            t=ticket(nostation);
            extime= wtime*(nostation-1);
            StationCount.setText(""+(nostation-1));
//                System.out.println("your path contains of "+nostation+" Stations");
            if(u>v){
                List rev = new ArrayList(line2.subList(Math.min(u,v),Math.max(u,v)+1));
                Collections.reverse(rev);
                Route.setText("You should walk in the direction of Shubra El-Kheima\n");
                //System.out.println("your rout is "+rev);
                Route.append("\nYour Route is\n" + rev);
            }
            else{  Route.setText("you should walk in the direction of El-Mounib\n");
                Route.append("\nyour route is\n " + line2.subList(Math.min(u, v), Math.max(u, v) + 1));

            }
            TicketPrice.setText(""+t+" Egp");
            TimeView.setText(""+extime+" min");
        }
        if(line3.contains(start) && line3.contains(destination))
        {
            u= (byte) line3.indexOf(start);
            v= (byte) line3.indexOf(destination);
            nostation= ((Math.max(u,v)+1)-(Math.min(u,v)));
            t=ticket(nostation);
            extime= wtime*(nostation-1);
            StationCount.setText(""+(nostation-1));
            if(u>v){
                List rev = new ArrayList(line3.subList(Math.min(u,v),Math.max(u,v)+1));
                Collections.reverse(rev);
                Route.setText("You should walk in the direction of Airport\n");
                Route.append("\nYour Route is\n" + rev);
            }
            else { Route.setText("you should walk in the direction of Cairo University\n");
                Route.append("\nYour Route is\n" + line3.subList(Math.min(u, v), Math.max(u, v) + 1));
            }
            TicketPrice.setText(""+t+" Egp");
            TimeView.setText(""+extime+" min");
        }
        // to check overlapping
        //Sadat ‫السادات‬ Line 1 & Line2
        //Al-Shohadaa ‫الشهداء‬ Line 1 & Line2
        // from line 1 to line 2
        if (line1.contains(start) && line2.contains(destination)){
            if(line1.contains(destination)){return;}else {
                Route.append("your possible paths are :");
                Route.append("\n---------within Al-Shohadaa-------");
                u = (byte) line1.indexOf(start);
                v = (byte) line2.indexOf(destination);
                if (u > line1.indexOf("Al-Shohadaa")) {
                    Route.append("\n you should walk in the direction of El-Marg and Switch in Al-shohadaa Station");
                    List rev = new ArrayList(line1.subList(Math.min(u, line1.indexOf("Al-Shohadaa")),
                            Math.max(u + 1, line1.indexOf("Al-Shohadaa"))));
                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 1 is  " + rev);

                } else {
                    Route.append("\n you should walk in the direction of Helwan and switch in Al-shohadaa Station");
                    Route.append("\nyour rout in line 1 is  " +
                            line1.subList(Math.min(u, line1.indexOf("Al-Shohadaa")),
                                    Math.max(u, line1.indexOf("Al-Shohadaa") + 1))
                    );

                }
                if (line2.indexOf("Al-Shohadaa") > v) {
                    Route.append("\n then take the direction of El-Mounib  ");
                    List rev = new ArrayList(line2.subList(
                            Math.min(v, line2.indexOf("Al-Shohadaa")),
                            Math.max(v, line2.indexOf("Al-Shohadaa"))
                    ));
                    Collections.reverse(rev);
                    Route.append("\n your rout in line 2 is  " + rev);
                } else {
                    Route.append("\n then take the direction of Shubra El-Kheima");
                    Route.append("\n your route in line 2 is  " +
                            line2.subList(Math.min(v, line2.indexOf("Al-Shohadaa") + 1),
                                    Math.max(v + 1, line2.indexOf("Al-Shohadaa"))
                            ));
                }
                nostation = (
                        (Math.max(u, line1.indexOf("Al-Shohadaa")) + 1) - Math.min(u, line1.indexOf("Al-Shohadaa"))
                                + Math.max(v, line2.indexOf("Al-Shohadaa")) - Math.min(v, line2.indexOf("Al-Shohadaa"))
                );
                //Rout1 done need to add direction Plus the Shortest
                R1 = nostation;
                t = ticket(nostation);
                extime = wtime * (nostation - 1);
                StationCount.setText("" + (nostation - 1));
                TicketPrice.setText("" + t + " Egp");
                TimeView.setText("" + extime + " min");
                //________________________________________________________________________________
                // print the route of El-sadat
                Route.append("\n-----------within Sadat------------");
                u = (byte) line1.indexOf(start);
                v = (byte) line2.indexOf(destination);
                if (u > line1.indexOf("Sadat")) {
                    Route.append("\nyou should walk in the direction of El-Marg Helwan and Switch in sadat Station");
                    List rev = new ArrayList(line1.subList(Math.min(u, line1.indexOf("Sadat")),
                            Math.max(u + 1, line1.indexOf("Sadat"))));
                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 1 is  " + rev);

                } else {
                    Route.append("\nyou should walk in the direction of Helwan and Switch in sadat Station");

                    Route.append("\nyour rout in line 1 is  " +
                            line1.subList(Math.min(u, line1.indexOf("Sadat")),
                                    Math.max(u, line1.indexOf("Sadat") + 1))
                    );
                }
                if (line2.indexOf("Sadat") > v) {
                    Route.append("\nthen take the direction of El-Mounib");
                    List rev = new ArrayList(line2.subList(
                            Math.min(v, line2.indexOf("Sadat")),
                            Math.max(v, line2.indexOf("Sadat"))
                    ));
                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 2 is  " + rev);
                } else {
                    Route.append("\nthen take the direction of Shubra El-Kheima");
                    Route.append("\nyour route in line 2 is  " +
                            line2.subList(Math.min(v, line2.indexOf("Sadat") + 1),
                                    Math.max(v + 1, line2.indexOf("Sadat"))
                            ));
                }
                nostation = (
                        (Math.max(u, line1.indexOf("Sadat")) + 1) - Math.min(u, line1.indexOf("Sadat"))
                                + Math.max(v, line2.indexOf("Sadat")) - Math.min(v, line2.indexOf("Sadat"))
                );
                R2 = nostation;
                t = ticket(nostation);
                extime = wtime * (nostation - 1);
                StationCount.setText("" + (nostation - 1));
                TicketPrice.setText("" + t + " Egp");
                TimeView.setText("" + extime + " min");
                //To Get the Shortest Path
                if (R1 < R2){ Route.append("The Shortest Path is Route1");
                    StationCount.setText("" + (R1 - 1));
                    TicketPrice.setText("" + ticket(R1) + " Egp");
                    TimeView.setText("" + wtime * (R1 - 1) + " min");}
                else if (R1 < R2) {Route.append("The Shortest Path is Route2");
                    StationCount.setText("" + (R2 - 1));
                    TicketPrice.setText("" + ticket(R2) + " Egp");
                    TimeView.setText("" + wtime * (R2 - 1) + " min");}

                else Route.append("\nThe Two Route are Equals");
            }
        }
        // reverse it
        if (line2.contains(start) && line1.contains(destination)){
            if(line2.contains(destination)){return;}else {
                Route.append("your possible paths are :");
                Route.append("\n--------------------within Al-Shohadaa--------------------");
                u = (byte) line2.indexOf(start);
                v = (byte) line1.indexOf(destination);
                if (u > line2.indexOf("Al-Shohadaa")) {
                    Route.append("\nyou should walk in the direction of El-Mounib  and Switch in Al-Shohadaa Station");

                    List rev = new ArrayList(line2.subList(Math.min(u, line2.indexOf("Al-Shohadaa")),
                            Math.max(u + 1, line2.indexOf("Al-Shohadaa"))));

                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 2 is  " + rev);
                } else {
                    Route.append("\nyou should walk in the direction of Shubra El-Kheima and Switch in Al-Shohadaa Station");

                    Route.append("\nyour rout in line 2 is  " +
                            line2.subList(Math.min(u, line2.indexOf("Al-Shohadaa")),
                                    Math.max(u, line2.indexOf("Al-Shohadaa") + 1))
                    );
                }
                if (line1.indexOf("Al-Shohadaa") > v) {
                    Route.append("\nthen take the direction of El-Marg");
                    List rev = new ArrayList(line1.subList(
                            Math.min(v, line1.indexOf("Al-Shohadaa")),
                            Math.max(v, line1.indexOf("Al-Shohadaa"))
                    ));

                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 1 is  " + rev);
                } else {
                    Route.append("\nthen take the direction of Helwan");
                    Route.append("\nyour route in line 1 is  " +
                            line1.subList(Math.min(v, line1.indexOf("Al-Shohadaa") + 1),
                                    Math.max(v + 1, line1.indexOf("Al-Shohadaa"))
                            ));
                }
                nostation = (
                        (Math.max(u, line2.indexOf("Al-Shohadaa")) + 1) - Math.min(u, line2.indexOf("Al-Shohadaa"))
                                + Math.max(v, line1.indexOf("Al-Shohadaa")) - Math.min(v, line1.indexOf("Al-Shohadaa"))
                );
                R1 = nostation;
                t = ticket(nostation);
                extime = wtime * (nostation - 1);
                StationCount.setText("" + (nostation - 1));
                TicketPrice.setText("" + t + " Egp");
                TimeView.setText("" + extime + " min");
                //________________________________________________________________________________
                // print the route of El-sadat
                Route.append("\n------------within Sadat----------");
                u = (byte) line2.indexOf(start);
                v = (byte) line1.indexOf(destination);
                if (u > line2.indexOf("Sadat")) {
                    Route.append("\nyou should walk in the direction of  El-Mounib and Switch in Sadat Station");

                    List rev = new ArrayList(line2.subList(Math.min(u, line2.indexOf("Sadat")),
                            Math.max(u + 1, line2.indexOf("Sadat"))));

                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 2 is  " + rev);
                } else {
                    Route.append("\nyou should walk in the direction of Shubra El-Kheima and Switch in Sadat Station");

                    Route.append("\nyour rout in line 2 is  " +
                            line2.subList(Math.min(u, line2.indexOf("Sadat")),
                                    Math.max(u, line2.indexOf("Sadat") + 1))
                    );
                }
                if (line1.indexOf("Sadat") > v) {
                    Route.append("\nthen take the direction of El-Marg");
                    List rev = new ArrayList(line1.subList(
                            Math.min(v, line1.indexOf("Sadat")),
                            Math.max(v, line1.indexOf("Sadat"))
                    ));

                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 1 is  " + rev);
                } else {
                    Route.append("\n then take the direction of Helwan");
                    Route.append("\nyour route in line 1 is  " +
                            line1.subList(Math.min(v, line1.indexOf("Sadat") + 1),
                                    Math.max(v + 1, line1.indexOf("Sadat"))
                            ));
                }
                nostation = (
                        (Math.max(u, line2.indexOf("Sadat")) + 1) - Math.min(u, line2.indexOf("Sadat"))
                                + Math.max(v, line1.indexOf("Sadat")) - Math.min(v, line1.indexOf("Sadat"))
                );
                R2 = nostation;
                t = ticket(nostation);
                extime = wtime * (nostation - 1);
                StationCount.setText("" + (nostation - 1));
                TicketPrice.setText("" + t + " Egp");
                TimeView.setText("" + extime + " min");
                if (R1 < R2){ Route.append("The Shortest Path is Route1");
                    StationCount.setText("" + (R1 - 1));
                    TicketPrice.setText("" + ticket(R1) + " Egp");
                    TimeView.setText("" + wtime * (R1 - 1) + " min");}
                else if (R1 < R2) {Route.append("The Shortest Path is Route2");
                    StationCount.setText("" + (R2 - 1));
                    TicketPrice.setText("" + ticket(R2) + " Egp");
                    TimeView.setText("" + wtime * (R2 - 1) + " min");}

                else Route.append("\nThe Two Route are Equals");
            }
        }
        //Nasser ‫عبدالناصر‬ ‫جمال‬ Line 1 & Line3
        if (line1.contains(start) && line3.contains(destination)){
            if(line1.contains(destination)){return;}else {
                Route.append("your possible paths are :");
                Route.append("\n--------------within Nasser-----------");
                u = (byte) line1.indexOf(start);
                v = (byte) line3.indexOf(destination);
                if (u > line1.indexOf("Nasser")) {
                    Route.append("\n you should walk in the direction of El marg");

                    List rev = new ArrayList(line1.subList(Math.min(u, line1.indexOf("Nasser")),
                            Math.max(u, line1.indexOf("Nasser"))));

                    Collections.reverse(rev);
                    Route.append("\n your rout in line 1 is  " + rev);
                } else {
                    Route.append("\n you should walk in the direction of Helwan");

                    Route.append("\n your rout in line 1 is  " +
                            line1.subList(Math.min(u, line1.indexOf("Nasser")),
                                    Math.max(u, line1.indexOf("Nasser")))
                    );
                }
                if (line3.indexOf("Nasser") > v) {
                    Route.append("\n then take the direction of Air port");
                    List rev = new ArrayList(line3.subList(
                            Math.min(v, line3.indexOf("Nasser")),
                            Math.max(v, line3.indexOf("Nasser"))
                    ));
                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 3 is  " + rev);
                } else {
                    Route.append("\n then take the direction of Cairo University");
                    Route.append("\nyour route in line 3 is  " +
                            line3.subList(Math.min(v, line3.indexOf("Nasser")),
                                    Math.max(v, line3.indexOf("Nasser"))
                            ));
                }
                nostation = (
                        (Math.max(u, line1.indexOf("Nasser")) + 1) - Math.min(u, line1.indexOf("Nasser"))
                                + Math.max(v, line3.indexOf("Nasser")) - Math.min(v, line3.indexOf("Nasser"))
                );
                t = ticket(nostation);
                extime = wtime * (nostation - 1);
                StationCount.setText("" + (nostation - 1));
                TicketPrice.setText("" + t + " Egp");
                TimeView.setText("" + extime + " min");
            }
        }
        // reverse
        if (line3.contains(start) && line1.contains(destination)){
            if(line1.contains(start)){return;}else {
                Route.append("your possible paths are :");
                Route.append("\n-------------within Nasser------------");
                u = (byte) line3.indexOf(start);
                v = (byte) line1.indexOf(destination);
                if (u > line3.indexOf("Nasser")) {
                    Route.append("\n you should walk in the direction of Air port ");

                    List rev = new ArrayList(line3.subList(Math.min(u, line3.indexOf("Nasser")),
                            Math.max(u, line3.indexOf("Nasser"))));

                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 3 is  " + rev);
                } else {
                    Route.append("\n you should walk in the direction of Cairo University ");

                    Route.append("\nyour rout in line 3 is  " +
                            line3.subList(Math.min(u, line3.indexOf("Nasser")),
                                    Math.max(u, line3.indexOf("Nasser")))
                    );
                }
                if (line1.indexOf("Nasser") > v) {
                    Route.append("\nthen take the direction of El marg");
                    List rev = new ArrayList(line1.subList(
                            Math.min(v, line1.indexOf("Nasser")),
                            Math.max(v, line1.indexOf("Nasser"))
                    ));

                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 1 is  " + rev);
                } else {
                    Route.append("\nthen take the direction of Helwan");
                    Route.append("\nyour route in line 1 is  " +
                            line1.subList(Math.min(v, line1.indexOf("Nasser")),
                                    Math.max(v, line1.indexOf("Nasser"))
                            ));
                }
                nostation = (
                        (Math.max(u, line3.indexOf("Nasser")) + 1) - Math.min(u, line3.indexOf("Nasser"))
                                + Math.max(v, line1.indexOf("Nasser")) - Math.min(v, line1.indexOf("Nasser"))
                );
                t = ticket(nostation);
                extime = wtime * (nostation - 1);
                StationCount.setText("" + (nostation - 1));
                TicketPrice.setText("" + t + " Pounds");
                TimeView.setText("" + extime + " minutes");
            }

        }
/*
Attaba ‫العتبة‬ Line 2 & Line3
Cairo University ‫القاهرة‬ ‫جامعة‬ Line 2 & Line 3
 */
        if (line2.contains(start) && line3.contains(destination)) {
            if(line3.contains(start)){return;}else {
                Route.append("your possible paths are :");
                u = (byte) line2.indexOf(start);
                v = (byte) line3.indexOf(destination);
                Route.append("\n------------within Attaba-----------");
                if (u > line2.indexOf("Attaba")) {
                    Route.append("\nyou should walk in the direction of Shubra El-Kheima and switch in Attaba station");
                    List rev = new ArrayList(line2.subList(Math.min(u, line2.indexOf("Attaba")),
                            Math.max(u, line2.indexOf("Attaba"))+1));

                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 2 is  " + rev);
                } else {
                    Route.append("\nyou should walk in the direction of El-Mounib and switch in Attaba station");
                    Route.append("\nyour rout in line 2 is  " +
                            line2.subList(Math.min(u, line2.indexOf("Attaba")),
                                    Math.max(u, line2.indexOf("Attaba"))+1)
                    );
                }
                if (line3.indexOf("\nAttaba") > v) {
                    Route.append("\nthen you walk in the direction of Cairo University ");
                    List rev = new ArrayList(line3.subList(
                            Math.min(v, line3.indexOf("Attaba")),
                            Math.max(v, line3.indexOf("Attaba"))+1
                    ));
                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 3 is  " + rev);
                } else {
                    Route.append("\nthen you walk in the direction of Air port");
                    Route.append("\nyour route in line 3 is  " +
                            line3.subList(Math.min(v, line3.indexOf("Attaba")),
                                    Math.max(v, line3.indexOf("Attaba"))+1
                            ));
                }
                nostation = (
                        (Math.max(u, line2.indexOf("Attaba")) + 1) - Math.min(u, line2.indexOf("Attaba"))
                                + Math.max(v, line3.indexOf("Attaba")) - Math.min(v, line3.indexOf("Attaba"))
                );
                R1 = nostation;
                t = ticket(nostation);
                extime = wtime * (nostation - 1);
                StationCount.setText("" + (nostation - 1));
                TicketPrice.setText("" + t + " Egp");
                TimeView.setText("" + extime + " min");
                //________________________________________________________________________________
                // print the route of El-sadat
                Route.append("\n-----------within Cairo University-------------");
                if (u > line2.indexOf("Cairo University")) {
                    Route.append("\nyou should walk in the direction of Shubra El-Kheima and switch in Cairo University station");
                    List rev = new ArrayList(line2.subList(Math.min(u, line2.indexOf("Cairo University")),
                            Math.max(u, line2.indexOf("Cairo University"))+1));

                    Collections.reverse(rev);
                    Route.append("\n your rout in line 2 is  " + rev);
                } else {
                    Route.append("\nyou should walk in the direction of El-Mounib and switch in Cairo University station");
                    Route.append("\nyour rout in line 2 is  " +
                            line2.subList(Math.min(u, line2.indexOf("Cairo University")),
                                    Math.max(u, line2.indexOf("Cairo University"))+1)
                    );
                }
                if (line3.indexOf("Cairo University") > v) {
                    Route.append("\nthen walk  in the direction of Air port");
                    List rev = new ArrayList(line3.subList(
                            Math.min(v, line3.indexOf("Cairo University")),
                            Math.max(v, line3.indexOf("Cairo University"))
                    ));
                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 3 is  " + rev);
                } else {
                    Route.append("\nthen walk  in the direction of Air port");
                    Route.append("\nyour route in line 3 is  " +
                            line3.subList(Math.min(v, line3.indexOf("Cairo University")),
                                    Math.max(v, line3.indexOf("Cairo University"))
                            ));
                }
                nostation = (
                        (Math.max(u, line2.indexOf("Cairo University")) + 1) - Math.min(u, line2.indexOf("Cairo University"))
                                + Math.max(v, line3.indexOf("Cairo University")) - Math.min(v, line3.indexOf("Cairo University"))
                );
                R2 = nostation;
                t = ticket(nostation);
                extime = wtime * (nostation - 1);
                StationCount.setText("" + (nostation - 1));
                TicketPrice.setText("" + t + " Egp");
                TimeView.setText("" + extime + " min");
                if (R1 < R2){ Route.append("The Shortest Path is Route1");
                    StationCount.setText("" + (R1 - 1));
                    TicketPrice.setText("" + ticket(R1) + " Egp");
                    TimeView.setText("" + wtime * (R1 - 1) + " min");}
                else if (R1 < R2) {Route.append("The Shortest Path is Route2");
                    StationCount.setText("" + (R2 - 1));
                    TicketPrice.setText("" + ticket(R2) + " Egp");
                    TimeView.setText("" + wtime * (R2 - 1) + " min");}

                else Route.append("\nThe Two Route are Equals");
            }
        }
        // reverse
        if (line3.contains(start) && line2.contains(destination)) {
            if(line2.contains(start)){return;}else{
                Route.append("your possible paths are :");
                u = (byte) line3.indexOf(start);
                v = (byte) line2.indexOf(destination);
                Route.append("\n---------within Attaba------------");
                if (u > line3.indexOf("Attaba")) {
                    Route.append("\nyou should walk in the direction of Shubra El-Kheima");

                    List rev = new ArrayList(line3.subList(Math.min(u, line3.indexOf("Attaba")),
                            Math.max(u, line3.indexOf("Attaba"))));

                    Collections.reverse(rev);
                    Route.append("\n your rout in line 3 is  " + rev);
                } else {
                    Route.append("\n you should walk in the direction of El-Mounib");

                    Route.append("\n your rout in line 3 is  " +
                            line3.subList(Math.min(u, line3.indexOf("Attaba")),
                                    Math.max(u, line3.indexOf("Attaba")))
                    );
                }

                if (line2.indexOf("Attaba") > v) {
                    Route.append("\n you should walk in the direction of Shubra El-Kheima");
                    List rev = new ArrayList(line2.subList(
                            Math.min(v, line2.indexOf("Attaba")),
                            Math.max(v, line2.indexOf("Attaba"))
                    ));

                    Collections.reverse(rev);
                    Route.append("\n your rout in line 2 is  " + rev);
                } else {
                    Route.append("\n you should walk in the direction of El-Mounib");
                    Route.append("\n your route in line 2 is  " +
                            line2.subList(Math.min(v, line2.indexOf("Attaba")),
                                    Math.max(v, line2.indexOf("Attaba"))
                            ));
                }


                nostation = (
                        (Math.max(u, line3.indexOf("Attaba")) + 1) - Math.min(u, line3.indexOf("Attaba"))
                                + Math.max(v, line2.indexOf("Attaba")) - Math.min(v, line2.indexOf("Attaba"))
                );

                R1 = nostation;
                t = ticket(nostation);
                extime = wtime * (nostation - 1);
                StationCount.setText("" + (nostation - 1));
                TicketPrice.setText("" + t + " Egp");
                TimeView.setText("" + extime + " min");
                //________________________________________________________________________________
                // print the route of El-sadat
                Route.append("\n------------within Cairo University-----------");
                if (u > line3.indexOf("Cairo University")) {
                    Route.append("\nyou should walk in the direction of Shubra El-Kheima");

                    List rev = new ArrayList(line3.subList(Math.min(u, line3.indexOf("Cairo University")),
                            Math.max(u, line3.indexOf("Cairo University"))));

                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 3 is  " + rev);
                } else {
                    Route.append("\nyou should walk in the direction of El-Mounib");

                    Route.append("\nyour rout in line 3 is  " +
                            line3.subList(Math.min(u, line3.indexOf("Cairo University")),
                                    Math.max(u, line3.indexOf("Cairo University")))
                    );
                }

                if (line2.indexOf("Cairo University") > v) {
                    Route.append("\nyou should walk in the direction of Shubra El-Kheima");
                    List rev = new ArrayList(line2.subList(
                            Math.min(v, line2.indexOf("Cairo University")),
                            Math.max(v, line2.indexOf("Cairo University"))
                    ));

                    Collections.reverse(rev);
                    Route.append("\nyour rout in line 2 is  " + rev);
                } else {
                    Route.append("\n then take the direction of El-Mounib");
                    Route.append("\nyour route in line 2 is  " +
                            line2.subList(Math.min(v, line2.indexOf("Cairo University")),
                                    Math.max(v, line2.indexOf("Cairo University"))
                            ));
                }


                nostation = (
                        (Math.max(u, line3.indexOf("Cairo University")) + 1) - Math.min(u, line3.indexOf("Cairo University"))
                                + Math.max(v, line2.indexOf("Cairo University")) - Math.min(v, line2.indexOf("Cairo University"))
                );
                R2 = nostation;
                t = ticket(nostation);
                extime = wtime * (nostation - 1);
                StationCount.setText("" + (nostation - 1));
                TicketPrice.setText("" + t + " Egp");
                TimeView.setText("" + extime + " min");
                if (R1 < R2){ Route.append("The Shortest Path is Route1");
                    StationCount.setText("" + (R1 - 1));
                    TicketPrice.setText("" + ticket(R1) + " Egp");
                    TimeView.setText("" + wtime * (R1 - 1) + " min");}
                else if (R1 < R2) {Route.append("The Shortest Path is Route2");
                    StationCount.setText("" + (R2 - 1));
                    TicketPrice.setText("" + ticket(R2) + " Egp");
                    TimeView.setText("" + wtime * (R2 - 1) + " min");}

                else Route.append("\nThe Two Route are Equals");
            }

        }


    }
}