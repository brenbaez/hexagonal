package com.hexagonal.shop.shared.domain;

import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;

public interface DiscountNextPurchase {

    void discount(DiscountCode discountCode);
}
