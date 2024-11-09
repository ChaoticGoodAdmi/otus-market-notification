package ru.ushakov.notification.domain

import java.math.BigDecimal

data class BillingEvent(
    val accountNumber: String,
    val phoneNumber: String,
    val transactionType: TransactionType,
    val amount: BigDecimal
)

data class OrderEvent(
    val eventType: String,
    val orderId: String,
    val userId: String,
    val status: String,
    val totalPrice: BigDecimal? = null,
    val email: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
)

enum class TransactionType {
    DEPOSIT, WITHDRAW, INSUFFICIENT_FUNDS
}