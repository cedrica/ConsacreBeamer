package com.consacresdeleternel.consacrebeamer.common;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {
	/**
	 * gets the systems default language bundle
	 */
	public static ResourceBundle getDefault() {
		return ResourceBundle.getBundle("bundle.consacrebeamer_de_DE");
	}

	/**
	 * Returns the ResourceBundle for the given Locale
	 *
	 * @param locale
	 *            locale to find bundle for
	 */
	public static ResourceBundle get(Locale locale) {
		return ResourceBundle.getBundle("bundle.account", locale);
	}

	/**
	 * Gets a string for the given key from this resource
	 * preag.app.leandra.bundle or one of its parents. Calling this method is
	 * equivalent to calling <blockquote>
	 * <code>(String) {@link #getObject(String) getObject}(key)</code>.
	 * </blockquote>
	 *
	 * @param key
	 *            the key for the desired string
	 *
	 * @return the string for the given key
	 *
	 * @throws NullPointerException
	 *             if <code>key</code> is <code>null</code>
	 * @throws MissingResourceException
	 *             if no object for the given key can be found
	 * @throws ClassCastException
	 *             if the object found for the given key is not a string
	 */
	public static String asKey(String key) {
		return (key != null)? getDefault().getString(key):null;
	}
}
