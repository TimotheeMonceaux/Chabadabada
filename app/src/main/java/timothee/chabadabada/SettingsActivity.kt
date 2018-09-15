package timothee.chabadabada

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private var nb_turns: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize the widgets with default values
        nb_turns = getSharedPreferences(getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE)
                .getInt(getString(R.string.shared_preferences_settings_number_of_rounds),10)

        // Map the listeners to the Widgets
        settings_nturns_seekBar.setOnSeekBarChangeListener((object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Write code to perform some action when progress is changed.
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.
                nb_turns = seekBar.progress
                settings_nturns_editText.text = Editable.Factory.getInstance().newEditable("${if (nb_turns == 0) "∞" else "$nb_turns"}")
            }
        }))
        settings_nturns_editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable?) {
                nb_turns = s.toString().toIntOrNull() ?: 0
                if (nb_turns > settings_nturns_seekBar.max) settings_nturns_seekBar.max = nb_turns
                settings_nturns_seekBar.progress = nb_turns
            }

        })
        settings_save_button.setOnTouchListener(saveButtonTouchListener)
    }

    private val saveButtonTouchListener = View.OnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            with (getSharedPreferences(getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE).edit()) {
                putInt(getString(R.string.shared_preferences_settings_number_of_rounds), nb_turns)
                commit()
            }
            startActivity(Intent(this, HomeActivity::class.java))
        }
        false
    }
}
