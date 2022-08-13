package com.example.diarynotesuasakb;
/* Nama : Prasetyo Hade M. Winoto
   Kelas : IF-3
   NIM : 10119108
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    String month, todaysDate, currentTime;
    Toolbar toolbar;
    EditText nTitle, nDetail;
    Spinner spinner;
    TextView day;
    Calendar c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        toolbar = findViewById(R.id.toolbar);
        day = findViewById(R.id.day);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nTitle = findViewById(R.id.nTitle);
        nDetail = findViewById(R.id.DtDetail);

        nTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0 ){
                    getSupportActionBar().setTitle(charSequence);
                }else {
                    getSupportActionBar().setTitle("New Note");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //spinner
        spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("📝 Memo");
        arrayList.add("❤ Love");
        arrayList.add("❗ Reminder");
        arrayList.add("🔏 Private");
        arrayList.add("🎓 Task");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        //kalendar
        c = Calendar.getInstance();
        Integer bln = (c.get(Calendar.MONTH)+1);
        if (bln == 1){
            month = "Jan";
        }else if (bln ==2){
            month = "Feb";
        }else if (bln ==3){
            month = "Mar";
        }else if (bln ==4){
            month = "Apr";
        }else if (bln ==5){
            month = "Mei";
        }else if (bln ==6){
            month = "Jun";
        }else if (bln ==7){
            month = "Jul";
        }else if (bln ==8){
            month = "Agu";
        }else if (bln ==9){
            month = "Sep";
        }else if (bln ==10){
            month = "Okt";
        }else if (bln ==11){
            month = "Nov";
        }else if (bln ==12){
            month = "Des";
        }
        todaysDate =  c.get(Calendar.DAY_OF_MONTH)+ " "  + month + " " + c.get(Calendar.YEAR);
        currentTime = pad(c.get(Calendar.HOUR)) + ":" +pad(c.get(Calendar.MINUTE));
        day.setText(todaysDate);
        Log.d("Kalender","Tanggal dan waktu : "+ todaysDate + " dan "+ currentTime);



    }

    private String pad(int i) {
        if (i < 10){
            return "0"+i;
        }else {
            return String.valueOf(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save,menu);
        return true;
    }

    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save){
            NoteItem note = new NoteItem(nTitle.getText().toString(),nDetail.getText().toString() , spinner.getSelectedItem().toString(), todaysDate,currentTime);
            NoteDatabase db = new NoteDatabase(this);
            db.addNote(note);
            goToMain();
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.delete){
            Toast.makeText(this, "Note is not saved.", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}