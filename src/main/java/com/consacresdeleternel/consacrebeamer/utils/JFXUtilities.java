package com.consacresdeleternel.consacrebeamer.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.controlsfx.control.MaskerPane;

import com.consacresdeleternel.consacrebeamer.manager.TaskManager;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * <h1>JavaFX FX Thread utilities</h1> JFXUtilities allow sync mechanism to the
 * FX thread
 * <p>
 *
 * @author pmoufarrej
 * @version 1.0
 * @since 2016-03-09
 */

public class JFXUtilities {

	/**
	 * Creates the Boolean Binding for the Maskerpane and the TaskManager
	 * 
	 * @param masker
	 *            will be displayed everytime there is a running task
	 */
	public static void bindMaskerPane(MaskerPane masker, TaskManager taskManager) {
		FadeTransition fadeIn = AnimationUtils.createFadeAnimation(0.0, 1.0, masker, 300);
		BooleanBinding tasksRunning = Bindings.equal(TaskManager.INTERMEDIATE, taskManager.progressProperty(), 0.0);
		tasksRunning.addListener((obs, oldVal, newVal) -> updateMaskerPane(newVal, fadeIn));
		masker.visibleProperty().bind(tasksRunning);
	}

	private static void updateMaskerPane(Boolean newVal, FadeTransition fadeIn) {
		if (newVal) {
			Platform.runLater(() -> {
				fadeIn.playFromStart();
			});
		}
	}

	private static final Logger LOG = Logger.getLogger(JFXUtilities.class);

	/**
	 * This method is used to run a specified Runnable in the FX Application
	 * thread, it returns before the task finished execution
	 *
	 * @param doRun
	 *            This is the sepcifed task to be excuted by the FX Application
	 *            thread
	 */
	public static void runInFX(Runnable doRun) {
		if (Platform.isFxApplicationThread()) {
			doRun.run();
			return;
		}
		Platform.runLater(doRun);
	}

	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	public static boolean containsWhitespace(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is used to run a specified Runnable in the FX Application
	 * thread, it waits for the task to finish before returning to the main
	 * thread.
	 *
	 * @param doRun
	 *            This is the sepcifed task to be executed by the FX Application
	 *            thread
	 */
	public static void runInFXAndWait(Runnable doRun) {
		if (Platform.isFxApplicationThread()) {
			doRun.run();
			return;
		}
		final CountDownLatch doneLatch = new CountDownLatch(1);
		Platform.runLater(() -> {
			try {
				doRun.run();
			} finally {
				doneLatch.countDown();
			}
		});
		try {
			doneLatch.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Returns true if successful. Current defaults are 1000, 5000, 200
	 *
	 * @param openDelay
	 *            Delay until Tooltip is shown in milliseconds
	 * @param visibleDuration
	 *            Delay how long Tooltip is shown in milliseconds
	 * @param closeDelay
	 *            Delay for close animation in milliseconds
	 *
	 * @return true if successfully changed
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean setTooltipTimers(long openDelay, long visibleDuration, long closeDelay) {
		try {
			Field f = Tooltip.class.getDeclaredField("BEHAVIOR");
			f.setAccessible(true);

			Class[] classes = Tooltip.class.getDeclaredClasses();
			for (Class clazz : classes) {
				if (clazz.getName().equals("javafx.scene.control.Tooltip$TooltipBehavior")) {//$NON-NLS-1$
					Constructor ctor = clazz.getDeclaredConstructor(Duration.class, Duration.class, Duration.class,
							boolean.class);
					ctor.setAccessible(true);
					Object tooltipBehavior = ctor.newInstance(new Duration(openDelay), new Duration(visibleDuration),
							new Duration(closeDelay), false);
					f.set(null, tooltipBehavior);
					break;
				}
			}
		} catch (Exception e) {
			LOG.error("Unable to extend display duration for Toolitps", e);//$NON-NLS-1$
			return false;
		}
		return true;
	}

	public static String convertColorToHex(Color color) {
		String hex = String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
				(int) (color.getBlue() * 255));
		return hex;
	}
}
