package android.vergara.tafoda.Model

import java.io.Serializable

class Note (val title: String,
            val description: String,
            val autor: String,
            val resumo: String,
            val paginas: Int,
            val index: Int): Serializable {
    override fun toString(): String {
        return "${title}, ${description}, ${autor}"
    }
}