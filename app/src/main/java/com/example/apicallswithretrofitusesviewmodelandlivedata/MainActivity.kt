package com.example.apicallswithretrofitusesviewmodelandlivedata
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicallswithretrofitusesviewmodelandlivedata.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val viewModel: MyViewModel by lazy {
        ViewModelProvider(this)[MyViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerviewUsers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerviewUsers.layoutManager = linearLayoutManager


        viewModel.getMyLiveData.observe(this, Observer { data ->
            data?.let {
                myAdapter = MyAdapter(baseContext, it)
                myAdapter.notifyDataSetChanged()
                binding.recyclerviewUsers.adapter = myAdapter
            }
        })

        viewModel.getMyData()
    }
}


