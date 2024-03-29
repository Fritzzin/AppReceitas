package com.example.appreceitas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Usuario;
import com.example.appreceitas.DAO.UsuarioDAO;
import com.example.appreceitas.R;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    private EditText textLogin;
    private EditText textSenha;

    private TextView tvErroUsuario;
    private TextView tvErroSenha;
    private TextView tvErroLogin;

    private Button btnLogin;
    private Button btnCadastroUsuario;

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarFindById();
        limparTexto();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verificarLogin()) {
                    realizarLogin();
                }
            }
        });

        btnCadastroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastroUsuario();
            }
        });
    }

    public void realizarLogin() {
        String login = textLogin.getText().toString();
        String senha = textSenha.getText().toString();
        String senhaEncriptada = encriptarSenha(senha);


        Usuario usuarioBanco = new UsuarioDAO(db).buscarUsuario(login);

        if (login.equals(usuarioBanco.getNome()) && senhaEncriptada.equals(usuarioBanco.getSenha())) {
            tvErroLogin.setText("");
            tvErroLogin.setVisibility(View.INVISIBLE);

            abrirDashboard(usuarioBanco);
        } else {
            tvErroLogin.setText("Erro ao realizar login! Verifique o usuário e senha.");
            tvErroLogin.setVisibility(View.VISIBLE);
        }
    }

    public boolean verificarLogin() {
        boolean retorno = false;

        String login = textLogin.getText().toString();
        String senha = textSenha.getText().toString();

        boolean checkTamanhoLogin = (login.length() >= 5);
        boolean checkTamanhoSenha = (senha.length() >= 5);

        if (checkTamanhoLogin) {
            tvErroUsuario.setVisibility(View.INVISIBLE);
            tvErroUsuario.setText("");
        } else {
            tvErroUsuario.setText("Necessário mais de 5 caracteres");
            tvErroUsuario.setVisibility(View.VISIBLE);
        }

        if (checkTamanhoSenha) {
            tvErroSenha.setVisibility(View.INVISIBLE);
            tvErroSenha.setText("");
        } else {
            tvErroSenha.setText("Necessário mais de 5 caracteres");
            tvErroSenha.setVisibility(View.VISIBLE);
        }

        if (checkTamanhoLogin && checkTamanhoSenha) {
            retorno = true;
        }

        return retorno;
    }

    public void iniciarFindById() {
        textLogin = (EditText) findViewById(R.id.editTextLogin);
        textSenha = (EditText) findViewById(R.id.editTextSenha);

        tvErroUsuario = (TextView) findViewById(R.id.tvErroUsuario);
        tvErroSenha = (TextView) findViewById(R.id.tvErroSenha);
        tvErroLogin = (TextView) findViewById(R.id.tvErroLogin);

        tvErroUsuario.setVisibility(View.INVISIBLE);
        tvErroSenha.setVisibility(View.INVISIBLE);
        tvErroLogin.setVisibility(View.INVISIBLE);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCadastroUsuario = (Button) findViewById(R.id.btnCadastro);
    }

    public void abrirDashboard(Usuario usuario) {
        Intent myIntent = new Intent(this, DashboardUsuario.class);
        myIntent.putExtra("idUsuario", usuario.getId());
        myIntent.putExtra("nome", usuario.getNome());
        myIntent.putExtra("tipo", usuario.getTipo());

        limparTexto();

        this.startActivity(myIntent);
    }

    public void abrirCadastroUsuario() {
        Intent myIntent = new Intent(this, CriarUsuario.class);

        limparTexto();

        this.startActivity(myIntent);
    }

    public void limparTexto() {
        textLogin.setText("");
        textSenha.setText("");
    }

    public static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String encriptarSenha(String senha) {
        byte[] shaInBytes = digest(senha.getBytes(UTF_8));
        return bytesToHex(shaInBytes);
    }
}