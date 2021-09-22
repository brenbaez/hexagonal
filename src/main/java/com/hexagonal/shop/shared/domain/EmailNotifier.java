package com.hexagonal.shop.shared.domain;

import com.hexagonal.shop.shared.domain.valueobject.Email;

public interface EmailNotifier {

    void sendNotification(Email email);
}
