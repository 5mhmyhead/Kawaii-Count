package com.kawaii.kawaiicount.utilities;

import com.kawaii.kawaiicount.App;
import com.kawaii.kawaiicount.objects.ActiveSidebarItem;
import com.kawaii.kawaiicount.objects.SidebarItem;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class AnimationHelper
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

    // HELPER FUNCTION TO CREATE A VERTICAL SLIDE TRANSITION DURING LOGIN SCREEN
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

    public static FadeTransition createFade(Node node, double fromValue, double toValue, int delay, int duration)
    {
        FadeTransition fade = new FadeTransition(Duration.millis(duration), node);

        fade.setDelay(Duration.millis(delay));
        fade.setFromValue(fromValue);
        fade.setToValue(toValue);

        return fade;
    }

    public static void showErrorMessage(Label label, String message, int displayDuration, int fadeDuration)
    {
        // STOP ANY EXISTING ANIMATION ON THIS LABEL
        Object existing = label.getProperties().get("animation");

        if (existing instanceof SequentialTransition)
            ((SequentialTransition) existing).stop();

        label.setText(message);
        label.setOpacity(1);

        PauseTransition pause = new PauseTransition(Duration.millis(displayDuration));
        FadeTransition fade = new FadeTransition(Duration.millis(fadeDuration), label);

        fade.setFromValue(1);
        fade.setToValue(0);

        SequentialTransition sequence = new SequentialTransition(pause, fade);
        label.getProperties().put("animation", sequence);
        sequence.play();
    }

    // SETS UP THE ANIMATED SIDEBAR SLIDE
    public static void setupSidebarButton(ActiveSidebarItem active, SidebarItem target, List<SidebarItem> allTargets)
    {
        final Color SOFT_RED = new Color(0.906, 0.427, 0.541, 1.0);
        final Color OFF_WHITE = new Color(0.973, 0.914, 0.898, 1.0);

        TranslateTransition slide = new TranslateTransition(Duration.millis(target.duration()), active.selectionIndicator());
        slide.setInterpolator(Interpolator.SPLINE(0.70, 0.0, 0.30, 1.0));
        slide.setToY(target.toY());

        Timeline fromButtonColor = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(active.button().textFillProperty(), SOFT_RED)),
                new KeyFrame(Duration.millis(100), new KeyValue(active.button().textFillProperty(), OFF_WHITE))
        );

        Timeline toButtonColor = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(target.button().textFillProperty(), OFF_WHITE)),
                new KeyFrame(Duration.millis(target.duration()), new KeyValue(target.button().textFillProperty(), SOFT_RED))
        );

        FadeTransition fadeFromIcon = createFade(active.icon(), 1, 0, 0, 100);
        FadeTransition fadeToIcon = createFade(target.icon(), 1, 0, 0, target.duration());

        slide.setOnFinished(_ -> {
            try
            {
                // WIDTH AND HEIGHT CHANGES BACK TO SMALL WHEN SIGNING OUT
                boolean isSignOut = target.fxml().equals("title-page");
                int width = isSignOut ? App.WIDTH : App.MAIN_WIDTH;
                int height = isSignOut ? App.HEIGHT : App.MAIN_HEIGHT;

                App.setRoot(target.fxml(), width, height, isSignOut);
            }
            catch (IOException e)
            {
                System.out.println("Failed to switch scenes: " + e.getMessage());
                e.printStackTrace();
            }
        });

        target.button().setOnAction(_ -> {
            // BLOCK ALL BUTTONS DURING ANIMATION
            active.button().setDisable(true);
            allTargets.forEach(t -> t.button().setDisable(true));

            fromButtonColor.play();
            toButtonColor.play();
            fadeFromIcon.play();
            fadeToIcon.play();
            slide.play();
        });
    }
}
