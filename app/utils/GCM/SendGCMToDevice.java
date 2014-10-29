package utils.GCM;

import java.util.List;

/**
 * Created by mint on 6-10-14.
 */
public class SendGCMToDevice {

    private String registrationDeviceId;
    private String authenticationId;
    private List<String> multiRegistrationDevicesId;
    private final String API_KEY = "AIzaSyCM-_FX_DzLKHpVO3lizgLI1VNgn8OmCjw";

    public SendGCMToDevice(){}

    public SendGCMToDevice(String registrationDeviceId, String authenticationId){
        this.registrationDeviceId = registrationDeviceId;
        this.authenticationId = authenticationId;
        GCMContent content = createContent();
        sendNotification(content);
    }

    public SendGCMToDevice(List<String> multiRegistrationDevicesId){
        this.multiRegistrationDevicesId = multiRegistrationDevicesId;
    }


    private GCMContent createContent(){
        GCMContent content = new GCMContent();
        content.addRegId(registrationDeviceId);
        content.createData("Admin", "Thank you for your registration", authenticationId);
        return content;
    }

    private void sendNotification(GCMContent gcmContent){
        SendToGCM.post(API_KEY, gcmContent);
    }








//
//
//
//
    public static void main(String[] args) {
        System.out.println("Sending POST to GCM");

        String apiKey = "AIzaSyCM-_FX_DzLKHpVO3lizgLI1VNgn8OmCjw";
        GCMContent content = createContents();

        SendToGCM.post(apiKey, content);
    }

    public static GCMContent createContents() {

        GCMContent c = new GCMContent();

        c.addRegId("APA91bFBW8ThH8ds2OKOkWrYrWC9Ie5tq4a7gMZhbH7otLyk-ghKpFp7OtYOtUgJfMyUUnDdGnJPqjMV9b67Db5Y34chN-_a7nQSri20jEWg0njKlkRkD66zLDspR2SMc0vqL3YhqDUhh_vVgH8l53kJedGMv02A4NRjk98K5g7hSn7kFHtYXJo");
        c.addRegId("APA91bFzx0L8RKu8XS7ot95SRjsmi1hlkj8PThx7dbPE5SZpp_XkPHVE4ZH7tOaVCYe4S-R19aRVNojbRXRBo_4o2Hx_jdg96Pl2imF1K_HjXsEChq8Os-H2t2fVPTwFxKBhsQGdF7FnN5KyD_McGWF6DzTQdFy4lh6uc0w8PuR5Dpx6CblrdQw");
        c.createData("New Test 7", "This might be a very long message with lots of typo " +
                "the reason why this is so long message is to see how the android device will reach" +
                "to such a long test. Will the text just get cut in the right order or " +
                "will the thing just blow up??????" +
                "the reason why this is so long message is to see how the android device will reach" +
                "to such a long test. Will the text just get cut in the right order or " +
                "will the thing just blow up??????" +
                "the reason why this is so long message is to see how the android device will reach" +
                "to such a long test. Will the text just get cut in the right order or " +
                "will the thing just blow up??????", "10202316981240066");
        return c;
    }






}