package space.iqbalsyafiq.craftyhub.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var auth: FirebaseAuth

    // live data
    val loading = MutableLiveData<Boolean>()
}