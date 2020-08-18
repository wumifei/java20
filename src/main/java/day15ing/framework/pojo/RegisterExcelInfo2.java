package day15ing.framework.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class RegisterExcelInfo2 {

    @Excel(name = "CaseId")
    private int id;
    @Excel(name = "Name")
    private String name;
    @Excel(name = "Url")
    private String url;
    @Excel(name = "Type")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RegisterExcelInfo2{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
