package tests.productCatalog;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static apiService.DeleteProduct.deleteProductRequest;
import static apiService.DeleteProduct.getProductRequest;
import static routes.Routes.*;
import static tests.productCatalog.CreatingProduct.createProductTest;
import static utils.TokenManager.getToken;

public class DeleteProductTest {
    String token = getToken("login");
    String targetProductId;
    @BeforeClass
    public void setUp() {
 targetProductId = createProductTest();
    }

    @Test
    public void deleteProductTest (){
        Response response = deleteProductRequest(targetProductId, token);
        Assert.assertEquals(response.jsonPath().getString("message"), "Product deactivated");

        String deletedId = response.jsonPath().getString("data.id");

        Assert.assertEquals(deletedId, targetProductId);

        System.out.println("The API confirmed deletion of ID: " + deletedId);


    }

    @Test
    public void  deleteProductWithNonExistingId (){
        Response response = deleteProductRequest(FAKEPRODUCTID , token);
        Assert.assertEquals(response.jsonPath().getString("message"), "Record not found");


    }
    @Test
    public void verifyIfProductDeletedSuccessfully (){
        Response getResponse = getProductRequest(targetProductId, token);
        Assert.assertEquals(getResponse.jsonPath().getString("message"), "Product not found");

    }
}
