package com.teknokrait.mussichusetts.view.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.teknokrait.mussichusetts.R
import com.teknokrait.mussichusetts.data.model.Track
import com.teknokrait.mussichusetts.util.Constants
import com.teknokrait.mussichusetts.util.InfiniteScrollListener
import com.teknokrait.mussichusetts.util.toast
import com.teknokrait.mussichusetts.view.adapter.TrackAdapter
import com.teknokrait.mussichusetts.view.viewmodel.TrackViewModel
import com.teknokrait.mussichusetts.view.viewmodel.TrackViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_find.*
import javax.inject.Inject

class FindFragment : Fragment() {

    @Inject
    lateinit var trackViewModelFactory: TrackViewModelFactory
    private var cryptocurrenciesAdapter = TrackAdapter(ArrayList())
    private lateinit var trackViewModel: TrackViewModel
    private var currentPage = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //AndroidSupportInjection.inject(this@FindFragment)

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
//                context?.toast(resources.getString(R.string.track_error_message) + it)
//            }
//        })
//
//        trackViewModel.tracksLoader.observe(this, Observer<Boolean> {
//            if (it == false) progressBar.visibility = View.GONE
//        })
    }


    private fun initializeRecycler() {
        val gridLayoutManager = GridLayoutManager(context, 1)
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

}
