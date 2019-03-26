package com.example.j_lds.sqlitetest;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.j_lds.sqlitetest.OfflineDatabase.OfflineDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private OfflineDatabaseHelper myOfflineDB;

    private Button btn_insert, btn_view, btn_update, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(OfflineDatabaseHelper.class.getName()+" : ", "Loaded !!!");

        myOfflineDB = new OfflineDatabaseHelper(this);

        btn_insert = (Button)findViewById(R.id.button);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insetData();
            }
        });

        btn_view = (Button)findViewById(R.id.button2);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAllData();
            }
        });

        btn_update = (Button)findViewById(R.id.button3);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        btn_delete = (Button)findViewById(R.id.button4);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //    good
    private void insetData(){
        boolean isInserted = myOfflineDB.insertDataCheckStatus(0,0,0);

        Log.e(OfflineDatabaseHelper.class.getName()+" : ", "Insert...");
        if (isInserted){
            Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();
            Log.e(myOfflineDB.getClass().getName()+" : ", "Data inserted");

            // view the data
            viewAllData();
        }else {
            Toast.makeText(this, "Data not inserted", Toast.LENGTH_SHORT).show();
            Log.e(myOfflineDB.getClass().getName()+" : ", "Data not inserted");
        }
    }

//    good
    private void viewAllData(){
        Cursor res = myOfflineDB.getAllData();
        if (res.getCount() == 0){
            showMsg("ERROR :", "No Data Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID : "+res.getString(0)+"\n");
            buffer.append("ID : "+res.getString(1)+"\n");
            buffer.append("ID : "+res.getString(2)+"\n");
            buffer.append("ID : "+res.getString(3)+"\n\n");
        }
        showMsg("Data : ", buffer.toString());
    }

//    need to fix
    private void updateData(){
        boolean isUpdated = myOfflineDB.updateOfflineClientCheck(1);

        Log.e(OfflineDatabaseHelper.class.getName()+" : ", "Update...");
        if (isUpdated){
            Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show();
            Log.e(myOfflineDB.getClass().getName()+" : ", "Data updated");
        }else{
            Toast.makeText(this, "Data not updated", Toast.LENGTH_SHORT).show();
            Log.e(myOfflineDB.getClass().getName()+" : ", "Data not updated");
        }
    }

//    good
    private void deleteData(){
        Integer deleteRow = myOfflineDB.deleteDataCheckStatus(1);
        if(deleteRow > 0){
            Toast.makeText(this, "Data deleted", Toast.LENGTH_SHORT).show();
            Log.e(myOfflineDB.getClass().getName()+" : ", "Data deleted");
        }else{
            Toast.makeText(this, "Data not  deleted", Toast.LENGTH_SHORT).show();
            Log.e(myOfflineDB.getClass().getName()+" : ", "Data not deleted");
        }
    }

    private void showMsg(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
