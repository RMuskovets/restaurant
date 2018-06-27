package org.roman.restaurant.ui;

import org.roman.restaurant.Table;

@FunctionalInterface
public interface TableChangedListener {

    void tableChanged(Table t);
}
