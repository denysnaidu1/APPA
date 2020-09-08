package com.daredevil.appa;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.daredevil.appa.Interface.ItemClickListener;
import com.daredevil.appa.Model.Event;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mtoogle;
    private Intent intent;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseReference panti=FirebaseDatabase.getInstance().getReference().child("Panti");
        DatabaseReference organisasi=FirebaseDatabase.getInstance().getReference().child("Organisasi");
        DatabaseReference event = FirebaseDatabase.getInstance().getReference().child("Event");
        event.keepSynced(true);panti.keepSynced(true);organisasi.keepSynced(true);
        firebaseAuth=FirebaseAuth.getInstance();
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);


        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mtoogle=new ActionBarDrawerToggle(this,mDrawer,toolbar,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mtoogle);
        mtoogle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
    }
    @Override
    public void onBackPressed() {
        if(mDrawer.isDrawerOpen(GravityCompat.START)){
            mDrawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoogle.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        return super.onCreateOptionsMenu(menu);
        //getMenuInflater().inflate(R.menu.main_activity3, menu);
        //return true;
    }

    public void infoPanti(View view) {
        intent=new Intent(this,cariPanti.class);
        startActivity(intent);
    }

    public void goPilihDonasi(View view) {
        intent=new Intent(this,pilih_Donasi.class);
        startActivity(intent);
    }

    public void goCariOrganisasi(View view) {
        intent=new Intent(this,cariOrganisasi.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HistoryFragment()).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Log out berhasil", Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
                intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        return true;
    }
}
