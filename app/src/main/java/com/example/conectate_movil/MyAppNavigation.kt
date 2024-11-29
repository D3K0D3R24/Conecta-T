package com.example.conectate_movil

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.conectate_movil.ui.screens.inicio_sesion.ui.MyLoginApp
import com.example.conectate_movil.ui.screens.inicio_sesion.view_model.LoginViewModel
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.example.conectate_movil.dashboard.view_model.DashboardViewModel
import com.example.conectate_movil.data.api.DataStoreService
import com.example.conectate_movil.data.datastore.UsuarioPreferenceDataStore
import com.example.conectate_movil.ui.screens.dashboard.ui.DashboardScreen
import com.example.conectate_movil.ui.screens.forgot_password.ui.ForgotPasswordScreen
import com.example.conectate_movil.ui.screens.forgot_password.ui.TwoVerificationScreen
import com.example.conectate_movil.ui.screens.forgot_password.ui.UpdateAccountScreen
import com.example.conectate_movil.ui.screens.forgot_password.view_model.ForgottenPasswordViewModel
import com.example.conectate_movil.ui.screens.inicio_sesion.ui.MyApp
import com.example.conectate_movil.ui.screens.inicio_sesion.view_model.InicioSesionViewModel
import com.example.conectate_movil.ui.screens.singup.SignUpView
import com.example.conectate_movil.ui.screens.singup.SingUpViewModel

class ConnectivityObserver(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean> = _isConnected

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isConnected.postValue(true)  // Conexión disponible
        }

        override fun onLost(network: Network) {
            _isConnected.postValue(false) // Conexión perdida
        }
    }

    init {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun checkConnection(): Boolean {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}

//aqui se detalla la navegacion de la app
//aqui podemos describir las ventanas que se van a mostrar

//ventana principal.
const val Home = "home"

//ventana de inicio de sesion, es la vista que se vera al iniciar
const val LoginPage = "ventana_Login"

//esta es la vista para dar inicio a registro de usuario de la aplicacion
const val contractScreen = "consultarContrato"

//ventana de crear una cuenta de usuario
const val SignUpPage = "ventana_SignUp"


//VENTANAS: FORGOTPASSWORD
const val twoVerificationScreen = "ventana_twoVerification"
const val ForgotPasswordPage = "ventana_ForgotPassword"
const val UpdateAccountPage = "ventana_UpdateAccount"




//aqui se detalla como se va a mostrar dettadamente como se navega y hacia donde
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(istab: Boolean) {
    val navController = rememberNavController()

    //Para comprobar si hay conexion a la red
    val context = LocalContext.current
    val connectivityObserver = remember { ConnectivityObserver(context) }
    val isConnected by connectivityObserver.isConnected.observeAsState(initial = connectivityObserver.checkConnection())
    val dataStoreService = DataStoreService(context)


    //Guardaremos la informacion de quien es la persona que inicio sesion y la fecha de inicio de sesion



    NavHost(navController = navController, startDestination = LoginPage) {
        composable(Home) {
            DashboardScreen(istab, DashboardViewModel())
        }

        //-----------------------------------------------------------------------
        //Este código de control sirve para llamar a otras vistas rápidamente
//        composable(Home) {
//
//            SignUpView(onClick = {})
//        }
        //este composable se mostrara cuando en MyApp se presiona el boton para ir a la ventana login
        //-----------------------------------------------------------------------


        composable(LoginPage) {
            MyLoginApp(istab, LoginViewModel(),
                onclick = {
                    navController.navigate(Home) {

                    }
                    //este es el evento que se dispara al presionar el boton y nos manda a la ventana detallada
                }, onclick1 = {

                    navController.navigate(contractScreen) {

                    }
                },
                onclickForgotPassword = {
                    navController.navigate(ForgotPasswordPage) {

                    }
                })
        }
        //Crear una cuenta
        composable(SignUpPage) {
            SignUpView(onClick = {
                navController.navigate(LoginPage) {

                }
            }, istab, SingUpViewModel())
        }

        //FortgotPasswordScreen
        composable(ForgotPasswordPage) {
            ForgotPasswordScreen(istab, ForgottenPasswordViewModel(),
                //este es el evento que se dispara al presionar el boton y nos manda a la ventana detallada
                onClick = {
                    navController.navigate(twoVerificationScreen) {

                    }
                })
        }

        composable(contractScreen) {
            MyApp(istab, InicioSesionViewModel(),
                //este es el evento que se dispara al presionar el boton y nos manda a la ventana detallada
                onClick = {
                    navController.navigate(SignUpPage) {

                    }
                })
        }

        composable(twoVerificationScreen) {
            TwoVerificationScreen(istab, ForgottenPasswordViewModel(), onClick = {
                //De momento no hará nada
                navController.navigate(UpdateAccountPage) {

                }
            })
        }

        composable(UpdateAccountPage) {
            UpdateAccountScreen(istab, ForgottenPasswordViewModel(), onClick = {
                //De momento no hará nada
            })
        }
    }
}
