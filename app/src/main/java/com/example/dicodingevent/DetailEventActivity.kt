package com.example.dicodingevent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.dicodingevent.data.response.Event
import com.example.dicodingevent.databinding.ActivityDetailEventBinding
import com.example.dicodingevent.databinding.ActivityMainBinding

class DetailEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        val event = intent.getParcelableExtra<Event>("EXTRA_EVENT")
        event?.let {
            Glide.with(this).load(it.imageLogo).into(binding.imageView)
            binding.tvName.text = it.name
            binding.tvOwner.text = it.ownerName
            binding.tvLocation.text = it.cityName
            binding.tvQuota.text = it.quota.toString()
            binding.tvBegin.text = it.beginTime
            binding.tvDescription.text = HtmlCompat.fromHtml(
                it.description,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            val link = it.link
            binding.btnRegist.setOnClickListener{
                val web = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(web)
            }
        }
        showLoading(false)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}