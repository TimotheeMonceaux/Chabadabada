package timothee.chabadabada

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Map the listeners to the  Buttons
        settings_save_button.setOnTouchListener(saveButtonTouchListener)
    }

    private val saveButtonTouchListener = View.OnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP)
            startActivity(Intent(this, HomeActivity::class.java))
        false
    }
}
