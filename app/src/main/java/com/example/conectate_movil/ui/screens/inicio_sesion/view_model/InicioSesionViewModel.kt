package com.example.conectate_movil.ui.screens.inicio_sesion.view_model

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conectate_movil.data.models.Clientes
import com.example.conectate_movil.data.repositositories.ClienteDataRepository
import com.example.conectate_movil.data.repositositories.UsuarioDataRepository
import com.example.conectate_movil.utils.globals_data.DataClientGlobal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InicioSesionViewModel: ViewModel() {

    private val repositorioCliente = ClienteDataRepository()
    private val repositoryUser = UsuarioDataRepository()

    private val _misClientes = MutableStateFlow<List<Clientes>>(emptyList())

    //variable para ir guardando los errores
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    //variable para ir almacendo los datos cuando el user Ingresa datos de idContrato
    private val _idClientOrEmail = MutableLiveData<String>() //nuestro dato privado
    //lo que llamamos a la vista para observar los datos al ingresar datos
    val idClienteOrEmail: LiveData<String> = _idClientOrEmail

    //para habilitar el boton si cumple las ocho caracteres o es un correo
    private val _isButtonEnable =MutableLiveData<Boolean>()
    val isButtonEnable : LiveData<Boolean> = _isButtonEnable

    //para mostrar un dialogo de alerta en caso de que el contrato no exista en la BD
    private val _isDataError =MutableLiveData<Boolean>()
    val isDataError : LiveData<Boolean> = _isDataError

    //para que indica que esta haciendo una consulta
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    //verificar si el dato proporcionado es correo: usado internamente en la fucion onIdContractChange
    private val _isEmailData = MutableLiveData<Boolean>()

    //funcion para corrobor si tiene la id cliente tiene comucho 8 caracteres
    private fun isIdCliente (valor: String):Boolean = valor.length <= 3
    //fncion para cooroborar que el valor sea un coreeo electronico
    private fun isEmailValid (email:String):Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    //variable que nos indica si el cliente existe o no
    private val _isGetDataCorrect = MutableStateFlow(false)
    val isGetDataCorrect: StateFlow<Boolean> = _isGetDataCorrect.asStateFlow()


    //funcion para ir gurdando lo que el usuario  ingrese
    fun onIdContractChange(valor:String){ //se estable la fucnion en el textflied al ir ingresando texto
        _idClientOrEmail.value = valor
//
//        if (isIdCliente(valor)){
//            _isButtonEnable.value = true//habilitamos el boton
//            _isEmailData.value = false //decimos que el dato que dio el usurio no es un correo
        if (isEmailValid(valor)){
            _isButtonEnable.value = true
            _isEmailData.value = true //decimos que el dato que dio el usurio es un correo
        }else{
            _isButtonEnable.value = false
        }
    }

    //funcion para MUESTRE UN DIALOGO DE ALERTA CUANDO EL CONTRATO NO EXISTE
    fun isDataValid(data: Boolean) {
        _isDataError.value = data
    }

    //funcion que ejecutara al presionar el boton
    fun onClickButton(){
        if (_isEmailData.value == true){
            isUsuarioRegistrado()
        }else{
            //getClientByIdCliente()
            print("ejecutando para ser id cliente")
        }
    }

    //funcion para saber si ya existe este usuario en la base d edatos p si ya se ha registrado anteriormente
    fun isUsuarioRegistrado(){
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = withContext(Dispatchers.IO){
                    repositoryUser.getUserAppByEmail(_idClientOrEmail.value.toString())
                }
                if (result.message == "A102"){ //si nos manda un A102 es porque no existe el ussuario regisstrado y procede a registrarse.
                    getClientbyEmail()
                }else{
                    _error.value = "Este  correo electrónico ya se encuentra registrado, " +
                            "si crees que esto es un error, comuníquese con el soporte técnico "
                    _isGetDataCorrect.value = false
                }
            }catch (e:Exception){
                _error.value = "Error del servidor: " +
                        "${e.message}"
            }finally {
                _isLoading.value = false
            }
        }
    }


    //funcion para obtener el correo y comparar si el igual para dejarnos pasar al siguiente view

    fun getClientbyEmail(){
        viewModelScope.launch {
            _isLoading.value = true  //usando la misma vatiable
            _error.value = null
            try {

                val misClientes = withContext((Dispatchers.IO)) { //se asegura de que la consulta se ejecue en segundo plano
                    listOf(repositorioCliente.getClienteByEmail(_idClientOrEmail.value.toString()))
                }

                _misClientes.value = misClientes

                //DataClientGlobal.sharedClient = misClientes[0] //compartimos la lista de cliente

                DataClientGlobal.setClient(misClientes[0]) //guardamos el cliente

                println("mis correo es: ${DataClientGlobal.getClient()?.correo_electronico}")


                if (misClientes.any { it.es_cliente == 1 }){
                    _isGetDataCorrect.value = true
                }else{
                    _error.value = "Error: el cliente con este correo electronico aun no ha finalizado su registro, aun no ha firmado el contrato. \n" +
                            "si es un error, contactese con el administrador"
                }

            } catch (e: Exception) {
                _error.value = "Error: no se encontro un cliente con ese correo electronico: " +
                        "si es un error, contactese con el administrador o intente de nuevo, " +
                        "si el error persiste, intente de nuevo mas tarde"
            } finally {
                _isLoading.value = false
            }
        }
    }



}