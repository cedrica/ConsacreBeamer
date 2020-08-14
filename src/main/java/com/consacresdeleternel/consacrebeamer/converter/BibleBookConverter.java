package com.consacresdeleternel.consacrebeamer.converter;

import java.io.File;

import com.consacresdeleternel.consacrebeamer.common.Helper;

import javafx.util.StringConverter;

public class BibleBookConverter extends StringConverter<File> {
    @Override
    public String toString(File object) {
        return Helper.retrieveTraductionNameFormFileName(object);
    }

    @Override
    public File fromString(String string) {
        return null;
    }
};