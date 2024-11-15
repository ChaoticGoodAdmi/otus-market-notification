package ru.ushakov.notification

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.ushakov.notification.domain.BillingEventEntity
import ru.ushakov.notification.domain.OrderEventEntity
import ru.ushakov.notification.repository.BillingEventRepository
import ru.ushakov.notification.repository.OrderEventRepository

@RestController
@RequestMapping("/notifications")
class NotificationController(
    private val orderEventRepository: OrderEventRepository,
    private val billingEventRepository: BillingEventRepository
) {

    @GetMapping("/orders")
    fun getAllOrderEvents(): List<OrderEventEntity> {
        return orderEventRepository.findAll()
    }

    @GetMapping("/billings")
    fun getAllBillingEvents(): List<BillingEventEntity> {
        return billingEventRepository.findAll()
    }
}