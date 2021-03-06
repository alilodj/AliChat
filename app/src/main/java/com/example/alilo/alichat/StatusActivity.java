package com.example.alilo.alichat;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {
private EditText Newsatus;
    private Button mSaveButton ;
 private DatabaseReference databaseReference ,databaseReference2 ;
    private FirebaseUser mCurenntuser ;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        //firebase
        progressBar= (ProgressBar)findViewById(R.id.progressBar2);
        mCurenntuser = FirebaseAuth.getInstance().getCurrentUser();
        String Current_id = mCurenntuser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(Current_id) ;
      //  databaseReference2 = FirebaseDatabase.getInstance().getReference("chat") ;

        Newsatus =(EditText) findViewById(R.id.newstatus) ;
        mSaveButton= (Button)  findViewById(R.id.SaveB) ;
        Newsatus.setText(getIntent().getStringExtra("status"));
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Toast.makeText(StatusActivity.this, "test", Toast.LENGTH_SHORT).show();

                if (progressBar != null) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                String statu = Newsatus.getText().toString();
            //    databaseReference.setValue(statu) ;
                databaseReference.child("statut").setValue(statu).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });

    }
}
