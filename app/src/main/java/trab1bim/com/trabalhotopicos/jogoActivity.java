package trab1bim.com.trabalhotopicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class jogoActivity extends AppCompatActivity {

    ArrayList<Integer> respostas = new ArrayList<Integer>();

    int num1, num2, op=0, pontos = 0, dificuldade;
    Random rand;

    public boolean verificResp(int val)
    {
        if(op == 0)
        {
           if(num1 + num2 == val)
               return true;
           else
               return false;
        }
        else
        {
            if(num1 - num2 == val)
                return true;
            else
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        Intent intent = getIntent();

        int tempo;
        final TextView textTempo = findViewById(R.id.textViewTimer);

        final String nome = intent.getStringExtra("Nome");
        dificuldade = intent.getIntExtra("Dificuldade",-1);

        if(dificuldade == 0)
            tempo = 30000;

        else if(dificuldade == 1)
            tempo = 20000;

        else
            tempo = 10000;

        new CountDownTimer(tempo,1000)
        {
            long time;
            public void onTick(long millisUntilFinished)
            {
                time = millisUntilFinished/1000;
                if(time <= 5)
                {
                    textTempo.setTextColor(Color.RED);

                    if(time % 2 != 0)
                        textTempo.setBackgroundColor(Color.rgb(255, 124, 124));
                    else
                        textTempo.setBackgroundColor(Color.TRANSPARENT);
                }
                textTempo.setText(Long.toString(time) + " s");
            }

            public void onFinish()
            {
                Intent intent = new Intent(jogoActivity.this, placarActivity.class);
                intent.putExtra("pontos",pontos);
                intent.putExtra("Nome",nome);
                startActivity(intent);
                finish();
            }
        }.start();

        rand = new Random();
        gerarNumeros(rand);
    }

    public void verificarButton(View view)
    {
        TextView textAcerto = findViewById(R.id.textViewSinal);
        TextView textPontos = findViewById(R.id.textViewAcertos);
        switch(view.getId())
        {
            case R.id.buttonEC:
                if(verificResp(respostas.get(0)))
                {
                    textAcerto.setTextColor(Color.GREEN);
                    textAcerto.setText("Acertou!");
                    pontos++;
                    textPontos.setText(String.valueOf(pontos));
                    gerarNumeros(rand);
                }
                else
                {
                    textAcerto.setTextColor(Color.RED);
                    textAcerto.setText("Errow!");
                    pontos--;
                    textPontos.setText(String.valueOf(pontos));
                    gerarNumeros(rand);
                }
                break;

            case R.id.buttonDC:
                if(verificResp(respostas.get(1)))
                {
                    textAcerto.setTextColor(Color.GREEN);
                    textAcerto.setText("Acertou!");
                    pontos++;
                    textPontos.setText(String.valueOf(pontos));
                    gerarNumeros(rand);
                }
                else
                {
                    textAcerto.setTextColor(Color.RED);
                    textAcerto.setText("Errow!");
                    pontos--;
                    textPontos.setText(String.valueOf(pontos));
                    gerarNumeros(rand);
                }
                break;

            case R.id.buttonEB:
                if(verificResp(respostas.get(2)))
                {
                    textAcerto.setTextColor(Color.GREEN);
                    textAcerto.setText("Acertou!");
                    pontos++;
                    textPontos.setText(String.valueOf(pontos));
                    gerarNumeros(rand);
                }
                else
                {
                    textAcerto.setTextColor(Color.RED);
                    textAcerto.setText("Errow!");
                    pontos--;
                    textPontos.setText(String.valueOf(pontos));
                    gerarNumeros(rand);
                }
                break;

            case R.id.buttonDB:
                if(verificResp(respostas.get(3)))
                {
                    textAcerto.setTextColor(Color.GREEN);
                    textAcerto.setText("Acertou!");
                    pontos++;
                    textPontos.setText(String.valueOf(pontos));
                    gerarNumeros(rand);
                }
                else
                {
                    textAcerto.setTextColor(Color.RED);
                    textAcerto.setText("Errow!");
                    pontos--;
                    textPontos.setText(String.valueOf(pontos));
                    gerarNumeros(rand);
                }
                break;
        }
    }

    public void gerarNumeros(Random rand)
    {
        respostas.clear();
        TextView textOperacao = findViewById(R.id.textViewOperacao);

        Button botaoDC = findViewById(R.id.buttonDC);
        Button botaoDB = findViewById(R.id.buttonDB);
        Button botaoEC = findViewById(R.id.buttonEC);
        Button botaoEB = findViewById(R.id.buttonEB);

        num1 = rand.nextInt(51);
        num2 = rand.nextInt(50);
        int locResp = rand.nextInt(4);

        if(dificuldade > 0)
            op = rand.nextInt(2);

        if(op == 0)
            textOperacao.setText(String.valueOf(num1) + " + " + String.valueOf(num2));
        else
            textOperacao.setText(String.valueOf(num1) + " - " + String.valueOf(num2));

        for(int i =0;i < 4; i++)
        {
            if(i == locResp)
            {
                if(op == 0)
                    respostas.add(num1+num2);
                else
                    respostas.add(num1-num2);
            }
            else if(op == 0)
                respostas.add((num1+rand.nextInt(3)-3)+(num2+rand.nextInt(3)+1));
            else if(op == 1)
                respostas.add((num1-rand.nextInt(3)+2)-(num2+rand.nextInt(3)-1));

        }
        botaoEC.setText(String.valueOf(respostas.get(0)));
        botaoDC.setText(String.valueOf(respostas.get(1)));
        botaoEB.setText(String.valueOf(respostas.get(2)));
        botaoDB.setText(String.valueOf(respostas.get(3)));
    }
}
