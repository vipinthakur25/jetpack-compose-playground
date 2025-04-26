package com.vipint.jetpack_compose_playground

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vipint.jetpack_compose_playground.basics.RecompositionActivity
import com.vipint.jetpack_compose_playground.basics.StateExampleActivity
import com.vipint.jetpack_compose_playground.databinding.ActivityMainBinding
import com.vipint.jetpack_compose_playground.sideeffects.SideEffectSelectionActivity
import com.vipint.jetpack_compose_playground.statehoisting.NeedOfStateHoistingActivity
import com.vipint.jetpack_compose_playground.statehoisting.StateHoistingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnStateExample.setOnClickListener {
            startActivity(Intent(this, StateExampleActivity::class.java))
        }
        binding.btnRecompositionExample.setOnClickListener {
            startActivity(Intent(this, RecompositionActivity::class.java))
        }
        binding.btnSideEffectExample.setOnClickListener {
            startActivity(Intent(this, SideEffectSelectionActivity::class.java))
        }
        binding.btnNeedOfStateHoisting.setOnClickListener {
            startActivity(Intent(this, NeedOfStateHoistingActivity::class.java))
        }
        binding.btnStateHoisting.setOnClickListener {
            startActivity(Intent(this, StateHoistingActivity::class.java))
        }

    }
}