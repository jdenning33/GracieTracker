package com.ece435.gracietracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";

    private OnFragmentInteractionListener mListener;

    public SignUpFragment() {
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ((Button) view.findViewById(R.id.EmailSignUpButton)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.SignInExistingButton)).setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
        void emailSignUp(String name, String email, String password);
        void goToSignInFragment();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.EmailSignUpButton:
                EditText emailText = (EditText) getView().findViewById(R.id.EmailText);
                String email = emailText.getText().toString();
                EditText passwordText = (EditText) getView().findViewById(R.id.PasswordText);
                String password = passwordText.getText().toString();
                EditText cPassordText = (EditText) getView().findViewById(R.id.ConfirmPasswordText);
                String cPassword = cPassordText.getText().toString();

                if(email.length() < 1 || password.length() < 1){
                    Toast.makeText(getActivity(), "Must provide an Email and Password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "Signing Up",
                        Toast.LENGTH_SHORT).show();
                mListener.emailSignUp(email, password, cPassword);

                break;
            case R.id.SignInExistingButton:
                mListener.goToSignInFragment();
                break;
        }

    }

}
