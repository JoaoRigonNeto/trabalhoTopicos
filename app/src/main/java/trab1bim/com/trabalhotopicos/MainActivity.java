package trab1bim.com.trabalhotopicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int dificuldade = 1;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar = findViewById(R.id.seekBarDificuldade);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                dificuldade = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) { }
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }

    public void placar(View view)
    {
        Intent it = new Intent(this, placarActivity.class);
        it.putExtra("Nome", "-100");
        it.putExtra("pontos", -100);
        startActivity(it);
    }

    public void jogar(View view)
    {
        EditText editText = findViewById(R.id.editTextNome);
        nome = editText.getText().toString();
        if(nome.length() == 0)
        {
            Toast.makeText(this,"Insira um nome", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Intent intent = new Intent(this,jogoActivity.class);
            intent.putExtra("Dificuldade",dificuldade);
            intent.putExtra("Nome",nome);
            startActivity(intent);
        }

    }
}
