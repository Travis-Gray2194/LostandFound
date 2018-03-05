package me.travisgray.DataLoader;

import me.travisgray.Models.Item;
import me.travisgray.Models.Role;
import me.travisgray.Models.User;
import me.travisgray.Repositories.ItemRepository;
import me.travisgray.Repositories.RoleRepository;
import me.travisgray.Repositories.UserRepository;
import me.travisgray.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data...");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");


        // Add user roles
        User user1 = new User("bob@burger.com", "password", "Bobby", "Burger", true, "bob");
        user1.setRoles(Arrays.asList(userRole));
        userRepository.save(user1);

        User user2 = new User("jane@virgin.com", "password", "Jane", "Virgin", true, "jane");
        user2.setRoles(Arrays.asList(userRole));
        userRepository.save(user2);

        // Add admin roles
        User user3 = new User("admin@secure.com", "password", "Admin", "User", true, "admin");
        user3.setRoles(Arrays.asList(adminRole));
        userRepository.save(user3);

        User user4 = new User("clark@kent.com", "password", "Clark", "Kent", true, "clark");
        user4.setRoles(Arrays.asList(userRole, adminRole));
        userRepository.save(user4);


        Item item1 = new Item("https://cnet4.cbsistatic.com/img/Jib97xtRK_GM7pPyfVsMdma9H9E=/fit-in/970x0/2014/10/13/0ea985b4-9f3a-43d0-9a47-21154c864996/samsung-galaxy-note-4-9024.jpg","Note 4","Missing","","Electronic" );
        itemRepository.save(item1);

        Item item2 = new Item("https://www.themarysue.com/wp-content/uploads/2016/06/ps3.jpg","PS3","Missing","","Electronic");
        itemRepository.save(item2);

        Item item3 = new Item("https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Xbox-360-Pro-wController.jpg/1200px-Xbox-360-Pro-wController.jpg","Xbox-360","Missing","","Electronic" );
        itemRepository.save(item3);

        Item item4 = new Item("https://cdn.macrumors.com/article-new/2014/11/2017ipadpro.jpg","Ipad Pro","Missing","","Electronic");
        itemRepository.save(item4);

        Item item5 = new Item("http://p.globalsources.com/IMAGES/PDT/BIG/550/B1053160550.jpg","Alienware Laptop","Missing","","Electronic" );
        itemRepository.save(item5);

        Item item6 = new Item("https://s7d2.scene7.com/is/image/PetSmart/SV0401_CATEGORY_HERO-Dog-Dog-20160818?$SV0401$","Yorkie","Missing","","Pets" );
        itemRepository.save(item6);


        Item item8 = new Item("https://upload.wikimedia.org/wikipedia/en/thumb/b/bc/Garfield_the_Cat.svg/1200px-Garfield_the_Cat.svg.png","Garfield","Missing","","Pets"  );
        itemRepository.save(item8);

        Item item9 = new Item("https://cdn.macrumors.com/article-new/2014/11/2017ipadpro.jpg","Spark","Missing","","Pets" );
        itemRepository.save(item9);

        Item item10 = new Item("https://i.ytimg.com/vi/SfLV8hD7zX4/maxresdefault.jpg","Fluffy","Missing" ,"","Pets" );
        itemRepository.save(item10);

        // Add user roles
//        User user = new User("bob@burger.com", "password", "Bobby", "Burger", true, "bob");
//        user.setRoles(Arrays.asList(userRole));
//        userRepository.save(user);

//        User user = new User("jane@virgin.com", "password", "Jane", "Virgin", true, "jane");
//        ;
//        Item item = new Item("https://cnet4.cbsistatic.com/img/Jib97xtRK_GM7pPyfVsMdma9H9E=/fit-in/970x0/2014/10/13/0ea985b4-9f3a-43d0-9a47-21154c864996/samsung-galaxy-note-4-9024.jpg","Note 4","Electronic","" );
//        itemRepository.save(item);
//        user.addItem(item);
//        userRepository.save(user);

//        // Add admin roles
//        user = new User("admin@secure.com", "password", "Admin", "User", true, "admin");
//        user.setRoles(Arrays.asList(adminRole));
//        item = new Item("https://www.themarysue.com/wp-content/uploads/2016/06/ps3.jpg","PS3","Electronic","" );
//        itemRepository.save(item);
//        user.addItem(item);
//        userRepository.save(user);
//
//        user = new User("clark@kent.com", "password", "Clark", "Kent", true, "clark");
//        user.setRoles(Arrays.asList(userRole, adminRole));
//        item = new Item("https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Xbox-360-Pro-wController.jpg/1200px-Xbox-360-Pro-wController.jpg","Xbox-360","Electronic","" );
//        itemRepository.save(item);
//        user.addItem(item);
//        userRepository.save(user);
//
//
//
//        Item item4 = new Item("https://cdn.macrumors.com/article-new/2014/11/2017ipadpro.jpg","Ipad Pro","Electronic","" );
//        itemRepository.save(item4);
//
//        Item item5 = new Item("http://p.globalsources.com/IMAGES/PDT/BIG/550/B1053160550.jpg","Alienware Laptop","Electronic","" );
//        itemRepository.save(item5);



    }
}