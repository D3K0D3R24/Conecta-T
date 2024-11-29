package com.example.conectate_movil.data.datastore

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.conectate_movil.utils.globals_data.UserDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.temporal.ChronoUnit


// Extensión para obtener el DataStore
val Context.dataStore by preferencesDataStore("user_prefs")

class UsuarioPreferenceDataStore(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        val ALIAS_KEY = stringPreferencesKey("alias")
        val EMAIL_KEY = stringPreferencesKey("email")
        val PASSWORD_KEY = stringPreferencesKey("password")
        val ID_CLIENT_KEY = stringPreferencesKey("id_cliente")
        val LOGIN_DATE_KEY = longPreferencesKey("login_date")
    }

    // Guardar datos en el DataStore del usuario
    suspend fun saveUserData(alias: String, email: String, password: String, id_cliente: Int) {
        dataStore.edit { preferences ->
            preferences[ALIAS_KEY] = alias
            preferences[EMAIL_KEY] = email
            preferences[PASSWORD_KEY] = password
            preferences[ID_CLIENT_KEY] = id_cliente.toString()
            preferences[LOGIN_DATE_KEY] = System.currentTimeMillis()
        }
    }

    // Obtener datos del DataStore del usuario
    val userData: Flow<UserDataStore?> = dataStore.data.map { preferences ->
        val alias = preferences[ALIAS_KEY]
        val email = preferences[EMAIL_KEY]
        val password = preferences[PASSWORD_KEY]
        val idClient = preferences[ID_CLIENT_KEY]
        val loginDate = preferences[LOGIN_DATE_KEY]

        if (alias != null && email != null && password != null && idClient != null && loginDate != null) {
            UserDataStore(
                alias = alias,
                email = email,
                password = password,
                id_cliente = idClient.toInt(),
                login_date = loginDate
            )
        } else {
            null
        }
    }

    //verificar si el usuario ya inicio sesion y si aun es valido (menos de 2 dias)
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun isUserLoggedIn(): Boolean {
        val preferences = dataStore.data.firstOrNull() ?: return false
        val loginDateMillis = preferences[LOGIN_DATE_KEY]
        return loginDateMillis?.let {
            val loginDate = Instant.ofEpochMilli(loginDateMillis)
            val now = Instant.now()
            val duration = ChronoUnit.DAYS.between(loginDate, now)
            duration <= 2
        } ?: false
    }

    // Borrar datos del DataStore del usuario. usado para cerrar sesion y cuando haya pasado 2 dias
    suspend fun deleteUserData() {
        dataStore.edit {it.clear()}

    }

}