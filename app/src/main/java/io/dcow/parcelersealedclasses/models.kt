package io.dcow.parcelersealedclasses

import org.parceler.*

//
// Parceler is still a tad unwieldy in Kotlin because you need lots of @ParcelConstructor and
// @ParcelProperty annotations unless you use default values which is not always desireable.
//

/**
 * Some application data to save.
 */
@Parcel
data class Data @ParcelConstructor constructor(@ParcelProperty("screen") val screen: Screen)

/**
 * Demo of a sealed class model type.
 */
@Parcel//(converter = ScreenConverter::class) // uncomment to make it work
sealed class Screen
{
    @Parcel
    class Landing : Screen()

    @Parcel
    class Dashboard : Screen()

    @Parcel
    class Settings : Screen()
}

class ScreenConverter : ParcelConverter<Screen>
{
    override fun fromParcel(parcel: android.os.Parcel?): Screen? {
        return Parcels.unwrap(parcel?.readParcelable(Screen::class.java.classLoader))
    }

    override fun toParcel(input: Screen?, parcel: android.os.Parcel?) {
        val out = parcel ?: return
        val obj = input ?: return
        when (obj) {
            is Screen.Landing -> out.writeParcelable(Parcels.wrap(obj), 0)
            is Screen.Dashboard -> out.writeParcelable(Parcels.wrap(obj), 0)
            is Screen.Settings -> out.writeParcelable(Parcels.wrap(obj), 0)
            // exhausted because Screen is a sealed class
        }
    }
}