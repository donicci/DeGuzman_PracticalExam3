package com.example.deguzman_prac3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public abstract class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    EditText cname, vnum, rdate, aplvl ;
    DBHelper helper = new DBHelper(this);
    Cursor table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = helper.selectRecord();
        cname = findViewById(R.id.dgCname);
        vnum = findViewById(R.id.dgVnum);
        rdate = findViewById(R.id.dgRdate);
        aplvl = findViewById(R.id.dgAplvl);

    }
    public void addRecord(View v){
        String c = cname.getText().toString();
        String vn = vnum.getText().toString();
        String r = rdate.getText().toString();
        String a = aplvl.getText().toString();
        boolean inserted = helper.insert(c,vn,r,a);
        if (inserted == true){
            Toast.makeText(this, "Record inserted", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record not inserted", Toast.LENGTH_LONG).show();
    }

    public void first (View v){
        table.moveToFirst();
        data();
    }

    public void previous (View v){
        table.moveToPrevious();
        if (table.isBeforeFirst()){
            Toast.makeText(this, "Record is at first position", Toast.LENGTH_LONG).show();
            //table.moveToFirst();
        } else data();
    }

    public void next (View v){
        table.moveToNext();
        if (table.isAfterLast()) {
            Toast.makeText(this, "Record is at last position", Toast.LENGTH_LONG).show();
            //table.moveToLast();
        } else data();
    }
    public void last (View v){
        table.moveToLast();
        data();
    }

    public void data(){
        cname.setText(table.getString(1));
        vnum.setText(table.getString(2));
        rdate.setText(table.getString(3));
        aplvl.setText(table.getString(4));
               // cname, vnum, rdate, aplvl
    }

    public void editRecord(View v){
        String id = table.getString(0);
        String c = cname.getText().toString();
        String vn = vnum.getText().toString();
        String r = rdate.getText().toString();
        String a = aplvl.getText().toString();

        boolean updated = helper.update(id,c,vn,r,a);
        if (updated == true){
            Toast.makeText(this, "Record updated", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record not updated", Toast.LENGTH_LONG).show();
    }

    public void deleteRecord(View v){
        String id = table.getString(0);
        boolean deleted = helper.delete(id);
        if (deleted == true){
            Toast.makeText(this, "Record deleted", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record deleted", Toast.LENGTH_LONG).show();
    }
}
