package fundamentaltask;

import Util.ControllerName;
import entities.DurableProducts;
import oop.persistance.controller.DurableHandler;
import oop.persistance.controller.HandlerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author G
 */
public class UnitTests {

    private DurableProducts dp = new DurableProducts("122");

    @Test
    void testController() {
        DurableHandler handler = (DurableHandler) HandlerFactory.createHandler(
                ControllerName.Durable);
        handler.create(dp);
        DurableProducts find = handler.searchByIdPart("122").
                get(0);
        Assert.assertEquals(find, dp);
        System.out.println("ez is lefut");
    }

}
