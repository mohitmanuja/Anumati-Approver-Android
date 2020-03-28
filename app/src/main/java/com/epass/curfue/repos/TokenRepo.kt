package com.epass.curfue.repos

import com.epass.curfue.models.TokenVerifyResponse
import com.epass.curfue.models.VerifyTokenRequest
import com.epass.curfue.network.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException


class TokenRepo {

    fun fetchTokenResult(
        token: String,
        service: RetrofitService,
        listener: ((Response<TokenVerifyResponse>) -> Unit)?,
        errorListener: ((String?) -> Unit)?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val verifyTokenRequest = VerifyTokenRequest(token)
                val response = service.postVerifyToken(verifyTokenRequest)
                listener?.invoke(response)
            } catch (e: HttpException) {
                errorListener?.invoke(null)
            } catch (e: Throwable) {
                errorListener?.invoke(null)
            } catch (e: SocketTimeoutException) {
                errorListener?.invoke(null)
            } catch (e: IOException) {
                errorListener?.invoke(null)
            }catch (e:Exception){
                errorListener?.invoke(null)
            }
        }
    }
}