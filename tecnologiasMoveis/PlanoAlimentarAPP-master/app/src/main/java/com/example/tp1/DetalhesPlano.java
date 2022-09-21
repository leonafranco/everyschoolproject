package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class DetalhesPlano extends AppCompatActivity {

    EditText txtRefeicao,txtInformacao,txtHora;
    int position;
    GerePlanos gerePlanos;
    int hora;
    int minuto;
    PlanoAlimentar index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_plano);
        txtRefeicao=findViewById(R.id.editTextTextRefeicao);
        txtHora=findViewById(R.id.editTextTimeHora);
        txtInformacao=findViewById(R.id.editTextTextInformacao);

        Bundle extras = getIntent().getExtras();

        if(extras!=null) {
            position = extras.getInt("position");
            gerePlanos =  (GerePlanos) extras.getSerializable("gerePlano");
        }

        System.out.println("??  "+gerePlanos.listaPlanos().size()+"  ??\n\n");

        index = gerePlanos.listaPlanos().get(position);

        txtRefeicao.setText(index.getRefeicao());
        txtHora.setText(index.getHora());
        txtInformacao.setText(index.getInformacaoRefeicao());

        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DetalhesPlano.this, new TimePickerDialog.OnTimeSetListener() {
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

        gravarRefeicao(txtRefeicao, txtInformacao, txtHora);    //É executado quando clicar no botão Gravar

        removerRefeicao(txtRefeicao, txtInformacao, txtHora);     //É executado quando clicar no botão Cancelar

    }

    public void gravarRefeicao(EditText aTxtRefeicao, EditText aTxtInformacao, EditText aTxtHora ) {

        Button btnGravar = findViewById(R.id.buttonGravar);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkHour(aTxtHora)) {
                    if(aTxtRefeicao.getText().toString().length() > 0 && aTxtInformacao.getText().toString().length() > 0 && aTxtHora.getText().toString().length() > 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DetalhesPlano.this);
                        builder.setCancelable(true);
                        builder.setTitle("Deseja Alterar os Dados de um Plano Alimentar");
                        builder.setMessage("Caso confirme irá perder todos os antigos dados");
                        builder.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        PlanoAlimentar p =new PlanoAlimentar(aTxtRefeicao.getText().toString(), aTxtInformacao.getText().toString(), aTxtHora.getText().toString());
                                        if(!gerePlanos.verificaHora(p)) {
                                            Toast.makeText(DetalhesPlano.this, "A essa hora já existe uma refeicao com o mesmo nome.", Toast.LENGTH_SHORT).show();
                                        }else {
                                            DB dataBaseHelper = new DB(DetalhesPlano.this);
                                            dataBaseHelper.updatePlano(p, index.getRefeicao());
                                            gerePlanos.listaPlanos().set(position,p);
                                            Intent intent = new Intent();
                                            intent.putExtra("backGere", gerePlanos);
                                            setResult(RESULT_OK, intent);
                                            finish();
                                        }
                                    }
                                });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }else if(aTxtInformacao.getText().toString().length() == 0) {
                        Toast.makeText(DetalhesPlano.this, "Precisa de inserir uma Informação não vazia", Toast.LENGTH_SHORT).show();
                    }else if(aTxtRefeicao.getText().toString().length() == 0) {
                        Toast.makeText(DetalhesPlano.this, "Precisa de inserir uma Refeição não vazia", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(DetalhesPlano.this, "Formato da hora necessita ser HH:mm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void removerRefeicao(EditText aTxtRefeicao, EditText aTxtInformacao, EditText aTxtHora) {
        Button btnCancelar = findViewById(R.id.buttonRemover);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DetalhesPlano.this);
                builder.setCancelable(true);
                builder.setTitle("Deseja Remover um Plano?");
                builder.setMessage("Caso confirme irá apagar permanentemente os dados");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    gerePlanos.listaPlanos().remove(position);
                                    Intent intent = new Intent();
                                    intent.putExtra("backGere", gerePlanos);
                                    setResult(RESULT_OK, intent);
                                    finish();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    public boolean checkHour (EditText aTxtHora) {
        if(aTxtHora.getText().toString().length()!=5) return false;
        if(aTxtHora.getText().toString().split(":")[0].length() != 2 || aTxtHora.getText().toString().split(":")[1].length() != 2) return false;
        int hora = Integer.parseInt(aTxtHora.getText().toString().split(":")[0]);
        int minuto = Integer.parseInt(aTxtHora.getText().toString().split(":")[1]);
        if(hora < 0 || hora > 23 || minuto < 0 || minuto > 59) return false;
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //Guarda o estado do objeto quando a activity for terminada
        //Isto para quando o ecrã mudar de posição
        savedInstanceState.putSerializable("arrayList", gerePlanos);
        savedInstanceState.putInt("posicaoArray", position);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Restaura os dados quando a activity voltar à posição
        gerePlanos = (GerePlanos) savedInstanceState.getSerializable("arrayList");
        position = savedInstanceState.getInt("posicaoArray");

        PlanoAlimentar index = gerePlanos.listaPlanos().get(position);

        txtRefeicao.setText(index.getRefeicao());
        txtHora.setText(index.getHora());
        txtInformacao.setText(index.getInformacaoRefeicao());
    }
}