package com.example.appreceitas.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Receita;

import java.util.ArrayList;

public class ReceitaIngredienteDAO {
    BancoDados bd;

    private static final String TABELA = "Receita_Ingrediente";

    public ReceitaIngredienteDAO(BancoDados bd) {
        this.bd = bd;
    }

    public void salvar(int idIngrediente, int idReceita, String quantidade, String tipoQtd) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("idReceita", idReceita);
            values.put("idIngrediente", idIngrediente);
            values.put("quantidade", quantidade);
            values.put("tipoQtd", tipoQtd);
            db.insert(TABELA, null, values);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public ArrayList<Receita> resultadoPesquisaLista(ArrayList<String> lista) {
        String query = "SELECT r.id, r.nome, r.idAutor, i.nome AS 'ingrediente' " +
                "FROM Receita r, Ingrediente i, Receita_Ingrediente ri " +
                "WHERE r.id = ri.idReceita " +
                "AND i.id = ri.idIngrediente " +
                "AND (";

        for (int i = 0; i < lista.size(); i++) {
            query += " i.nome LIKE '%" + lista.get(i).toString() + "%' ";

            if (!(i == (lista.size() - 1))) {
                query += " OR ";
            }
        }
        query += ");";
        Log.i("teste", query);

        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        ArrayList<Receita> listaReceitas = new ArrayList<Receita>();
        try {
            do {
                int codigo = cursor.getInt(0);
                String nomeReceita = cursor.getString(1);
                int idAutor = cursor.getInt(2);
                Receita receita = new Receita(codigo, nomeReceita, idAutor);
                listaReceitas.add(receita);
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {
        }

//        for (int i = 0; i < listaReceitas.size(); i++) {
//            Log.i("teste",listaReceitas.get(i).getNome());
//        }
//        Receive recipes back that contains at least one of the ingredients
//        compare list of ingredients with the recipe ingredients
//        from the comparison see how many ingredients are used in each recipe
//        add them to the subtext
        return listaReceitas;
    }

    public ArrayList<String> resultadoReceitas(ArrayList<String> lista) {
        String query = "SELECT r.id, r.nome, r.idAutor, i.nome AS 'ingrediente' " +
                "FROM Receita r, Ingrediente i, Receita_Ingrediente ri " +
                "WHERE r.id = ri.idReceita " +
                "AND i.id = ri.idIngrediente " +
                "AND (";

        for (int i = 0; i < lista.size(); i++) {
            query += " i.nome LIKE '%" + lista.get(i).toString() + "%' ";

            if (!(i == (lista.size() - 1))) {
                query += " OR ";
            }
        }
        query += ") " +
                "GROUP BY r.nome;";
        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        ArrayList<String> listaReceitas = new ArrayList<String>();
        try {
            do {
                String nomeReceita = cursor.getString(1);
                listaReceitas.add(nomeReceita);
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {
        }
        return listaReceitas;
    }

    public boolean excluir(int id) {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            String where = "id" + "=" + id;
            db.delete(TABELA, where, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
