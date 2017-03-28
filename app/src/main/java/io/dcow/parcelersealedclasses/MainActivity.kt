package io.dcow.parcelersealedclasses

import android.app.Activity
import android.os.Bundle
import org.parceler.Parcels

class MainActivity : Activity()
{
    companion object {
        const val key = "main_activity.key"
    }

    var data: Data? = null
        set(value) {
            field = value
            updateScreen(value)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = Parcels.unwrap<Data>(savedInstanceState?.getParcelable(key))
                ?: Data(Screen.Landing())
    }

    fun updateScreen(data: Data?) {
        val screen = data?.screen ?: return
        val layout = when (screen) {
            is Screen.Landing -> R.layout.landing
            is Screen.Dashboard -> R.layout.dashboard
            is Screen.Settings -> R.layout.settings
        }
        setContentView(layout)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(key, Parcels.wrap(data))
    }
}
