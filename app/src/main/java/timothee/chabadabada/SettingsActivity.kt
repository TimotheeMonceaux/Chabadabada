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
import timothee.chabadabada.model.raw.Language

class SettingsActivity : AppCompatActivity() {

    private var nb_turns: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize the widgets with default values
        nb_turns = getSharedPreferences(getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE)
                .getInt(getString(R.string.shared_preferences_settings_number_of_rounds),10)
        settings_nturns_editText.text = Editable.Factory.getInstance().newEditable("${if (nb_turns == 0) "∞" else "$nb_turns"}")
        if (nb_turns > settings_nturns_seekBar.max) settings_nturns_seekBar.max = nb_turns
        settings_nturns_seekBar.progress = nb_turns

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

        if (getSharedPreferences(getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE)
                        .getString(getString(R.string.shared_preferences_settings_first_card_language), Language.French.toString()) == Language.French.toString())
            settings_radioGroup_firstCard.check(settings_radioButton_frenchFirstCard.id)
        else if (getSharedPreferences(getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE)
                        .getString(getString(R.string.shared_preferences_settings_first_card_language), Language.French.toString()) == Language.English.toString())
            settings_radioGroup_firstCard.check(settings_radioButton_englishFirstCard.id)

        if (getSharedPreferences(getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE)
                        .getString(getString(R.string.shared_preferences_settings_second_card_language), Language.English.toString()) == Language.French.toString())
            settings_radioGroup_secondCard.check(settings_radioButton_frenchSecondCard.id)
        else if (getSharedPreferences(getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE)
                        .getString(getString(R.string.shared_preferences_settings_first_card_language), Language.English.toString()) == Language.English.toString())
            settings_radioGroup_secondCard.check(settings_radioButton_englishSecondCard.id)


        settings_save_button.setOnTouchListener(saveButtonTouchListener)
    }

    private val saveButtonTouchListener = View.OnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            with (getSharedPreferences(getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE).edit()) {
                putInt(getString(R.string.shared_preferences_settings_number_of_rounds), nb_turns)

                if (settings_radioButton_frenchFirstCard.isChecked)
                    putString(getString(R.string.shared_preferences_settings_first_card_language), Language.French.toString())
                else if (settings_radioButton_englishFirstCard.isChecked)
                    putString(getString(R.string.shared_preferences_settings_first_card_language), Language.English.toString())

                if (settings_radioButton_frenchSecondCard.isChecked)
                    putString(getString(R.string.shared_preferences_settings_second_card_language), Language.French.toString())
                else if (settings_radioButton_englishSecondCard.isChecked)
                    putString(getString(R.string.shared_preferences_settings_second_card_language), Language.English.toString())


                commit()
            }
            finish()
        }
        false
    }
}
