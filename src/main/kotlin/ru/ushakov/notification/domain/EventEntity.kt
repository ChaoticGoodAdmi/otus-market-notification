package ru.ushakov.notification.domain


import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "order_events")
data class OrderEventEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val email: String,
    val orderId: String,
    val status: String,
    val eventType: String
) {
    constructor() : this(0, "", "", "", "") {

    }
}

@Entity
@Table(name = "billing_events")
data class BillingEventEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val accountNumber: String,
    val amount: BigDecimal,
    val transactionType: String
) {
    constructor() : this(0, "", BigDecimal.ZERO, "")
}