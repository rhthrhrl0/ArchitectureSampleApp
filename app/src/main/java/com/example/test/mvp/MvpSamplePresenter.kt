package com.example.test.mvp

import com.example.test.model.Counter

class MvpSamplePresenter(
    private val view: MvpSampleContract.View,
    private val counter: Counter,
) : MvpSampleContract.Presenter {
    init {
        view.changeNumber(counter.number)
    }

    override fun plus() {
        if (counter.plus()) return view.changeNumber(counter.number)
        view.showMaximumMessage()
    }

    override fun minus() {
        if (counter.minus()) return view.changeNumber(counter.number)
        view.exit()
    }
}
