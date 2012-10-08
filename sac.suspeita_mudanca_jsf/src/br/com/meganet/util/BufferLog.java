package br.com.meganet.util;


public class BufferLog {

	private StringBuffer sb = new StringBuffer();

	public synchronized BufferLog append(Object obj) {
		sb.append(String.valueOf(obj) + "\n");
		return this;
	}

	public synchronized BufferLog append(String str) {
		sb.append(str + "\n");
		return this;
	}

	public synchronized BufferLog append(StringBuffer sb) {
		sb.append(sb + "\n");
		return this;
	}

	public synchronized BufferLog append(char str[]) {
		sb.append(str);
		sb.append("\n");
		return this;
	}

	public synchronized BufferLog append(char str[], int offset, int len) {
		sb.append(str, offset, len);
		sb.append("\n");
		return this;
	}

	public synchronized BufferLog append(boolean b) {
		sb.append(b);
		sb.append("\n");
		return this;
	}

	public synchronized BufferLog append(char c) {
		sb.append(c);
		sb.append("\n");
		return this;
	}

	public synchronized BufferLog append(int i) {
		sb.append(i);
		sb.append("\n");
		return this;
	}

	public synchronized BufferLog appendCodePoint(int codePoint) {
		sb.appendCodePoint(codePoint);
		sb.append("\n");
		return this;
	}

	public synchronized BufferLog append(long lng) {
		sb.append(lng);
		sb.append("\n");
		return this;
	}

	public String toString() {
		return sb.toString();
	}

}
