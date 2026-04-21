package com.kawaii.kawaiicount.utilities;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class TransitionHelper
{
    // HELPER FUNCTION TO CREATE A HORIZONTAL SLIDE TRANSITION DURING LOGIN SCREEN
    public static TranslateTransition createSlideX(Node node, double toX, int delay, int duration)
    {
        TranslateTransition transition = new TranslateTransition();

        transition.setNode(node);
        transition.setDelay(Duration.millis(delay));
        transition.setDuration(Duration.millis(duration));

        transition.setInterpolator(Interpolator.SPLINE(0.70, 0.0, 0.30, 1.0));
        transition.setToX(toX);

        return transition;
    }

    public static TranslateTransition createSlideY(Node node, double toY, int delay, int duration)
    {
        TranslateTransition transition = new TranslateTransition();

        transition.setNode(node);
        transition.setDelay(Duration.millis(delay));
        transition.setDuration(Duration.millis(duration));

        transition.setInterpolator(Interpolator.SPLINE(0.70, 0.0, 0.30, 1.0));
        transition.setToY(toY);

        return transition;
    }
}
