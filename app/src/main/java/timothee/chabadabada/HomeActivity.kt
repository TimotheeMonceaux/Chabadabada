package timothee.chabadabada

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class HomeActivity : AppCompatActivity() {
    private fun getValue(): String {
        return possibleValues.get(Random().nextInt(possibleValues.size))
    }

    private val startButtonTouchListener = View.OnTouchListener { _, _ ->
        home_text_button.text = getValue()
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        // Map the listener to the Start Button
        home_text_button.setOnTouchListener(startButtonTouchListener)
    }

    companion object {
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
