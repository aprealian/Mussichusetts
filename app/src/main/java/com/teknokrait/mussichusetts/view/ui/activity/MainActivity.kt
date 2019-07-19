package com.teknokrait.mussichusetts.view.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.teknokrait.mussichusetts.R
import com.teknokrait.mussichusetts.data.model.Track
import com.teknokrait.mussichusetts.util.Constants
import com.teknokrait.mussichusetts.util.InfiniteScrollListener
import com.teknokrait.mussichusetts.util.toast
import com.teknokrait.mussichusetts.view.adapter.CustomPagerAdapter
import com.teknokrait.mussichusetts.view.adapter.TrackAdapter
import com.teknokrait.mussichusetts.view.viewmodel.TrackViewModel
import com.teknokrait.mussichusetts.view.viewmodel.TrackViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import devlight.io.library.ntb.NavigationTabBar
import kotlinx.android.synthetic.main.fragment_find.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LifecycleOwner {

    @Inject
    lateinit var trackViewModelFactory: TrackViewModelFactory
    private var cryptocurrenciesAdapter = TrackAdapter(ArrayList())
    private lateinit var trackViewModel: TrackViewModel
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        initTab()

//        initializeRecycler()
//
//        trackViewModel = ViewModelProviders.of(this, trackViewModelFactory).get(
//                TrackViewModel::class.java)
//
//        progressBar.visibility = View.VISIBLE
//        loadData()
//
//        trackViewModel.tracksResult.observe(this,
//                Observer<List<Track>> {
//                    if (it != null) {
//                        val position = cryptocurrenciesAdapter.itemCount
//                        cryptocurrenciesAdapter.addTracks(it)
//                        recycler.adapter = cryptocurrenciesAdapter
//                        recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
//                    }
//                })
//
//        trackViewModel.tracksError.observe(this, Observer<String> {
//            if (it != null) {
//                toast(resources.getString(R.string.track_error_message) + it)
//            }
//        })
//
//        trackViewModel.tracksLoader.observe(this, Observer<Boolean> {
//            if (it == false) progressBar.visibility = View.GONE
//        })
    }

    private fun initializeRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addOnScrollListener(InfiniteScrollListener({ loadData() }, gridLayoutManager))
        }
    }

    private fun loadData() {
        trackViewModel.loadTracks(Constants.LIMIT, currentPage * Constants.OFFSET)
        currentPage++
    }


    override fun onDestroy() {
        //trackViewModel.disposeElements()
        super.onDestroy()
    }

    private fun initTab() {
        val adapter = CustomPagerAdapter(supportFragmentManager)
        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        viewPager.offscreenPageLimit = 4
        viewPager.adapter = adapter

        val colors = resources.getStringArray(R.array.colors)
        val titles = resources.getStringArray(R.array.tab_titles)
        val navigationTabBar = findViewById<View>(R.id.navTabBar) as NavigationTabBar
        val models =  arrayListOf<NavigationTabBar.Model>()
        models.add(
                NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_action_home_unactive),
                        Color.parseColor(colors[0])
                ).selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_home_active))
                        .title(titles[0])
                        .badgeTitle(titles[0])
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_action_bookmark_unactive),
                        Color.parseColor(colors[1])
                ).selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_bookmark_active))
                        .title(titles[1])
                        .badgeTitle(titles[1])
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_action_menu_unactive),
                        Color.parseColor(colors[2])
                ).selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_menu_active))
                        .title(titles[2])
                        .badgeTitle(titles[2])
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_action_info_unactive),
                        Color.parseColor(colors[3])
                ).selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_info_active))
                        .title(titles[3])
                        .badgeTitle(titles[3])
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_action_info_unactive),
                        Color.parseColor(colors[3])
                ).selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_info_active))
                        .title(titles[3])
                        .badgeTitle(titles[3])
                        .build()
        )
        navigationTabBar.models = models
        navigationTabBar.setViewPager(viewPager, 0)

        navigationTabBar.titleMode = NavigationTabBar.TitleMode.ACTIVE
        navigationTabBar.badgeGravity = NavigationTabBar.BadgeGravity.BOTTOM
        navigationTabBar.badgePosition = NavigationTabBar.BadgePosition.CENTER
    }
}
