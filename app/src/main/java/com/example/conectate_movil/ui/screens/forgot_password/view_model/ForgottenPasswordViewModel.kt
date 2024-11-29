package com.example.conectate_movil.ui.screens.forgot_password.view_model

import EmailSender
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conectate_movil.data.models.Clientes
import com.example.conectate_movil.data.repositositories.ClienteDataRepository
import com.example.conectate_movil.utils.globals_data.TokenRepository
import com.example.conectate_movil.data.repositositories.UsuarioDataRepository
import com.example.conectate_movil.utils.globals_data.DataClientGlobal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


class ForgottenPasswordViewModel: ViewModel() {

    private val repositoryUser = UsuarioDataRepository()
    private val repositorioCliente = ClienteDataRepository()
    private val _misClientes = MutableStateFlow<List<Clientes>>(emptyList())

    //variable para ir guardando los errores
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    //para mostrar un dialogo de alerta en caso de que el contrato no exista en la BD
    private val _isDataError = MutableLiveData<Boolean>()

    //para que indica que esta haciendo una consulta
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    //variable para ir almacendo los datos cuando el user Ingresa datos de idContrato
    private val _idClientOrEmail = MutableLiveData<String>() //nuestro dato privado

    //lo que llamamos a la vista para observar los datos al ingresar datos
    val idClienteOrEmail: LiveData<String> = _idClientOrEmail

    //variable token
    // Variable privada para manejar el token
    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token.asStateFlow() // Exposición pública como StateFlow

    //para habilitar el boton si es el token correcto
    private val _isButtonEnable = MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    //verificar si el dato proporcionado es correo: usado internamente en la fucion onIdContractChange
    private val _isEmailData = MutableLiveData<Boolean>()

    //fncion para cooroborar que el valor sea un coreeo electronico
    private fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    //variable que nos indica si el cliente existe o no
    private val _isGetDataCorrect = MutableStateFlow(false)
    val isGetDataCorrect: StateFlow<Boolean> = _isGetDataCorrect.asStateFlow()


    //FOR UPDATEACCOUNT
    private val _nombreCliente = MutableLiveData<String>()
    val nombreCliente:LiveData<String> = _nombreCliente

    private val _alias = MutableLiveData<String>()
    val alias:LiveData<String> = _alias

    private val _email = MutableLiveData<String>()
    val email:LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password:LiveData<String> = _password

    private val _id_cliente = MutableLiveData<Int>()

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword:LiveData<String> = _confirmPassword

    private val _telefono = MutableLiveData<String>()
    val telefono:LiveData<String> = _telefono

    private val _direccion = MutableLiveData<String>()
    val direccion:LiveData<String> = _direccion

    //usamos init para inicializar los valores en la vista.
    init{
        _nombreCliente.value = DataClientGlobal.getClient()?.nombre_completo
        _telefono.value = DataClientGlobal.getClient()?.telefono
        _direccion.value = DataClientGlobal.getClient()?.direccion
        _email.value = DataClientGlobal.getClient()?.correo_electronico
        _id_cliente.value = DataClientGlobal.getClient()?.id_cliente
    }



    //FUNCIONES FORGOTTEN PASSWORD

    fun onIdContractChange(valor: String) {
        _idClientOrEmail.value = valor
        if (isEmailValid(valor)) {
            _isButtonEnable.value = true
            _isEmailData.value = true
        } else {
            _isButtonEnable.value = false
        }
    }

    fun verifyToken(cadena: String){
        val tokenKEY = TokenRepository.token
        _isGetDataCorrect.value = cadena == tokenKEY
    }


    //FUNCION PRINCIPAL PARA ENVIAR EL TOKEN POR EMAILS
    private fun sendTokenToEmail(email:String) {

        //Enviar el token por correo
        EmailSender.sendEmailAsync(
            to = email,
            subject = "Solicitud de token de verificación",
            body = """
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        body {
                            font-family: 'Poppins', sans-serif;
                            margin: 0;
                            padding: 0;
                            background-color: #f4f4f4;
                            color: #333;
                        }

                        .email-container {
                            max-width: 600px;
                            margin: 0 auto;
                            background: #fff;
                            border-radius: 8px;
                            overflow: hidden;
                            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                        }

                        .header {
                            background-color: #33ACC1;
                            padding: 20px;
                            text-align: center;
                            color: white;
                        }

                        .header h1 {
                            margin: 0;
                            font-size: 24px;
                            font-weight: 600;
                        }

                        .content {
                            padding: 20px;
                        }

                        .content h2 {
                            color: #2E86C1;
                        }

                        .token {
                            color: #4CAF50;
                            text-align: center;
                            font-size: 32px;
                            margin: 20px 0;
                            font-weight: 600;
                        }

                        .footer {
                            text-align: center;
                            padding: 10px;
                            font-size: 12px;
                            color: #777;
                        }

                        /* Responsive Design */
                        @media (max-width: 600px) {
                            .header h1 {
                                font-size: 20px;
                            }

                            .content {
                                padding: 15px;
                            }

                            .token {
                                font-size: 28px;
                            }
                        }
                    </style>
                </head>

                <body>
                    <div class="email-container">
                        <!-- Header -->
                        <div class="header">
                            <h1>¡Solicitud de Token de Verificación!</h1>
                        </div>

                        <!-- Content -->
                        <div class="content">
                            <h2>Hola, ${email}</h2>
                            <p>Hemos recibido una solicitud para generar un token de verificación asociado a tu cuenta.</p>
                            <p>Tu token es:</p>
                            <div class="token">${_token.value}</div>
                            <p>Por favor, utiliza este token para completar tu proceso de autenticación. Este token es válido por los
                                próximos <b>10 minutos</b>.</p>
                            <br>
                            <p>Si no realizaste esta solicitud, por favor ignora este correo o contacta a nuestro equipo de soporte.</p>
                        </div>

                        <!-- Footer -->
                        <div class="footer">
                            <p>Gracias,<br>El equipo de Conect@t</p>
                        </div>
                    </div>
                </body>
                </html>
            """.trimIndent(),
            callback = object : EmailSender.EmailStatusCallback {
                override fun onSuccess() {
                    println("Correo enviado con éxito")
                }

                override fun onError(error: String) {
                    println("Error al enviar el correo: $error")
                }
            }
        )
    }

    // Método para generar el token
    private fun tokenGenerate(longitud: Int = 5) {
        CoroutineScope(Dispatchers.IO).launch {
            val generatedToken = (1..longitud).joinToString("") { Random.nextInt(0, 10).toString() }
            _token.value = generatedToken
        }
    }

    //Temporizador
    private val initialTime = 600 // Tiempo inicial en segundos (ej: 10 minutos)
    var timeLeft by mutableIntStateOf(initialTime)
    var isRunning by mutableStateOf(false)

    suspend fun start(email: String) {
        _isButtonEnable.value = false
        //Generar el token
        tokenGenerate()

        //Esperar a que el token no sea nulo
        _token.filterNotNull().first()

        //Asignamos el valor del token al objeto
        TokenRepository.token = _token.value
        println(TokenRepository.token)

        //Enviamos el correo
        sendTokenToEmail(email)

        if (!isRunning && timeLeft > 0) {
            isRunning = true
            viewModelScope.launch {
                while (isRunning && timeLeft > 0) {
                    delay(1000L)
                    timeLeft--
                }
                isRunning = false // Detener el temporizador automáticamente cuando llega a 0
                _isButtonEnable.value = true
            }
        }
    }

    fun stop() {
        isRunning = false
    }

    fun reset() {
        timeLeft = initialTime
        isRunning = false
    }

    //============================================ EXTENDS FROM CLIENT


    //funcion para MUESTRE UN DIALOGO DE ALERTA CUANDO EL CONTRATO NO EXISTE
    fun isDataValid(data: Boolean) {
        _isDataError.value = data
    }

    //funcion que ejecutara al presionar el boton
    fun onClickButton() {
        if (_isEmailData.value == true) {
            isUsuarioRegistrado()
        } else {
            //getClientByIdCliente()
            print("ejecutando para ser id cliente")
        }
    }

    //funcion para saber si ya existe este usuario en la base d edatos p si ya se ha registrado anteriormente
    private fun isUsuarioRegistrado() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = withContext(Dispatchers.IO) {
                    repositoryUser.getUserAppByEmail(_idClientOrEmail.value.toString())
                }
                if (result.message == "A102") { //si nos manda un A102 es porque no existe el ussuario regisstrado y procede a registrarse.
                    _error.value = "Error:  Usuario no encontrado"
                    _isGetDataCorrect.value = false
                } else {
                    _isGetDataCorrect.value = true
                    //Obtenemos la informacion del cliente
                    getClientbyEmail()
                }
            } catch (e: Exception) {
                _error.value = "Error del servidor: " +
                        "${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    //FUNCION UPDATE USER
    fun onChangeValueSingUp(alias:String, password:String, confirmPassword:String){
        _alias.value = alias
        _password.value = password
        _confirmPassword.value = confirmPassword
        //habilitamos en boton si las contrasenias son iguales y la longitud es mayor a 6
        _isButtonEnable.value = idPassEqual(password, confirmPassword) && isPasswordValid(password)
    }

    //filtro para validar si las contrasenias son iguales y si la longitud es mayor a 6
    fun idPassEqual(password:String, confirmPassword:String):Boolean{
        return password == confirmPassword
    }
    fun isPasswordValid(password:String):Boolean{
        return password.length > 6
    }


    //ACTIUALIZAR INFORMACION DEL USUARIO
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
        }
    }

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

    fun onClickBtn() {
        isUsuarioRegistrado()
    }
}
