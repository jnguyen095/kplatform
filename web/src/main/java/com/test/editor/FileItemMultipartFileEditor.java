package com.test.editor;

import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.web.multipart.MultipartFile;

public class FileItemMultipartFileEditor extends ClassEditor {

	public void setValue(Object value) {
		if (value instanceof MultipartFile) {
			MultipartFile multipartFile = (MultipartFile) value;
			super.setValue(multipartFile);
		}
		else if (value instanceof byte[]) {
			super.setValue(value);
		}
		else {
			super.setValue(value != null ? value.toString().getBytes() : null);
		}
	}

	public String getAsText() {
		byte[] value = (byte[]) getValue();
		return (value != null ? new String(value) : "");
	}

}
