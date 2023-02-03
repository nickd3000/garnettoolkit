import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import support.InventoryComponent;
import support.Monster;
import support.MonsterLogicComponent;
import support.MoveRightComponent;

public class ComponentTest {

    @Test
    public void TestAccessingSiblingComponentsWithinGameObject() {
        Monster monster = new Monster();
        monster.addComponent(new MonsterLogicComponent());
        monster.addComponent(new MoveRightComponent());
        monster.addComponent(new InventoryComponent());

        MonsterLogicComponent monsterLogicComponent = monster.getComponent(MonsterLogicComponent.class);
        InventoryComponent inventoryComponent = monsterLogicComponent.accessInventoryComponent();

        Assertions.assertNotNull(inventoryComponent);
        Assertions.assertTrue(inventoryComponent instanceof InventoryComponent);
    }
}
