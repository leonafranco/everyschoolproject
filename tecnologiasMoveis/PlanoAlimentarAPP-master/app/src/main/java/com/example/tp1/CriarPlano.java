package com.example.tp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class CriarPlano extends AppCompatActivity {

    GerePlanos gerePlanos;
    int hora;
    int minuto;
    EditText txtHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_plano);

        gerePlanos = (GerePlanos) getIntent().getSerializableExtra("refGere");

        //Obtemos o id das caixas de texto visiveis no ecrã
        txtHora = findViewById(R.id.editTextTimeHora);
        EditText txtRefeicao = findViewById(R.id.editTextTextRefeicao);
        EditText txtInformacao = findViewById(R.id.editTextTextInformacao);

        gravarRefeicao(txtRefeicao, txtInformacao, txtHora);    //É executado quando clicar no botão Gravar

        cancelarRefeicao(txtRefeicao, txtInformacao, txtHora);     //É executado quando clicar no botão Cancelar

        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CriarPlano.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,hora, minuto);
                        txtHora.setText(DateFormat.format("HH:mm", calendar));
                    }
                }, 12,0,true
                );
                timePickerDialog.updateTime(hora, minuto);
                timePickerDialog.show();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("Hora", hora);
        outState.putInt("Minutos", minuto);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CriarPlano.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,hora, minuto);
                        txtHora.setText(DateFormat.format("HH:mm", calendar));
                    }
                }, 12,0,true
                );
                timePickerDialog.updateTime(savedInstanceState.getInt("Hora"), savedInstanceState.getInt("Minutos"));
                timePickerDialog.show();
            }
        });

    }

    public void gravarRefeicao(EditText aTxtRefeicao, EditText aTxtInformacao, EditText aTxtHora) {

        Button btnGravar = findViewById(R.id.buttonGravar);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkHour(aTxtHora)) {
                    if (aTxtRefeicao.getText().toString().length() > 0 && aTxtInformacao.getText().toString().length() > 0 && aTxtHora.getText().toString().length() > 0) {
                        PlanoAlimentar p = new PlanoAlimentar(aTxtRefeicao.getText().toString(), aTxtInformacao.getText().toString(), aTxtHora.getText().toString());
                        if (!gerePlanos.adicionarRefeicao(p)){
                            Toast.makeText(CriarPlano.this, "A essa hora já existe uma refeicao com o mesmo nome.", Toast.LENGTH_SHORT).show();
                        }else{
                            DB dataBaseHelper = new DB(CriarPlano.this);
                            dataBaseHelper.adicionaPlano(p);
                            Intent intent = new Intent();
                            intent.putExtra("backGere", gerePlanos);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }else if(aTxtInformacao.getText().toString().length() == 0) {
                        Toast.makeText(CriarPlano.this, "Precisa de inserir uma Informação não vazia", Toast.LENGTH_SHORT).show();
                    }else if(aTxtRefeicao.getText().toString().length() == 0) {
                        Toast.makeText(CriarPlano.this, "Precisa de inserir uma Refeição não vazia", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CriarPlano.this, "Formato da hora necessita ser HH:mm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void cancelarRefeicao(EditText aTxtRefeicao, EditText aTxtInformacao, EditText aTxtHora) {
        Button btnCancelar = findViewById(R.id.buttonCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aTxtRefeicao.setText("");   //Limpa os dados da caixa de texto para inserir a refeição
                aTxtInformacao.setText("");     //Limpa os dados da caixa de texto para a informação
                aTxtHora.setText("");   //Limpaa os dados da caixa de texto para inserir as horas

                finish();   //Vai redirecionar para a activity anterior
            }
        });
    }

    public boolean checkHour(EditText aTxtHora) {
        if (aTxtHora.getText().toString().length() != 5) return false;
        if (aTxtHora.getText().toString().split(":")[0].length() != 2 || aTxtHora.getText().toString().split(":")[1].length() != 2)
            return false;
        int hora = Integer.parseInt(aTxtHora.getText().toString().split(":")[0]);
        int minuto = Integer.parseInt(aTxtHora.getText().toString().split(":")[1]);
        if (hora < 0 || hora > 23 || minuto < 0 || minuto > 59) return false;
        return true;
    }

}