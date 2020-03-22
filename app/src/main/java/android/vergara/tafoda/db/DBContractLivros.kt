package android.vergara.tafoda.db

import android.provider.BaseColumns

object DBContractLivros {
    class LivroEntry : BaseColumns {
        companion object {
            val TABLE_LIVROS = "livros"
            val COLUMN_TITLE = "title"
            val COLUMN_DESC = "description"
            val COLUMN_AUTOR = "autor"
            val COLUMN_RESUMO = "resumo"
            val COLUMN_PAGINAS = "paginas"
            val COLUMN_IND = "ind"
        }
    }

}