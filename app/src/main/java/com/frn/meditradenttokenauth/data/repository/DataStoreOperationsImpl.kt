package com.frn.meditradenttokenauth.data.repository

import android.content.Context
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.frn.meditradenttokenauth.domain.repository.DataStoreOperations
import com.frn.meditradenttokenauth.utils.Constants.PREFERENCES_NAME
import com.frn.meditradenttokenauth.utils.Constants.PREF_KEY_ACCESS_TOKEN
import com.frn.meditradenttokenauth.utils.Constants.PREF_KEY_ON_BOARDING
import com.frn.meditradenttokenauth.utils.Constants.PREF_KEY_REFRESH_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = PREF_KEY_ON_BOARDING)
        val accessToken = stringPreferencesKey(name = PREF_KEY_ACCESS_TOKEN)
        val refreshToken = stringPreferencesKey(name = PREF_KEY_REFRESH_TOKEN)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }

    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    override suspend fun saveToken(accessToken: String , refreshToken:String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.accessToken] = accessToken
            preferences[PreferencesKey.refreshToken] = refreshToken
        }
    }

    override fun readToken(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map { preferences ->
                val accessToken = preferences[PreferencesKey.accessToken] ?: ""
                accessToken
            }
    }

    override fun readRefreshToken(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map { preferences ->
                val accessToken = preferences[PreferencesKey.refreshToken] ?: ""
                accessToken
            }    }

}