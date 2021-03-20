package com.adverse.foodorderingapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.adverse.foodorderingapp.R;
import com.adverse.foodorderingapp.fragments.FragmentHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //    Define global variable here
    androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Initialize variables here

//        Custom Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        BottomNavigation
        bottomNavigation = findViewById(R.id.bottom_nav);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_nav_item1:
                        Toast.makeText(MainActivity.this, "Bottom Nav item 1 selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bottom_nav_item2:
                        Toast.makeText(MainActivity.this, "Bottom Nav item 2 selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bottom_nav_item3:
                        Toast.makeText(MainActivity.this, "Bottom Nav item 3 selected", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return true;
            }
        });
//        Navigation Drawer View in main activity
        NavigationView navigationView = findViewById(R.id.nav_view);

//       Navigation Drawer Layout in menu
        drawerLayout = findViewById(R.id.drawer_layout);

//        Navigation Drawer item click event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//               function call to public boolean itemSelected(MenuItem item) to get selected item
                return itemSelected(item);
            }
        });
//        Navigation Drawer: open close
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        rendering default fragment on application startup
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();

//            selected fragment will be highlighted
            navigationView.setCheckedItem(R.id.item1);
        }
    }

    //    Navigation Drawer: add functionality on navigation drawer item click..
    public boolean itemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(MainActivity.this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(MainActivity.this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Toast.makeText(MainActivity.this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item4:
                Toast.makeText(MainActivity.this, "Item 4 selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item5:
//                rendering selected fragment based on Navigation drawer menu item's id
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
                break;
            case R.id.item6:
                Toast.makeText(MainActivity.this, "Item 6 selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.test1:
                ShareCompat.IntentBuilder.from(MainActivity.this)
                        .setType("text/plain")
                        .setChooserTitle("News Reader")
                        .setText("code for News Reader app is available at https://github.com/neerajp67/News-Reader\nstay connected  with us on https://github.com/neerajp67/ for more exiting projects.. ")
                        .startChooser();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    BottomNavigationView bottomNavigationView;
    //    Bottom Nav Click Events
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.bottom_nav_item1:
                            Toast.makeText(MainActivity.this, "Bottom Nav item 1 selected", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.bottom_nav_item2:
                            Toast.makeText(MainActivity.this, "Bottom Nav item 2 selected", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.bottom_nav_item3:
                            Toast.makeText(MainActivity.this, "Bottom Nav item 3 selected", Toast.LENGTH_SHORT).show();
                            return true;
                    }
                    return true;
                }
            };

    //    To render search_menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //    To add functionality when a menu item is selected click..
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        return item.getItemId() == R.id.search_keyword;
    }
}