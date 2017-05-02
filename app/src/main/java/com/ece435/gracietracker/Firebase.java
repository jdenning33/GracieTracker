package com.ece435.gracietracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by jdenn on 4/30/2017.
 */

public class Firebase {

    //Authentication
    private static FirebaseAuth mAuth;
    private static FirebaseAuth.AuthStateListener mAuthListener;

    //Database
    private static FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference mDatabaseReference;
    private static DatabaseReference mUserReference;

    //General
    private static AppCompatActivity mGracieAuthListener;
    private static OnFireBaseInteractionListenerDB mGracieListenerDB;
    public static FirebaseUser getFirebaseUser(){
        return mAuth.getCurrentUser();
    }
    public static GracieUser GRACIEUSER;

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFireBaseInteractionListenerAuth {
        void onSignInResult(boolean success);
        void onSignUpResult(boolean success);
        void onFirebaseAuthWithGoogleResult(boolean success);
//        void onInitialGracieUserUpdate(GracieUser gracieUser);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFireBaseInteractionListenerDB {
        void onInitialGracieUserUpdate();
        void reinitializeDatabase();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Gracie Object
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static GracieUser getGracieUser(){
        return GRACIEUSER;
    }
    public static void commitGracieUserToDB(){
        Firebase.commitUserToDB(GRACIEUSER);
    }
    public static void resetCurrentUser(){
        GRACIEUSER = new GracieUser();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Initialize Firebase Auth and DB
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void initializeAuth(AppCompatActivity context){
        if (context instanceof OnFireBaseInteractionListenerAuth) {
            mGracieAuthListener = context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        //Auth
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    // GracieUser is signed in
                    Log.d("Login", "onAuthStateChanged:signed:_in" + user.getUid());
                } else {
                    // GracieUser is signed out
                    Log.d("Login", "onAuthStateChanged:signed_out");
                }
            }
        };
    }
    public static void initializeDB(final OnFireBaseInteractionListenerDB mListenerDB){
        //DB
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mUserReference = mDatabaseReference.child("users").child(getFirebaseUser().getUid());

        mGracieListenerDB = mListenerDB;

        GRACIEUSER = new GracieUser();
        GRACIEUSER.uid = getFirebaseUser().getUid();
        GRACIEUSER.email = getFirebaseUser().getEmail();

        ValueEventListener initialUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GracieUser gracieUser = dataSnapshot.getValue(GracieUser.class);
                if(gracieUser != null){
                    GRACIEUSER.update(gracieUser);
                    mListenerDB.onInitialGracieUserUpdate();
                }
                else {
                    gracieUser = new GracieUser();
                    gracieUser.uid = getFirebaseUser().getUid();
                    gracieUser.email = getFirebaseUser().getEmail();
                    GRACIEUSER.update(gracieUser);
                    mListenerDB.onInitialGracieUserUpdate();
                    Log.e("Firebase", "null user snapshot");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Firebase", "loadUser:onCancelled", databaseError.toException());
            }
        };

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                GracieUser gracieUser = dataSnapshot.getValue(GracieUser.class);
                if(gracieUser != null){
                    GRACIEUSER.update(gracieUser);
                }
                else { Log.e("Firebase", "null user snapshot"); }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Firebase", "loadUser:onCancelled", databaseError.toException());
            }
        };
        mUserReference.addListenerForSingleValueEvent(initialUserListener);
        mUserReference.addValueEventListener(userListener);
    }
    // Control Firebase auth lifecycle
    public static void onStart() {
        mAuth.addAuthStateListener(mAuthListener);
    }
    public static void onStop(){
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Email Sign In
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void emailSignIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mGracieAuthListener, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Login", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            ((OnFireBaseInteractionListenerAuth) mGracieAuthListener).onSignInResult(true);
                        } else {
                            ((OnFireBaseInteractionListenerAuth) mGracieAuthListener).onSignInResult(false);
                        }
                    }
                });
    }
    public static void emailSignUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mGracieAuthListener, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Login", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            ((OnFireBaseInteractionListenerAuth) mGracieAuthListener).onSignUpResult(true);
                        } else {
                            ((OnFireBaseInteractionListenerAuth) mGracieAuthListener).onSignUpResult(false);
                        }
                    }
                });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Google Sign In
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void authWithGoogle(AuthCredential credential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mGracieAuthListener, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            ((OnFireBaseInteractionListenerAuth) mGracieAuthListener).onFirebaseAuthWithGoogleResult(true);
                        } else {
                            ((OnFireBaseInteractionListenerAuth) mGracieAuthListener).onFirebaseAuthWithGoogleResult(false);
                        }
                    }
                });

    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Update User
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void commitUserToDB(GracieUser user){
        if(getFirebaseUser() == null || user == null) {
            FirebaseAuth.getInstance().signOut();
            GRACIEUSER.update(new GracieUser());
        }

        if(user.uid != getFirebaseUser().getUid()) {
            Log.e("FuckMe", "Well at least I didn't fuck anyones data up");
            mGracieListenerDB.reinitializeDatabase();
            return;
        }

        if(user.preferredName == "Change"){ user.preferredName = getFirebaseUser().getDisplayName(); }
        if(user.email == "Change"){ user.email = getFirebaseUser().getEmail(); }
        if(user.uid == "Change"){ user.uid = getFirebaseUser().getUid(); }
        // Write a message to the database
        mDatabaseReference.child("users").child(getFirebaseUser().getUid()).setValue(user);
    }


}
