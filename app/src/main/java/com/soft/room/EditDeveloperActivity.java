package com.soft.room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.soft.room.model.entity.Deparment;
import com.soft.room.model.entity.Developer;
import com.soft.room.model.repo.DeveloperRepo;

public class EditDeveloperActivity extends AppCompatActivity {

    public static final String KEY_DEVELOPER_ID = "developer_id";

    private TextInputEditText edName;
    private TextInputEditText edAge;
    private TextInputEditText edSkill;
    private TextInputEditText edDapertment;

    private Button btn_save, btn_delete;

    private Spinner spinnerSkills;
    private Spinner spinnerDepartment;

    private Developer developer;

    private DeveloperRepo developerRepo;

    private ArrayAdapter<Deparment> dpAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_developer);

        dpAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        developerRepo = new DeveloperRepo(MainApplication.getDatabase(this).developerDAO(),
                MainApplication.getDatabase(this).departmentDAO());

        int id = getIntent().getIntExtra(KEY_DEVELOPER_ID, 0);

        edName = findViewById(R.id.edName1);
        edAge = findViewById(R.id.edAge1);
        edSkill = findViewById(R.id.edSkill1);
        edDapertment = findViewById(R.id.edDepartment1);

        btn_save = findViewById(R.id.btn_save);
        btn_delete = findViewById(R.id.btn_delete);

        spinnerSkills = findViewById(R.id.spinnerSkill);
        spinnerDepartment = findViewById(R.id.spinnerDepartment);
        spinnerDepartment.setAdapter(dpAdapter);

        dpAdapter.addAll(developerRepo.getDepartments());

        if (id > 0) {

            developer = developerRepo.getDeveloper(id);
            btn_delete.setVisibility(View.VISIBLE);

            edName.setText(developer.getName());
            edAge.setText(String.valueOf(developer.getAge()));
            edSkill.setText(developer.getSkill().name());

            Deparment deparment = developerRepo.getDeparment(developer.getDepartmentId());
            edDapertment.setText(deparment.getName());

            spinnerSkills.setSelection(developer.getSkill().ordinal());

            for (int i = 0; i < dpAdapter.getCount(); i++) {
                Deparment de = dpAdapter.getItem(i);
                if (de.getId() == developer.getDepartmentId()) {
                    spinnerDepartment.setSelection(i);
                    break;
                }
            }

        } else {
            developer = new Developer();
        }

        spinnerSkills.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Developer.Skill skill = Developer.Skill.values()[position];
                edSkill.setText(skill.name());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Deparment deparment = dpAdapter.getItem(position);
                edDapertment.setText(deparment.getName());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edSkill.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return true;
            }
        });

        edSkill.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    spinnerSkills.performClick();
                }
                return true;
            }
        });

        edDapertment.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return true;
            }
        });

        edDapertment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    spinnerDepartment.performClick();
                }
                return true;
            }
        });
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:

                developer.setName(edName.getText().toString());
                developer.setAge(Integer.parseInt(edAge.getText().toString()));
                developer.setSkill((Developer.Skill.values()[spinnerSkills.getSelectedItemPosition()]));

                Deparment dep = (Deparment) spinnerDepartment.getSelectedItem();

                developer.setDepartmentId(dep.getId());

                developerRepo.save(developer);
                break;
            case R.id.btn_delete:
                //TODO
                developerRepo.delete(developer);
                break;
        }
        finish();
    }
}
