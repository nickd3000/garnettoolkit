package support;

import com.physmo.garnettoolkit.Component;

public class MoveRightComponent extends Component {
    @Override
    public void tick(double t) {
        System.out.println("MoveRight ticked");
    }

    @Override
    public void init() {
        System.out.println("MoveRight init");
    }
}
