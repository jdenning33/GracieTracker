package com.ece435.gracietracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements
        Firebase.OnFireBaseInteractionListener,
        SignInFragment.OnFragmentInteractionListener,
        SignUpFragment.OnFragmentInteractionListener{

    FragmentManager fragmentManager;

    private Firebase fireBase;
    private GoogleApiClient mGoogleApiClient;
    public static final int RC_SIGN_IN = 111;

    private GracieUser gracieUser;

    public static final String USER_NAME = "com.ece435.gracietracker.USER_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gracieUser = new GracieUser();

        fireBase = new Firebase(this);
        fireBase.initialize();
        initializeGoogleSignIn();

        // Create a fragment manager so that we can initialize the MainFragment
        fragmentManager = getSupportFragmentManager();
        goToSignInFragment();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ///     Fragment and Activity Controls
    ////////////////////////////////////////////////////////////////////////////////////////////
    public void goToMainView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToSignInFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignInFragment signInFragment = SignInFragment.newInstance();
        fragmentTransaction.replace(R.id.MainFragment, signInFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void goToSignUpFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignUpFragment signUpFragment = SignUpFragment.newInstance();
        fragmentTransaction.replace(R.id.MainFragment, signUpFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Email Sign In
    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void emailSignIn(String email, String password){
        fireBase.emailSignIn(email, password);
    }
    @Override
    public void onSignInResult(boolean success){
        if(success){
            Toast.makeText(LoginActivity.this, "You Have Successfully Logged In",
                    Toast.LENGTH_SHORT).show();
            goToMainView();
        } else {
            Toast.makeText(LoginActivity.this, "Failed To Log In",
                    Toast.LENGTH_SHORT).show();
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Email Sign Up (Firebase)
    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void emailSignUp(String name, String email, String password, String dob, String belt) {
        if(email.length() < 1 || password.length() < 1){
            Toast.makeText(LoginActivity.this, "Must provide an Email and Password",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        gracieUser.currentBelt = 2;
        gracieUser.preferredName = name;
        gracieUser.email = email;

        fireBase.emailSignUp(email, password);
    }
    @Override
    public void onSignUpResult(boolean success){
        if (success) {
            Toast.makeText(LoginActivity.this, "You have successfully created an account",
                    Toast.LENGTH_SHORT).show();

            FirebaseUser firebaseUser = fireBase.getCurrentUser();
            gracieUser.uid = firebaseUser.getUid();

            fireBase.updateUser(gracieUser);

            goToMainView();
        } else {
            Toast.makeText(LoginActivity.this, "Authentication Failed",
                    Toast.LENGTH_SHORT).show();
        }

    }


    // Control Firebase auth lifecycle
    @Override
    public void onStart() {
        super.onStart();
        fireBase.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(fireBase.getCurrentUser() != null) goToMainView();

    }
    @Override
    public void onStop(){
        super.onStop();
        fireBase.onStop();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    ///     Google Sign In
    /////////////////////////////////////////////////////////////////////////////////////////////
    ///     Google Sign In Initialization
    public void initializeGoogleSignIn(){
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder( this )
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this, "Connection Failed", Toast.LENGTH_SHORT);
                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }
    @Override
    public void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    public void handleSignInResult(GoogleSignInResult result){
        if (result.isSuccess()) {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = result.getSignInAccount();
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

            fireBase.authWithGoogle(credential);
        } else {
            Toast.makeText(LoginActivity.this, "Google Login Unsuccessful",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onFirebaseAuthWithGoogleResult(boolean success){
        Log.d("Login", "signInWithCredential:success");

        if (success) {
            // Sign in success, update UI with the signed-in user's information
            Toast.makeText(LoginActivity.this, "Google to Firebase Authentication succeeded.",
                    Toast.LENGTH_SHORT).show();

            goToMainView();
        } else {
            // If sign in fails, display a message to the user.
            Toast.makeText(LoginActivity.this, "Google to Firebase Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
