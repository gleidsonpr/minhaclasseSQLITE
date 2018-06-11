package com.gleidsonviana.meusom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Gleidson Viana on 10/06/2018.
 */

public class BDController {
    private SQLiteDatabase db;
    private CriarBD banco;

    public BDController(Context context){
        banco = new CriarBD(context);
    }

    public String insereDado(String _id,String titulo,String artista,String duracao,String data,String display_name,String flgFavorito, String flgExcluir){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriarBD._id, _id);
        valores.put(CriarBD.titulo, titulo);
        valores.put(CriarBD.artista, artista);
        valores.put(CriarBD.duracao, duracao);
        valores.put(CriarBD.data, data);
        valores.put(CriarBD.display_name, display_name);
        valores.put(CriarBD.flgFavorito, flgFavorito);
        valores.put(CriarBD.flgExcluir, flgExcluir);
        resultado = db.insert(CriarBD.tabela, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }


    public Cursor carregaDados(boolean fav, Boolean exc){
        Cursor cursor;
        String[] campos =  {
                banco._id,
                banco.id,
                banco.titulo,
                banco.artista,
                banco.duracao,
                banco.data,
                banco.display_name,
                banco.flgFavorito,
                banco.flgExcluir
        };

        String where=" ";

        if(exc){
                where=" flgExcluir = 1 ";
        }
        else if(fav){
            where=" flgFavorito = 1 ";
        }
        else {
            where=" flgFavorito = 0 and  flgExcluir = 0";
        }

        db = banco.getReadableDatabase();
        cursor = db.query(banco.tabela, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void marcarRegistroParaEcluir(int id,String flgMarcarExcluir){
        //seta 1 para excluir ou zero para não
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriarBD.id + "=" + id;

        valores = new ContentValues();
        valores.put(CriarBD.flgExcluir, flgMarcarExcluir);

        db.update(CriarBD.tabela,valores,where,null);
        db.close();
    }


    public void marcarRegistroParaFavorito(int id,String flgMarcarFavorito){
        //seta 1 para favorito ou zero para não
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriarBD.id + "=" + id;

        valores = new ContentValues();
        valores.put(CriarBD.flgFavorito, flgMarcarFavorito);

        db.update(CriarBD.tabela,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = CriarBD.id + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriarBD.tabela,where,null);
        db.close();
    }
}
