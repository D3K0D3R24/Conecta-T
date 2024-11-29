package com.example.conectate_movil.ui.screens.singup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conectate_movil.data.repositositories.UsuarioDataRepository
import com.example.conectate_movil.utils.globals_data.DataClientGlobal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SingUpViewModel: ViewModel(){

    private val repositoryUser = UsuarioDataRepository()

    private val _nombreCliente = MutableLiveData<String>()
    val nombreCliente:LiveData<String> = _nombreCliente

    private val _alias = MutableLiveData<String>()
    val alias:LiveData<String> = _alias

    private val _email = MutableLiveData<String>()
    val email:LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password:LiveData<String> = _password

    private val _id_cliente = MutableLiveData<Int>()
    //val id_cliente:LiveData<Int> = _id_cliente

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword:LiveData<String> = _confirmPassword

    private val _telefono = MutableLiveData<String>()
    val telefono:LiveData<String> = _telefono

    private val _direccion = MutableLiveData<String>()
    val direccion:LiveData<String> = _direccion

    //variable que nos indica si el cliente existe o no
    private val _isGetDataCorrect = MutableStateFlow(false)
    val isGetDataCorrect: StateFlow<Boolean> = _isGetDataCorrect.asStateFlow()


    //variable para ir guardando los errores
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    //usamos init para inicializar los valores en la vista.
    init{
        _nombreCliente.value = DataClientGlobal.getClient()?.nombre_completo
        _telefono.value = DataClientGlobal.getClient()?.telefono
        _direccion.value = DataClientGlobal.getClient()?.direccion
        _email.value = DataClientGlobal.getClient()?.correo_electronico
        _id_cliente.value = DataClientGlobal.getClient()?.id_cliente
    }

    private val _isBtnEnabled = MutableLiveData<Boolean>()
    val isBtnEnabled:LiveData<Boolean> = _isBtnEnabled

    fun onChangeValueSingUp(alias:String, password:String, confirmPassword:String){
        _alias.value = alias
        _password.value = password
        _confirmPassword.value = confirmPassword
        //habilitamos en boton si las contrasenias son iguales y la longitud es mayor a 6
        _isBtnEnabled.value = idPassEqual(password, confirmPassword) && isPasswordValid(password)
    }

    //filtro para validar si las contrasenias son iguales y si la longitud es mayor a 6
    fun idPassEqual(password:String, confirmPassword:String):Boolean{
        return password == confirmPassword
    }
    fun isPasswordValid(password:String):Boolean{
        return password.length > 6
    }


    private fun crearUsuario(){
        viewModelScope.launch {
            //_isLoading.value = true  //usando la misma vatiable
            _error.value = null
            try {
                val result = withContext(Dispatchers.IO){
                    listOf(repositoryUser.postCrearUsuario(
                        nombre_usuario = _nombreCliente.value.toString(),
                        alias = _alias.value.toString(),
                        correo_electronico = _email.value.toString(),
                        password = _password.value.toString(),
                        fk_cliente = _id_cliente.value.toString().toInt()
                    ))
                }
                if (result.any { it.status == "B4C5" } ){
                    _isGetDataCorrect.value = true
                }else{
                    _error.value = "Error al insertar los datos"
                }
            }catch (e:Exception){
                _error.value = "Error por parte del servidor: " +
                        "${e.message}"

            }
            /*finally {
                _isLoading.value = false
            }*/
        }
    }

    fun isUsuarioRegistrado(){
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = withContext(Dispatchers.IO){
                    repositoryUser.getUserAppByEmail(_email.value.toString())
                }
                if (result.message == "A102"){
                    crearUsuario()
                }else{
                    _error.value = "El usuario ya existe " +
                            "si es un error, contactese con el administrador "
                    _isGetDataCorrect.value = false
                }
            }catch (e:Exception){
                _error.value = "Error en la consulta del filtro uno por parte del servidor: " +
                        "${e.message}"
            }finally {
                _isLoading.value = false
            }
        }
    }

    fun onClickBtn() {
        isUsuarioRegistrado()
    }


}