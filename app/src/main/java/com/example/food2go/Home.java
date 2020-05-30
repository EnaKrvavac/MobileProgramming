package com.example.food2go;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import Interface.ItemClickListener;
import ViewHolder.MenuViewHolder;
import common.Common;
import model.Category;

public class Home extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener

    {

        FirebaseDatabase database;
        DatabaseReference category;
        TextView txtFullName;

        RecyclerView recycler_menu;
        RecyclerView.LayoutManager layoutManager;
        FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;



        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");




        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
               Intent cartIntent=new Intent(Home.this,Cart.class);
               startActivity(cartIntent);
            }
        });
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(
             this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
           toggle.syncState();

           NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view);
           navigationView.setNavigationItemSelectedListener(this);

           View headerView = navigationView.getHeaderView(0);
            txtFullName = (TextView)headerView.findViewById(R.id.txtFullName);
            txtFullName.setText(Common.currentUser.getName());

            recycler_menu = findViewById(R.id.recycle_menu);
            recycler_menu.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            recycler_menu.setLayoutManager(layoutManager);


            loadMenu();

    }

        private void loadMenu () {
         adapter = new FirebaseRecyclerAdapter<Category,
                MenuViewHolder>(Category.class, R.layout.menu_item, MenuViewHolder.class, category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                final Category clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                       Intent foodList= new Intent(Home.this,FoodList.class);
                       foodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                       startActivity(foodList);

                        }

                });
            }
        };
        recycler_menu.setAdapter(adapter);
    }


        @Override
        public void onBackPressed () {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.home,menu);
        return true;

    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){


        return super.onOptionsItemSelected(item);
    }



        public boolean onNavigationItemSelected (MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {

        } else if (id == R.id.nav_cart) {
            Intent cartIntent = new Intent(Home.this, Cart.class);
            startActivity(cartIntent);

        } else if (id == R.id.nav_logout) {
            Intent signIn = new Intent(Home.this, Signin.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Agar tidak bisa menekan tombol kembali setelah di LogOut
            startActivity(signIn);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    }
