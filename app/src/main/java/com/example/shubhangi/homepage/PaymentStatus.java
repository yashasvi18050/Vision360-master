package com.example.shubhangi.homepage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

public class PaymentStatus extends AppCompatActivity {
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageReference;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userid = user.getUid();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        final TextView showstatus = (TextView) findViewById(R.id.showstatus);
        final TextView showtid=(TextView)findViewById(R.id.showtid);

        StorageReference ref = storageReference.child(userid);

        ref.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
            @Override
            public void onSuccess(StorageMetadata storageMetadata) {
                // Metadata now contains the metadata for 'images/forest.jpg'
                String TID = storageMetadata.getCustomMetadata("TID");
                String Status = storageMetadata.getCustomMetadata("STATUS");

                showstatus.setText("STATUS: "+Status);
                showtid.setText("TID: "+TID);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
                showstatus.setText("STATUS: INACTIVE");
            }
        });


    }


}
