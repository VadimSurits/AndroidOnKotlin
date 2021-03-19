package com.example.androidonkotlin.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidonkotlin.R
import com.example.androidonkotlin.databinding.FragmentDetailsBinding
import com.example.androidonkotlin.model.Weather
import com.example.androidonkotlin.utils.showSnackBar
import com.example.androidonkotlin.viewmodel.AppState
import com.example.androidonkotlin.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        viewModel.detailsLiveData.observe(viewLifecycleOwner, {
            renderData(it)
        })
        viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                setWeather(appState.weatherData[0])
            }
            is AppState.Loading -> {
                binding.mainView.visibility = View.GONE
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                binding.mainView.showSnackBar(
                        getString(R.string.error),
                        getString(R.string.reload),
                        {
                            viewModel.getWeatherFromRemoteSource(
                                    weatherBundle.city.lat,
                                    weatherBundle.city.lon)
                        })
            }
        }
    }

    private fun setWeather(weather: Weather) {
        val city = weatherBundle.city
        binding.cityName.text = city.cityName
        binding.cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
        )
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
        binding.weatherCondition.text = weather.condition
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
