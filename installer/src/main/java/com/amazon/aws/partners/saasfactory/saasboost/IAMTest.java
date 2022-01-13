package com.amazon.aws.partners.saasfactory.saasboost;

// import software.amazon.awssdk.regions.Region;
// import software.amazon.awssdk.services.iam.IamClient;
// import
// com.amazon.aws.partners.saasfactory.saasboost.clients.AwsClientBuilderFactory;
// import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
// import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;

// public class IAMTest {

// public static void main(String[] args) {
// AwsCredentialsProvider awsCredentialsProvider =
// DefaultCredentialsProvider.builder().build();

// AwsClientBuilderFactory awsClientBuilderFactory =
// AwsClientBuilderFactory.builder()
// .region(Region.CN_NORTH_1)
// .credentialsProvider(awsCredentialsProvider)
// .build();
// IamClient iam = awsClientBuilderFactory.iamBuilder().build();
// System.out.println("test");
// iam.listRoles(request -> request.pathPrefix("/aws-service-role"));
// }
// }

import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.ListRolesResponse;
import software.amazon.awssdk.services.iam.model.ListUsersRequest;
import software.amazon.awssdk.services.iam.model.ListUsersResponse;
import software.amazon.awssdk.services.iam.model.User;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
// snippet-end:[iam.java2.list_users.import]

/**
 * To run this Java V2 code example, ensure that you have setup your development
 * environment, including your credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class IAMTest {
    public static void main(String[] args) {

        Region region = Region.CN_NORTH_1;
        IamClient iam = IamClient.builder()
                .region(region)
                .build();

        ListRolesResponse rolesResponse = iam.listRoles(request -> request.pathPrefix("/aws-service-role"));

        System.out.println(rolesResponse.toString());

        // listAllUsers(iam);
        System.out.println("Done");
        iam.close();
    }

    // snippet-start:[iam.java2.list_users.main]
    public static void listAllUsers(IamClient iam) {

        try {

            boolean done = false;
            String newMarker = null;

            while (!done) {
                ListUsersResponse response;

                if (newMarker == null) {
                    ListUsersRequest request = ListUsersRequest.builder().build();
                    response = iam.listUsers(request);
                } else {
                    ListUsersRequest request = ListUsersRequest.builder()
                            .marker(newMarker).build();
                    response = iam.listUsers(request);
                }

                for (User user : response.users()) {
                    System.out.format("\n Retrieved user %s", user.userName());
                }

                if (!response.isTruncated()) {
                    done = true;
                } else {
                    newMarker = response.marker();
                }
            }
        } catch (IamException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    // snippet-end:[iam.java2.list_users.main]
}