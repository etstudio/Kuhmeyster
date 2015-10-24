package ru.etstudio.kuhmeyster;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import java.util.List;

import ru.etstudio.kuhmeyster.db.dao.KindDAO;
import ru.etstudio.kuhmeyster.db.entity.Kind;

public class DatabaseKindTest extends AndroidTestCase {

    private KindDAO kindDAO;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        kindDAO = new KindDAO(context);
    }

    @Override
    public void tearDown() throws Exception {
        kindDAO.close();
        super.tearDown();
    }

    public void testGetAll() {
        List<Kind> kinds = kindDAO.getAll();
        assertTrue(kinds.size() == 12);
        for (int i = 0; i < 12; i++) {
            Kind kind = kinds.get(i);
            assertNotNull(kind);
        }
    }

    public void testGet() {
        for (int i = 1; i <= 12; i++) {
            Kind kind = kindDAO.get(i);
            assertNotNull(kind);
        }
    }

    public void testInsert() {
        Kind kind = new Kind("TEST");
        long id = kindDAO.insert(kind);
        assertTrue(id > -1);
        kind = kindDAO.get(id);
        assertNotNull(kind);
    }

    public void testUpdate() {
        Kind kind = kindDAO.get(13);
        kind.setLabel("TEST_!");
        kindDAO.update(kind);
        kind = kindDAO.get(13);
        assertEquals(kind.getLabel(), "TEST_!");
    }

    public void testZDelete() {
        List<Kind> kinds = kindDAO.getAll();
        for (Kind kind : kinds) {
            kindDAO.delete(kind);
        }
        assertTrue(kindDAO.getAll().size() == 0);
    }
}
