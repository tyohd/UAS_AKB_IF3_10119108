package com.example.diarynotesuasakb;

/* Nama : Prasetyo Hade M. Winoto
   Kelas : IF-3
   NIM : 10119108
 */

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
public class EditActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText nTitle, nDetail;
    Spinner spinner;
    TextView day;
    Calendar c;
    String month;
    String todaysDate;
    String currentTime;
    NoteDatabase db;
    NoteItem note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent i = getIntent();
        Long id = i.getLongExtra("ID",0);
        db = new NoteDatabase(this);
        note = db.getNote(id);



        toolbar = findViewById(R.id.toolbar);
        day = findViewById(R.id.day);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(note.getTitle());
        nTitle = findViewById(R.id.nTitle);
        nDetail = findViewById(R.id.nDetail);

        nTitle.setText(note.getTitle());
        nDetail.setText(note.getContent());


        nTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0 ){
                    getSupportActionBar().setTitle(charSequence);
                }else {
                    getSupportActionBar().setTitle("Catatan Baru");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //spinner
        spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("📝 Catatan");
        arrayList.add("🎓 Kuliah");
        arrayList.add("🏠 Rumah");
        arrayList.add("🔏 Pribadi");
        arrayList.add("❗ Penting");
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save){
            note.setTitle(nTitle.getText().toString());
            note.setContent(nDetail.getText().toString());
            int id = db.editNote(note);
            Toast.makeText(this, "Note successfuly changed", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
            i.putExtra("ID",note.getID());
            startActivity(i);
        }
        if (item.getItemId() == R.id.delete){
            Toast.makeText(this, "Cancelled.", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}