package com.example.dicodingevent

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.dicodingevent.data.response.ListEventsItem
import com.example.dicodingevent.data.retrofit.ApiConfig
import com.example.dicodingevent.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_finished_event
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//       NAVIGATION BAR ^
//        API data retrofit
//        supportActionBar?.hide()
//        val layoutManager = LinearLayoutManager(this)
//        binding.tvTitle.layoutManager = layoutManager
//        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
//        binding.tvTitle.addItemDecoration(itemDecoration)

    }

//    private fun findEvent() {
//        showLoading(true)
//        val client = ApiConfig.getApiService().getEvent(EVENT_ID)
//        client.enqueue(object : Callback<ListEventsItem> {
//            override fun onResponse(
//                call: Call<ListEventsItem>,
//                response: Response<ListEventsItem>
//            ) {
//                showLoading(false)
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        setEventData(responseBody.events)
//                    }
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<ListEventsItem>, t: Throwable) {
//                showLoading(false)
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }
//
//    private fun setEventData(event: List<ListEventsItem>) {
//        binding.tvTitle.text = event.name
//        binding.tvDescription.text = event.description
//        Glide.with(this@MainActivity)
//            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
//            .into(binding.ivPicture)
//    }
////    private fun setReviewData(consumerReviews: List<CustomerReviewsItem>) {
////        val adapter = ReviewAdapter()
////        adapter.submitList(consumerReviews)
////        binding.rvReview.adapter = adapter
////        binding.edReview.setText("")
////    }
//
//    private fun showLoading(isLoading: Boolean) {
//        if (isLoading) {
//            binding.progressBar.visibility = View.VISIBLE
//        } else {
//            binding.progressBar.visibility = View.GONE
//        }
//    }
//
//
}