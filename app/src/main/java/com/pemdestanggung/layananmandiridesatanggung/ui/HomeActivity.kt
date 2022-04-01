package com.pemdestanggung.layananmandiridesatanggung.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.shape.CornerFamily
import com.pemdestanggung.layananmandiridesatanggung.customui.MaterialCardCustomCorner
import com.pemdestanggung.layananmandiridesatanggung.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageContainer.shapeAppearanceModel =
            binding
                .imageContainer
                .shapeAppearanceModel
                .toBuilder()
                .setTopLeftCorner(MaterialCardCustomCorner())
                .setTopRightCorner(MaterialCardCustomCorner())
                .setBottomLeftCorner(CornerFamily.ROUNDED, 70f)
                .setBottomRightCorner(CornerFamily.ROUNDED, 70f)
                .build()
    }
}