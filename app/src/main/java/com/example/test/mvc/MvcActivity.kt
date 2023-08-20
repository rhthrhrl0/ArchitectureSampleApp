package com.example.test.mvc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R
import com.example.test.model.Counter

// 안드로이드는 프레임워크 의존적인 개발 환경임. 때문에, 액티비티는 사실 가만히 숨만숴도 할일이 너무 많음
// 예를 들어, 다른 액티비티를 시작시키던지, 프래그먼트를 교체한다던지, 권한 허용을 받는다던지 등 이미 할 일이 너무 많음
// 그런데, 여기서 모델객체들을 조작하고, 또 그 값을 꺼내서 뷰들을 교체하는 작업을 하고, 뷰에 리스너도 설정해주고 등등까지
// 여기 액티비티에서 다 해버리면 나중에 프로그램 사이즈가 커질수록 너무 길고 더러워져서 유지보수가 힘들 수 있음.
class MvcActivity : AppCompatActivity() {
    private var counter: Counter = Counter()

    private val numberTv: TextView by lazy {
        findViewById(R.id.tv_number)
    }

    private val minusBtn: Button by lazy {
        findViewById(R.id.btn_minus)
    }

    private val plusBtn: Button by lazy {
        findViewById(R.id.btn_plus)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)

        changeNumber(counter.number)
        minusBtn.setOnClickListener {
            if (counter.minus()) return@setOnClickListener changeNumber(counter.number)
            exit()
        }

        plusBtn.setOnClickListener {
            if (counter.plus()) return@setOnClickListener changeNumber(counter.number)
            showMaximumMessage()
        }
    }

    private fun changeNumber(number: Int) {
        numberTv.text = number.toString()
    }

    private fun exit() {
        finish()
    }

    private fun showMaximumMessage() {
        Toast.makeText(this, R.string.already_maximum_message, Toast.LENGTH_SHORT).show()
    }
}
