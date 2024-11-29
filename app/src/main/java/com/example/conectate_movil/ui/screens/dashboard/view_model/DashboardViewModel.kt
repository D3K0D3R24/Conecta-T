package com.example.conectate_movil.dashboard.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.conectate_movil.data.models.Plan

class DashboardViewModel: ViewModel(){

    // Nombre del Usuario
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    // lista de planes
    private val _planList = MutableLiveData<List<Plan>>()
    val planList: LiveData<List<Plan>> = _planList

}
