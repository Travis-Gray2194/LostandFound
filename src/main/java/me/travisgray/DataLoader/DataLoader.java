package me.travisgray.DataLoader;

import me.travisgray.Models.Item;
import me.travisgray.Models.Role;
import me.travisgray.Models.User;
import me.travisgray.Repositories.ItemRepository;
import me.travisgray.Repositories.RoleRepository;
import me.travisgray.Repositories.UserRepository;
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


        Item item1 = new Item("https://cnet4.cbsistatic.com/img/Jib97xtRK_GM7pPyfVsMdma9H9E=/fit-in/970x0/2014/10/13/0ea985b4-9f3a-43d0-9a47-21154c864996/samsung-galaxy-note-4-9024.jpg","Note 4","Electronic","" );
        itemRepository.save(item1);

        Item item2 = new Item("https://www.themarysue.com/wp-content/uploads/2016/06/ps3.jpg","PS3","Electronic","" );
        itemRepository.save(item2);

        Item item3 = new Item("https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Xbox-360-Pro-wController.jpg/1200px-Xbox-360-Pro-wController.jpg","Xbox-360","Electronic","" );
        itemRepository.save(item3);

        Item item4 = new Item("https://cdn.macrumors.com/article-new/2014/11/2017ipadpro.jpg","Ipad Pro","Electronic","" );
        itemRepository.save(item4);

        Item item5 = new Item("http://p.globalsources.com/IMAGES/PDT/BIG/550/B1053160550.jpg","Alienware Laptop","Electronic","" );
        itemRepository.save(item5);
    }
}