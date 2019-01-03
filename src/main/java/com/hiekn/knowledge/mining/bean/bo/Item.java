package com.hiekn.knowledge.mining.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

    private String label;

    private String value;

    public static Item of(String label, String value) {
        return new Item(label, value);
    }
}
