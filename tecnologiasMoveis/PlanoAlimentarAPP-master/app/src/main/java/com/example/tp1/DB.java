package com.example.tp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DB extends SQLiteOpenHelper {


    private GerePlanos gerePlanos;
    private GereHistoricoPlano gereHistoricoPlano;

    public DB(@Nullable Context context) {
        super(context, "planoAlimentar.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTablePlanoAlimentar = "CREATE TABLE PlanoAlimentar (P_ID INTEGER PRIMARY KEY AUTOINCREMENT, P_HORA  " +
                "VARCHAR(512) NOT NULL, P_REFEICAO  VARCHAR(512) NOT NULL, P_INFORMACAO_REFEICAO  VARCHAR(512) NOT NULL)";
        String createTableHistoricoAlimentar = "CREATE TABLE HistoricoPlanoAlimentar (H_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " H_HORA VARCHAR(512) NOT NULL, H_REFEICAO  VARCHAR(512) NOT NULL, H_INFORMACAO_REFEICAO  VARCHAR(512) NOT NULL, " +
                "H_dataCriacao Date Not Null, H_modoConsumida  VARCHAR(512) NOT NULL, H_horaRealizada  VARCHAR(512), H_observacao VARCHAR(512) NOT NULL)";



        db.execSQL(createTablePlanoAlimentar);
        db.execSQL(createTableHistoricoAlimentar);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean adicionaPlano(PlanoAlimentar planoAlimentar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("P_HORA", planoAlimentar.getHora());
        cv.put("P_REFEICAO", planoAlimentar.getRefeicao());
        cv.put("P_INFORMACAO_REFEICAO", planoAlimentar.getInformacaoRefeicao());

        long result = db.insert("PlanoAlimentar", null, cv);

        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean adicionaHistorico(HistoricoPlanoAlimentar historicoPlanoAlimentar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String date = new SimpleDateFormat("dd/MM/yyyy").format(historicoPlanoAlimentar.getDataCriacao());

        cv.put("H_HORA", historicoPlanoAlimentar.getHora());
        cv.put("H_REFEICAO", historicoPlanoAlimentar.getNomeRefeicao());
        cv.put("H_INFORMACAO_REFEICAO", historicoPlanoAlimentar.getInformacaoRefeicao());
        cv.put("H_dataCriacao", date);
        cv.put("H_modoConsumida", historicoPlanoAlimentar.getModoConsumida());
        cv.put("H_horaRealizada", historicoPlanoAlimentar.getHoraRealizada());
        cv.put("H_observacao", historicoPlanoAlimentar.getObservacao());

        long result = db.insert("HistoricoPlanoAlimentar", null, cv);

        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public GerePlanos listarPlano() {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from PlanoAlimentar";

        gerePlanos = new GerePlanos();


        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String hora = cursor.getString(1);
                String refeicao = cursor.getString(2);
                String informacaoRefeicao = cursor.getString(3);

                PlanoAlimentar p  = new PlanoAlimentar(refeicao, informacaoRefeicao, hora);
                gerePlanos.adicionarRefeicao(p);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return gerePlanos;

    }

    public GereHistoricoPlano listarHistorico() {

        SQLiteDatabase db = this.getReadableDatabase();
        gereHistoricoPlano = new GereHistoricoPlano();

        String query = "Select * from HistoricoPlanoAlimentar";


        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            String hora = cursor.getString(1);
            String refeicao = cursor.getString(2);
            String informacaoRefeicao = cursor.getString(3);
            String dataCriacao = cursor.getString(4);
            String modoConsumida = cursor.getString(5);
            String horaRealizada = cursor.getString(6);
            String observacao = cursor.getString(7);

            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataCriacao);

                HistoricoPlanoAlimentar h = new HistoricoPlanoAlimentar(hora, refeicao, informacaoRefeicao, date, horaRealizada, observacao, modoConsumida);

                gereHistoricoPlano.adicionarPlanoHistorico(h);

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        cursor.close();
        db.close();
        return gereHistoricoPlano;

    }

    public void updatePlano(PlanoAlimentar planoAlimentar, String mudanca) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("P_HORA", planoAlimentar.getHora());
        cv.put("P_REFEICAO", planoAlimentar.getRefeicao());
        cv.put("P_INFORMACAO_REFEICAO", planoAlimentar.getInformacaoRefeicao());


        db.update("PlanoAlimentar", cv,"P_REFEICAO = ?", new String[]{mudanca} );
        db.close();
    }
}
