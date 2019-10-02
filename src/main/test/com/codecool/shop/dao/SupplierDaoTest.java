package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.jdbc.SupplierDaoJDBC;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest extends DaoTest {

    @Test
    void testAdd() {
        Supplier supplier = new Supplier("test", "test");
        SupplierDao supplierDao = DaoController.getSupplierDao();
        supplierDao.add(supplier);
        Assertions.assertNotEquals(0, supplier.getId());
    }

    @Test
    void testFind_validId() {
        SupplierDao supplierDao = DaoController.getSupplierDao();
        Supplier result = supplierDao.find(2);
        Assertions.assertEquals(2, result.getId());
    }

    @Test
    void testFind_invalidId() {
        SupplierDao supplierDao = DaoController.getSupplierDao();
        Supplier result = supplierDao.find(-1);
        Assertions.assertNull(result);
    }

    @Test
    void testRemove() {
        int id = 1;
        SupplierDao supplierDao = DaoController.getSupplierDao();
        Supplier before = supplierDao.find(id);
        supplierDao.remove(id);
        Supplier after = supplierDao.find(id);
        Assertions.assertNotNull(before);
        Assertions.assertNull(after);
    }

    @Test
    void testGetAll() {
        int supplierCount = 3;
        SupplierDao supplierDao = DaoController.getSupplierDao();
        List<Supplier> suppliers = supplierDao.getAll();
        Assertions.assertEquals(supplierCount, suppliers.size());
    }
}