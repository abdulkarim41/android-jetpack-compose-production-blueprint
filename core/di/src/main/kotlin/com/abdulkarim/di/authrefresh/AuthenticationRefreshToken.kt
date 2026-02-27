package com.abdulkarim.di.authrefresh
import com.abdulkarim.securestorage.SecureStorage
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthenticationRefreshToken @Inject constructor(
    private val secureStorage: SecureStorage,
    private val authRefreshServiceHolder: AuthRefreshServiceHolder
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        if (responseCount(response) >= 2) return null

        val refreshToken = secureStorage.getRefreshToken()

        val requestBody = RefreshTokenApiParams(
            refreshToken = refreshToken
        )

        val refreshCall =
            authRefreshServiceHolder.getAuthRefreshApi()
                ?.refreshToken(requestBody)

        val refreshResponse = refreshCall?.execute()

        return if (refreshResponse?.isSuccessful == true) {

            refreshResponse.body()?.let {
                secureStorage.saveAccessToken(it.accessToken)
                secureStorage.saveRefreshToken(it.refreshToken)

                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }

        } else {
            null
        }
    }

    private fun responseCount(response: Response): Int {
        var result = 1
        var priorResponse = response.priorResponse
        while (priorResponse != null) {
            result++
            priorResponse = priorResponse.priorResponse
        }
        return result
    }
}