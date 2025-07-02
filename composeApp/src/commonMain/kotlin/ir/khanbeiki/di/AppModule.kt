package ir.khanbeiki.di

import ir.khanbeiki.data.ApiService
import ir.khanbeiki.data.ApiServiceImpl
import ir.khanbeiki.data.createHttpClient
import ir.khanbeiki.data.repository.ProductRepository
import ir.khanbeiki.data.repository.ProductRepositoryImpl
import ir.khanbeiki.screens.detail.ProductDetailViewModel
import ir.khanbeiki.screens.list.ProductsViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { createHttpClient() }
    single<ApiService> { ApiServiceImpl(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    factory { ProductsViewModel(get()) }
    factory { ProductDetailViewModel(get()) }
}