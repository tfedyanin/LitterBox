package org.tim;

import java.util.concurrent.ExecutorService;

/**
 * User: Tim
 * 01.06.2015 20:59
 */
public class HightLevelClient {
    final private ExecutorService executorService;
    final private LowLevelClient lowLevelClient;

    public HightLevelClient(ExecutorService executorService, LowLevelClient lowLevelClient) {
        this.executorService = executorService;
        this.lowLevelClient = lowLevelClient;
    }


}
