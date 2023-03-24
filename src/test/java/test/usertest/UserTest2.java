package test.usertest;

import com.github.javafaker.Faker;
import endpoint.user.UserEndPoints2;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.User;

public class UserTest2 {
    Faker faker;
    User userPayload;
public Logger logger;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();
        //fake data generator
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        // logs
logger= LogManager.getLogger(this.getClass());



    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("****************Create User******************");
        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("****************User Is Created******************");

    }

    @Test(priority = 2)
    public void testGetUserByName() {
        logger.info("****************Reading User Info******************");
        Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("****************User Info Displayed******************");
    }


    @Test(priority = 3)
    public void testUpdateUserByName() {
        logger.info("****************Updating User******************");
        // update data
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());

        Response response = UserEndPoints2.updateUser(userPayload, this.userPayload.getUsername());
        response.then().log().all(); // response.then().log().all(); it is called as chai assertion it is comes along with rest assured
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("****************User Is Updated******************");
        // Checking data after update
        Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        logger.info("****************Deleting User******************");
        Response response = UserEndPoints2.deleteUser(userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("****************User Is Deleted******************");

    }


}
