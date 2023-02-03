package support;

import com.physmo.gametoolkit.Component;

public class MonsterLogicComponent extends Component {
    @Override
    public void tick(double t) {

    }

    @Override
    public void init() {

    }

    public InventoryComponent accessInventoryComponent() {
        InventoryComponent inventoryComponent = parent.getComponent(InventoryComponent.class);
        return inventoryComponent;
    }
}
