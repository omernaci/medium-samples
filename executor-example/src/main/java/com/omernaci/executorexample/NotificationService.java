package com.omernaci.executorexample;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class NotificationService {

    private final ExecutorService executor;
    private final MeterRegistry meterRegistry;

    public NotificationService(@Qualifier("notificationExecutorService") ExecutorService executor,
                               MeterRegistry meterRegistry) {
        this.executor = executor;
        this.meterRegistry = meterRegistry;
    }

    public void sendNotification(String orderId, String email, String phone) {
        executor.submit(() -> {
            long start = System.currentTimeMillis();
            try {
                sendEmail(orderId, email);
                meterRegistry.counter("notifications.email.success").increment();
            } catch (Exception e) {
                meterRegistry.counter("notifications.email.failure").increment();
            } finally {
                meterRegistry.timer("notifications.email.timer")
                        .record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
            }
        });
    }

    private void sendEmail(String orderId, String email) {
        int maxAttempts = 3;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                attempt++;
                if (new Random().nextBoolean()) throw new RuntimeException("Simulated failure");
                System.out.printf("Email sent to %s for order %s%n", email, orderId);
            } catch (Exception e) {
                System.err.printf("Failed attempt %d to send email: %s%n", attempt, e.getMessage());
                meterRegistry.counter("notifications.email.failure").increment();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

}
