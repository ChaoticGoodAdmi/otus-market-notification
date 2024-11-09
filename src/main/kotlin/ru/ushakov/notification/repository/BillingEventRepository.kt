package ru.ushakov.notification.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.ushakov.notification.domain.BillingEventEntity

interface BillingEventRepository : JpaRepository<BillingEventEntity, Long>