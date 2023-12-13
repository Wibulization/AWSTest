package helloworld;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(DemoApplication.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Unable to load spring boot application", ex);
        }
    }

    public LambdaHandler() {

    }

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest input, Context context) {

        return handler.proxy(input, context);
    }

    public static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> getHandler() {
        return handler;
    }

}
