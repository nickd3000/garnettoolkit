package support;

import com.physmo.garnettoolkit.Component;

public class MonsterLogicComponent extends Component {
    @Override
    public void tick(double t) {

    }

    @Override
    public void init() {

    }

    @Override
    public void draw() {

    }

    public InventoryComponent accessInventoryComponent() {
        InventoryComponent inventoryComponent = parent.getComponent(InventoryComponent.class);
        return inventoryComponent;
    }
}
