package timothee.chabadabada

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var turn: Int = 0
    var nbTurns: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve Preferences
        nbTurns = getSharedPreferences(getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE)
                        .getInt(getString(R.string.shared_preferences_settings_number_of_rounds),10)

        // Set the Layout
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        nextTurn()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null && event.action == MotionEvent.ACTION_UP) {
            nextTurn()
        }
        return true
    }

    private fun nextTurn() {
        if (++turn > nbTurns) {
            finish()
        }
        else {
            main_turn_counter_text.text = "TURN $turn / $nbTurns"
            main_first_card_text.text = getValue()
            main_second_card_text.text = getValue()
        }
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
