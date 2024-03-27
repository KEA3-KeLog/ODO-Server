package odo.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInfo {
    private String itemName;
    private double itemPrice;

    public ItemInfo(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

}
  