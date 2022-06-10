package com.example.appreceitas.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.example.appreceitas.Apoio.BancoDados;
import com.example.appreceitas.Class.Receita;

import java.util.ArrayList;

public class ReceitaFavoritaDAO {
    BancoDados bd;

    private static final String TABELA = "Receita_Favorita";
    private static final String COLUNA_CODIGO = "id";
    private static final String COLUNA_IDUSUARIO = "idUsuario";
    private static final String COLUNA_IDRECEITA = "idReceita";
    private static final String COLUNA_FAVORITO = "favorito";

    public ReceitaFavoritaDAO(BancoDados bd) {
        this.bd = bd;
    }

    public boolean isFavorite(int idUsuario, int idReceita) {
        boolean retorno = false;

        SQLiteDatabase db = bd.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA + " " +
                "WHERE " + COLUNA_IDUSUARIO + " = " + idUsuario + " " +
                "AND " + COLUNA_IDRECEITA + " = " + idReceita + "; ";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            do {
                String favorito = cursor.getString(3);

                if(favorito.equals("TRUE")) {
                    retorno = true;
                }
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException c) {
        }
        return retorno;
    }

    public boolean modificarFavorito(int idUsuario, int idReceita, String favorito){
        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            String where = COLUNA_IDRECEITA + "=" + idReceita + " AND " + COLUNA_IDUSUARIO + "=" + idUsuario;

            ContentValues values = new ContentValues();
            values.put(COLUNA_FAVORITO, favorito);
            db.update(TABELA, values, where, null);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
