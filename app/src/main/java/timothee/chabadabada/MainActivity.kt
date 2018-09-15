package timothee.chabadabada

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import timothee.chabadabada.core.WordStreamer
import timothee.chabadabada.model.Word
import timothee.chabadabada.model.raw.Language
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {

    private var turn: Int = 0
    private var nbTurns: Int = 0
    private var timer: Int = 0
    private var wordStreamerFirstCard: WordStreamer? = null
    private var wordStreamerSecondCard: WordStreamer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve Preferences
        nbTurns = getSharedPreferences(getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE)
                        .getInt(getString(R.string.shared_preferences_settings_number_of_rounds),10)
        wordStreamerFirstCard =  WordStreamer.getInstance(this,
                                                          Language.valueOf(getSharedPreferences(getString(R.string.shared_preferences_settings),
                                                                                                Context.MODE_PRIVATE)
                                                                               .getString(getString(R.string.shared_preferences_settings_first_card_language),
                                                                                          Language.French.toString())!!))
        wordStreamerSecondCard = WordStreamer.getInstance(this,
                                                          Language.valueOf(getSharedPreferences(getString(R.string.shared_preferences_settings),
                                                                                                 Context.MODE_PRIVATE)
                                                                               .getString(getString(R.string.shared_preferences_settings_first_card_language),
                                                                                          Language.English.toString())!!))

        // Set the Layout
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // Map the listeners to the  Buttons
        main_hourglass_button.setOnTouchListener(hourglassButtonTouchListener)

        nextTurn()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null && event.action == MotionEvent.ACTION_UP) {
            nextTurn()
        }
        return true
    }

    private fun nextTurn() {
        if (++turn > nbTurns && nbTurns > 0) {
            finish()
        }
        else {
            main_turn_counter_text.text = "TURN $turn / ${ if (nbTurns != 0)  nbTurns else "∞"}"
            main_first_card_text.text = wordStreamerFirstCard?.nextValue()?.word
            main_second_card_text.text = wordStreamerSecondCard?.nextValue()?.word
            main_timer_text.visibility = View.GONE
            main_hourglass_button.visibility = View.VISIBLE
        }
    }

    private val hourglassButtonTouchListener = View.OnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP)
            startTimer()
        false
    }

    private fun startTimer() {
        // Initialize the Timer View
        timer = 301
        main_hourglass_button.visibility = View.GONE
        main_timer_text.visibility = View.VISIBLE

        // Run the Timer and Timer Task
        fixedRateTimer("hourglass-timer",true,0, 100) {
            if (--timer <= 0) {
                runOnUiThread {main_timer_text.text = "OVERTIME, CHANGING CARD IN ${5+timer/10}..."}
                if (timer <= -50) {
                    this.cancel()
                    runOnUiThread {nextTurn()}
                }
            }
            else runOnUiThread {main_timer_text.text = "${timer/10.0}s LEFT"}
        }
    }
}
