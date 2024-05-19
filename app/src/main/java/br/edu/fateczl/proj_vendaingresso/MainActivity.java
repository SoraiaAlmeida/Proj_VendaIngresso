package br.edu.fateczl.proj_vendaingresso;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.proj_vendaingresso.model.Ingresso;
import br.edu.fateczl.proj_vendaingresso.model.IngressoVIP;

public class MainActivity extends AppCompatActivity {

    private EditText etCodigo, etValor, etTaxa, etFuncao;
    private CheckBox cbVIP;
    private Button btnComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etCodigo = findViewById(R.id.etCodigo);
        etValor = findViewById(R.id.etValor);
        etTaxa = findViewById(R.id.etTaxa);
        etFuncao = findViewById(R.id.etFuncao);
        cbVIP = findViewById(R.id.cbVIP);
        btnComprar = findViewById(R.id.btnComprar);

        cbVIP.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                etFuncao.setVisibility(View.VISIBLE);
            } else {
                etFuncao.setVisibility(View.GONE);
            }
        });

        btnComprar.setOnClickListener(v -> {
            String Codigo = etCodigo.getText().toString();
            float Valor = Float.parseFloat(etValor.getText().toString());
            float Taxa = Float.parseFloat(etTaxa.getText().toString());

            Log.i("codigo", Codigo);
            Log.i("valor", String.valueOf(Valor));
            Log.i("taxa", String.valueOf(Taxa));


            Bundle bundle = new Bundle();
            bundle.putString("codigo", Codigo);
            bundle.putFloat("valor", Valor);
            bundle.putFloat("taxa", Taxa);

            if (cbVIP.isChecked()) {
                String funcao = etFuncao.getText().toString();
                bundle.putString("funcao", funcao);
            }

            abrirTelaCompra(bundle);
        });
    }

    private void abrirTelaCompra(Bundle bundle) {
        Intent intent = new Intent(MainActivity.this, CompraFinalizada.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}