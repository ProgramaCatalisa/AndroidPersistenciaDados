package br.com.zup.movieflix.ui.register.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.databinding.ActivityRegisterBinding
import br.com.zup.movieflix.domain.model.User
import br.com.zup.movieflix.ui.home.view.HomeActivity
import br.com.zup.movieflix.ui.register.viewmodel.RegisterViewModel
import br.com.zup.movieflix.ui.viewstate.ViewState

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bvLogin.setOnClickListener {
            viewModel.register(
                user = User(
                    name = binding.etUserNameRegister.text.toString(),
                    email = binding.etEmailRegister.text.toString(),
                    password = binding.etPasswordRegister.text.toString(),
                    confirmationPassword = binding.etConfirmPasswordRegister.text.toString()
                )
            )
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.registerState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        this,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }
}