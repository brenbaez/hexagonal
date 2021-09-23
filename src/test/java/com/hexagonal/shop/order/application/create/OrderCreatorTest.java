package com.hexagonal.shop.order.application.create;

import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.order.domain.Order;
import com.hexagonal.shop.order.domain.OrderRepository;
import com.hexagonal.shop.order.domain.ProductRepository;
import com.hexagonal.shop.order.domain.create.OrderMother;
import com.hexagonal.shop.shared.domain.ProductMother;
import com.hexagonal.shop.shared.domain.ProductQuantityMother;
import com.hexagonal.shop.shared.domain.product.Product;
import com.hexagonal.shop.shared.domain.valueobject.Address;
import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;
import com.hexagonal.shop.shared.domain.valueobject.Email;
import com.hexagonal.shop.shared.domain.exception.InvalidEmail;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderCreatorTest {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderCreator useCaseSUT;
    private Product product1;
    private Product product2;
    private Product product3;
    private List<Product> products;
    private Set<ProductId> productIds;
    private Map<ProductId, ProductQuantity> quantityPerProduct;
    private DiscountCode discountCode;
    private Address address;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        useCaseSUT = new OrderCreator(orderRepository, productRepository);
        product1 = ProductMother.randomProduct();
        product2 = ProductMother.randomProduct();
        product3 = ProductMother.randomProduct();

        products = Stream.of(product1, product2, product3).collect(Collectors.toList());
        productIds = products.stream().map(Product::getProductId).collect(Collectors.toSet());

        quantityPerProduct = generateQuantityAndProducts(productIds);
        discountCode = new DiscountCode(getRandomValue());
        address = new Address(getRandomValue());
    }

    @Test
    void shouldCreateAnOrder() {

        Email email = new Email("test@gmail.com");

        Order order = OrderMother.withData(products, quantityPerProduct, discountCode, address, email);

        when(productRepository.getAll(productIds)).thenReturn(products);

        Order expectedOrder = useCaseSUT.create(quantityPerProduct, address, discountCode, email);

        verify(orderRepository, times(1)).save(any());

        assertEquals(order.details(), expectedOrder.details());
    }

    @Test
    void shouldCreateAnOrderWithValidEmails() {

        List<Email> emails = Stream.of(
                new Email("test@mymail.com"),
                new Email("first.last@iana.org"),
                new Email("wo..oly@iana.org"),
                new Email("new@Email.com"))
                .collect(Collectors.toList());

        emails.forEach(email -> {
            Order order = OrderMother.withData(products, quantityPerProduct, discountCode, address, email);

            when(productRepository.getAll(productIds)).thenReturn(products);

            Order expectedOrder = useCaseSUT.create(quantityPerProduct, address, discountCode, email);

            assertEquals(order.details(), expectedOrder.details());
        });

        verify(orderRepository, atMost(emails.size())).save(any());

    }

    @Test
    void shouldFailInvalidEmail() {

        String invalidEmail = "ivalid@email.1234";

        Exception exception = assertThrows(InvalidEmail.class, () -> {
            useCaseSUT.create(quantityPerProduct, address, discountCode, new Email(invalidEmail));
        });

        String expectedMessage = "<" + invalidEmail + "> is not a valid email";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(orderRepository, never()).save(any());
        verify(productRepository, never()).getAll(productIds);
    }

//    @Test
//    void shouldCreateAnOrderEmailSuffixNumericNumber() {
//
//        Email validEmail = new Email("valid@email.1234");
//
//        Order order = OrderMother.withData(products, quantityPerProduct, discountCode, address, validEmail);
//
//        when(productRepository.getAll(productIds)).thenReturn(products);
//
//        Order expectedOrder = useCaseSUT.create(quantityPerProduct, address, discountCode, validEmail);
//
//        assertEquals(order.details(), expectedOrder.details());
//
//        verify(orderRepository, times(1)).save(any());
//    }


    private String getRandomValue() {
        return RandomStringUtils.random(10, true, false);
    }

    private static Map<ProductId, ProductQuantity> generateQuantityAndProducts(Set<ProductId> prodIds) {
        Map<ProductId, ProductQuantity> quantityPerProduct = new HashMap<>();

        prodIds.forEach(productId -> quantityPerProduct.put(productId, ProductQuantityMother.randomProductQuantity()));

        return quantityPerProduct;
    }
}