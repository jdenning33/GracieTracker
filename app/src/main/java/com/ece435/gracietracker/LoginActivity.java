package com.ece435.gracietracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_NAME = "com.ece435.gracietracker.USER_NAME";
    public static final int RC_SIGN_IN = 111;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set the toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ///////////////////////////////////////////////////////////////////////////////////////////
        ///     Firebase Initialization
        ///////////////////////////////////////////////////////////////////////////////////////////
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

        ///////////////////////////////////////////////////////////////////////////////////////////
        ///     Google Sign In Initialization
        ///////////////////////////////////////////////////////////////////////////////////////////
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this, "Connection Failed", Toast.LENGTH_SHORT);
                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        SignInButton googleSignInButton = (SignInButton) this.findViewById(R.id.GoogleSignInButton);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

        if( isLoggedIn() ){
            goToMainView( null );
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///     Action Bar Initialization
    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar, menu);
        return true;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    ///     Control Firebase auth lifecycle
    /////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) goToMainViewHelp();

    }
    @Override
    public void onStop(){
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    ///     Firebase Sign In and Sign Up
    /////////////////////////////////////////////////////////////////////////////////////////////

    // SIGN UP
    public void createNewUser(View view){
        EditText emailText = (EditText) findViewById(R.id.EmailText);
        String email = emailText.getText().toString();
        EditText passwordText = (EditText) findViewById(R.id.PasswordText);
        String password = passwordText.getText().toString();

        if(email.length() < 1 || password.length() < 1){
            Toast.makeText(LoginActivity.this, "Must provide an Email and Password",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        createNewUserHelp(email, password);
    }
    public void createNewUserHelp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Login", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(LoginActivity.this, "You have successfully created an account",
                                    Toast.LENGTH_SHORT).show();
                            goToMainViewHelp();
                        }

                        // ...
                    }
                });
    }

    // SIGN IN
    public void loginUser(View view){
        EditText emailText = (EditText) findViewById(R.id.EmailText);
        String email = emailText.getText().toString();
        EditText passwordText = (EditText) findViewById(R.id.PasswordText);
        String password = passwordText.getText().toString();

        if(email.length() < 1 || password.length() < 1){
            Toast.makeText(LoginActivity.this, "Must provide an Email and Password",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        loginUserHelp(email, password);
    }
    public void loginUserHelp(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Login", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("Login", "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Could Not Authenticate",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "You Have Successfully Logged In",
                                    Toast.LENGTH_SHORT).show();
                            goToMainViewHelp();
                        }

                        // ...
                    }
                });
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    ///     Google Sign In
    /////////////////////////////////////////////////////////////////////////////////////////////
    private void googleSignIn() {
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
            firebaseAuthWithGoogle(account);

            Toast.makeText(LoginActivity.this, "You Have Successfully Logged In With Google",
                    Toast.LENGTH_SHORT).show();

            goToMainViewHelp();
        } else {
            Toast.makeText(LoginActivity.this, "Google Login Unsuccessful",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("Login", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Login", "signInWithCredential:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            goToMainViewHelp();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Login", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Google to Firebase Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    ///     Homebrew Methods
    /////////////////////////////////////////////////////////////////////////////////////////////
    public void goToMainView(View view) {
        goToMainViewHelp();
    }
    public void goToMainViewHelp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean isLoggedIn() {
        return false;
    }
}
