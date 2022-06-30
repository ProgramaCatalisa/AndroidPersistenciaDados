package br.com.zup.movieflix.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.databinding.ActivityLoginBinding
import br.com.zup.movieflix.domain.model.User
import br.com.zup.movieflix.ui.home.view.HomeActivity
import br.com.zup.movieflix.ui.login.viewmodel.LoginViewModel
import br.com.zup.movieflix.ui.register.view.RegisterActivity
import br.com.zup.movieflix.ui.viewstate.ViewState

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegistro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.bvLogin.setOnClickListener {
            viewModel.login(
                user = User(
                    email = binding.etUsername.text.toString(),
                    password = binding.etPassword.text.toString()
                ),
                flagSaveData = binding.swSaveData.isChecked
            )
        }

        initObserver()
        viewModel.getUserLogged()
    }

    private fun initObserver() {
        viewModel.userLoggedState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    binding.etUsername.setText(it.data.email)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        this,
                        "Ops tivemos um problema! Tente novamente.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }

        viewModel.loginState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }

                is ViewState.Error -> {
                    binding.etUsername.error = it.throwable.message
                    binding.etPassword.error = it.throwable.message
                }
                else -> {}
            }

        }
    }
}