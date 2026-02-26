package com.abdulkarim.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkarim.domain.apiusecase.product.FetchProductsApiUseCase
import com.abdulkarim.common.base.Result
import com.abdulkarim.entity.product.ProductApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class ProductViewModel @Inject constructor(
//    private val fetchProductsApiUseCase: FetchProductsApiUseCase
//) : ViewModel() {
//
//    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
//    val uiState = _uiState.asStateFlow()
//
//
//    val action: (UiAction) -> Unit = {
//        when(it) {
//            is UiAction.FetchProductsApiAction -> fetchProducts()
//        }
//    }
//
//    init { fetchProducts() }
//
//    fun fetchProducts() {
//        viewModelScope.launch {
//            fetchProductsApiUseCase.execute().collect { result ->
//                when(result){
//                    is Result.Loading -> _uiState.value = UiState.Loading
//                    is Result.Success -> _uiState.value = UiState.ApiSuccess(result.data)
//                    is Result.Error -> _uiState.value = UiState.ApiError(result.message)
//                }
//            }
//        }
//    }
//}
//
//sealed interface UiState {
//    data object Loading : UiState
//    data class ApiSuccess(val data: List<ProductApiEntity>) : UiState
//    data class ApiError(val message: String) : UiState
//}
//
//sealed interface UiAction {
//    data object FetchProductsApiAction : UiAction
//}

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val fetchProductsApiUseCase: FetchProductsApiUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var allProducts: List<ProductApiEntity> = emptyList()

    init { fetchProducts() }

    val action: (UiAction) -> Unit = {
        when(it) {
            UiAction.FetchProductsApiAction -> fetchProducts()
            is UiAction.SearchChanged -> updateSearch(it.query)
            is UiAction.CategoryChanged -> updateCategory(it.category)
        }
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            fetchProductsApiUseCase.execute().collect { result ->
                when(result){
                    is Result.Loading -> {
                        _uiState.value = UiState.Loading
                    }
                    is Result.Success -> {
                        allProducts = result.data

                        _uiState.value = UiState.ApiSuccess(
                            products = result.data,
                            filteredProducts = result.data,
                            categories = buildCategories(result.data)
                        )
                    }
                    is Result.Error -> {
                        _uiState.value = UiState.ApiError(result.message)
                    }
                }
            }
        }
    }


    private fun updateSearch(query: String) {
        val current = _uiState.value
        if (current is UiState.ApiSuccess) {
            filterProducts(current.copy(searchQuery = query))
        }
    }

    private fun updateCategory(category: String) {
        val current = _uiState.value
        if (current is UiState.ApiSuccess) {
            filterProducts(current.copy(selectedCategory = category))
        }
    }

    private fun filterProducts(state: UiState.ApiSuccess) {

        val filtered = allProducts.filter { product ->

            val matchesCategory =
                state.selectedCategory == "All" ||
                        product.category.equals(state.selectedCategory, true)

            val matchesSearch =
                product.title.contains(state.searchQuery, true)

            matchesCategory && matchesSearch
        }

        _uiState.value = state.copy(filteredProducts = filtered)
    }

    private fun buildCategories(products: List<ProductApiEntity>): List<String> {
        val dynamicCategories = products.map { it.category }.distinct()
        return listOf("All") + dynamicCategories
    }

    sealed interface UiState {

        data object Loading : UiState

        data class ApiSuccess(
            val products: List<ProductApiEntity>,
            val filteredProducts: List<ProductApiEntity>,
            val categories: List<String>,
            val searchQuery: String = "",
            val selectedCategory: String = "All"
        ) : UiState

        data class ApiError(val message: String) : UiState
    }

    sealed interface UiAction {
        data object FetchProductsApiAction : UiAction
        data class SearchChanged(val query: String) : UiAction
        data class CategoryChanged(val category: String) : UiAction
    }
}

