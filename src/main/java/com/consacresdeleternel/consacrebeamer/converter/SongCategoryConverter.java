package com.consacresdeleternel.consacrebeamer.converter;

import com.consacresdeleternel.consacrebeamer.data.SongCategory;

import javafx.util.StringConverter;

public class SongCategoryConverter extends StringConverter<SongCategory> {

	@Override
	public SongCategory fromString(String string) {
		return null;
	}

	@Override
	public String toString(SongCategory songCategory) {
		return songCategory.getName();
	}

}
