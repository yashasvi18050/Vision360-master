package com.example.shubhangi.homepage;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class USERPAYMENT extends AppCompatActivity {
    Button buttonSelect;
    Button buttonUpload;
    Button buttonCancel;
    TextView showtid;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageReference;
    boolean found=false;
    private int PICK_IMAGE_REQUEST=1;

    private  static final int STORAGE_PERMISSION_CODE=123;

    private Bitmap bit;

    ImageView image;
    String userid;
    EditText text;
    String tid;
    private Uri file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpayment);

          //  super.onCreate(savedInstanceState);

            requestStoragePermission();
            user = FirebaseAuth.getInstance().getCurrentUser();
            userid=user.getUid();
             storage = FirebaseStorage.getInstance();
             storageReference = storage.getReference();



            image=(ImageView)findViewById(R.id.imageView);
            text=(EditText)findViewById(R.id.edit);
            tid=text.getText().toString();

            buttonSelect= (Button)findViewById(R.id.select);
            buttonUpload=(Button)findViewById(R.id.upload);
            buttonCancel=(Button)findViewById(R.id.cancel);
            showtid=(TextView)findViewById(R.id.showtid);



        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooser();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bit==null){
                    Toast.makeText(USERPAYMENT.this,"Please select the image first", Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadMultiPart();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageBitmap(null);
            }
        });
        }
        public void onRequestPermission(int request , @NonNull String [] permissions, @NonNull int[]results){
            if(request==STORAGE_PERMISSION_CODE){
                if(results.length>0&&results[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"Permission Not Granted",Toast.LENGTH_SHORT).show();
                }
            }
        }

        protected void onActivityResult(int request,int result,Intent d){
            super.onActivityResult(request,result,d);
            if(request==PICK_IMAGE_REQUEST&&result==RESULT_OK&&d!=null&&d.getData()!=null){
                file=d.getData();
                try{
                    bit=MediaStore.Images.Media.getBitmap(getContentResolver(),file);

                    TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

                    Frame imageFrame = new Frame.Builder()
                            .setBitmap(bit)
                            .build();

                    String blocks = "";
                    String lines = "";
                    String words = "";
                    String match="Order ID: ";
                    String check;
                    SparseArray<TextBlock> textBlocks = textRecognizer.detect(imageFrame);



                    for (int i = 0; i < textBlocks.size(); i++) {
                        TextBlock tBlock = textBlocks.get(textBlocks.keyAt(i));


                         blocks+=tBlock.getValue()+"\n";


                        for (Text line : tBlock.getComponents()) {
                            //extract scanned text lines here
                            check=line.getValue();
                            lines = lines + check + "\n";
                            if(check.matches("Order ID:.[0-9]{10}"))
                            {
                                String patternString1 = "([0-9]{10})";
                                Pattern pattern = Pattern.compile(patternString1);
                                Matcher matcher = pattern.matcher(check);
                                if(matcher.find()){
                                    tid=matcher.group(1);
                                    Toast.makeText(USERPAYMENT.this,tid,Toast.LENGTH_SHORT).show();
                                }
                                found = true;
                                showtid.setText(check);
                            }


                            for (Text element : line.getComponents()) {
                                //extract scanned text words here
                                words = words + element.getValue() + ", ";
                            }

                            ;// return string
                        }
                    }

                    if(found==false)
                    {
                        Toast.makeText(USERPAYMENT.this,"Order ID : Not found ",Toast.LENGTH_SHORT).show();
                        showtid.setText("Order ID: Not Found");
                    }
                   image.setImageBitmap(bit);

                    }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }


        private void requestStoragePermission(){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                return;
            }
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

            }
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }



        public void chooser(){
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i,"Select Image"),PICK_IMAGE_REQUEST);
        }

        public void uploadMultiPart(){

            if(file!= null)
            {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                StorageReference ref = storageReference.child(userid);

                StorageMetadata metadata = new StorageMetadata.Builder()
                        .setContentType("image/jpg")
                        .setCustomMetadata("TID",tid)
                        .setCustomMetadata("STATUS","ACTIVE")
                        .build();


                ref.updateMetadata(metadata)
                        .addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                            @Override
                            public void onSuccess(StorageMetadata storageMetadata) {
                                // Updated metadata is in storageMetadata
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Uh-oh, an error occurred!
                            }
                        });


                ref.putFile(file,metadata)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(USERPAYMENT.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(USERPAYMENT.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Image Uploaded "+(int)progress+"%");
                            }
                        });
            }
        }




    }

