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

        main_first_card_text.text = getValue()
        main_second_card_text.text = getValue()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null && event.action == MotionEvent.ACTION_UP) {
            main_first_card_text.text = getValue()
            main_second_card_text.text = getValue()
        }
        return true
    }

    companion object {
        private val possibleValues: List<String> = listOf(
                "FAUT",
                "AMOUR",
                "TEMPS",
                "SEXE",
                "VOLE",
                "SAIS",
                "FAIRE",
                "DANSE",
                "PAPA",
                "DIRE",
                "COEUR",
                "TOMBER",
                "BEAU",
                "POURQUOI",
                "NUIT",
                "VEUX",
                "AIME",
                "JOUR",
                "DANSER",
                "LAISSE",
                "MOTS",
                "MONDE",
                "SOIR",
                "VIENS",
                "PEUX",
                "LOUP",
                "PEUR",
                "CIEL",
                "VENT",
                "LAID",
                "DINGUE",
                "VAIS",
                "BRAS",
                "FOND",
                "SILENCE",
                "AIMER",
                "GENS",
                "CROIS",
                "HAUT",
                "APPELLE",
                "VOIR",
                "ENTENDS",
                "GRAND",
                "TOP"
        )

        private fun getValue(): String {
            return possibleValues[Random().nextInt(possibleValues.size)]
        }
    }
}
