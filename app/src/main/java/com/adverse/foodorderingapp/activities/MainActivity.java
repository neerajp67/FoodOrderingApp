package com.adverse.foodorderingapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.adverse.foodorderingapp.R;
import com.adverse.foodorderingapp.fragments.FragmentHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //    Define global variable here
    androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigation;
    Button btn1;

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
                    case R.id.menu_home:
                        Toast.makeText(MainActivity.this, "Bottom Nav home selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.cart_view:
                        Toast.makeText(MainActivity.this, "Bottom Nav cart_view selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.search:
                        Toast.makeText(MainActivity.this, "Bottom Nav search selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.account:
                        Toast.makeText(MainActivity.this, "Bottom Nav account selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.login:
                        Toast.makeText(MainActivity.this, "Bottom Nav login selected", Toast.LENGTH_SHORT).show();
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

/*

        Spinner spinner =findViewById(R.id.spinner1);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
*/



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

    //    To render search_menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.search_keyword);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                FragmentManager fragmentManager = getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                Change fragment name as required
                final FragmentHome myFragment = new FragmentHome();

//                Log.i("test date", searchView.getQuery().toString());
                Bundle b = new Bundle();
                b.putString("query", searchView.getQuery().toString());
                myFragment.setArguments(b);
//                Change fragment container wherever required
                fragmentTransaction.replace(R.id.fragment_container, myFragment).commit();
                searchView.onActionViewCollapsed();
//                load fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //    To add functionality when a menu item is selected click..
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        return item.getItemId() == R.id.search_keyword;
    }
   public void FoodDesc(View view)
   {

   }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}