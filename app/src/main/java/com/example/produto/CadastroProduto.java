package com.example.produto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.produto.database.ProdutoDAO;
import com.example.produto.modelo.Produto;

public class CadastroProduto extends AppCompatActivity {

    private boolean edicao = false;
    private boolean excluir = false;
    private boolean salvou = false;
    private int id = 0;
    private Float valor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        setTitle("Cadastro de Produtos");
        carregarProduto();
    }

    private void carregarProduto() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("produtoSelecionado") != null) {
            Produto produto = (Produto) intent.getExtras().get("produtoSelecionado");
            EditText editTextNome = findViewById(R.id.editTextNome);
            EditText editTextValor = findViewById(R.id.editTextValor);
            editTextNome.setText(produto.getNome());
            editTextValor.setText(String.valueOf(produto.getValor()));
            id = produto.getId();
        }
    }

    public void onClickBack(View v) {
        finish();
    }

    public void onClickSave(View v) {
        processar();
    }

    public void onClickRemove(View v) {
        excluir = true;
        processar();
    }

    private void processar() {

        EditText editTextNome = findViewById(R.id.editTextNome);
        EditText editTextValor = findViewById(R.id.editTextValor);

        String nome = editTextNome.getText().toString();
        String valorstring = editTextValor.getText().toString();
        if (!valorstring.isEmpty()) {
            valor = Float.parseFloat(valorstring);
        }
        if ((nome.isEmpty() && !excluir)){
            Toast.makeText(CadastroProduto.this, "É preciso informar o nome do produto.", Toast.LENGTH_LONG).show();
            return;
        } else if ((valorstring.isEmpty()) && !excluir) {
            Toast.makeText(CadastroProduto.this, "É preciso informar o valor do produto.", Toast.LENGTH_LONG).show();
            return;
        } else {
            Produto produto = new Produto(id, nome, valor);
            ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
            if (excluir) {
                produtoDAO.excluir(produto);
            } else {
                salvou = produtoDAO.salvar(produto);
                if (!salvou) {
                    Toast.makeText(CadastroProduto.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
                }
            }
            finish();
        }
    }
}