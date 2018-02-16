package com.consacresdeleternel.consacrebeamer.utils;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.FontAwesome.Glyph;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import javafx.scene.paint.Color;

/**
 * Helper Class to provide basic access to FontAwesome glyphs. To find what kind
 * of glyphs are usable go to http://www.fontawesome.io
 *
 * @author c.watzl
 */
public class GlyphUtilities {

	private static final String FONT = "FontAwesome";
	static final GlyphFont GLYPH_FONT = GlyphFontRegistry.font(FONT);

	private static org.controlsfx.glyphfont.Glyph create(final Glyph icon, final Color foreGround, final Double size,
			String style) {

		org.controlsfx.glyphfont.Glyph glyphIcon = GLYPH_FONT.create(icon);

		if (foreGround != null) {
			glyphIcon.setColor(foreGround);
		}

		if (size != null) {
			glyphIcon.setFontSize(size);
		}

		if (style != null) {
			glyphIcon.setStyle(style);
		}

		return glyphIcon;
	}

	/**
	 * Creates a Icon with default size for the given Glyph
	 *
	 * @param glyph
	 * @return Glyph icon
	 */
	public static org.controlsfx.glyphfont.Glyph create(Glyph glyph) {

		return create(glyph, null, null, null);

	}

	/**
	 * Creates a Icon with default size for the given Glyph with a custom Style
	 *
	 * @param glyph
	 *            the Glyph Icon
	 * @param style
	 *            css defining style
	 * @return Glyph icon
	 */
	public static org.controlsfx.glyphfont.Glyph create(Glyph glyph, String style) {

		return create(glyph, null, null, style);

	}

	/**
	 * Creates an Glyph Icon with default size and a custom Color.
	 *
	 * @param glyph
	 *            Glyph Enum for icon
	 * @param color
	 *            The color to set
	 * @return the created Glyph Icon
	 */

	public static org.controlsfx.glyphfont.Glyph create(Glyph glyph, Color color) {
		org.controlsfx.glyphfont.Glyph iconGlyph = create(glyph);

		iconGlyph.setColor(color);

		return iconGlyph;
	}

	public static org.controlsfx.glyphfont.Glyph generateGlyph(final Color color, final FontAwesome.Glyph iconTyp,
			final double size) {
		final org.controlsfx.glyphfont.Glyph font = GlyphFontRegistry.font("FontAwesome").create(iconTyp);
		font.setFontSize(size);
		font.setColor(color);
		return font;
	}
}
