package moe.pine.emoji.example.slack

import android.app.AlertDialog
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_add_emoji.*

/**
 * AddEmojiFragment
 * Created by pine on Apr 13, 2017.
 */
class AddEmojiFragment : Fragment() {
    companion object {
        fun newInstance() = AddEmojiFragment()

        val EMOJI_URL = "https://emoji.pine.moe/emoji?align=center&back_color=FFFFFF00&color=EC71A1FF&font=notosans-mono-bold&public_fg=true&size_fixed=false&stretch=true&text=%E7%B5%B5%E6%96%87%0A%E5%AD%97%E3%80%82"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_add_emoji, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button_add.setOnClickListener { this.add() }

        val pref = PreferenceManager.getDefaultSharedPreferences(this.context)
        edit_text_team.setText(pref.getString("add_emoji_team", ""))
        edit_text_email.setText(pref.getString("add_emoji_email", ""))
        edit_text_password.setText(pref.getString("add_emoji_password", ""))
        edit_text_emoji_name.setText(pref.getString("add_emoji_emoji_name", ""))
    }

    private fun add() {
        if (!this.validate()) {
            AlertDialog.Builder(this.context)
                    .setTitle("Error")
                    .setMessage("Invalid form values")
                    .setCancelable(false)
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            return
        }

        val task = AddEmojiTask(this.context!!)
        val arguments = AddEmojiTask.Arguments(
                edit_text_team.text.toString(),
                edit_text_email.text.toString(),
                edit_text_password.text.toString(),
                edit_text_emoji_name.text.toString(),
                EMOJI_URL
        )
        task.execute(arguments)

        val pref = PreferenceManager.getDefaultSharedPreferences(this.context)
        val editor = pref.edit()
        editor.putString("add_emoji_team", edit_text_team.text.toString())
        editor.putString("add_emoji_email", edit_text_email.text.toString())
        editor.putString("add_emoji_password", edit_text_password.text.toString())
        editor.putString("add_emoji_emoji_name", edit_text_emoji_name.text.toString())
        editor.apply()
    }

    private fun validate(): Boolean
            = edit_text_team.text.isNotEmpty()
            && edit_text_email.text.isNotEmpty()
            && edit_text_password.text.isNotEmpty()
            && edit_text_emoji_name.text.isNotEmpty()
}