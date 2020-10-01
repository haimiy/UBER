package com.example.uber.adminpackages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.uber.Model;
import com.example.uber.MyAdapter;
import com.example.uber.R;

import java.util.ArrayList;
import java.util.Collections;

public class AdminActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

       mRecyclerView = findViewById(R.id.recylerView);
       preferences = this.getSharedPreferences("My Pref", MODE_PRIVATE);
       getMyList();

    }

    private void getMyList(){
        ArrayList<Model> models = new ArrayList<>();
                Model m = new Model();
                m.setTitle("Dashboard");
                m.setDescription("How many user on the system");
                m.setImg(R.drawable.dash);
                models.add(m);

                m = new Model();
                m.setTitle("Register");
                m.setDescription("Register new user");
                m.setImg(R.drawable.user);
                models.add(m);

                m = new Model();
                m.setTitle("Map");
                m.setDescription("View your current destination");
                m.setImg(R.drawable.map);
                models.add(m);

                m = new Model();
                m.setTitle("Setting");
                m.setDescription("View profile and others setting");
                m.setImg(R.drawable.setting);
                models.add(m);

                m = new Model();
                m.setTitle("History");
                m.setDescription("History of events");
                m.setImg(R.drawable.history);
                models.add(m);

                String mSortSetting = preferences.getString("Sort", "ascending");
                if (mSortSetting.equals("ascending")){
                    Collections.sort(models, Model.BY_TITLE_ASCENDING);
                }
                else if (mSortSetting.equals("descending")){
                    Collections.sort(models, Model.BY_TITLE_DESCENDING);
                }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, models);
        mRecyclerView.setAdapter(myAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sorting){
            sortDailog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortDailog() {
        String[] options = {"Ascending","Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by:");
        builder.setIcon(R.drawable.ic_action_sort);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort","ascending");
                    editor.apply();
                    getMyList();
                }
                if (which == 1){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort","descending");
                    editor.apply();
                    getMyList();
                }
            }
        });
        builder.create().show();
    }
}
