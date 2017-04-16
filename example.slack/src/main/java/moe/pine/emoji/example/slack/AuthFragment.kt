package moe.pine.emoji.example.slack

import android.app.AlertDialog
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_auth.*

/**
 * AuthFragment
 * Created by pine on Apr 13, 2017.
 */
class AuthFragment : Fragment() {
    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_auth, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button_login.setOnClickListener { this.auth() }

        val pref = PreferenceManager.getDefaultSharedPreferences(this.context)
        edit_text_team.setText(pref.getString("auth_team", ""))
        edit_text_email.setText(pref.getString("auth_email", ""))
        edit_text_password.setText(pref.getString("auth_password", ""))
    }

    private fun auth() {
        if (!this.validate()) {
            AlertDialog.Builder(this.context)
                    .setTitle("Error")
                    .setMessage("Invalid form values")
                    .setCancelable(false)
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            return
        }

        val task = AuthTask(this.context)
        val arguments = AuthTask.Arguments(
                edit_text_team.text.toString(),
                edit_text_email.text.toString(),
                edit_text_password.text.toString()
        )
        task.execute(arguments)

        val pref = PreferenceManager.getDefaultSharedPreferences(this.context)
        val editor = pref.edit()
        editor.putString("auth_team", edit_text_team.text.toString())
        editor.putString("auth_email", edit_text_email.text.toString())
        editor.putString("auth_password", edit_text_password.text.toString())
        editor.apply()
    }

    private fun validate(): Boolean
            = edit_text_team.text.isNotEmpty()
            && edit_text_email.text.isNotEmpty()
            && edit_text_password.text.isNotEmpty()
}