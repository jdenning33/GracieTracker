package com.ece435.gracietracker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class SignInFragment extends Fragment implements View.OnClickListener {

    private SignInFragment.OnFragmentInteractionListener mListener;


    public SignInFragment() {
    }

    public static SignInFragment newInstance(){
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
//        args.putString(COURSE_TITLE, courseTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        ((Button) view.findViewById(R.id.EmailSignInButton)).setOnClickListener(this);
        ((ImageButton) view.findViewById(R.id.GoogleSignInButton)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.CreateNewAccountButton)).setOnClickListener(this);

        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignInFragment.OnFragmentInteractionListener) {
            mListener = (SignInFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void emailSignIn(String email, String password);
        void googleSignIn();
        void goToSignUpFragment();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.EmailSignInButton:
                EditText emailText = (EditText) getView().findViewById(R.id.EmailText);
                String email = emailText.getText().toString();
                EditText passwordText = (EditText) getView().findViewById(R.id.PasswordText);
                String password = passwordText.getText().toString();

                if(email.length() < 1 || password.length() < 1){
                    Toast.makeText(getActivity(), "Must provide an Email and Password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "Signing In",
                        Toast.LENGTH_SHORT).show();
                mListener.emailSignIn(email, password);

                break;
            case R.id.GoogleSignInButton:
                mListener.googleSignIn();
                break;
            case R.id.CreateNewAccountButton:
                mListener.goToSignUpFragment();
                break;
        }

    }
}
