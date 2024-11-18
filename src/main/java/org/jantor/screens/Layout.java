package org.jantor.screens;

import greenfoot.World;
import org.jantor.constants.Constants;
import org.jantor.ui.Widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Layout {

    private final int verticalGap;
    private final int horizontalGap;

    private final int usableWidth;

    private final Widget[] widgets;

    public Layout(Widget[] widgets, int verticalGap, int horizontalGap, int padLeft, int padRight, int padTop, int padBottom) {
        this.usableWidth = Screen.width - (padLeft + padRight);

        this.widgets = widgets;

        this.verticalGap = verticalGap;
        this.horizontalGap = horizontalGap;

    }

    public Layout(Widget[] widgets) {
        this(widgets, 50, 50, 0, 0, 0, 0);
    }

    public void addTo(World world) {
        for (int columnIndex = 0; columnIndex < getColumns().size(); columnIndex++) {
            List<Widget> column = getColumns().get(columnIndex);
            for (int rowIndex = 0; rowIndex < column.size(); rowIndex++) {
                world.addObject(column.get(rowIndex), getX(columnIndex), getY(rowIndex));
            }
        }
    }

    private List<List<Widget>> getColumns() {
        int columnCount = 1;
        int columnWidth = Constants.elementWidth + horizontalGap;
        while (columnWidth * columnCount <= usableWidth) {
            columnCount++;
        }
        return splitList(widgets, columnCount);
    }

    private List<List<Widget>> getRows() {
        List<List<Widget>> columns = getColumns();

        List<List<Widget>> rows = new ArrayList<>();
        int maxColumnSize = columns.stream().mapToInt(List::size).max().orElse(0);
        for (int i = 0; i < maxColumnSize; i++) {
            List<Widget> row = new ArrayList<>();
            for (List<Widget> column : columns) {
                if (i < column.size()) {
                    row.add(column.get(i));
                }
            }
            rows.add(row);
        }
        return rows;
    }

    private List<List<Widget>> splitList(Widget[] arr, int partSize) {
        List<List<Widget>> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i += partSize) {
            result.add(Arrays.asList(Arrays.copyOfRange(arr,
                    i, Math.min(i + partSize, arr.length)
            )));
        }
        return result;
    }

    private int getX(int columnIndex) {
        int widgetWidth = Constants.elementWidth + horizontalGap;
        int totalWidth = getColumns().size() * widgetWidth;
        return (int) ((columnIndex + 0.5) * widgetWidth) + (Screen.width - totalWidth) / 2;
    }

    private int getY(int rowIndex) {
        int widgetHeight = Constants.elementWidth + verticalGap;
        int totalHeight = getRows().size() * widgetHeight;
        return (int) ((rowIndex + 0.5) * widgetHeight) + (Screen.height - totalHeight) / 2;
    }


}