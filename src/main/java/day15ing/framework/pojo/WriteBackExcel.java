package day15ing.framework.pojo;

public class WriteBackExcel {
    private int sheetIndex;
    private int rowNum;
    private int cellNum;
    private String content;

    public WriteBackExcel() {
    }

    public WriteBackExcel(int sheetIndex, int rowNum, int cellNum, String content) {
        this.sheetIndex = sheetIndex;
        this.rowNum = rowNum;
        this.cellNum = cellNum;
        this.content = content;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WriteBackExcel{");
        sb.append("sheetIndex=").append(sheetIndex);
        sb.append(", rowNum=").append(rowNum);
        sb.append(", cellNum=").append(cellNum);
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
