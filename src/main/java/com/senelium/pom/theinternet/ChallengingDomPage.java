package com.senelium.pom.theinternet;

import com.senelium.assertion.SeAssert;
import com.senelium.element.Table;

public class ChallengingDomPage {
    private final Table table;

    public ChallengingDomPage() {
        table = new Table();
    }

    public void shouldHeaderDisplay(String name) {
        SeAssert.expect(table.getHeader(name))
                .toBeVisible(String.format("Table header [%s] is not visible", name));
    }

    public void shouldCellDisplayUnderHeader(String header, String cell) {
        SeAssert.expect(table.getCellOfHeader(header, cell))
                .toBeVisible(String.format("Cell [%s] is not visible under header [%s]", cell, header));
    }
}
