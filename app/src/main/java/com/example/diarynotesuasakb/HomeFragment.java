package com.example.diarynotesuasakb;
/* Nama : Prasetyo Hade M. Winoto
   Kelas : IF-3
   NIM : 10119108
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeFragment extends Fragment {


    Toolbar toolbar;
    private RecyclerView recyclerView;
    FloatingActionButton button;
    NoteAdapter noteAdapter;
    List<NoteItem> noteItems;
    private FirebaseAuth auth;

    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.addBtn:
                    Intent i = new Intent(getActivity(), AddNoteActivity.class);
                    startActivity(i);
                    break;
                case R.id.btnlogout:
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setCancelable(true);
                    builder.setTitle("Logout");
                    builder.setMessage("Are you sure you want to logout ?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    auth.signOut();
                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);

        recyclerView = view.findViewById(R.id.listOfNote);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view.findViewById(R.id.addBtn).setOnClickListener(listener);
        toolbar = view.findViewById(R.id.toolbar);
        NoteDatabase db = new NoteDatabase(view.getContext());
        noteItems = db.getNotes();
        noteAdapter = new NoteAdapter(view.getContext(), noteItems);
        recyclerView.setAdapter(noteAdapter);

        view.findViewById(R.id.btnlogout).setOnClickListener(listener);
        auth = FirebaseAuth.getInstance();

        /*View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.addBtn:
                        Intent i = new Intent(getActivity(), AddNoteActivity.class);
                        startActivity(i);
                        break;
                    case R.id.btnlogout:
                        auth.signOut();
                        break;
                }
            }
        };*/



      /*  button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddNoteActivity.class);
                startActivity(i);
            }
        });*/

       /* btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
            }
        });*/
        return view;

    }
}