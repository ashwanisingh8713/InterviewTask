package com.interview.task.model;

/**
 * Created by ashwanisingh on 24/06/18.
 */
public class TwoColumnBean {

    private ItemBean column1;
    private ItemBean column2;

    public TwoColumnBean(ItemBean column1, ItemBean column2) {
        this.column1 = column1;
        this.column2 = column2;
    }

    public ItemBean getColumn1() {
        return column1;
    }

    public void setColumn1(ItemBean column1) {
        this.column1 = column1;
    }

    public ItemBean getColumn2() {
        return column2;
    }

    public void setColumn2(ItemBean column2) {
        this.column2 = column2;
    }
}
