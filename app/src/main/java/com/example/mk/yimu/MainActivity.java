package com.example.mk.yimu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mk.yimu.Model.DatePickerFragment;
import com.example.mk.yimu.Model.FMostrarActividades;
import com.example.mk.yimu.Model.FPerfil;
import com.example.mk.yimu.Model.FSeleccionHora;
import com.example.mk.yimu.Model.FragmentAPista;
import com.example.mk.yimu.Model.FragmentCactividad;
import com.example.mk.yimu.Model.PerfilActiviy;
import com.example.mk.yimu.Model.TimePickerFragment;
import com.example.mk.yimu.Model.Usuario;

//import com.example.mk.yimu.Model.TimePickerFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentCactividad.OnFragmentInteractionListener,FragmentAPista.OnFragmentInteractionListener,FSeleccionHora.OnFragmentInteractionListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //View header = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);

        //TextView texto = (TextView) header.findViewById(R.id.nombre);
        //texto.setText(Usuario.nombre);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View header = navigationView.getHeaderView(0);
        TextView text = (TextView) header.findViewById(R.id.nombre);
        TextView text1 = (TextView) header.findViewById(R.id.email);
        EditText hora=(EditText)  findViewById(R.id.hora);
        text.setText("Usuario: "+Usuario.nombre1);
        text1.setText("");
        text1.setText("Email:" +Usuario.email1);


        navigationView.setNavigationItemSelectedListener(this);

        // navigationView.addHeaderView(header);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        if (id == R.id.a) {
            fragment = new FragmentAPista();
            fragmentTransaction = true;


        } else if (id == R.id.b) {


        } else if (id == R.id.c) {
            fragment = new FragmentCactividad();
            fragmentTransaction = true;

        } else if (id == R.id.d) {

        } else if (id == R.id.g) {
            Intent intent = new Intent(MainActivity.this, PerfilActiviy.class);
            startActivity(intent);
        } else if (id == R.id.f) {
            fragment = new FMostrarActividades();
            fragmentTransaction = true;

        }
        if(fragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment1, fragment)
                    .commit();

            //menuItem.setChecked(true);
            //getSupportActionBar().setTitle(menuItem.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

  public void showTimePickerDialog(View v) {
        EditText time=(EditText) findViewById(R.id.hora);
          DialogFragment newFragment = new TimePickerFragment(time);
          newFragment.show(getSupportFragmentManager(), "timePicker");

      }
    public void showDatePickerDialog(View v) {
        EditText time=(EditText) findViewById(R.id.date);
        DatePickerFragment newFragment = new DatePickerFragment(time);

        newFragment.show(getFragmentManager(), "datePicker");
    }


}


