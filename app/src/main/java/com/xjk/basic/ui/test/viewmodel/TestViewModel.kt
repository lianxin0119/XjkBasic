package com.xjk.basic.ui.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class TestModel(
    val name: MutableLiveData<String> = MutableLiveData("11")
)

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/18 19:36
 */
class TestViewModel : ViewModel() {

    val model: TestModel = TestModel()

    fun requestName(name: String) {
        Thread {
            Thread.sleep(500)
            model.name.postValue(name)
        }.start()
    }

}