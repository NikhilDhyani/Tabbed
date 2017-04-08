package com.example.nikhil.tabbed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by nikhil on 22/3/17.
 */



    public class OpenLink extends Fragment {


    public TextView textView;
    public Button btn;
    public EditText edit;
    public int num;
    public String number;
    // public EditText select;
  ///  public Button button;
    ArrayList<String> phoneNumbers = new ArrayList<>();
    public String urll;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.openlink, container, false);



            textView = (TextView) rootView.findViewById(R.id.tv1);
            btn = (Button) rootView.findViewById(R.id.plzopen);
//            edit = (EditText) rootView.findViewById(R.id.whichone);

     //       button = (Button) rootView.findViewById(R.id.button2);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
            ref.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Get map of users in datasnapshot
                            collectPhoneNumbers((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //handle databaseError
                        }
                    });



            btn.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                   // number = edit.getText().toString();
                   // System.out.println("fuck off");
                   // System.out.println(number);


                    Random randomno = new Random();//Integer.parseInt(number);
//
                    int number= randomno.nextInt(5);
                    num = number;
                   urll = phoneNumbers.get(num).toString();
                    textView.setText(urll);
//   System.out.println(num);
                    OpenThisLink(urll);
                }
            });


                    return rootView;
        }

    private void collectPhoneNumbers(Map<String,Object> users) {

        //ArrayList<String> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add((String) singleUser.get("link"));
        }




     //   urll = phoneNumbers.get(num).toString();
       // textView.setText(urll);

       // System.out.println(phoneNumbers.toString());

        //System.out.println(urll);
        //System.out.println("BULL SHIT");

//        System.out.println(num);
    }






    public void OpenThisLink(String url)
    {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);

        startActivity(intent);
    }




}





