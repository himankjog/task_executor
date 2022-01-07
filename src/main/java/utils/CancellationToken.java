package utils;

import java.util.concurrent.atomic.AtomicBoolean;

public class CancellationToken {
    private final AtomicBoolean cancellationToken;

    public CancellationToken() {
        cancellationToken = new AtomicBoolean(false);
    }

    public boolean isCancelled() {
        return cancellationToken.get();
    }

    public void cancel() {
        cancellationToken.compareAndSet(false, true);
    }
}
