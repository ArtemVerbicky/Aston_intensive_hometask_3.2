package ru.verb.aston_intensive_hometask_3_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.verb.aston_intensive_hometask_3_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        with(binding) {
            buttonSecondActivity.setOnClickListener {
                launchActivity(SecondActivity::class.java)
            }
            buttonThirdActivity.setOnClickListener {
                launchActivity(ThirdActivity::class.java)
            }
        }
    }
    private inline fun <reified T> launchActivity(clazz: Class<T>) {
        Intent(this, clazz).apply {
            startActivity(this)
        }
    }
}