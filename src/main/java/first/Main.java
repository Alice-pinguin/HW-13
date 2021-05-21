package first;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final int CUSTOMER_ID = 4;

    public static void main(String[] args) throws IOException, InterruptedException {
        //create a new user
        User newUser = createdNewUser();
        User createdUser = HttpUtil.sendPost(newUser);
        System.out.println("Task #1");
        System.out.println("1.1) creating a new user :\n" + createdUser);
        System.out.println("-".repeat(10));

        //update user
        User user = HttpUtil.sendGet(CUSTOMER_ID);
        System.out.println("1.2) User before update : " + user);
        user.setName("NewName");
        user.setUsername("NewUserName");
        user = HttpUtil.sendPut(user.getId(), user);
        System.out.println("     User after  update : " + user);
        System.out.println("-".repeat(10));

        //delete user
        int statusResponse = HttpUtil.sendDelete(CUSTOMER_ID);
        System.out.println("1.3) Status of deleting : " + statusResponse);


        //get User's list
        List<User> users = HttpUtil.sendGetUsers();
        System.out.println("1.4) List of users :");
        users.forEach(System.out::println);
        System.out.println("-".repeat(10));

        //get info by UserId
        User specificUser = HttpUtil.sendGetUserById(CUSTOMER_ID);
        System.out.println("1.5) User's Info by ID=" + CUSTOMER_ID + "\n" + specificUser);
        System.out.println("-".repeat(10));

        //get info by UserName
        users = HttpUtil.sendGetUserByName(specificUser.getUsername());
        System.out.println("1.6) User's Info by Name = " + specificUser.getUsername() + "\n" + users);
        System.out.println("-".repeat(10));

        //all comments of last post of specific user
        String  allCommentToLastPostOfUser = HttpUtil.getAllCommentsOfLastPostByUser(user);
        System.out.println("Task #2");
        System.out.println(allCommentToLastPostOfUser);
        System.out.println("-".repeat(15));

        //all opened tasks of specific user
        List<Task> allOpenedTaskOfUser = HttpUtil.getAllOpenedTasksOfUser(user);
        System.out.println("Task #3");
        allOpenedTaskOfUser.forEach(System.out::println);
    }
    private static User createdNewUser() {
        return new User.Builder()
                .name("Alisa")
                .username("Penguin")
                .email("alicejust53@gmail.com")
                .address(new Address.Builder()
                        .street("peremohy")
                        .suite("134")
                        .city("Dnipro")
                        .zipcode("49127")
                        .geo(new Geo.Builder()
                                .lat(- 33.4327)
                                .lng( 55.5221)
                                .build())
                        .build())
                .phone("093-0985992")
                .website("siteteest.com")
                .company(new Company.Builder()
                        .name("Testname")
                        .catchPhrase("TestCatchPhrase")
                        .bs("TestBs")
                        .build())
                .build();
    }
}