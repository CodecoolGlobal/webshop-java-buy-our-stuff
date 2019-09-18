package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LineItemDaoMem implements LineItemDao {

    private List<LineItem> data = new ArrayList<>();

    @Override
    public void add(LineItem lineItem) {
        data.add(lineItem);
    }

    @Override
    public void remove(LineItem lineItem) {
        data.remove(lineItem);
    }

    @Override
    public void update(LineItem lineItem, int quantity) {
        data
                .stream()
                .filter(item -> item.getProduct() == lineItem.getProduct())
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
    }

    @Override
    public List<LineItem> getBy(Cart cart) {
        return data
                .stream()
                .filter(lineItem -> lineItem.getCartId() == cart.getId())
                .collect(Collectors.toList());
    }
}