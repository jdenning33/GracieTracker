package com.ece435.gracietracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by jdenn on 4/30/2017.
 */

public class Firebase {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private AppCompatActivity mListener;

    public Firebase(AppCompatActivity context){
        if (context instanceof OnFireBaseInteractionListener) {
            mListener = context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public FirebaseUser getCurrentUser(){
        return mAuth.getCurrentUser();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFireBaseInteractionListener {
        void onSignInResult(boolean success);
        void onSignUpResult(boolean success);
        void onFirebaseAuthWithGoogleResult(boolean success);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Initialize FireBase
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void initialize(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    // User is signed in
                    Log.d("Login", "onAuthStateChanged:signed:_in" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Login", "onAuthStateChanged:signed_out");
                }
            }
        };
    }
    // Control Firebase auth lifecycle
    public void onStart() {
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop(){
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Email Sign In
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void emailSignIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mListener, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Login", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            ((OnFireBaseInteractionListener) mListener).onSignInResult(true);
                        } else {
                            ((OnFireBaseInteractionListener) mListener).onSignInResult(false);
                        }

                        // ...
                    }
                });
    }
    public void emailSignUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mListener, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Login", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            ((OnFireBaseInteractionListener) mListener).onSignUpResult(true);
                        } else {
                            ((OnFireBaseInteractionListener) mListener).onSignUpResult(false);
                        }
                    }
                });
    }

    public void authWithGoogle(GoogleSignInAccount acct){
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mListener, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            ((OnFireBaseInteractionListener) mListener).onFirebaseAuthWithGoogleResult(true);
                        } else {
                            ((OnFireBaseInteractionListener) mListener).onFirebaseAuthWithGoogleResult(false);
                        }

                        // ...
                    }
                });

    }

}
