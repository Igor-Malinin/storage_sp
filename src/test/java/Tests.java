import com.example.storage_sp.applications.StorageInterface;
import com.example.storage_sp.applications.StorageInterfaceImpl;
import com.example.storage_sp.domain.Item;
import com.example.storage_sp.repository.StorageState;
import com.example.storage_sp.repository.StorageStateImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    private static final int STORAGE_COLS = 20;
    private static final int STORAGE_ROWS = 20;
    private static final int BOX_CAPACITY = 10;




    // тесты с хешмапой


    // подсчет KPI
/*    @Test
    public void testCalcKPI() {
*//*        double kpi = 0;
        double currentCase;
        double idealCase = 0;
        for (int i = 0; i < 20; i++) {
            storage.addItem("keyboard", "newKeyboard", "newDescription");
        }
        for (int i = 0; i < 20; i++) {
            storage.addItem("monitor", "newMonitor", "newDescription");
        }*//*
//
//        int amount = 8;
//        int temp = storage.getShelfs();
//        for (int j = 0, k = 0; j < amount; j++) {
//            if (j % temp != 0)
//                idealCase += k;
//            else {
//                k++;
//                idealCase += k;
//            }
//        }
//        for (int i = 0; i < amount; i++) {
//            storage.takeItem("monitor");
//        }
//        currentCase = storage.getIterations();
//        kpi = idealCase/currentCase;
//        System.out.println("iC = " + idealCase + "; cC = " + currentCase + "; KPI = " + kpi);

    }*/

    @Test
    public void testAddDel() {
        StorageState storageState;
        StorageInterface storage;
        storageState = new StorageStateImpl();
        storage = new StorageInterfaceImpl(storageState);
        storage.createStorage(STORAGE_COLS, STORAGE_ROWS, BOX_CAPACITY);

        int MONITORS_COUNT = 3;
        int KEYBOARDS_COUNT = 2;
        // добавляем несколько предметов по типу.
        // место, на которое кладется предмет устанавливается автоматически (ближайшее свободное)
        for (int i = 0; i < KEYBOARDS_COUNT; i++) {
            storage.addItem("keyboard", "newKeyboard", "newDescription");
        }

        for (int i = 0; i < MONITORS_COUNT; i++) {
            storage.addItem("monitor", "newMonitor", "newDescription");
        }


        Set<String> monitorIds = storage.browseItemsByType("monitor").stream().map(Item::getId).collect(Collectors.toSet());
        assertEquals(MONITORS_COUNT, monitorIds.size());

        Set<String> keyboardsIds = storage.browseItemsByType("keyboard").stream().map(Item::getId).collect(Collectors.toSet());
        assertEquals(KEYBOARDS_COUNT, keyboardsIds.size());

        //storage.takeItems(keyboardsIds);
  /*      storage.takeItems(monitorIds);*/


        //Assert.assertEquals("newDescription", storage.takeItem(i).getDescription());
    }

    // Негативный тест
    /*@Test

    public void expectedExceptions() {
        StorageState storageState;
        StorageInterface storage;
        storageState = new StorageStateImpl();
        storage = new StorageInterfaceImpl(storageState);
        storage.createStorage(STORAGE_COLS, STORAGE_ROWS, BOX_CAPACITY);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            storage.takeItems(Collections.singleton("non-existent-id"));
        });

        String expectedMessage = "not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }*/

}
