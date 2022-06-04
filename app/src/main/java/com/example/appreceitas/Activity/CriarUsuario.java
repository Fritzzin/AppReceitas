package com.example.appreceitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Usuario;
import com.example.appreceitas.DAO.UsuarioDAO;
import com.example.appreceitas.R;

public class CriarUsuario extends AppCompatActivity {

    BancoDados db = new BancoDados(this);

    private EditText textLogin;
    private EditText textSenha;
    private EditText textSenhaConfirmar;

    private Button btnCadastrar;
    private Button btnVoltar;

    private TextView erroUsuario;
    private TextView erroSenha;
    private TextView erroSenhaConfirmar;
    private TextView erroUsuario2;
    private TextView erroSenhaConfirmar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_usuario);
        iniciarFindById();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verificarCadastro()) {
                    realizarCadastro();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecharTela();
            }
        });

    }

    public void iniciarFindById() {
        textLogin = (EditText) findViewById(R.id.cadastroEtLogin);
        textSenha = (EditText) findViewById(R.id.cadastroEtSenha);
        textSenhaConfirmar = (EditText) findViewById(R.id.cadastroEtSenhaConfirmar);

        btnCadastrar = (Button) findViewById(R.id.cadastroButtonCadastrar);
        btnVoltar = (Button) findViewById(R.id.cadastroButtonVoltar);

        erroUsuario = (TextView) findViewById(R.id.cadastroErroLogin);
        erroSenha = (TextView) findViewById(R.id.cadastroErroSenha);
        erroSenhaConfirmar = (TextView) findViewById(R.id.cadastroErroSenhaConfirmar);
        erroUsuario2 = (TextView) findViewById(R.id.cadastroErroLogin2);
        erroSenhaConfirmar2 = (TextView) findViewById(R.id.cadastroErroSenhaConfirmar2);

        erroUsuario.setText("");
        erroSenha.setText("");
        erroSenhaConfirmar.setText("");
        erroUsuario2.setText("");
        erroSenhaConfirmar2.setText("");

        erroUsuario.setVisibility(View.INVISIBLE);
        erroSenha.setVisibility(View.INVISIBLE);
        erroSenhaConfirmar.setVisibility(View.INVISIBLE);
        erroUsuario2.setVisibility(View.INVISIBLE);
        erroSenhaConfirmar2.setVisibility(View.INVISIBLE);
    }

    public void realizarCadastro() {
        String login = textLogin.getText().toString();
        String senha = textSenha.getText().toString();
        Usuario usuario = new Usuario(login, senha, "Usuário");

        UsuarioDAO dao = new UsuarioDAO(db);
        dao.salvar(usuario);

        textLogin.setText("");
        textSenha.setText("");
        textSenhaConfirmar.setText("");

        fecharTela();
    }

    public void fecharTela(){
        this.finish();
    }

    public boolean verificarCadastro() {
        boolean retorno = false;

        String login = textLogin.getText().toString();
        String senha = textSenha.getText().toString();
        String senhaConfirmar = textSenhaConfirmar.getText().toString();

        boolean checkTamanhoLogin = (login.length() >= 5);
        boolean checkTamanhoSenha = (senha.length() >= 5);
        boolean checkTamanhoSenhaConfirmar = (senhaConfirmar.length() >= 5);
        boolean checkLoginExistente = new UsuarioDAO(db).existeUsuario(login);
        boolean checkSenhasIguais = (senha.equals(senhaConfirmar));

        Log.i("Teste", ""+login.length());
        Log.i("Teste", ""+checkTamanhoLogin);

        if (checkTamanhoLogin) {
            erroUsuario.setVisibility(View.INVISIBLE);
            erroUsuario.setText("");
        } else {
            erroUsuario.setText("Necessário mais de 5 caracteres");
            erroUsuario.setVisibility(View.VISIBLE);
        }

        if (checkTamanhoSenha) {
            erroSenha.setVisibility(View.INVISIBLE);
            erroSenha.setText("");
        } else {
            erroSenha.setText("Necessário mais de 5 caracteres");
            erroSenha.setVisibility(View.VISIBLE);
        }

        if (checkTamanhoSenhaConfirmar) {
            erroSenhaConfirmar.setVisibility(View.INVISIBLE);
            erroSenhaConfirmar.setText("");
        } else {
            erroSenhaConfirmar.setText("Necessário mais de 5 caracteres");
            erroSenhaConfirmar.setVisibility(View.VISIBLE);
        }

        if (checkLoginExistente) {
            erroUsuario2.setText("\n Usuário já existente. Tente outro usuário!");
            erroUsuario2.setVisibility(View.VISIBLE);
            Log.i("Teste", "JA EXISTE LOGIN!");
        } else {
            erroUsuario2.setVisibility(View.INVISIBLE);
            erroUsuario2.setText("");
            Log.i("Teste", "Usuario disponivel!");
        }

        if (checkSenhasIguais) {
            erroSenhaConfirmar2.setVisibility(View.INVISIBLE);
            erroSenhaConfirmar2.setText("");
        } else {
            erroSenhaConfirmar.setText("As senhas não são iguais! Confirme a senha!");
            erroSenhaConfirmar.setVisibility(View.VISIBLE);
        }

        if (checkTamanhoLogin && checkTamanhoLogin && checkTamanhoSenhaConfirmar
                && !checkLoginExistente && checkSenhasIguais) {
            retorno = true;
        }

        return retorno;
    }

}