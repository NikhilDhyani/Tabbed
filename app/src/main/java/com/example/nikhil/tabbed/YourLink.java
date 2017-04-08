package com.example.nikhil.tabbed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.name;

/**
 * Created by nikhil on 22/3/17.
 */



    public class YourLink extends Fragment {


    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView linkDetail;
    private EditText inputLink;
    private Button push;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    String uid;
    String email;
    // user = firebase.auth().currentUser;

    FirebaseUser userss = FirebaseAuth.getInstance().getCurrentUser();






    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.yourlink, container, false);
        // return rootView;

        linkDetail = (TextView) rootView.findViewById(R.id.linkView);
        inputLink = (EditText) rootView.findViewById(R.id.editText);

        push = (Button) rootView.findViewById(R.id.post);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("link").setValue("Demo Link");

        // app_title change listener
       /* mFirebaseInstance.getReference("link").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String changedLink = dataSnapshot.getValue(String.class);
                String x = changedLink.toString();
                // update toolbar title
                updatedLink.setText(x);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        mFirebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                User changedLink = dataSnapshot.getValue(User.class);
               String x = changedLink.toString();
                //updatedLink.setText(x);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


            push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String LinkObtained = inputLink.getText().toString();
                linkDetail.setText(LinkObtained);

                //NEW CODE ADDED


                if (userss != null) {
                    // Name, email address, and profile photo Url
                    String name = userss.getDisplayName();
                    email = userss.getEmail();
                    //Uri photoUrl = userss.getPhotoUrl();

                    // Check if user's email is verified
                 //   boolean emailVerified = user.isEmailVerified();

                    // The user's ID, unique to the Firebase project. Do NOT use this value to
                    // authenticate with your backend server, if you have one. Use
                    // FirebaseUser.getToken() instead.
                     uid = userss.getUid();
                    updateUser(LinkObtained,uid,email);
                }
                //NEW CODE ENDED


                userId = mFirebaseDatabase.push().getKey();

                if (TextUtils.isEmpty(userId)) {
                    createUser(LinkObtained);
                }
                else {
                    updateUser(LinkObtained,uid,email);
                }

            }
        });

        return  rootView;
    }
            private void createUser(String link) {
                // TODO
                // In real apps this userId should be fetched
                // by implementing firebase auth
                if (TextUtils.isEmpty(userId)) {
                    userId = mFirebaseDatabase.push().getKey();
                }

                User user = new User(link,"ehllo",email);

                mFirebaseDatabase.child(userId).setValue(user);


            }
            private void updateUser(String link,String id,String email) {
                // updating the user via child nodes
               // if (!TextUtils.isEmpty(link))
                 //   mFirebaseDatabase.child(userId).child("name").setValue(name);

                //if (!TextUtils.isEmpty(link))
               userId = id;
                User user = new User(link,"ehllSSo",email);

                mFirebaseDatabase.child(userId).setValue(user);
                 //   mFirebaseDatabase.child(userId).child("link").setValue(link);
            }










            // Save / update the user



         //   return rootView;







}


