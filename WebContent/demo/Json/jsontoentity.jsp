<%@ page import="com.fasterxml.jackson.annotation.JsonAutoDetect" %>
<%@ page import="com.fasterxml.jackson.annotation.JsonIgnore" %>
<%@ page import="com.fasterxml.jackson.annotation.JsonProperty" %>
<%@ page import="tgtools.data.DataRow" %>
<%@ page import="tgtools.data.DataTable" %>
<%@ page import="java.sql.Types" %>
<%--
  Created by IntelliJ IDEA.
  User: tian_
  Date: 2017-05-08
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DataTable dt =new DataTable();
    dt.appendColumn("ID_");
    dt.appendColumn("REV_").setColumnType(Types.BIGINT);

    DataRow row1= dt.appendRow();
    row1.setValue("ID_","fdsafdas");
    row1.setValue("REV_",321321L);

    DataRow row2= dt.appendRow();
    row2.setValue("ID_","aaaa2133212");
    row2.setValue("REV_",232132132132132131L);


    MyData[] datas=tgtools.util.JsonParseHelper.parseToObject(dt,MyData[].class,false);
    out.println(String.valueOf(datas.length));
    out.println("<br/>");
    out.println(datas[0].getID());
    out.println("<br/>");
    out.println(String.valueOf(datas[0].getRev()));
    out.println("<br/>");
    String json= tgtools.util.JsonParseHelper.parseToJson(datas,false);
    out.println(json);
    out.println("<br/>");


    MyDataa[] dataas=tgtools.util.JsonParseHelper.parseToObject(dt,MyDataa[].class,false);
    out.println("dataas:"+dataas[0].getID());
    out.println("<br/>");
    out.println("dataas:"+String.valueOf(dataas[0].getRev()));
    out.println("<br/>");
%>
<%!
    //使用getter setter json转换
public static class MyData{
private String m_ID;
    private Long m_Rev;
    @JsonProperty(value="ID_")
    public String getID() {
        return m_ID;
    }
    @JsonProperty(value="ID_")
    public void setID(String p_ID) {
        m_ID = p_ID;
    }
    @JsonProperty(value="REV_")
    public Long getRev() {
        return m_Rev;
    }
    @JsonProperty(value="REV_")
    public void setRev(Long p_Rev) {
        m_Rev = p_Rev;
    }
}
    //不使用getter setter json转换
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE,setterVisibility = JsonAutoDetect.Visibility.NONE)
    public static class MyDataa{
        private String ID_;
        private Long REV_;
        @JsonIgnore
        private String m_Type;
        public String getID() {
            return ID_;
        }
        public void setID(String p_ID) {
            ID_ = p_ID;
        }
        public Long getRev() {
            return REV_;
        }
        public void setRev(Long p_Rev) {
            REV_ = p_Rev;
        }

        public String getType() {
            return m_Type;
        }

        public void setType(String p_Type) {
            m_Type = p_Type;
        }
    }
%>