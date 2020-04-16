package com.example.parsing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ValuteAdapter valuteAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    Toolbar topAppBar;
    private ArrayList<Integer> imageArray = new ArrayList<>();
    ArrayList<ParsingValute> arr_2 = new ArrayList<>();
    MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
    MaterialDatePicker<Long> picker;
    Date today = Calendar.getInstance().getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String currentDate = dateFormat.format(today);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        builder.setTitleText("Выберите дату");
        picker = builder.build();
        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);
        ParseString.parse(arr_2, currentDate);
        setupBottomNavigationMenu();
        setupRecyclerView();
        setupSwipeRefresh();


        picker.addOnPositiveButtonClickListener(selection -> {
            String currentTime = dateFormat.format(selection);
            //Toast.makeText(getBaseContext(), "Выбрана дата: " + currentTime, Toast.LENGTH_SHORT).show();
            arr_2.clear();
            imageArray.clear();
            setupImageArray(imageArray);
            ParseString.parse(arr_2, currentTime);
            valuteAdapter.setDate(currentTime);
            valuteAdapter.notifyDataSetChanged();
            Toast toast = Toast.makeText(getApplicationContext(), "Данные успешно обновлены на " + currentTime, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favorite) {
            //Toast.makeText(getBaseContext(), "Нажата кнопка дата", Toast.LENGTH_SHORT).show();
            picker.show(getSupportFragmentManager(), picker.toString());
        }
        return true;
    }

    private void setupRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        setupImageArray(imageArray);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        valuteAdapter = new ValuteAdapter(this, arr_2, currentDate, imageArray);
        recyclerView.setAdapter(valuteAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupSwipeRefresh(){
        swipeRefreshLayout = findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            arr_2.clear();
            imageArray.clear();
            setupImageArray(imageArray);
            ParseString.parse(arr_2, currentDate);
            valuteAdapter.setDate(currentDate);
            valuteAdapter.notifyDataSetChanged();
            Toast toast = Toast.makeText(getApplicationContext(), "Данные успешно обновлены на " + currentDate, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void setupBottomNavigationMenu(){
        BottomNavigationView bottomNav =  findViewById(R.id.bottomMenu);
        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.listValute:
                    break;
                case R.id.conversionValute:
                    Intent intent2 = new Intent(MainActivity.this, Conversation.class);
                    startActivity(intent2);
                    break;
//                  case R.id.settings:
//                  Intent intent3 = new Intent(MainActivity.this, SettingsActivity.class);
//                  startActivity(intent3);
//                  break;
            }
            return false;
        });

    }

    public static void setupImageArray(ArrayList<Integer> imageArrayList) {
        imageArrayList.add(R.drawable.australia);
        imageArrayList.add(R.drawable.azer);
        imageArrayList.add(R.drawable.great_britain);
        imageArrayList.add(R.drawable.armenia);
        imageArrayList.add(R.drawable.belorussia);
        imageArrayList.add(R.drawable.bolgaria);
        imageArrayList.add(R.drawable.brazil);
        imageArrayList.add(R.drawable.hu);
        imageArrayList.add(R.drawable.hk);
        imageArrayList.add(R.drawable.dk);
        imageArrayList.add(R.drawable.us);
        imageArrayList.add(R.drawable.euro);
        imageArrayList.add(R.drawable.in);
        imageArrayList.add(R.drawable.kz);
        imageArrayList.add(R.drawable.ca);
        imageArrayList.add(R.drawable.kg);
        imageArrayList.add(R.drawable.cn);
        imageArrayList.add(R.drawable.md);
        imageArrayList.add(R.drawable.no);
        imageArrayList.add(R.drawable.pl);
        imageArrayList.add(R.drawable.ro);
        imageArrayList.add(R.drawable.sdr);
        imageArrayList.add(R.drawable.sg);
        imageArrayList.add(R.drawable.tj);
        imageArrayList.add(R.drawable.tr);
        imageArrayList.add(R.drawable.tm);
        imageArrayList.add(R.drawable.uz);
        imageArrayList.add(R.drawable.ua);
        imageArrayList.add(R.drawable.cz);
        imageArrayList.add(R.drawable.se);
        imageArrayList.add(R.drawable.ch);
        imageArrayList.add(R.drawable.za);
        imageArrayList.add(R.drawable.kr);
        imageArrayList.add(R.drawable.jp);
    }

}








