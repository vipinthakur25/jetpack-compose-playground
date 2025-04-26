package com.vipint.jetpack_compose_playground.sideeffects

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vipint.jetpack_compose_playground.R
import com.vipint.jetpack_compose_playground.databinding.ActivitySideEffectSelectionBinding

class SideEffectSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySideEffectSelectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySideEffectSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnWhatAreSideEffect.setOnClickListener {
            startActivity(Intent(this, WhatAreSideEffectsActivity::class.java))
        }
        binding.btnLaunchedEffect.setOnClickListener {
            startActivity(Intent(this, LaunchedEffectActivity::class.java))
        }
        binding.btnRememberCoroutineScope.setOnClickListener {
            startActivity(Intent(this, RememberCoroutineScopeActivity::class.java))
        }
        binding.btnDisposableEffect.setOnClickListener {
            startActivity(Intent(this, DisposableEffectActivity::class.java))
        }
        binding.btnSideEffect.setOnClickListener {
            startActivity(Intent(this, SideEffectActivity::class.java))
        }
        binding.btnRememberUpdatedState.setOnClickListener {
            startActivity(Intent(this, RememberUpdatedStateActivity::class.java))
        }
        binding.btnDerivedStateOf.setOnClickListener {
            startActivity(Intent(this, DerivedStateOfActivity::class.java))
        }
        binding.btnProduceStateOf.setOnClickListener {
            startActivity(Intent(this, ProduceStateOfActivity::class.java))
        }
        binding.btnSnapshotFlow.setOnClickListener {
            startActivity(Intent(this, SnapShotFlowActivity::class.java))
        }
    }
}