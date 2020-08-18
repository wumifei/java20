package day15ing.framework.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class RegisterExcelInfo {

    @Excel(name="用例编号")
    private int caseId;
    @Excel(name = "用例描述")
    private String name;
    @Excel(name = "url")
    private String url;
    @Excel(name = "请求方式")
    private String type;
    @Excel(name = "参数")
    private String params;
    @Excel(name = "参数类型")
    private String content_type;
    @Excel(name="期望结果")
    private String expectResult;
    @Excel(name="sql")
    private String sql;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getExpectResult() {
        return expectResult;
    }

    public void setExpectResult(String expectResult) {
        this.expectResult = expectResult;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RegisterExcelInfo{");
        sb.append("caseId=").append(caseId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", params='").append(params).append('\'');
        sb.append(", content_type='").append(content_type).append('\'');
        sb.append(", expectResult='").append(expectResult).append('\'');
        sb.append(", sql='").append(sql).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
