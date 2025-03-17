package com.meindonsa.toolbox.view;

import com.meindonsa.toolbox.utils.Functions;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    @NotBlank
    private String to;
    private String from;

    @NotBlank private String subject;
    private String content;
    private List<Image> images;
    private List<File> attachments;

    @NotBlank
    private String type;

    private Map<String, Object> infos;

    private String uid;
    private int retry;

    public static Email generateEmail(String email, String type, Map<String, Object> infos) {
        final String subject = (String) infos.get("subject");
        return Email.builder()
                .retry(0)
                .infos(infos)
                .to(email)
                .uid(Functions.generateUniqueIdentifier(16))
                .type(type)
                .subject(subject)
                .build();
    }

    public static Email generateEmail(
            String email, String type, Map<String, Object> infos, List<File> attachments) {
        Email generatedEmail = generateEmail(email, type, infos);
        generatedEmail.setAttachments(attachments);
        return generatedEmail;
    }
}
