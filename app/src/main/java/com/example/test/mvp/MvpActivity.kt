package com.example.test.mvp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R
import com.example.test.model.Counter

// Mvp 패턴은 기존의 MVC 패턴의 액티비티에 몰린 과도한 역할 중 비즈니스 로직 처리 부분을 프레젠터에게 옮겨가도록 한 것이다
// 이로 인해, 액티비티에는 뷰에 리스너를 달아서 프레젠터의 특정 메소드를 호출만 하도록 연결만 해주고, 프레젠터로 부터 명령을 받는 메소드를 퍼블릭으로 열어둔 것 외에는 코드가 거의 남지 않게 된다.
// 프레젠터에서는 모델 조작 및 뷰(=액티비티)에게 명령을 내려서 뷰의 설정값을 바꾸는 등의 수행을 한다.
// Mvp 패턴은 Contract인터페이스로 뷰와 프레젠터 간의 계약을 맺도록 한다. 이로 인해, 인터페이스만 보고도 해당 뷰와 프레젠터에서 무슨일이 일어나는지 예측할 수 있다.
// 단, 아직도 액티비티에서는 리스너 연결 로직이 모두 필요하다. 또한, 프레젠터가 뷰객체에게 데이터 가공 등의 처리 결과를 직접적으로 알려야 하므로
// 프레젠터가 뷰를 알고 있어야 한다는 단점이 아직 남아있다.
class MvpActivity : AppCompatActivity(), MvpSampleContract.View {
    private val numberTv: TextView by lazy {
        findViewById(R.id.tv_number)
    }

    private val minusBtn: Button by lazy {
        findViewById(R.id.btn_minus)
    }

    private val plusBtn: Button by lazy {
        findViewById(R.id.btn_plus)
    }

    private lateinit var presenter: MvpSampleContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)

        presenter = MvpSamplePresenter(this, Counter())

        minusBtn.setOnClickListener { presenter.minus() }
        plusBtn.setOnClickListener { presenter.plus() }
    }

    override fun changeNumber(number: Int) {
        numberTv.text = number.toString()
    }

    override fun showMaximumMessage() {
        Toast.makeText(this, R.string.already_maximum_message, Toast.LENGTH_SHORT).show()
    }

    override fun exit() {
        finish()
    }
}
