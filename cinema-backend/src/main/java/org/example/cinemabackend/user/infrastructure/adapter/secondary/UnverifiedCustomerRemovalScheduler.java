package org.example.cinemabackend.user.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
class UnverifiedCustomerRemovalScheduler {
    private static final Logger LOGGER = LogManager.getLogger(UnverifiedCustomerRemovalScheduler.class);
    private static final Long TIME_FOR_VERIFICATION = 24L;
    private static final long INTERVAL_FOR_CHECKING_UNVERIFIED_CUSTOMERS = 1000L * 60 * 60 * 24;
    private final UserRepository userRepository;

    @Scheduled(fixedRate = INTERVAL_FOR_CHECKING_UNVERIFIED_CUSTOMERS)
    public void cleanUpDbFromUnverifiedCustomers() {
        LOGGER.info("Checking for unverified customers to delete.");
        final var unverifiedCustomers = userRepository.findAllUnverifiedCustomers();
        unverifiedCustomers.forEach(this::deleteUnverifiedCustomer);
    }

    private void deleteUnverifiedCustomer(User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime createdAt = user.getCreatedAt();

        if (createdAt.isBefore(now.minusHours(TIME_FOR_VERIFICATION))) {
            userRepository.deleteUser(user);
            LOGGER.info("User " + user.getEmail() + " deleted for not verifying their account.");
        }
    }
}
