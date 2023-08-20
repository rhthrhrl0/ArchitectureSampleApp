package com.example.test.mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.test.R
import com.example.test.databinding.ActivityMvvmBinding

// MVVM 패턴을 적용해서, 기존의 MVP 패턴의 단점인 프레젠터가 뷰를 알아야 한다는 단점을 해소했다.
// 뷰모델은 프레젠터와 다르게, 단순하게 자신은 데이터 가공만하고 데이터들을 유지하고 관리한다.
// 해당 데이터가 필요한 뷰들이 알아서 해당 뷰모델에 있는 데이터들을 가져가서 보여줘야 한다.
// 이를 위해, 보통 MVVM 패턴에 옵저빙 패턴 혹은 데이터바인딩을 같이 사용한다.
// 이 예제에서는 Event상태 데이터들은 옵저빙 패턴으로 관찰한다.
// number라는 데이터는 데이터 바인딩으로 뷰가 반영되도록 연결했다.
// 액티비티에서 데이터바인딩 객체를 초기화하는 방법은 두 가지인데, 아래 코드에서 주석 처리된 두 문장을 대신 사용해도 된다.
class MvvmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMvvmBinding
    private val viewModel: MvvmSampleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm)
//        binding = ActivityMvvmBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.event.observe(this) { handleEvnet(it) }
    }

    private fun handleEvnet(event: MvvmSampleViewModel.Event) {
        when (event) {
            is MvvmSampleViewModel.Event.Exit -> exit()
            is MvvmSampleViewModel.Event.ShowMaximumMessage -> showMaximumMessage()
        }
    }

    private fun showMaximumMessage() {
        Toast.makeText(this, R.string.already_maximum_message, Toast.LENGTH_SHORT).show()
    }

    private fun exit() {
        finish()
    }
}
