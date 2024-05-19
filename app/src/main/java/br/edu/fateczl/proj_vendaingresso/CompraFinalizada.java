package br.edu.fateczl.proj_vendaingresso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.text.NumberFormat;
import java.util.Locale;

import br.edu.fateczl.proj_vendaingresso.model.Ingresso;
import br.edu.fateczl.proj_vendaingresso.model.IngressoVIP;

public class CompraFinalizada extends AppCompatActivity {

    private TextView tvDetalhes;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compra_finalizada);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_compra_finalizada), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDetalhes = findViewById(R.id.tvDetalhes);
        tvDetalhes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnVoltar = findViewById(R.id.btnVoltar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String codigo = bundle.getString("codigo");
            float valor = bundle.getFloat("valor");
            float taxa = bundle.getFloat("taxa");

            String detalhes;
            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            if (bundle.containsKey("funcao")) {
                String funcao = bundle.getString("funcao");
                IngressoVIP ingressoVIP = new IngressoVIP(codigo, valor, funcao);
                float valorFinal = ingressoVIP.valorFinal(taxa);
                detalhes = getString(R.string.detalhes_codigo) + codigo + "\n" +
                        getString(R.string.detalhes_valor_final) + valorFinal + "\n" +
                        getString(R.string.detalhes_funcao) + funcao;
            } else {
                Ingresso ingresso = new Ingresso(codigo, valor);
                float valorFinal = ingresso.valorFinal(taxa);
                detalhes = getString(R.string.detalhes_codigo) + codigo + "\n" +
                        getString(R.string.detalhes_valor_final) + valorFinal;
            }

            tvDetalhes.setText(detalhes);
        }

        btnVoltar.setOnClickListener(op -> voltar());
    }

    private void voltar() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();

    }
}