package com.consacresdeleternel.consacrebeamer.utils;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationUtils {
    /**
     * Add this to for example a Maskerpane to make a nice fade Transition. For Example:
     * <pre>FadeTransition fadeIn = FXGuiUtils.createFadeAnimation(0.0, 1.0, maskerPane, 300);</pre>
     * @param from which opacity value you want to start
     * @param to which opacity value you want to end
     * @param content the node that will be fade
     * @param millis how long the fade effect should be
     * @return the FadeTransition you need to call <pre>.playFromStart</pre>
     */
    public static FadeTransition createFadeAnimation(double from, double to, Node content, int millis) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(millis));
        fadeIn.setFromValue(from);
        fadeIn.setToValue(to);
        fadeIn.setCycleCount(1);
        fadeIn.setAutoReverse(false);
        fadeIn.setNode(content);
        return fadeIn;
    }
}
