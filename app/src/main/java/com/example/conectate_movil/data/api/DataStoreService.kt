package com.example.conectate_movil.data.api
import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

// Extensión para crear una instancia de DataStore
val Context.dataStore by preferencesDataStore(name = "user_preferences")

class DataStoreService(private val context: Context) {

    // Clave para el dato "username"
    private val USERNAME_KEY = stringPreferencesKey("username")
    private val TOKEN_KEY = stringPreferencesKey("token")

    /**
     * Método para leer el username como un Flow.
     */
    val username: Flow<String?>
        get() = context.dataStore.data.map { preferences ->
            preferences[USERNAME_KEY]
        }

    /**
     * Método para escribir/actualizar el username.
     */
    suspend fun updateUsername(newUsername: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = newUsername
        }
    }

    /**
     * Método para escribir/actualizar el token.
     */
    suspend fun setToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    /**
     * Método para obtener el username de forma síncrona (bloqueante).
     * Nota: Evitar usar en el hilo principal.
     */
    suspend fun getUsername(): String? {
        return context.dataStore.data
            .map { preferences -> preferences[USERNAME_KEY] }
            .firstOrNull()
    }
    /**
     * Método para obtener el token de forma síncrona (bloqueante).
     * Nota: Evitar usar en el hilo principal.
     */
    suspend fun getToken(): String? {
        return context.dataStore.data
            .map { preferences -> preferences[TOKEN_KEY] }
            .firstOrNull()
    }
}
