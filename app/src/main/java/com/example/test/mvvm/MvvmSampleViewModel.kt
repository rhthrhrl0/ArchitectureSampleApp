package com.example.test.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.model.Counter

class MvvmSampleViewModel : ViewModel() {
    private val counter = Counter()

    private val _number: MutableLiveData<Int> = MutableLiveData(counter.number)
    val number: LiveData<Int>
        get() = _number

    // 원래는 이런 프로퍼티는 MutableLiveData말고, SingleLiveEvent나 Event래퍼 클래스를 사용해야 하나, 샘플 예제이므로 그냥 뮤터블 라이브데이터로 진행
    private val _event: MutableLiveData<Event> = MutableLiveData()
    val event: LiveData<Event>
        get() = _event

    fun plus() {
        if (counter.plus()) return changeNumber(counter.number)
        _event.value = Event.ShowMaximumMessage
    }

    fun minus() {
        if (counter.minus()) return changeNumber(counter.number)
        _event.value = Event.Exit
    }

    private fun changeNumber(number: Int) {
        _number.value = number
    }

    sealed class Event {
        object Exit : Event()
        object ShowMaximumMessage : Event()
    }
}
