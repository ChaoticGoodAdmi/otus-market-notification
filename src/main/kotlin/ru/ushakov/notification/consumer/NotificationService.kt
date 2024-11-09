package ru.ushakov.notification.consumer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.ushakov.notification.domain.*
import ru.ushakov.notification.repository.BillingEventRepository
import ru.ushakov.notification.repository.OrderEventRepository

@Service
class NotificationService(
    private val orderEventRepository: OrderEventRepository,
    private val billingEventRepository: BillingEventRepository
) {

    @KafkaListener(topics = ["order-events"], groupId = "\${spring.kafka.consumer.group-id}")
    fun handleOrderEvent(message: String) {
        val orderEvent = parseOrderEvent(message)

        val orderEntity = OrderEventEntity(
            email = orderEvent.email,
            orderId = orderEvent.orderId,
            status = orderEvent.status,
            eventType = orderEvent.eventType
        )

        orderEventRepository.save(orderEntity)
    }

    @KafkaListener(topics = ["billing-events"], groupId = "\${spring.kafka.consumer.group-id}")
    fun handleBillingEvent(message: String) {
        val billingEvent = parseBillingEvent(message)

        val billingEntity = BillingEventEntity(
            accountNumber = billingEvent.accountNumber,
            amount = billingEvent.amount,
            transactionType = billingEvent.transactionType.name
        )

        billingEventRepository.save(billingEntity)
    }

    private fun parseOrderEvent(message: String): OrderEvent {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(message, OrderEvent::class.java)
    }

    private fun parseBillingEvent(message: String): BillingEvent {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(message, BillingEvent::class.java)
    }
}