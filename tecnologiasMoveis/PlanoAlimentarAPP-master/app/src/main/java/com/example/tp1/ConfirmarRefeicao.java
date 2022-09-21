package com.example.tp1;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConfirmarRefeicao extends AppCompatActivity {

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private EditText editTextHora;
    private EditText editTextObservacao;
    private RadioGroup radioGroup;
    private RadioButton radioButtonConsume;
    private PlanoAlimentar planoAlimentar;
    private int hora;
    private int minuto;
    private final static int REQUEST_CODE = 001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_meal);

        TextView textViewHora = findViewById(R.id.textViewHoraPrevista);
        TextView textViewRefeicao = findViewById(R.id.textViewRefeicao2);

        editTextHora = findViewById(R.id.editTextHoraRealizada);
        editTextObservacao = findViewById(R.id.editTextObservacao);

        radioGroup = findViewById(R.id.radioGroupMode);
        radioButtonConsume = findViewById(radioGroup.getCheckedRadioButtonId());

        planoAlimentar = (PlanoAlimentar) getIntent().getSerializableExtra("refeicaoHistorico");

        textViewHora.setText(planoAlimentar.getHora());
        textViewRefeicao.setText(planoAlimentar.getRefeicao());

        confirmarRefeicao();
        cancelarRegistoRefeicao(editTextHora, editTextObservacao);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

                switch(checkedId)
                {
                    case R.id.radioButtonNotConsume:
                        editTextHora.setFocusable(false);
                        editTextHora.setEnabled(false);
                        editTextHora.setInputType(InputType.TYPE_NULL);
                        editTextHora.setFocusableInTouchMode(false);
                        break;

                    case R.id.radioButtonConsume:
                        editTextHora.setFocusable(true);
                        editTextHora.setEnabled(true);
                        editTextHora.setInputType(InputType.TYPE_NULL);
                        editTextHora.setFocusableInTouchMode(true);
                        break;
                }
            }
        });



        editTextHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ConfirmarRefeicao.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,hora, minuto);
                        editTextHora.setText(DateFormat.format("HH:mm", calendar));
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

        editTextHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ConfirmarRefeicao.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,hora, minuto);
                        editTextHora.setText(DateFormat.format("HH:mm", calendar));
                    }
                }, 12,0,true
                );
                timePickerDialog.updateTime(savedInstanceState.getInt("Hora"), savedInstanceState.getInt("Minutos"));
                timePickerDialog.show();
            }
        });

    }

    public void confirmarRefeicao() {

        Button botaoConfirmar = findViewById(R.id.buttonGravar2);

        botaoConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonConsume) {
                    if (verificarHoraRealizada(editTextHora)) {
                        Date date = new Date();
                        HistoricoPlanoAlimentar h = new HistoricoPlanoAlimentar(planoAlimentar.getHora(), planoAlimentar.getRefeicao(), planoAlimentar.getInformacaoRefeicao(),
                                date, editTextHora.getText().toString(), editTextObservacao.getText().toString(), radioButtonConsume.getText().toString());
                        DB dataBaseHelper = new DB(ConfirmarRefeicao.this);
                        dataBaseHelper.adicionaHistorico(h);
                        Intent i = new Intent(getApplicationContext(), ListarHistoricoPlano.class);
                        //i.putExtra("gereHistorico", gerePlanos);
                        startActivityForResult(i, REQUEST_CODE);
                    } else
                        Toast.makeText(ConfirmarRefeicao.this, "Formato da hora necessita ser HH:mm", Toast.LENGTH_SHORT).show();
                } else {
                    if (editTextObservacao.getText().toString().isEmpty()){
                        Toast.makeText(ConfirmarRefeicao.this, "Introduza uma observação", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Date date = new Date();
                        HistoricoPlanoAlimentar h = new HistoricoPlanoAlimentar(planoAlimentar.getHora(), planoAlimentar.getRefeicao(), planoAlimentar.getInformacaoRefeicao(),
                                date, editTextHora.getText().toString(), editTextObservacao.getText().toString(), radioButtonConsume.getText().toString());
                        DB dataBaseHelper = new DB(ConfirmarRefeicao.this);
                        dataBaseHelper.adicionaHistorico(h);
                        Intent i = new Intent(getApplicationContext(), ListarHistoricoPlano.class);
                        startActivityForResult(i, REQUEST_CODE);

                    }
                }
            }
        });
    }

    public void cancelarRegistoRefeicao(EditText aEditTextHora, EditText aEditTextObservacao) {

        Button botaoCancelar = findViewById(R.id.buttonCancelar2);

        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aEditTextHora.setText("");
                aEditTextObservacao.setText("");

                finish();
            }
        });
    }

    public boolean verificarHoraRealizada(EditText aTxtHora) {
        if (aTxtHora.getText().toString().length() != 5) return false;
        if (aTxtHora.getText().toString().split(":")[0].length() != 2 || aTxtHora.getText().toString().split(":")[1].length() != 2)
            return false;
        int hora = Integer.parseInt(aTxtHora.getText().toString().split(":")[0]);
        int minuto = Integer.parseInt(aTxtHora.getText().toString().split(":")[1]);
        if (hora < 0 || hora > 23 || minuto < 0 || minuto > 59) return false;
        return true;
    }


}
