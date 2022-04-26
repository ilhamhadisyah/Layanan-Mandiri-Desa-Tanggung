package com.pemdestanggung.layananmandiridesatanggung.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.pemdestanggung.layananmandiridesatanggung.R
import com.pemdestanggung.layananmandiridesatanggung.databinding.ActivitySliderBinding
import com.pemdestanggung.layananmandiridesatanggung.ui.appslider.SliderAdapter
import com.pemdestanggung.layananmandiridesatanggung.ui.appslider.sliderfragment.Slide_1_Fragment
import com.pemdestanggung.layananmandiridesatanggung.ui.appslider.sliderfragment.Slide_2_Fragment
import com.pemdestanggung.layananmandiridesatanggung.ui.appslider.sliderfragment.Slide_3_Fragment
import com.pemdestanggung.layananmandiridesatanggung.ui.home.HomeActivity
import com.pemdestanggung.layananmandiridesatanggung.utils.PreferenceManager

class SliderActivity : AppCompatActivity() ,View.OnClickListener{

    private lateinit var binding : ActivitySliderBinding
    private val fragmentList = ArrayList<Fragment>()
    private lateinit var prefManager : PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefManager = PreferenceManager(this)

        if (!prefManager.isFirstTimeLaunched){
            startMainActivity()
            finishAffinity()
        }
        binding = ActivitySliderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sliderAdapter = SliderAdapter(this)
        binding.viewPager.adapter = sliderAdapter

        fragmentList.addAll(
            listOf(Slide_1_Fragment(),Slide_2_Fragment(),Slide_3_Fragment())
        )

        sliderAdapter.setFragmentList(fragmentList)
        binding.indicatorLayout.apply {
            setIndicatorCount(sliderAdapter.itemCount)
            selectCurrentPosition(0)
        }

        with(binding){
            nextBtn.setOnClickListener(this@SliderActivity)
            skipBtn.setOnClickListener(this@SliderActivity)
        }
        register()
    }

    private fun startMainActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun register() {
        binding.apply {
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    indicatorLayout.selectCurrentPosition(position)
                    if (position < fragmentList.lastIndex) {
                        skipBtn.visibility = View.VISIBLE
                        nextBtn.text = "Next"

                    } else {
                        skipBtn.visibility = View.INVISIBLE
                        nextBtn.text = "Mulai"
                    }

                }
            })
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.next_btn -> {
                val position = binding.viewPager.currentItem
                if (position < fragmentList.lastIndex) {
                    binding.viewPager.currentItem = position + 1
                } else {
                    prefManager.isFirstTimeLaunched=false
                    startMainActivity()
                }
            }
            R.id.skip_btn -> {
                prefManager.isFirstTimeLaunched=false
                startMainActivity()
            }
        }
    }

}