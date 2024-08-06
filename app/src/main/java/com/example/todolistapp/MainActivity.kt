package com.example.todolistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.todolistapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isAllTasksSelected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val currentDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(Date())
        binding.message = currentDate

        updateTextColor()

        binding.buttonCreateTask.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, "Bottom Sheet Fragment")
        }

        // Get NavController from the NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.textViewCompletedTask.setOnClickListener {
            isAllTasksSelected = false
            updateTextColor()
            if (navController.currentDestination?.id != R.id.completedTaskFragment2) {
                navController.navigate(R.id.action_allTaskFragment_to_completedTaskFragment2)
            }
        }

        binding.textViewAllTasks.setOnClickListener {
            isAllTasksSelected = true
            updateTextColor()
            if (navController.currentDestination?.id != R.id.allTaskFragment) {
                navController.navigate(R.id.action_completedTaskFragment2_to_allTaskFragment)
            }
        }
    }

    private fun updateTextColor() {
        val textColorSelected = resources.getColor(R.color.pink, theme)
        val textColorUnselected = resources.getColor(R.color.grey, theme)

        if (isAllTasksSelected) {
            binding.textViewAllTasks.setTextColor(textColorSelected)
            binding.textViewCompletedTask.setTextColor(textColorUnselected)
        } else {
            binding.textViewAllTasks.setTextColor(textColorUnselected)
            binding.textViewCompletedTask.setTextColor(textColorSelected)
        }
    }
}
