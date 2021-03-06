package space.iqbalsyafiq.craftyhub.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    application: Application,
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : BaseViewModel(application) {

    private var storage = Firebase.storage

    // live data
    val loading = MutableLiveData<Boolean>()
    val success = MutableLiveData<Boolean>()

    // methods
    fun registerUser(email: String, password: String) {
        loading.value = true

        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val data = hashMapOf(
                            "uuid" to auth.currentUser?.uid,
                            "email" to email,
                            "password" to password
                        )
                        addToDatabase(data)
                    } else {
                        loading.value = false
                        success.value = false
                    }
                }
        }
    }

    private fun addToDatabase(data: HashMap<String, String?>) {
        viewModelScope.launch {
            db.collection("users").document(data.getValue("uuid").toString()).set(data)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        loading.value = false
                        success.value = true
                    } else {
                        loading.value = false
                        success.value = false
                    }
                }
        }
    }
}