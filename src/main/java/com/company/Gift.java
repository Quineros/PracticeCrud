package com.company;

import java.util.ArrayList;
import java.util.List;

public class Gift implements Crud<Gift>{
    private String name;
    private int price;
    private int id;
    private List<Gift> giftList = new ArrayList<>();
    private static int giftsCount;

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        if(!name.isEmpty()) this.name = name;
        else throw new IllegalArgumentException("Invalid gift name");
    }

    public int getPrice() {
        return price;
    }

    public final void setPrice(int price) {
        if(price >= 0) this.price = price;
        else throw new IllegalArgumentException("Invalid gift price");
    }

    @Override
    public void create(Gift gift) {
        this.update(gift);
        giftList.add(gift);
        giftsCount++;
    }

    @Override
    public void update(Gift gift) {
        try {
            setName(gift.name);
            setPrice(gift.price);
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Gift find(Integer id) {
        List<Gift> gifts = giftList;
        for (Gift gift : gifts) {
            if (gift.id == id) return gift;
        }
        System.out.println("Gift not found");
        return null;
    }

    @Override
    public void delete(Integer id) {
        List<Gift> gifts = giftList;
        for (Gift gift : gifts) {
            if (gift.id == id) giftList.remove(gift);
        }
    }

    public Gift(String name, int price) {
        this.setName(name);
        this.setPrice(price);
        this.id = giftsCount;
        create(this);
    }
}
