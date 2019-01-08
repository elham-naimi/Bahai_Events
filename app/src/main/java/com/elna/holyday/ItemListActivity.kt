package com.elna.kotlinfragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.elna.model.HolyDay
import com.elna.ui.HolyDayListAdapter
import com.elna.viewmodel.HolyDayViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import java.net.URL

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private lateinit var holyDayViewModel: HolyDayViewModel
    private lateinit var adapter: HolyDayListAdapter
    private var twoPane: Boolean = false
    private var TAG = ItemListActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        holyDayViewModel = ViewModelProviders.of(this).get(HolyDayViewModel::class.java)
        setSupportActionBar(toolbar)
        toolbar.title = title

        val onClickListener: View.OnClickListener

        onClickListener = View.OnClickListener { v ->
            val item = v.tag as HolyDay
            if (twoPane) {
                val fragment = ItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ItemDetailFragment.ARG_ITEM_ID, item.holyDayName)
                    }
                }
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                    putExtra(ItemDetailFragment.ARG_ITEM_ID, item.holyDayName)
                }
                v.context.startActivity(intent)
            }
        }

        adapter = HolyDayListAdapter(this, onClickListener)



        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        item_list.adapter = adapter;

    }

    fun getResourceAsText(path: String): URL {
        return object {}.javaClass.getResource(path)
    }

    fun String.asResource(work: (String) -> Unit) {
        val content = this.javaClass::class.java.getResource(this).readText()
        work(content)
    }

    override fun onStart() {
        super.onStart()
        Log.i("TAG","path ");
        holyDayViewModel.loadHolyDays()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe({ result -> onSuccess(result) },
                { failure -> onFailed(failure) })

       Log.i("TAG","url "+ClassLoader.getSystemResource("."))

        Log.i("TAG","path : "+ClassLoader.getSystemResource("/holyday.json"))
        Log.i("TAG","path : "+ClassLoader.getSystemResource("holyday.json"))
        Log.i("TAG","path : "+ClassLoader.getSystemResource("/res/raw/holyday.json"))
        Log.i("TAG","path : "+ClassLoader.getSystemResource("/resources/raw/holyday.json"))
        Log.i("TAG","path : "+ClassLoader.getSystemResource("res/raw/holyday.json"))
        Log.i("TAG","path : "+ClassLoader.getSystemResource("resources/raw/holyday.json"))
        Log.i("TAG","path : "+ClassLoader.getSystemResource("app/res/raw/holyday.json"))
        Log.i("TAG","path : "+ClassLoader.getSystemResource("/app/res/raw/holyday.json"))
        Log.i("TAG","path : "+ClassLoader.getSystemResource("app/resources/raw/holyday.json"))
        Log.i("TAG","path : "+ClassLoader.getSystemResource("/app/resources/raw/holyday.json"))

        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("/holyday.json").hasMoreElements())
        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("holyday.json").hasMoreElements())
        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("/res/raw/holyday.json").hasMoreElements())
        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("/resources/raw/holyday.json").hasMoreElements())
        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("res/raw/holyday.json").hasMoreElements())
        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("resources/raw/holyday.json").hasMoreElements())
        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("app/res/raw/holyday.json").hasMoreElements())
        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("/app/res/raw/holyday.json").hasMoreElements())
        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("app/resources/raw/holyday.json").hasMoreElements())
        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("/app/resources/raw/holyday.json").hasMoreElements())

        Log.i("TAG","path : "+ClassLoader.getSystemClassLoader().getResources("/holyday.json").hasMoreElements())

        Log.i("TAG","path :::");
        Log.i("TAG","path : "+this::class.java.classLoader.getResource("holyday"))

        Log.i("TAG", "path"+this::class.java.classLoader.getResource("/holyday.json"))

        Log.i("TAG","path : "+this::class.java.classLoader.getResource("holyday.json"))
        Log.i("TAG","path : "+this::class.java.classLoader.getResource("/resources/raw/holyday.json"))
        Log.i("TAG","path : "+this::class.java.classLoader.getResource("resources/raw/holyday.json"))
        Log.i("TAG","path : "+this::class.java.classLoader.getResource("resources/holyday.json"))
        Log.i("TAG","path : "+this::class.java.classLoader.getResource("/resources/holyday.json"))

      //  System.out.print("path : "+this::class.java.getResource("/html/file.html"))


       // val fileContent = javaClass.getResource("/html/file.html").readText()

    }

    private fun onFailed(failure: Throwable) {
        Log.i(TAG, "OnFailed"+failure.printStackTrace())
        Log.i(TAG, "OnFailed"+failure.localizedMessage)
    }

    private fun onSuccess(result: ArrayList<HolyDay>) {
        Log.i(TAG, "OnSuccess"+result.size)
        if (result != null) {
            Log.i(TAG, "OnSuccess" + result)
                    adapter.setWords(result)
        }
    }

}

