package com.miladsh7.nikestore

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import androidx.room.Room
import com.facebook.drawee.backends.pipeline.Fresco
import com.miladsh7.nikestore.feature.ProductDetailViewModel
import com.miladsh7.nikestore.feature.auth.AuthViewModel
import com.miladsh7.nikestore.feature.cart.CartViewModel
import com.miladsh7.nikestore.feature.checkout.CheckoutViewModel
import com.miladsh7.nikestore.feature.common.ProductListAdapter
import com.miladsh7.nikestore.feature.favorites.FavoriteProductsViewModel
import com.miladsh7.nikestore.feature.home.HomeViewModel
import com.miladsh7.nikestore.feature.list.ProductListViewModel
import com.miladsh7.nikestore.feature.main.MainViewModel
import com.miladsh7.nikestore.feature.order.OrderHistoryViewModel
import com.miladsh7.nikestore.feature.product.comment.CommentListViewModel
import com.miladsh7.nikestore.feature.profile.ProfileViewModel
import com.miladsh7.nikestore.feature.shipping.ShippingViewModel
import com.miladsh7.nikestore.services.FrescoImageLoadingService
import com.miladsh7.nikestore.services.ImageLoadingService
import com.miladsh7.nikestore.services.http.createApiServiceInstance
import com.miladsh7.nikestore.data.db.AppDatabase
import com.miladsh7.nikestore.data.repo.*
import com.miladsh7.nikestore.data.repo.order.OrderRemoteDataSource
import com.miladsh7.nikestore.data.repo.order.OrderRepository
import com.miladsh7.nikestore.data.repo.order.OrderRepositoryImpl
import com.miladsh7.nikestore.data.repo.source.*

import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        Fresco.initialize(this)

        val myModules = module {
            single { createApiServiceInstance() }
            single<ImageLoadingService> { FrescoImageLoadingService() }
            single { Room.databaseBuilder(this@App, AppDatabase::class.java, "db_app").build() }
            factory<ProductRepository> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    get<AppDatabase>().productDao()
                )
            }

            single<SharedPreferences> {
                this@App.getSharedPreferences(
                    "app_settings",
                    MODE_PRIVATE
                )
            }
            single { UserLocalDataSource(get()) }
            single<UserRepository> {
                UserRepositoryImpl(
                    UserLocalDataSource(get()),
                    UserRemoteDataSource(get())
                )
            }
            single<OrderRepository> { OrderRepositoryImpl(OrderRemoteDataSource(get())) }

            factory { (viewType: Int) -> ProductListAdapter(viewType, get()) }
            factory<BannerRepository> { BannerRepositoryImpl(BannerRemoteDataSource(get())) }
            factory<CommentRepository> { CommentRepositoryImpl(CommentRemoteDataSource(get())) }
            factory<CartRepository> { CartRepositoryImpl(CartRemoteDataSource(get())) }
            viewModel { HomeViewModel(get(), get()) }
            viewModel { (bundle: Bundle) -> ProductDetailViewModel(bundle, get(), get()) }
            viewModel { (productId: Int) -> CommentListViewModel(productId, get()) }
            viewModel { (sort: Int) -> ProductListViewModel(sort, get()) }
            viewModel { AuthViewModel(get()) }
            viewModel { CartViewModel(get()) }
            viewModel { MainViewModel(get()) }
            viewModel { ShippingViewModel(get()) }
            viewModel { (orderId: Int) -> CheckoutViewModel(orderId, get()) }
            viewModel { ProfileViewModel(get()) }
            viewModel { FavoriteProductsViewModel(get()) }
            viewModel { OrderHistoryViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }

        val userRepository: UserRepository = get()
        userRepository.loadToken()
    }
}