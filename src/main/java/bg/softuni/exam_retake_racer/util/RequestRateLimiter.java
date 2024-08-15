package bg.softuni.exam_retake_racer.util;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestRateLimiter {

    private final Map<String, UserRequestInfo> requestCounts = new ConcurrentHashMap<>();
    private final Integer maxRequests;
    private final Long timeWindowInMilliseconds;

    public RequestRateLimiter(Integer maxRequests, Long timeWindowInMilliseconds) {
        this.maxRequests = maxRequests;
        this.timeWindowInMilliseconds = timeWindowInMilliseconds;
    }

    public synchronized boolean isAllowed(String username) {
        Instant now = Instant.now();
        UserRequestInfo userRequestInfo = requestCounts.getOrDefault(username, new UserRequestInfo(0, now));

        if (now.minusMillis(timeWindowInMilliseconds).isAfter(userRequestInfo.getLastRequestTime())) {
            userRequestInfo = new UserRequestInfo(0, now);
        }

        if (userRequestInfo.getRequestCount() < maxRequests) {
            userRequestInfo.incrementRequestCount();
            userRequestInfo.setLastRequestTime(now);
            requestCounts.put(username, userRequestInfo);
            return true;
        }

        return false;
    }

    private static class UserRequestInfo {
        private Integer requestCount;
        private Instant lastRequestTime;

        public UserRequestInfo(Integer requestCount, Instant lastRequestTime) {
            this.requestCount = requestCount;
            this.lastRequestTime = lastRequestTime;
        }

        public Integer getRequestCount() {
            return requestCount;
        }

        public UserRequestInfo incrementRequestCount() {
            this.requestCount++;
            return this;
        }

        public Instant getLastRequestTime() {
            return lastRequestTime;
        }

        public UserRequestInfo setLastRequestTime(Instant lastRequestTime) {
            this.lastRequestTime = lastRequestTime;
            return this;
        }
    }
}
