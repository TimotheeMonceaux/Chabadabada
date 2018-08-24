package timothee.chabadabada

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*


class HomeActivity : AppCompatActivity() {
    private val startButtonTouchListener = View.OnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP)
            startActivity(Intent(this, MainActivity::class.java))
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the Layout
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        // Map the listener to the Start Button
        home_start_button.setOnTouchListener(startButtonTouchListener)
    }
}
