package com.example.walmartassetment.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartassetment.R
import com.example.walmartassetment.api.CountryAPIClient
import com.example.walmartassetment.api.ICountryAPI
import com.example.walmartassetment.api.model.Country
import com.example.walmartassetment.databinding.ActivityMainBinding
import com.example.walmartassetment.repository.RepositoryImpl
import com.example.walmartassetment.ui.adapter.CountryAdapter
import com.example.walmartassetment.viewmodel.CountryViewModel
import com.example.walmartassetment.viewmodel.CountryViewModelFactory
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {
    val viewModelFactory by lazy {
        val api = CountryAPIClient.provideRetrofit().create(ICountryAPI::class.java)
        val repository = RepositoryImpl(api)
        CountryViewModelFactory(repository)
    }

    private lateinit var viewModel: CountryViewModel

    val countryAdapter by lazy { CountryAdapter() }
    lateinit var recyclerv: RecyclerView
    lateinit var topAppBar: MaterialToolbar

    lateinit var countryList: List<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var actMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory)[CountryViewModel::class.java]
        actMainBinding.viewmodel = viewModel
        actMainBinding.lifecycleOwner = this

        recyclerv = findViewById(R.id.rvCountry)
        topAppBar = findViewById(R.id.topAppBar)
        initGridAdapter()

        toolBarItemListener()
        viewModel.getCountryList()
        viewModel.livedataCountries.observe(this, Observer {
            countryList = it
            handleResults(it)
        })
        viewModel.error.observe(this, Observer {
            handleErrors(it)
        })
    }

    private fun initGridAdapter() {
        recyclerv.layoutManager = LinearLayoutManager(this)
        recyclerv.adapter = countryAdapter
    }

    private fun toolBarItemListener() {
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_search -> {

                    menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
                        override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                            return true
                        }

                        override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                            handleResults(countryList)
                            return true
                        }
                    })
                    val searchView: SearchView =
                        MenuItemCompat.getActionView(menuItem) as SearchView


                    searchView.setOnQueryTextListener(object :
                        SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String): Boolean {
                            if (countryList.size != 0){
                                var countries = countryList.filter { query == it.name  || query == it.code}
                                handleResults(countries)
                            }else {
                                viewModel.getCountryList()
                            }
                            return false
                        }

                        override fun onQueryTextChange(newText: String) = true
                    })

                    searchView.setOnCloseListener(object : SearchView.OnCloseListener {
                        override fun onClose(): Boolean {
                            handleResults(countryList)
                            return true
                        }
                    })

                    true
                }
                else -> false
            }
        }
    }

    private fun handleResults(_countryList: List<Country>) {
        countryAdapter.submitList(_countryList)
    }

    private fun handleErrors(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

}