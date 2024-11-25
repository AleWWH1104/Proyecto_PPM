package com.wishify.proyecto_ppm.ui.wishLists.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.R
import kotlinx.coroutines.tasks.await

data class ListData(
    val listNameP: String = "",
    val eventP: String = "",
    val codeList: String = "",
    val imageRes: Int = R.drawable.img
)

class MainListsRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    suspend fun fetchLists(): List<ListData> {
        val currentUser = auth.currentUser
        val uid = currentUser?.uid ?: return emptyList()

        val userDoc = db.collection("UsuariosP").document("UsuarioP")
            .collection("UsuarioP").document(uid)
            .get().await()

        val codeLists = userDoc.get("CodeList") as? List<String> ?: emptyList()
        val fetchedLists = mutableListOf<ListData>()

        for (codeList in codeLists) {
            val listDoc = db.collection("ListasP").document("ListaP")
                .collection("ListaP").document(codeList)
                .get().await()

            val listNameP = listDoc.getString("listNameP") ?: ""
            val eventP = listDoc.getString("EventP") ?: ""
            val imageRes = listDoc.getLong("ImageRes")?.toInt() ?: R.drawable.img

            fetchedLists.add(ListData(listNameP = listNameP, eventP = eventP, codeList = codeList, imageRes = imageRes))
        }

        return fetchedLists
    }

    suspend fun deleteList(codeList: String): Boolean {
        val currentUser = auth.currentUser
        val uid = currentUser?.uid ?: return false

        try {
            db.collection("ListasP").document("ListaP")
                .collection("ListaP").document(codeList)
                .delete().await()

            db.collection("UsuariosP").document("UsuarioP")
                .collection("UsuarioP").document(uid)
                .update("CodeList", com.google.firebase.firestore.FieldValue.arrayRemove(codeList)).await()

            return true
        } catch (e: Exception) {
            println("Error al eliminar lista: ${e.message}")
            return false
        }
    }
}
