package com.team11.charityserver.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.team11.charityserver.model.Media;
import com.team11.charityserver.model.Messagevo;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class MessageService {
    private Map<String, MessageAttributeValue> smsAttributes;

    public Map<String, MessageAttributeValue> getDefaultSMSAttributes() {
        if (smsAttributes == null) {
            smsAttributes = new HashMap<>();
            smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                    .withStringValue("CharityCenter")
                    .withDataType("String"));
            smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
                    .withStringValue("0.05")
                    .withDataType("Number"));
            smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                    .withStringValue("Transactional")
                    .withDataType("String"));
        }
        return smsAttributes;
    }

    public PublishResult sendSMSMessage(String phoneNumber, String text) {
        return sendSMSMessage(phoneNumber, text, getDefaultSMSAttributes());
    }

    public PublishResult sendSMSMessage(String phoneNumber, String text, Map<String, MessageAttributeValue> smsAttributes) {
        final AWSCredentials awsCredentials = new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return "AKIA6K57SA7NE3VWS2NF"; // 带有发短信权限的 IAM 的 ACCESS_KEY
            }

            @Override
            public String getAWSSecretKey() {
                return "uLb8m+JkufP6ATGlS/ScnfzTSA56qA+lBGCaPNOD"; // 带有发短信权限的 IAM 的 SECRET_KEY
            }
        };
        AWSCredentialsProvider provider = new AWSCredentialsProvider() {
            @Override
            public AWSCredentials getCredentials() {
                return awsCredentials;
            }

            @Override
            public void refresh() {
            }
        };
        AmazonSNS amazonSNS = null;
        try {
            amazonSNS = AmazonSNSClientBuilder.standard().withCredentials(provider).withRegion("eu-west-1").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amazonSNS.publish(
                new PublishRequest()
                        .withMessage(text)
                        .withPhoneNumber(phoneNumber)
                        .withMessageAttributes(smsAttributes)
        );
    }

    public void sendEmailBySmtp(Messagevo emailSentRecord) throws Exception {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        String smtpUserName = "AKIA6K57SA7NMLQZDL7D"; // 带有权限的 AWS 帐号
        String smtpUserPassword = "BG73Fxg3HM7byD6YQs1UCANrhidx+OfpCZDJxMkHNY6f"; // 带有权限的 AWS 密码
        msg.setFrom(new InternetAddress("zzhou53@sheffield.ac.uk")); // 发送的 email 帐号
        msg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(emailSentRecord.getToMailAddress()));
        msg.setSubject(emailSentRecord.getSubject());
        msg.setContent(emailSentRecord.getContent(), "text/html");
        Transport transport = session.getTransport();
        try {
            transport.connect("email-smtp." + "eu-west-1" + ".amazonaws.com",
                    smtpUserName, smtpUserPassword);
            transport.sendMessage(msg, msg.getAllRecipients());
            //System.out.println("success post");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        } finally {
            transport.close();
        }
        //System.out.println("enter end");
    }

    public void sendMessage(String phoneNumber, String text) {
        PublishResult publishResult = this.sendSMSMessage(phoneNumber, text);
        System.out.println(publishResult);
    }

    public void sendEmail(Messagevo messagevo) throws Exception {
        this.sendEmailBySmtp(messagevo);
    }

    public void sendTwitter(Media media) {

    }
}
