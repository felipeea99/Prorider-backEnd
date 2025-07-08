package com.prorider.interfaces.payments;

import java.util.UUID;

public interface ISubscription {
    SubscriptionDbResponse createSubscription (SubscriptionRequest subscriptionRequest);
    SubscriptionDbResponse cancelSubscription (UUID storeId);
    SubscriptionDbResponse findSubscriptionById (UUID subscriptionId);
    SubscriptionDb findSubscriptionByIdBaseForm (UUID subscriptionId);
    void handleWebhookEvent(String payload, String sigHeader);

    SubscriptionDbResponse TOSubscriptionDbResponse(SubscriptionDb subscriptionDb);
}
