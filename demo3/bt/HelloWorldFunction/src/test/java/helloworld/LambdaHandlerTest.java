package helloworld;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;

public class LambdaHandlerTest {
    @Test
    void whenTheUsersPathIsInvokedViaLambda_thenShouldReturnAList() throws IOException {
        LambdaHandler lambdaHandler = new LambdaHandler();
        AwsProxyRequest req = new AwsProxyRequestBuilder("/api/v1/users", "GET").build();
        AwsProxyResponse resp = lambdaHandler.handleRequest(req, null);
        Assertions.assertNotNull(resp.getBody());
        Assertions.assertEquals(200, resp.getStatusCode());
    }
}
