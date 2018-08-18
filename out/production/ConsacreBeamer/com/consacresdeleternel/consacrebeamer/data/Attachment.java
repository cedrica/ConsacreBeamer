package com.consacresdeleternel.consacrebeamer.data;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
	private File attachement;

	public File getAttachement() {
		return attachement;
	}

	public void setAttachement(File attachement) {
		this.attachement = attachement;
	}

	@Override
	public String toString() {
		return "Attachment [attachement=" + attachement + "]";
	}

}
