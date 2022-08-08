package com.example.grocerystoreapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocerystoreapp.api.LocationRepository
import com.example.grocerystoreapp.api.ProductRepository
import com.example.grocerystoreapp.model.ProductData
import com.example.grocerystoreapp.model.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

const val TAG = "ProductViewModel"


class ProductViewModel (
    private val repository: ProductRepository,
    private val dispatcher: CoroutineDispatcher,
    private val locationRepository: LocationRepository
        ): ViewModel() {

    private val _productLiveData = MutableLiveData<UIState>()
    val productLiveData: LiveData<UIState> get() = _productLiveData

    private val _locationLiveData = MutableLiveData<UIState>()
    val locationLiveData: LiveData<UIState> get() = _locationLiveData


    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(
                TAG,
                "Context $coroutineContext\nMessage: ${throwable.localizedMessage}",
                throwable
            )
        }
    }
    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    fun getKrogerProducts(locationId: String, term: String) {
        viewModelSafeScope.launch {
            repository.getProducts(locationId, term).collect {
                _productLiveData.postValue(it)

            }
        }
    }

    fun getLocation(zipcode: String){
        viewModelSafeScope.launch {
            locationRepository.getLocations(zipcode).collect{
                _locationLiveData.postValue(it)
            }
        }
    }

    fun setLoading(){ _productLiveData.value = UIState.Loading}
    fun setLoadingForDetails(){ _productLiveData.value = UIState.Loading}
    fun setSuccess(item: ProductData){ _productLiveData.value = UIState.Success(item)}
    fun setLoadingForLocation(){_locationLiveData.value = UIState.Loading}

}