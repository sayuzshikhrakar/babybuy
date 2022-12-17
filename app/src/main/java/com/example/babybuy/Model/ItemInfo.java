package com.example.babybuy.Model;

public class ItemInfo {
    private String itemId;
    private String itemName;
    private String description;
    private String userId;

    public String getItemId() {
        return itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public ItemInfo(String itemId, String itemName,String description,String userId)
    {
        this.itemId=itemId;
        this.itemName=itemName;
        this.description=description;
        this.userId=userId;
    }

    public ItemInfo(){}
}
