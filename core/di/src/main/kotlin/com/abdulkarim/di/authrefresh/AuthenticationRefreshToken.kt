package com.abdulkarim.di.authrefresh
import com.abdulkarim.securestorage.SecureStorage
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthenticationRefreshToken @Inject constructor(
    private val secureStorage: SecureStorage,
    private var authRefreshServiceHolder: AuthRefreshServiceHolder
) : Authenticator{
    override fun authenticate(route: Route?, response: Response): Request? {
        return if (response.code == 401){
            val authenticatorCall = authRefreshServiceHolder.getAuthRefreshApi()?.refreshToken(secureStorage.getRefreshToken())
            val refreshTokenResponse = authenticatorCall?.execute()
            if (refreshTokenResponse?.body() != null && refreshTokenResponse.isSuccessful && refreshTokenResponse.code() == 200){
                refreshTokenResponse.body()?.let {
                    secureStorage.saveAccessToken(it.accessToken)
                    secureStorage.saveRefreshToken(it.refreshToken)
                    response.request.newBuilder().header("Authorization", "Bearer ${it.accessToken}").build()
                }

            } else null
        } else null
    }

}