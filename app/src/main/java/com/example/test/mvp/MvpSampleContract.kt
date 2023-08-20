package com.example.test.mvp

interface MvpSampleContract {
    interface View {
        fun changeNumber(number: Int)
        fun showMaximumMessage()
        fun exit()
    }

    interface Presenter {
        fun plus()
        fun minus()
    }
}
