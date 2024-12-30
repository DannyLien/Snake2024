package com.hank.snake

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.hank.snake.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SnakeViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        //
        viewModel = ViewModelProvider(this).get(SnakeViewModel::class.java)
        viewModel.body.observe(this) {
            binding.contentView.gameView.snakeBody = it
            binding.contentView.gameView.invalidate()
        }
        viewModel.apple.observe(this) {
            binding.contentView.gameView.apple = it
            binding.contentView.gameView.invalidate()
        }
        viewModel.score.observe(this) {
            binding.contentView.score.text = it.toString()
        }
        viewModel.gameState.observe(this) {
            if (it == GameState.GAME_OVER) {
                AlertDialog.Builder(this)
                    .setTitle("Game")
                    .setMessage("Game Over")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
        viewModel.start()
        binding.contentView.top.setOnClickListener { viewModel.move(Direction.TOP) }
        binding.contentView.down.setOnClickListener { viewModel.move(Direction.DOWN) }
        binding.contentView.left.setOnClickListener { viewModel.move(Direction.LEFT) }
        binding.contentView.right.setOnClickListener { viewModel.move(Direction.RIGHT) }

        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .setAnchorView(R.id.fab).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}