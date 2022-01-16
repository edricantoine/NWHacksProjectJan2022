package com.example.pictureapp;


import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.pictureapp.databinding.FragmentFirstBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.util.Collections;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    //hello

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    private void requestSignIn() {
//        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .requestScopes(new Scope(DriveScopes.DRIVE_FILE))
//                .build();
//
//        GoogleSignInClient client = GoogleSignIn.getClient(this, signInOptions);
//
//
//        startActivityForResult(client.getSignInIntent(), 400);
//    }
//    // 4:40 https://www.youtube.com/watch?v=rznYp43KLRc

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 400:
//                if (resultCode == RESULT_OK) {
//                    handleSignInIntent(data);
//                }
//                break;
//        }
//    }

//    private void handleSignInIntent(Intent data) {
//        GoogleSignIn.getSignedInAccountFromIntent(data)
//                .addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
//                    @Override
//                    public void onSuccess(GoogleSignInAccount googleSignInAccount) {
//
//// something is not lettin gme import this class
//                        GoogleAccountCredential credential = GoogleAccountCredential.
//                                usingOAUth2(FirstFragment.this, Collections.singleton(DriveScopes.DRIVE_FILE));
//                    }
//
//                    credential.setSelectedAccount(googleSignInAccount.getAccount());
//
//                    Drive googleDriveService = new Drive.Builder(
//                            AndroidHttp.newCompatibleTransport(),
//                            new GsonFactory(),
//                            credential)
//                            .setApplicationName("My Drive Tutorial")
//                            .build();
//                    )
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });


//    }

}
