package com.fawry.atfawry.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fawry.atfawry.Greeting
import android.widget.TextView
//import kotlinx.coroutines.MainScope
//import kotlinx.coroutines.launch
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val greeting = Greeting()
//    private val mainScope = MainScope()

    private val viewModel: UserViewModel by viewModels()

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "Loading..."

        job = lifecycleScope.launch {
            viewModel.getUsers().collectLatest {
                tv.text = it[0].name;
            }
        }

//        mainScope.launch {
//            kotlin.runCatching {
//                greeting.greeting()
//            }.onSuccess {
//                tv.text = it
//            }.onFailure {
//                tv.text = "Error: ${it.localizedMessage}"
//            }
//        }

    }
}
