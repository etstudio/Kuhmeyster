package ru.etstudio.kuhmeyster;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import java.util.Date;
import java.util.List;

import ru.etstudio.kuhmeyster.db.dao.DishDAO;
import ru.etstudio.kuhmeyster.db.dao.KindDAO;
import ru.etstudio.kuhmeyster.db.entity.Dish;
import ru.etstudio.kuhmeyster.db.entity.Kind;

public class DatabaseDishTest extends AndroidTestCase {
    private DishDAO dishDAO;
    private KindDAO kindDAO;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        dishDAO = new DishDAO(context);
        kindDAO = new KindDAO(context);
    }

    @Override
    public void tearDown() throws Exception {
        dishDAO.close();
        super.tearDown();
    }

    public void testGetAll() {
        List<Dish> dishes = dishDAO.getAll();
        assertTrue(dishes.size() == 100);
        for (Dish dish : dishes) {
            assertNotNull(dish.getKind());
        }
    }

    public void testGet() {
        Dish dish = dishDAO.get(30);
        assertNotNull(dish);
        assertNotNull(dish.getKind());
    }

    public void testAInsert() {
        for (int i = 0; i < 100; i++) {
            Dish dish = new Dish();
            dish.setTitle("TITLE " + String.valueOf(i));
            dish.setCreated(new Date());
            dish.setEveryday(true);
            dish.setCooking("COOKING " + String.valueOf(i));
            Kind kind = kindDAO.get(4);
            assertNotNull(kind);
            dish.setKind(kind);
            long id = dishDAO.insert(dish);
            assertTrue(id > -1);
        }
    }

    public void testUpdate() {
        Dish dish = dishDAO.get(30);
        Kind newKind = kindDAO.get(2);
        dish.setKind(newKind);
        dishDAO.update(dish);
        dish = dishDAO.get(30);
        assertTrue(dish.getKind().getId() == 2);
    }

    public void testZDelete() {
        Dish dish = dishDAO.get(30);
        assertNotNull(dish);
        dishDAO.delete(dish);
        dish = dishDAO.get(30);
        assertNull(dish);
    }
}
