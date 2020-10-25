package com.example.produto.database.contract;

import com.example.produto.database.entity.ProdutoEntity;

public final class ProdutoContract {
    private ProdutoContract() {
    }

    public static final String criarTabela() {
        return "CREATE TABLE " + ProdutoEntity.TABLE_NAME + " (" +
                ProdutoEntity._ID + " INTEGER PRIMARY KEY," +
                ProdutoEntity.COLUMN_NAME_NOME + " TEXT," +
                ProdutoEntity.COLUMN_NAME_VALOR + " REAL)";
    }

    public static final String removerTabela() {
        return "DROP TABLE IF EXISTS " + ProdutoEntity.TABLE_NAME;

    }
}
