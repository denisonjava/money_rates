package com.example.parsing;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Conversation extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "";
    public float course;

    String str;
    Toolbar topAppBarCon;
    EditText date;
    String nominal;
    Date today = Calendar.getInstance().getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
    MaterialDatePicker<Long> picker;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        builder.setTitleText("Выберите дату:");
        picker = builder.build();
        topAppBarCon = findViewById(R.id.topAppBarCon);
        setSupportActionBar(topAppBarCon);
        date = findViewById(R.id.textInputData);
        String currentDate = dateFormat.format(today);
        date.setText(currentDate);
        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottomMenu);
        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.listValute:
                                Intent intent = new Intent(Conversation.this, MainActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.conversionValute:
                                break;
//                            case R.id.settings:
//                                Intent intent3 = new Intent(Conversation.this, SettingsActivity.class);
//                                startActivity(intent3);
//                                break;
                        }
                        return false;
                    }
                });

        EditText editText = (EditText) findViewById(R.id.textInputLayoutCourse);
        ArrayList<String> arr_name = new ArrayList<>();
        ArrayList<String> arr_nominal = new ArrayList<>();
        ArrayList<ParsingValute> arr_valute = new ArrayList<>();
        ParseString.parse(arr_valute, currentDate);
        for (int i = 0; i < arr_valute.size(); i++) {
            arr_name.add(i, arr_valute.get(i).getName());
        }for (int i = 0; i < arr_valute.size(); i++) {
            arr_nominal.add(i, arr_valute.get(i).getNominal());
        }
        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arr_name);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                nominal = arr_valute.get(position).getNominal();
                editText.setText(arr_valute.get(position).getValue());
                str = (arr_valute.get(position).getValue());
                str = str.replace(',', '.');
                course = Float.parseFloat(str);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                String currentTime = dateFormat.format(selection);
                arr_name.clear();
                arr_valute.clear();
                arr_nominal.clear();
                ParseString.parse(arr_valute, currentTime);
                for (int i = 0; i < arr_valute.size(); i++) {
                    arr_name.add(i, arr_valute.get(i).getName());
                }for (int i = 0; i < arr_valute.size(); i++) {
                    arr_nominal.add(i, arr_valute.get(i).getNominal());
                }
                adapter.notifyDataSetChanged();
                spinner.setSelection(0);
                date.setText(currentTime);
                editText.setText(arr_valute.get(0).getValue());
                str = (arr_valute.get(0).getValue());
                str = str.replace(',', '.');
                course = Float.parseFloat(str);
                Toast toast = Toast.makeText(getApplicationContext(), "Данные успешно обновлены", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
    }


    public void convert(View v) {
        EditText value = (EditText) findViewById(R.id.editText);
        EditText value_score = (EditText) findViewById(R.id.editText_score);
        if ((!value.getText().toString().equals(" ")) || (!value.getText().toString().equals("0")) || (Integer.parseInt(value.getText().toString()) != 0)) {
            String valueres = value.getText().toString();
            float result_one = Float.parseFloat(valueres);
            float res = ((result_one / course)*Float.parseFloat(nominal));
            String res_format = String.format("%.4f",res);
            value_score.setText(res_format);
        } else {
            Toast.makeText(getBaseContext(), "Введите сумму в рублях!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Locale locale = getResources().getConfiguration().locale; Locale.setDefault(locale);
        if (item.getItemId() == R.id.favorite) {
            //Toast.makeText(getBaseContext(), "Нажата кнопка дата", Toast.LENGTH_SHORT).show();
            picker.show(getSupportFragmentManager(), picker.toString());
        }
        return true;
    }

    @Override
    public void onClick(View v) {

    }


}
