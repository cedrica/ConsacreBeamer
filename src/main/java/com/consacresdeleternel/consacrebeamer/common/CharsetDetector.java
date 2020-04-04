package com.consacresdeleternel.consacrebeamer.common;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CharsetDetector {

	public String charset(String value) {
		String[] charsets = new String[] { "big5", "euc-kr", "iso-8859-1", "iso-8859-2", "iso-8859-3", "iso-8859-4",
				"iso-8859-5", "iso-8859-6", "iso-8859-7", "iso-8859-8", "koi8-r", "shift-jis", "utf-8", "windows-1250",
				"windows-1251", "windows-1252", "windows-1253", "windows-1254", "windows-1255", "windows-1256",
				"windows-1257", "windows-1258", "windows-874" };
		String probe = StandardCharsets.UTF_8.name();
		for (String c : charsets) {
			Charset charset = Charset.forName(c);
			if (charset != null) {
				if (value.equals(convert(convert(value, charset.name(), probe), probe, charset.name()))) {
					return c;
				}
			}
		}
		return StandardCharsets.UTF_8.name();
	}

	public String convert(String value, String fromEncoding, String toEncoding) {
		try {
			return new String(value.getBytes(fromEncoding), toEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return toEncoding;
	}

	public String decode(String val) {
		ByteBuffer bb = ByteBuffer.wrap(val.getBytes());
		Charset csets = Charset.forName("UTF-8");
		CharBuffer chb = csets.decode(bb);
		bb.rewind();
		return chb.toString();
	}
}
