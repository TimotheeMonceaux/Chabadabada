package timothee.chabadabada

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_home_page.*
import java.util.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class HomePageActivity : AppCompatActivity() {
    private fun getValue(): String {
        return possibleValues.get(Random().nextInt(possibleValues.size))
    }

    private val mDelayHideTouchListener = View.OnTouchListener { _, _ ->
        text_button.text = getValue()
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        text_button.setOnTouchListener(mDelayHideTouchListener)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private val possibleValues: List<String> = listOf(
                "AMOUR",
                "PASSION",
                "COULEUR",
                "NUIT",
                "JOUR",
                "MINUIT",
                "SOLEIL",
                "CIEL"
        )
    }
}
