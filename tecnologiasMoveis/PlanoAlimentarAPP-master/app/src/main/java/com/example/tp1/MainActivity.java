package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 001;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
    GerePlanos gerePlanos;
    ArrayList<PlanoAlimentar> planoAlimentar;
    TextView horaAtual;
    TextView horaRefeicao;
    TextView nomeRefeicao;
    TextView textViewNomeDoente;
    CardView viewCard;
    Button botaoPlano, botaoHistorico;
    static int posicao = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        horaAtual = findViewById(R.id.editTextTime);
        viewCard = findViewById(R.id.cardDetalhesAlimentacao);
        horaRefeicao = findViewById(R.id.textViewHoraRefeicao);
        nomeRefeicao = findViewById(R.id.textViewRefeicao);
        botaoPlano = findViewById(R.id.buttonPlano);
        textViewNomeDoente = findViewById(R.id.textViewInserirNome);
        botaoHistorico = findViewById(R.id.buttonHistorico);
        DB dataBaseHelper = new DB(MainActivity.this);
        gerePlanos = new GerePlanos();
        gerePlanos = dataBaseHelper.listarPlano();

        sortArrayList();

        

        if (gerePlanos.listaPlanos().size() > 0) {
            horaRefeicao.setText(gerePlanos.listaPlanos().get(posicao).getHora());
            nomeRefeicao.setText(gerePlanos.listaPlanos().get(posicao).getRefeicao());
        } else {
            horaRefeicao.setText(" ");
            nomeRefeicao.setText(" ");
        }

        changeColorCard();
        atualizarCartaoRefeicao();

        planoAlimentar = gerePlanos.listaPlanos();

        botaoPlano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ListaPlanoAlimentar.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("arrayListRef", gerePlanos);
                i.putExtras(bundle);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        botaoHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.tp1.ListarHistoricoPlano"));
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //Guarda o estado do objeto quando a activity for terminada
        //Isto para quando o ecrã mudar de posição
        savedInstanceState.putSerializable("arrayList", gerePlanos);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Restaura os dados quando a activity voltar à posição
        gerePlanos = (GerePlanos) savedInstanceState.getSerializable("arrayList");

        if (gerePlanos.listaPlanos().size() > 0) {
            horaRefeicao.setText(gerePlanos.listaPlanos().get(posicao).getHora());
            nomeRefeicao.setText(gerePlanos.listaPlanos().get(posicao).getRefeicao());
        } else {
            horaRefeicao.setText(" ");
            nomeRefeicao.setText(" ");
        }

        changeColorCard();
        atualizarCartaoRefeicao();

        planoAlimentar = gerePlanos.listaPlanos();

        botaoPlano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ListaPlanoAlimentar.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("arrayListRef", gerePlanos);
                i.putExtras(bundle);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null && data.getExtras() != null) {
                    gerePlanos = (GerePlanos) data.getExtras().getSerializable("backHome");


                    if (gerePlanos.listaPlanos().size() > 0) {
                        horaRefeicao.setText(gerePlanos.listaPlanos().get(posicao).getHora());
                        nomeRefeicao.setText(gerePlanos.listaPlanos().get(posicao).getRefeicao());
                    } else {
                        horaRefeicao.setText(" ");
                        nomeRefeicao.setText(" ");
                    }

                    changeColorCard();
                    atualizarCartaoRefeicao();

                    planoAlimentar = gerePlanos.listaPlanos();

                    botaoPlano.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getApplicationContext(), ListaPlanoAlimentar.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("arrayListRef", gerePlanos);
                            i.putExtras(bundle);
                            startActivityForResult(i, REQUEST_CODE);
                        }
                    });

                }
            }
        }
    }

    private void atualizarCartaoRefeicao() {
        Button botaoOk = findViewById(R.id.buttonOk);

        //Antes de remover o estado do ArrayList que contém todas as refeições
        if (gerePlanos.listaPlanos().size() == 0) {
            botaoOk.setEnabled(false);  //Desabilita o botão Ok se ainda não existir refeições adicionadas
        } else {
            botaoOk.setEnabled(true);
        }

        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gerePlanos.listaPlanos().size() > 0) {
                    Intent i = new Intent(getApplicationContext(), ConfirmarRefeicao.class);
                    i.putExtra("refeicaoHistorico", gerePlanos.listaPlanos().get(posicao));
                    if(posicao < gerePlanos.listaPlanos().size()) {
                        posicao++;
                    }
                    startActivityForResult(i, REQUEST_CODE);
                    horaRefeicao.setText(gerePlanos.listaPlanos().get(posicao).getHora());
                    nomeRefeicao.setText(gerePlanos.listaPlanos().get(posicao).getRefeicao());

                    //Atualiza o cartão com a informação do próximo elemento no ArrayList
                    //horaRefeicao.setText(gerePlanos.listaPlanos().get(0).getHora());
                    //nomeRefeicao.setText(gerePlanos.listaPlanos().get(0).getRefeicao());

                    changeColorCard();  //Atualiza a cor do cartão
                } else {
                    horaRefeicao.setText(" ");
                    nomeRefeicao.setText(" ");
                    viewCard.setCardBackgroundColor(Color.WHITE);   //Altera a cor do cartão para a cor por defeito caso já não exista mais refeições
                    botaoOk.setEnabled(false);  //Volta a desabilitar o botão Ok se não existirem mais refeições
                }
            }
        });
    }


    private void changeColorCard() {

        SharedPreferences sharePref = getSharedPreferences("SharedPrefDef", MODE_PRIVATE);


        String nomeDoente = sharePref.getString("nomeDoente", "");

        textViewNomeDoente.setText(nomeDoente);

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

        int intervaloTempo;

        try {
            intervaloTempo = sharePref.getInt("intervaloRef", Integer.parseInt(""));

        } catch (NumberFormatException nfe) {
            intervaloTempo = 15;
        }

        int finalIntervaloTempo = intervaloTempo;
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        horaAtual.setText(format.format(System.currentTimeMillis()));
                    }
                });

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (planoAlimentar.size() > 0) {
                            PlanoAlimentar refeicao = planoAlimentar.get(0);
                            String hora = refeicao.getHora();
                            String horaTimer = horaAtual.getText().toString();

                            int horaAtual = Integer.parseInt(horaTimer.split(":")[0]);
                            int minutosAtuais = Integer.parseInt(horaTimer.split(":")[1]);

                            int horaRef = Integer.parseInt(hora.split(":")[0]);
                            int minuto = Integer.parseInt(hora.split(":")[1]);

                            if ((horaAtual >= horaRef && minutosAtuais > minuto ) || horaAtual > horaRef)
                                viewCard.setCardBackgroundColor(Color.RED);

                            else if (horaAtual == horaRef && (minutosAtuais - minuto) < finalIntervaloTempo)
                                viewCard.setCardBackgroundColor(Color.YELLOW);

                            else
                                viewCard.setCardBackgroundColor(Color.GREEN);
                        }
                    }
                });


            }
        }, 0, 60L, TimeUnit.MINUTES);
    }

    private void sortArrayList() {
        Collections.sort(gerePlanos.listaPlanos(), new Comparator<PlanoAlimentar>() {
            @Override
            public int compare(PlanoAlimentar o1, PlanoAlimentar o2) {
                return o1.getHora().compareTo(o2.getHora());
            }
        });
    }
}
