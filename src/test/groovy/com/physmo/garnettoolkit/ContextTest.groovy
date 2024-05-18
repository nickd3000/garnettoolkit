package com.physmo.garnettoolkit

import spock.lang.Specification
import support.Monster
import support.MoveRightComponent
import support.SoundEngine

class ContextTest extends Specification {

    def "basic test for context"() {
        given: "A context with a monster that has a MoveRightComponent"
            Context context = new Context()
            Monster monster = new Monster("")
            MoveRightComponent moveRight = new MoveRightComponent()
            monster.addComponent(moveRight)
            context.add(monster)

        when: "The context is initialized and ticked"
            context.init()
            context.tick(1)

        then: "There are no explicit assertions in this test, but exceptions should not be thrown"
            // No assertions needed, but if any method throws an exception, the test will fail.
    }

    def "test getting object by type from context"() {
        given: "A context with a SoundEngine"
            Context context = new Context()
            context.add(new SoundEngine("sound engine"))
            context.init()
            context.tick(1)

        when: "The SoundEngine is retrieved by its type"
            SoundEngine soundEngine = context.getObjectByType(SoundEngine.class)

        then: "The SoundEngine should be accessible, of the correct type, and have ticked once"
            soundEngine != null
            soundEngine instanceof SoundEngine
            soundEngine.getTickCount() == 1
    }
}