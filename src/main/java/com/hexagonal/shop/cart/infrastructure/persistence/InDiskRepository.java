package com.hexagonal.shop.cart.infrastructure.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexagonal.shop.cart.domain.Cart;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InDiskRepository implements CartRepository {

    @Override
    public Optional<Cart> get(CartId cartId) {
        Map<String, Cart> carts = readCartsFromDisk();
        return Optional.ofNullable(carts.get(cartId.value()));
    }

    @Override
    public void save(Cart cart) {
        Map<String, Cart> carts = readCartsFromDisk();
        carts.put(cart.getId().value(), cart);
        writeCartsFileInDisk(carts);
    }

    private Map<String, Cart> readCartsFromDisk() {
        try (FileInputStream fis = new FileInputStream("/temp/database/carts")) {
            byte[] cartBytes = fis.readAllBytes();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Cart> cartMap = mapper.readValue(cartBytes, new TypeReference<Map<String, Cart>>() {
            });
            return cartMap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return new HashMap<>();
        }
    }

    private void writeCartsFileInDisk(Map<String, Cart> carts) {
        try (FileOutputStream fos = new FileOutputStream("/temp/database/carts")) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                fos.write(mapper.writeValueAsBytes(carts));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
