package endpoint.user;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.User;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    // Method created for getting URL from properties file
    public static ResourceBundle getURL() {
        ResourceBundle routes = ResourceBundle.getBundle("routes");  // it will load the properties file
        return routes;
    }

   // Post Method
    public static Response createUser(User payload) { // import userpojo

        String post_url = getURL().getString("post_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when().post(post_url);

        return response;
    }

    // Get Method
    public static Response readUser(String username) {
        String get_url = getURL().getString("get_url");
        Response response = given()
                .pathParam("username", username)


                .when().get(get_url);

        return response;
    }

    // Put Method
    public static Response updateUser(User payload, String username) {
        String update_url = getURL().getString("update_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username", username)

                .when().put(update_url);

        return response;
    }

    // Delete Method
    public static Response deleteUser(String username) {
        String delete_url = getURL().getString("delete_url");
        Response response = given()
                .pathParam("username", username)


                .when().delete(delete_url);

        return response;
    }

}
