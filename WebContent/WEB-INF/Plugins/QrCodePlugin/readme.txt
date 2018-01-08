byte[] EncodeString(String content);
void EncodeOutputStream(String content,OutputStream pOutputStream);
String DecodeStream(InputStream pInputStream);
String DecodeFile(File pFile);


如：
byte[] res=plugin.execute("EncodeString",content);


