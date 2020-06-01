package trab1bim.com.trabalhotopicos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class placarActivity extends AppCompatActivity {

    ArrayList<String> nomes = new ArrayList<String>();
    ArrayList<Integer> pontuacao = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar);
        Intent intent = getIntent();

        int pontos = intent.getIntExtra("pontos",0);
        String nome = intent.getStringExtra("Nome");

        carregarPlacar();

        if(nome != "-100" && pontos != -100)
        {
            addSort(nome,pontos);
            salvarPlacar();
        }

        TextView textPrimeiro = findViewById(R.id.textViewPrimeiro);
        TextView textSegundo = findViewById(R.id.textViewSegundo);
        TextView textTerceiro = findViewById(R.id.textViewTerceiro);

        textPrimeiro.setText("Nome: " +" Pontuação: ");
        textSegundo.setText("Nome: " +" Pontuação: ");
        textTerceiro.setText("Nome: " +" Pontuação: ");

        if(nomes.size() >= 1)
            textPrimeiro.setText("Nome: " +nomes.get(0)+" Pontuação: "+ String.valueOf(pontuacao.get(0)));
        if(nomes.size() >= 2)
            textSegundo.setText("Nome: " +nomes.get(1)+" Pontuação: "+ String.valueOf(pontuacao.get(1)));
        if(nomes.size() >= 3)
            textTerceiro.setText("Nome: " +nomes.get(2)+" Pontuação: "+ String.valueOf(pontuacao.get(2)));
    }

    public void addSort(String nome, int pontos)
    {
        nomes.add(nome);
        pontuacao.add(pontos);

        for(int i = pontuacao.size();i>=1;i--) //Aqui, utilizamos um BubbleSort para a ordenação
        {
            for(int j = 1;j<i;j++)
            {
                if(pontuacao.get(j-1) < pontuacao.get(j))
                {
                    int aux;
                    String Saux;
                    aux = pontuacao.get(j);
                    Saux = nomes.get(j);
                    pontuacao.set(j,pontuacao.get(j-1));
                    nomes.set(j,nomes.get(j-1));
                    pontuacao.set(j-1,aux);
                    nomes.set(j-1,Saux);
                }
            }
        }
    }

    public void salvarPlacar()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonNomes = gson.toJson(nomes);
        String jsonPontuacao = gson.toJson(pontuacao);
        editor.putString("nomes",jsonNomes);
        editor.putString("pontuacao",jsonPontuacao);
        editor.apply();
    }

    public void carregarPlacar()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonNomes = sharedPreferences.getString("nomes",null);
        String jsonPontuacao =  sharedPreferences.getString("pontuacao",null);
        Type typeString = new TypeToken<ArrayList<String>>() {}.getType();
        Type typeInteger = new TypeToken<ArrayList<Integer>>() {}.getType();
        nomes = gson.fromJson(jsonNomes,typeString);
        pontuacao = gson.fromJson(jsonPontuacao,typeInteger);
        if(nomes == null)
            nomes = new ArrayList<>();
        if(pontuacao == null)
            pontuacao = new ArrayList<>();
    }

    public void limparPlacar(View view)
    {
        new AlertDialog.Builder(this)
                .setTitle("Limpar dados")
                .setMessage("Quer mesmo limpar o placar?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        SharedPreferences sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear().apply();
                        TextView textPrimeiro = findViewById(R.id.textViewPrimeiro);
                        TextView textSegundo = findViewById(R.id.textViewSegundo);
                        TextView textTerceiro = findViewById(R.id.textViewTerceiro);
                        textPrimeiro.setText("Nome: " +" Pontuação: ");
                        textSegundo.setText("Nome: " +" Pontuação: ");
                        textTerceiro.setText("Nome: " +" Pontuação: ");
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void jogarNovamente(View view)
    {
        finish();
    }
}
