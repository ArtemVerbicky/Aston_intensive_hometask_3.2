package ru.verb.aston_intensive_hometask_3_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.verb.aston_intensive_hometask_3_2.databinding.ActivitySecondBinding

const val DELAY = 2000L

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.textEdit.addTextChangedListener(getDebouncedTextWatcher(DELAY) {
            binding.image.downloadImageWithGlide(it)
        })
    }
}