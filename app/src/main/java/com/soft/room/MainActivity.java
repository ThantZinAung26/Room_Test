package com.soft.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.soft.room.model.entity.Developer;
import com.soft.room.model.repo.DeveloperRepo;

public class MainActivity extends AppCompatActivity {

    private DeveloperAdapter adapter;
    private DeveloperRepo developerRepo;

    @Override
    protected void onResume() {
        super.onResume();
        adapter.submitList(developerRepo.findAll());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new DeveloperAdapter();
        developerRepo = new DeveloperRepo(MainApplication.getDatabase(this).developerDAO(), MainApplication.getDatabase(this).departmentDAO());


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setOnAdapterItemClickListener(new DeveloperAdapter.OnAdapterItemClickListener() {
            @Override
            public void onClick(Developer dev) {
                Intent intent = new Intent(MainActivity.this, EditDeveloperActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditDeveloperActivity.class);
                startActivity(intent);
            }
        });


    }
}
