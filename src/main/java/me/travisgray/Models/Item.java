package me.travisgray.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ${TravisGray} on 2/28/2018.
 */

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String image;

    private String itemName;

    private String itemStatus;

    private String itemType;

    @ManyToMany(mappedBy = "items")
    private Set<User> user;

    public Item() {

        this.user = new HashSet<User>();
    }

    public Item(String image, String itemName, String itemType,String itemStatus) {
        this.image = image;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemStatus = itemStatus;
    }


    public void addUser(User u){
        this.user.add(u);
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
