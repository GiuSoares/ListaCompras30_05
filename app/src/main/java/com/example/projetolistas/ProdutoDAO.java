package com.example.projetolistas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetolistas.BancodeDados.Banco;
import com.example.projetolistas.JavaClasses.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static final void inserir(Produto produto, Context context) {

        Banco banco = new Banco(context);

        ContentValues valores = new ContentValues();

        valores.put("NomeProduto", produto.getNome());
        valores.put("Preco", produto.getPreco());
        valores.put("Qtde", produto.getQtde());

        //valores.put("texto", produto.() );

        SQLiteDatabase db = banco.getWritableDatabase();
        db.insert("lista", null, valores);
    }

    public static final void excluir(int id, Context context) {

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("lista", "id = " + id, null);
    }

    public static final List<Produto> listar(Context context) {
        List<Produto> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        String sql = "SELECT * FROM lista ORDER BY id DESC ";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            do {

                Produto produto = new Produto();
                produto.setId(cursor.getInt(0));
                produto.setNome(cursor.getString(4));
                produto.setQtde(cursor.getInt(1));
                produto.setPreco(cursor.getFloat(3));

                lista.add(produto);

            } while (cursor.moveToNext());
        }
        return lista;
    }

}

