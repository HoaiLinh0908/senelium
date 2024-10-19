package com.senelium.element;

import org.openqa.selenium.By;

public class Table {
    private final Element table;
    private final Element header;

    public Table() {
        this(Element.byTag("table"));
    }

    public Table(Element table) {
        this.table = table;
        this.header = this.table.getChild(By.tagName("th"));
    }

    public Element getHeaders() {
        return this.header;
    }

    public Element getHeader(int index) {
        return this.table.getChild(By.cssSelector("th:nth-of-type(" + index + ")"));
    }

    public Element getHeader(String name) {
        return this.table.getChild(By.xpath("//th[text()=\"" + name + "\"]"));
    }

    public Element getCellsOfHeader(String headerName) {
        int headerIndex = this.getHeaderIndex(headerName);
        return this.table.getChild(By.cssSelector("td:nth-of-type(" + headerIndex + ")"));
    }

    public Element getCellOfHeader(int headerIndex, int rowIndex) {
        return this.table.getChild(By.cssSelector("tr:nth-of-type(" + rowIndex + ") > td:nth-of-type(" + headerIndex + ")"));
    }

    public Element getCellOfHeader(String headerName, int rowIndex) {
        int headerIndex = this.getHeaderIndex(headerName);
        return this.getCellOfHeader(headerIndex, rowIndex);
    }

    public Element getCellOfHeader(String headerName, String rowValue) {
        int headerIndex = this.getHeaderIndex(headerName);
        return this.table.getChild(By.xpath(String.format("//td[%s][text()=\"%s\"]", headerIndex, rowValue)));
    }

    private int getHeaderIndex(String name) {
        return Integer.parseInt(this.getHeader(name).getProperty("cellIndex")) + 1;
    }
}
