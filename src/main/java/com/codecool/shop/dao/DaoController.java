package com.codecool.shop.dao;

import com.codecool.shop.model.*;
import com.codecool.shop.util.BaseData;
import lombok.Getter;

public class DaoController {
    private static final DaoType DAO_TYPE = DaoType.JDBC;

    private static DaoImplementationSupplier daoImplementationSupplier = DaoImplementationSupplier.getInstance(DAO_TYPE);

    @Getter private static final ProductDao productDao = daoImplementationSupplier.getProductDao();
    @Getter private static final ProductCategoryDao productCategoryDao = daoImplementationSupplier.getProductCategoryDao();
    @Getter private static final SupplierDao supplierDao = daoImplementationSupplier.getSupplierDao();
    @Getter private static final CartDao cartDao = daoImplementationSupplier.getCartDao();
    @Getter private static final LineItemDao lineItemDao = daoImplementationSupplier.getLineItemDao();
    @Getter private static final UserDao userDao = daoImplementationSupplier.getUserDao();

    public static void init() {
        clear();

        initCart();
        initSuppliers();
        initProductCategories();
        initProducts();
        initUsers();
    }

    public static void clear() {
        daoImplementationSupplier = DaoImplementationSupplier.getInstance(DAO_TYPE);
    }

    private static void initCart() {
        Cart cart = new Cart();
        daoImplementationSupplier.getCartDao().add(cart);
    }

    private static void initSuppliers() {
        for (Supplier supplier : BaseData.defaultSuppliers()) {
            supplierDao.add(supplier);
        }
    }

    private static void initProductCategories() {
        for (ProductCategory productCategory : BaseData.defaultProductCategories()) {
            productCategoryDao.add(productCategory);
        }
    }

    private static void initProducts() {
        for (Product product : BaseData.defaultProducts()) {
            productDao.add(product);
        }
    }

    private static void initUsers() {
        for (User user : BaseData.defaultUsers()) {
            userDao.add(user);
        }
    }
}
