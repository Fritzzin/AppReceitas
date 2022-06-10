package com.example.appreceitas.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Ingrediente;
import com.example.appreceitas.Class.Receita;

import java.util.ArrayList;

public class IngredienteDAO {
    BancoDados bd;

    private static final String TABELA = "Ingrediente";
    private static final String COLUNA_CODIGO = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_TIPO = "tipo";

    public IngredienteDAO(BancoDados bd) {
        this.bd = bd;
    }

    public boolean salvar(Ingrediente ingrediente) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUNA_NOME, ingrediente.getNome());
            values.put(COLUNA_TIPO, ingrediente.getTipo());

            db.insert(TABELA, null, values);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            String where = COLUNA_CODIGO + "=" + id;
            db.delete(TABELA, where, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Ingrediente ingrediente, int id) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            String where = COLUNA_CODIGO + "=" + id;

            ContentValues values = new ContentValues();
            values.put(COLUNA_NOME, ingrediente.getNome());
            values.put(COLUNA_TIPO, ingrediente.getTipo());
            db.update(TABELA, values, where, null);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Ingrediente> listarTodos() {
        ArrayList<Ingrediente> lista = new ArrayList<>();

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            do {
                int codigo = cursor.getInt(0);
                String nome = cursor.getString(1);
                String tipo = cursor.getString(2);

                Ingrediente ingrediente = new Ingrediente(codigo, nome, tipo);
                lista.add(ingrediente);
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {
        }
        return lista;
    }

    public ArrayList<Ingrediente> buscarIngredienteNome(String nomeIngrediente) {
        ArrayList<Ingrediente> lista = new ArrayList<>();

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA +" " +
                "WHERE nome LIKE '%"+nomeIngrediente+"%';";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try{
            do {
                int codigo = cursor.getInt(0);
                String nome = cursor.getString(1);
                String tipo = cursor.getString(2);

                Ingrediente ingrediente = new Ingrediente(codigo, nome, tipo);
                lista.add(ingrediente);
            } while (cursor.moveToNext());
        }catch (CursorIndexOutOfBoundsException c) {

        }
        return lista;
    }

    public ArrayList<Ingrediente> buscarIngredientesReceita(int idReceita) {
        ArrayList<Ingrediente> lista = new ArrayList<>();

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT i.id, i.nome, i.tipo, ri.quantidade, ri.tipoQtd FROM Ingrediente i, Receita r, Receita_Ingrediente ri " +
                "WHERE r.id = '"+idReceita+"'" +
                "AND r.id = ri.idReceita " +
                "AND i.id = ri.idIngrediente;";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try{
            do {
                int codigo = cursor.getInt(0);
                String nome = cursor.getString(1);
                String tipo = cursor.getString(2);
                String quantidade = cursor.getString(3);
                String tipoQtd = cursor.getString(4);

                Ingrediente ingrediente = new Ingrediente(codigo, nome, tipo, quantidade, tipoQtd);
                lista.add(ingrediente);
            } while (cursor.moveToNext());
        }catch (CursorIndexOutOfBoundsException c) {

        }
        return lista;
    }
}

