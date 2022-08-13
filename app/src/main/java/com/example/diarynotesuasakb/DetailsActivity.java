package com.example.diarynotesuasakb;
/* Nama : Prasetyo Hade M. Winoto
   Kelas : IF-3
   NIM : 10119108
 */
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.example.diarynotesuasakb.databinding.ActivityDetailsBinding;


public class DetailsActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityDetailsBinding binding;
    TextView nDetails, nDate,nDetailTitle, nCategory;
    NoteDatabase db;
    NoteItem noteItem;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nDetails = findViewById(R.id.detailsOfNote);
        nDate = findViewById(R.id.nDate);
        nDetailTitle = findViewById(R.id.nDetailTitle);
        nCategory = findViewById(R.id.nCategory);


        Intent intent = getIntent();
        id = intent.getLongExtra("ID",0);


        db = new NoteDatabase(this);
        NoteItem note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        nDetails.setText(note.getContent());
        nDate.setText("Dibuat pada : "+note.getDate()+" "+note.getTime());
        nDetailTitle.setText(note.getTitle());
        nCategory.setText(note.getCategory());
        nDetails.setMovementMethod(new ScrollingMovementMethod());


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNote(note.getID());
                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
                Toast.makeText(DetailsActivity.this, "Catatan Dihapus", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.editNote){
            Intent i = new Intent(this, EditActivity.class);

            i.putExtra("ID",id);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }
}