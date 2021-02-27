package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.*;

import java.util.List;

class ProductCategoryDaoTest {

    @BeforeEach
    void initData() {
        DaoManager.init();
    }

    @AfterAll
    static void clearData() {
        DaoManager.clear();
    }

    @Test
    void testAdd() {
        ProductCategory productCategory = new ProductCategory("test", "test", "test");
        ProductCategoryDao productCategoryDao = DaoManager.getProductCategoryDao();
        productCategoryDao.add(productCategory);
        Assertions.assertNotEquals(0, productCategory.getId());
    }

    @Test
    void testFind_validId() {
        int id = 1;
        ProductCategoryDao productCategoryDao = DaoManager.getProductCategoryDao();
        ProductCategory result = productCategoryDao.find(id);
        Assertions.assertEquals(id, result.getId());
    }

    @Test
    void testFind_invalidId() {
        ProductCategoryDao productCategoryDao = DaoManager.getProductCategoryDao();
        Assertions.assertThrows(DataNotFoundException.class, () -> productCategoryDao.find(-1));
    }

    @Test
    void testRemove() {
        int id = 1;
        ProductCategoryDao productCategoryDao = DaoManager.getProductCategoryDao();
        ProductCategory productCategory = productCategoryDao.find(id);
        productCategoryDao.remove(id);
        Assertions.assertNotNull(productCategory);
        Assertions.assertThrows(DataNotFoundException.class, () -> productCategoryDao.find(id));
    }

    @Test
    void testGetAll() {
        int productCategoryCount = 3;
        ProductCategoryDao productCategoryDao = DaoManager.getProductCategoryDao();
        List<ProductCategory> productCategories = productCategoryDao.getAll();
        Assertions.assertEquals(productCategoryCount, productCategories.size());
    }
}