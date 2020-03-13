package com.consacresdeleternel.consacrebeamer.bibel;

import org.controlsfx.dialog.Wizard;

import com.consacresdeleternel.consacrebeamer.Helper;

public class BibelWidzardViewModel extends Wizard {
	public BibelWidzardViewModel() {
		Helper.load(this);
	}
}
