package ru.verb.aston_intensive_hometask_3_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.verb.aston_intensive_hometask_3_2.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater).also { setContentView(it.root) }
        with(binding) {
            textEdit.addTextChangedListener(getDebouncedTextWatcher(DELAY) {
                image.downloadImageWithNativeTools(it)
            })
        }
    }
}