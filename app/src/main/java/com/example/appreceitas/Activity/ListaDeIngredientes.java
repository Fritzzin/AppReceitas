package com.example.appreceitas.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Ingrediente;
import com.example.appreceitas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaDeIngredientes extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    Button btnVoltar;
    Button btnPesquisar;
    FloatingActionButton btnAdicionar;
    ListView listaIngredientes;
    TextView textoErro;
    ArrayList<String> lista = new ArrayList<String>();
    ArrayList<Ingrediente> listaId = new ArrayList<Ingrediente>();
    int idUsuario;


    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 78) {
                        Intent intent = result.getData();

                        textoErro.setVisibility(View.INVISIBLE);
                        textoErro.setText("");

                        if (intent != null) {
//                            Extract data
                            String nome = intent.getStringExtra("nome");
                            String id = intent.getStringExtra("idIngrediente");
                            adicionarLista(nome, Integer.parseInt(id));
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_ingredientes);
        iniciarFindViewById();

        if (getIntent().hasExtra("idUsuario")) {
            Bundle extras = getIntent().getExtras();
            idUsuario = extras.getInt("idUsuario");
        }


        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirListaIngredientes();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirPesquisaReceitas();
            }
        });

        listaIngredientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                listaId.remove(i);
                lista.remove(i);

                setArrayAdapter(lista);
            }
        });
    }

    public void iniciarFindViewById() {
        btnVoltar = (Button) findViewById(R.id.listaIngredientesBtnVoltar);
        btnPesquisar = (Button) findViewById(R.id.listaIngredientesBtnPesquisar);
        btnAdicionar = (FloatingActionButton) findViewById(R.id.listaIngredientesBtnAdd);
        listaIngredientes = (ListView) findViewById(R.id.listaIngredientesLista);
        textoErro = (TextView) findViewById(R.id.listaIngredientesTvErro);
        textoErro.setText("");
        textoErro.setVisibility(View.INVISIBLE);
    }

    public void voltar() {
        this.finish();
    }

    private void setArrayAdapter(ArrayList<String> lista) {
        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                lista);
        listaIngredientes.setAdapter(arrayAdapter);
    }

    private void abrirListaIngredientes() {
        Intent myIntent = new Intent(this, ListarIngredientesTodos.class);
        activityLauncher.launch(myIntent);
//        this.startActivity(myIntent);
    }

    public void adicionarLista(String nome, int id) {
        Ingrediente ingrediente = new Ingrediente(id, nome);

        boolean duplicado = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(nome)) {
                duplicado = true;
            }
        }

        if (!duplicado) {
            this.lista.add(nome);
            this.listaId.add(ingrediente);
        }
        setArrayAdapter(lista);
    }

    public void abrirPesquisaReceitas() {
        if (lista.size() == 0 || lista == null) {
            textoErro.setVisibility(View.VISIBLE);
            textoErro.setText("Adicione pelo menos um item na lista!");
        } else {
            textoErro.setVisibility(View.INVISIBLE);
            textoErro.setText("");

            Intent myIntent = new Intent(this, ResultadoPesquisa.class);
            myIntent.putExtra("idUsuario", idUsuario);

            for (int i = 0; i < lista.size(); i++) {
                String tag = "ingrediente" + i;
                myIntent.putExtra(tag, lista.get(i));
            }
            myIntent.putExtra("qtd", lista.size());
            this.startActivity(myIntent);

            for (int i = 0; i < listaId.size(); i++) {
                Log.i("teste", "ID: " + listaId.get(i).getId() + "      NOME: " + listaId.get(i).getNome());
            }
        }
    }
}