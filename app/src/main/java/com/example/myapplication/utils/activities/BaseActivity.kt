package com.example.myapplication.utils.activities

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.SystemClock
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Chronometer
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.myapplication.utils.Interactors
import com.example.myapplication.R
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import java.util.Locale
import kotlin.reflect.KClass

open class BaseActivity : AppCompatActivity(), INavigationRequestListener {

    protected var rxBusSubscriber = RxBusSubscriber()
    private var subs = CompositeDisposable()
    private lateinit var chronometer: Chronometer
    private var insetsAlreadyLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Interactors.context = application
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window.statusBarColor = resources.getColor(R.color.white, resources.newTheme())
    }

    override fun onResume() {
        super.onResume()
        chronometer = Chronometer(this)
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()

    }

    override fun onPause() {
        super.onPause()
        if (this::chronometer.isInitialized) chronometer.stop()
    }

    override fun onDestroy() {
        subs.clear()
        rxBusSubscriber.unsubscribe()
        super.onDestroy()
    }


    fun showFragment(fragment: Fragment, idPlacement: Int = 555) {
        supportFragmentManager.beginTransaction().replace(idPlacement, fragment)
            .commitAllowingStateLoss()
    }

    open fun closeFragment(idPlacement: Int = 555): Boolean {
        val f = supportFragmentManager.findFragmentById(idPlacement)
        if (f != null) {
            supportFragmentManager.beginTransaction().remove(f).commitAllowingStateLoss()
        }
        return f != null
    }

    override fun onStartActivity(intent: Intent) {
        startActivity(intent)
    }

    override fun onGoBack() {
        finish()
    }

    override fun showDialogFragment(dialogFragment: BaseDialogFragment) {
        if (isFinishing || supportFragmentManager.findFragmentByTag(dialogFragment.javaClass.simpleName) != null) {
            return
        }

        try {
            dialogFragment.show(supportFragmentManager, dialogFragment.javaClass.simpleName)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open fun adjustFontScale(configuration: Configuration) {
        configuration.fontScale = 1.0.toFloat()
        val metrics: DisplayMetrics = resources.displayMetrics
        val wm: WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay?.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)
    }

    open fun hideKeyboard() {
        currentFocus?.let {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    open fun showToast(isError: Boolean, message: String) {}

    fun createTransitionLauncher() =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        }

    fun showKeyBoard(view: View) {
        currentFocus?.let {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    // Get a MemoryInfo object for the device's current memory status.
    fun getAvailableMemory(): ActivityManager.MemoryInfo {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return ActivityManager.MemoryInfo().also { memoryInfo ->
            activityManager.getMemoryInfo(memoryInfo)
        }
    }

    companion object {
        const val NOTIFICATIONS_UNKNOWN = 0
        const val NOTIFICATIONS_ON = 1
        const val NOTIFICATIONS_OFF = 2

        const val CLOSE_ACTIVITY_ON_RESULT = 999
        const val PERFORM_MERGE_REQUEST = 19999
        const val FRAGMENT_FRAME_ID = 555 // fragment id for anko activities

        const val ACT_FBPAGES = 3000
        const val ACT_OPEN_ALERT = 3001
        const val ACT_POST_VIDEO = 3002
        const val PERFORM_POSTING = 3003
        const val ACT_FB_PROFILE_LINK = 3004

        const val ACT_FB_PROFILE_LINK_FOR_POST = 64206
        const val ACT_TWITTER_PROFILE_LINK_FOR_POST = 140

        const val ACT_INSTAGRAM_AUTH = 4000
        const val ACT_TIKTOK_AUTH = 4001

        var notificationsEnabled = NOTIFICATIONS_UNKNOWN
    }
}

abstract class UIContainer {
    abstract val itemView: View
}

fun Context.setAppLocale(language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    return createConfigurationContext(config)
}

class RxBusSubscriber {

    private val subscriptions = CompositeDisposable()

    fun <T : Any> subscribe(clazz: KClass<T>, callback: (T) -> Unit) {
        subscriptions += RxBus.events(clazz.java).onBackpressureBuffer(10)
            .observeOn(AndroidSchedulers.mainThread()).subscribe(callback, { it.printStackTrace() })
    }

    fun unsubscribe() {
        subscriptions.clear()
    }
}

object RxBus {

    private val bus = PublishSubject.create<Any>().toSerialized()
    fun post(event: Any) {
        bus.onNext(event)
    }

    internal fun <T> events(type: Class<T>): Flowable<T> {
        return bus.toFlowable(BackpressureStrategy.LATEST).ofType(type)
    }
}