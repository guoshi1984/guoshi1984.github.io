/* LeetCode 157
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 character 
 * left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * */

public class ReadBuffer {
	public static readNcharacters(char[] buf, int n) {
		int index = 0;
		char[] buf4;
		while(index < n) {
			int length = read4(buf4);
			for (int i = 0; i< length; i++) {
				buf[index++] = buf4[i];
			}
		}
	}

}
