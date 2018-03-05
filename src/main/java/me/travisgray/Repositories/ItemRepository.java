package me.travisgray.Repositories;

import me.travisgray.Models.Item;
import me.travisgray.Models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ${TravisGray} on 2/28/2018.
 */
public interface ItemRepository extends CrudRepository<Item,Long> {

    Iterable<Item> findAllByItemTypeContainingIgnoreCase(String searchitems);
    Iterable<Item> findAllByUser(User user);
    Iterable<Item> findAllByItemType(String itemtype);
    Iterable<Item> findAllByItemStatus(String itemstatus);


}
