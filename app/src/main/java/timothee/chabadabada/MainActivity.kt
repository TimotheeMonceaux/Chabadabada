package timothee.chabadabada

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the Layout
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        main_background_text.text = getValue()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null && event.action == MotionEvent.ACTION_UP) main_background_text.text = getValue()
        return true
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

        private fun getValue(): String {
            return possibleValues[Random().nextInt(possibleValues.size)]
        }
    }
}
