package timothee.chabadabada

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the Layout
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        // Map the listeners to the  Buttons
        home_start_button.setOnTouchListener(startButtonTouchListener)
        home_settings_button.setOnTouchListener(settingsButtonTouchListener)
        home_quit_button.setOnTouchListener(quitButtonTouchListener)
    }

    private val startButtonTouchListener = View.OnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP)
            startActivity(Intent(this, MainActivity::class.java))
        false
    }

    private val settingsButtonTouchListener = View.OnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP)
            startActivity(Intent(this, SettingsActivity::class.java))
        false
    }

    private val quitButtonTouchListener = View.OnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP)
            finishAndRemoveTask()
        false
    }
}
