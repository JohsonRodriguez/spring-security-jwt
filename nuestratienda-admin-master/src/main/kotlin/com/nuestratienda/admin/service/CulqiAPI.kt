package com.nuestratienda.admin.service

import com.nuestratienda.admin.model.PaymentDetails
import jdk.jfr.ContentType
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class CulqiAPI {
    fun getPayment(paymentDetails: PaymentDetails) : String? {
        val webClient = WebClient.builder()
            .baseUrl("https://api.culqi.com/v2")
            .defaultHeaders { header: HttpHeaders->
                header.setBearerAuth("sk_test_O94QhKF1GFH0gXtX")
                header.contentType = MediaType.APPLICATION_JSON
            }
            .build()

        val response =  webClient.post().uri("/charges")
            .bodyValue(paymentDetails)
            .accept(MediaType.APPLICATION_JSON)
            .exchangeToMono { response ->
                response.bodyToMono(String::class.java)
            }.block()

        return response
    }
}

