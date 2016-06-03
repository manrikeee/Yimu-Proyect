package com.example.mk.yimu.Model;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mk.yimu.Interface.DeportesService;
import com.example.mk.yimu.LoginActivity;
import com.example.mk.yimu.MainActivity;
import com.example.mk.yimu.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Mk on 19/05/2016.
 */
public class PerfilActiviy  extends AppCompatActivity {
    ImageView avatar;
    Button changeAvatar;
    static TextView nombre;
    TextView email;
    RelativeLayout layout;
    ImageButton button;
    View vista;
    ArrayList<String> fromColumns;
    private AlertDialog _photoDialog;
    private Uri mImageUri;
    public static ArrayAdapter<String>adapter;
    private static final int ACTIVITY_SELECT_IMAGE = 1020,

           ACTIVITY_SELECT_FROM_CAMERA = 1040, ACTIVITY_SHARE = 1030;
   // private PhotoUtils photoUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
         nombre= (TextView) findViewById(R.id.nombre);
         email= (TextView) findViewById(R.id.email);
        layout= (RelativeLayout) findViewById(R.id.deportes);
        button= (ImageButton) findViewById(R.id.add_deporte);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLocationDialog();
            }
        });

        nombre.setText(Usuario.nombre1);
        email.setText(Usuario.email1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarUsuarios();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "aqui llamo a la funcion", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fromColumns = new ArrayList<>();
        double suma=0;
        for (Deporte deporte: LoginActivity.deportes_disponibles){
            fromColumns.add(deporte.getNombre());

        }

    }
    public void mostrarUsuarios() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PerfilActiviy.this);
        builder.setTitle("Deportes Favoritos");
        ArrayList<String> nombres=new ArrayList<>();

        ListView modeList = new ListView(nombre.getContext());
        for(Deporte deporte: LoginActivity.deportes_usuario) {
            nombres.add(deporte.getNombre());
        }
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(nombre.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, nombres);
        modeList.setAdapter(modeAdapter);

        builder.setView(modeList);
        final Dialog dialog = builder.create();

        dialog.show();

        Log.i("allEvents", "ERROR12 : "  );
    }


    public void showLocationDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(PerfilActiviy.this);





        builder.setTitle("Deportes");

         final ListView listview=new ListView(nombre.getContext());
    adapter = new ArrayAdapter<String>(nombre.getContext(), android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, fromColumns);

        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listview.setHeaderDividersEnabled(true);

        listview.setAdapter(adapter);
        builder.setView(listview);
        //builder.setMessage("Ey LOco2");
        //builder.setMessage("Ey LOco3");

        String positiveText = "Añadir Deporte";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                      Eliminar(listview);


                    }


                });


        String negativeText ="Cancelar ";
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void showLocationDialog2() {
    AlertDialog.Builder builder = new AlertDialog.Builder(nombre.getContext());
builder.setTitle("Deportes Favoritos");
        ArrayList<String> nombres=new ArrayList<>();

        ListView modeList = new ListView(nombre.getContext());
        for(Deporte deporte: LoginActivity.deportes_usuario) {
        nombres.add(deporte.getNombre());
        }
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(nombre.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, nombres);
        modeList.setAdapter(modeAdapter);

        builder.setView(modeList);
final Dialog dialog = builder.create();

        dialog.show();

        Log.i("allEvents", "ERROR12 : "  );
        }
    public void Eliminar(ListView lw) {
        String deporte=fromColumns.get(lw.getCheckedItemPosition());
        InsertarDeporte(deporte);


    }

    public static void InsertarDeporte(final String deporte){
        Deporte deporte_nuevo=new Deporte();


    for(Deporte deporte1: LoginActivity.deportes_disponibles){
        if (deporte1.getNombre().equals(deporte)){
            deporte_nuevo=deporte1;
        }
    }


        RestClient restClient =new RestClient();
        Retrofit retrofit=restClient.getRetrofit();
        DeportesService service = retrofit.create(DeportesService.class);
        final Call<String> respuesta= service.setDeporte(Usuario.id1,deporte_nuevo.getId(),"Principiante");
        respuesta.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(nombre.getContext(), "Deporte añadido a favoritos", Toast.LENGTH_SHORT).show();
                LoginActivity.recibirDeportes();

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}



