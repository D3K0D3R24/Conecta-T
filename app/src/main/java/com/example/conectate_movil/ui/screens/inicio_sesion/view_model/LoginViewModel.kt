package com.example.conectate_movil.ui.screens.inicio_sesion.view_model

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.conectate_movil.data.repositositories.UsuarioDataRepository
import com.example.conectate_movil.utils.globals_data.DataUsuarioGloblal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(): ViewModel() {

    private val repositoryUsuarioGET = UsuarioDataRepository()
       //guardar email
    private val _loginEmail = MutableLiveData<String>()
    val loginEmail : LiveData<String> = _loginEmail

    //guardar contraseña
    private val _loginPassword = MutableLiveData<String>()
    val loginPassword : LiveData<String> = _loginPassword

    //habilitamos el boton iniciar secion si este cumple si es email y contraseña
    private val _isButtonEnable = MutableLiveData<Boolean>()
    val isButtonEnable : LiveData<Boolean> = _isButtonEnable

    //variable para ir guardando los errores
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    //variable que nos indica si el cliente existe o no
    private val _isGetDataCorrect = MutableStateFlow(false)
    val isGetDataCorrect: StateFlow<Boolean> = _isGetDataCorrect.asStateFlow()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isValidEmail = MutableLiveData<Boolean>()
    val isValidEmail : LiveData<Boolean> = _isValidEmail

    //funcion que usaran los textField
    fun onLoginTextFieldChange(email:String, password: String){
        _loginEmail.value = email
        _loginPassword.value = password
        _isValidEmail.value = isEmailValid(email)
        _isButtonEnable.value = isEmailValid(email) && isPasswordValid(password)

    }

    //verificamos si el correo es un correo valido
    private fun isEmailValid (email:String):Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    //contraseña corroborar si la longitud de la contraseña es mayor a 6
    private fun isPasswordValid(password: String): Boolean = password.length > 6 //corrobora si la contraseña es mayor a 6 caracteres

    //en caso de que ingreso mal la contraseña o correo ya no haga consulta al servidor si no ahora lo haga en forma local
    private val _bandera = MutableLiveData(0)
    private val _emailTemp = MutableLiveData("")

    fun onClickLogin(){
        if (_bandera.value == 0){
            isUsuarioRegistrado()
        }else{
            loginLocal()
        }
    }

    fun loginLocal(){
        val user = DataUsuarioGloblal.getUsuario()
        if (user != null) {
            if (user.password == _loginPassword.value.toString() && _emailTemp.value == _loginEmail.value.toString()) {
                _bandera.value = 0
                _isGetDataCorrect.value = true
            }else{
                _error.value = "La  contraseña ingresada son incorrectos"
            }
        }else{
            _bandera.value = 0

        }
    }

    fun isUsuarioRegistrado(){
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = withContext(Dispatchers.IO){
                    repositoryUsuarioGET.getUserAppByEmail(_loginEmail.value.toString())
                }
                if (result.message == "E001"){
                    login()
                }else{
                    _error.value = "Error: el usuario no existe o no ha completado su registro como usuario de la aplicacion, si es un error, contactese con el administrador"
                }
            }catch (e:Exception){
                _error.value = "Error en la consulta del filtro uno, por parte del servidor: " +
                        "${e.message}"
            }finally {
                _isLoading.value = false
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    listOf(repositoryUsuarioGET.getUsuarioforLogin(_loginEmail.value.toString()))
                }

                DataUsuarioGloblal.setUsuario(result[0])

                if (result.any() { it.password == _loginPassword.value.toString() }) {
                    _isGetDataCorrect.value = true
                    //ahira guardaremos los datos en el el data store
                }else{
                    _bandera.value = 1
                    _emailTemp.value = _loginEmail.value.toString()
                    _error.value = "La  contraseña ingresada son incorrectos"
                }
            } catch (e: Exception) {
                _error.value = "Error por parte del servidor: ${e.message}"
            }
        }
    }

}