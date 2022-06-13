package com.example.appreceitas.Apoio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class BancoDados extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 3;
    private static final String BANCO_LOCATION = "bd_location";

    private static final String QUERY_USUARIO = "CREATE TABLE IF NOT EXISTS Usuario ( " +
            "id INTEGER PRIMARY KEY, " +
            "nome TEXT NOT NULL, " +
            "senha TEXT NOT NULL, " +
            "tipo TEXT NOT NULL " +
            ");";

    private static final String QUERY_INGREDIENTE = "CREATE TABLE IF NOT EXISTS Ingrediente ( " +
            "id INTEGER PRIMARY KEY, " +
            "nome TEXT NOT NULL, " +
            "tipo TEXT NOT NULL " +
            ");";

    private static final String QUERY_RECEITA = "CREATE TABLE IF NOT EXISTS Receita ( " +
            "id INTEGER PRIMARY KEY, " +
            "nome TEXT NOT NULL, " +
            "idAutor INTEGER NOT NULL, " +
            "modoPreparo TEXT NOT NULL, " +
            "FOREIGN KEY (idAutor) REFERENCES Usuario(id) " +
            ");";

    private static final String QUERY_RECEITA_INGREDIENTE = "CREATE TABLE IF NOT EXISTS Receita_Ingrediente ( " +
            "id INTEGER PRIMARY KEY, " +
            "idReceita INTEGER NOT NULL, " +
            "idIngrediente INTEGER NOT NULL, " +
            "quantidade TEXTO NOT NULL, " +
            "tipoQtd TEXTO NOT NULL, " +
            "FOREIGN KEY (idReceita) REFERENCES Receita(id), " +
            "FOREIGN KEY (idIngrediente) REFERENCES Ingrediente(id) " +
            ");";

    private static final String QUERY_COMENTARIO = "CREATE TABLE IF NOT EXISTS Comentario ( " +
            "id INTEGER PRIMARY KEY, " +
            "idUsuario INTEGER NOT NULL, " +
            "idReceita INTEGER NOT NULL, " +
            "texto TEXT NOT NULL, " +
            "FOREIGN KEY (idUsuario) REFERENCES Usuario(id), " +
            "FOREIGN KEY (idReceita) REFERENCES Receita(id) " +
            ");";

    private static final String QUERY_RECEITA_FAVORITA = "CREATE TABLE IF NOT EXISTS Receita_Favorita( " +
            "id INTEGER PRIMARY KEY, " +
            "idUsuario INTEGER NOT NULL, " +
            "idReceita INTEGER NOT NULL, " +
            "favorito TEXT NOT NULL, " +
            "FOREIGN KEY (idUsuario) REFERENCES Usuario(id), " +
            "FOREIGN KEY (idReceita) REFERENCES Receita(id) " +
            ");";

    private static final String INSERT_USUARIO = "INSERT INTO Usuario(nome, senha, tipo) VALUES ( " +
            "'Augusto', " +
            "'da7a12cc87257e61ee52fcf7283aded557fb92e293c24cb127f1858b69d97b2b', " +
            "'Admin' " +
            ");";

    private static final String INSERT_INGREDIENTE = "INSERT INTO Ingrediente (nome, tipo) VALUES ( " +
            "'Ovo', " +
            "'Animal' " +
            ");";

    private static final String INSERT_RECEITA = "INSERT INTO Receita (nome, idAutor, modoPreparo) VALUES ( " +
            "'Ovos Mexidos', " +
            "1 ," +
            "'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc porttitor turpis vitae " +
            "congue vehicula. Ut nec dolor non massa varius sollicitudin at eget libero. Aenean " +
            "porta lacinia tortor ac finibus. Fusce tempor, sapien semper efficitur tristique, " +
            "diam sapien blandit dui, at facilisis sem nulla vitae leo.'" +
            ");";

    private static final String INSERT_COMENTARIO = "INSERT INTO Comentario (idUsuario, idReceita, texto) VALUES ( " +
            "1, " +
            "1, " +
            "'Comentario teste para a aplicacao' " +
            ");";

    private static final String INSERT_RECEITA_INGREDIENTE = "INSERT INTO Receita_Ingrediente (idReceita, idIngrediente, quantidade, tipoQtd) VALUES ( " +
            "1, " +
            "1, " +
            "'1', " +
            "'Unidade(s)' " +
            ");";

    private static final String INSERT_RECEITA_FAVORITA = "INSERT INTO Receita_Favorita (idUsuario, idReceita, favorito) VALUES ( " +
            "1, " +
            "1, " +
            "'TRUE' " +
            ");";

    public BancoDados(@Nullable Context context) {
        super(context, BANCO_LOCATION, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_USUARIO);
        db.execSQL(QUERY_INGREDIENTE);
        db.execSQL(QUERY_RECEITA);
        db.execSQL(QUERY_RECEITA_INGREDIENTE);
        db.execSQL(QUERY_COMENTARIO);
        db.execSQL(QUERY_RECEITA_FAVORITA);

        db.execSQL(INSERT_USUARIO);
        db.execSQL(INSERT_INGREDIENTE);
        db.execSQL(INSERT_RECEITA);
        db.execSQL(INSERT_COMENTARIO);
        db.execSQL(INSERT_RECEITA_INGREDIENTE);
        db.execSQL(INSERT_RECEITA_FAVORITA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void resetaDB() {
        SQLiteDatabase db = getWritableDatabase();
        onOpen(db);
        db.execSQL("DELETE FROM Usuario");
        db.execSQL("DELETE FROM Ingrediente");
        db.execSQL("DELETE FROM Receita");
        db.execSQL("DELETE FROM Receita_Ingrediente");
        db.execSQL("DELETE FROM Comentario");
        db.execSQL("DELETE FROM Receita_Favorita");

        db.execSQL(INSERT_USUARIO);
        db.execSQL(INSERT_INGREDIENTE);
        db.execSQL(INSERT_RECEITA);
        db.execSQL(INSERT_COMENTARIO);
        db.execSQL(INSERT_RECEITA_INGREDIENTE);
        db.execSQL(INSERT_RECEITA_FAVORITA);
        db.close();
    }
}
