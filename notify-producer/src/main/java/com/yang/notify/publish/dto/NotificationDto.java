package com.yang.notify.publish.dto;

import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/5
 **/
//@Data
@Getter
public class NotificationDto {
    private Long receiveUserId;
    private String title;
    private String content;
    private Long sendUserId;
    private String serviceName;
    private String abstractContent;
    private Type type;
    private Priority priority;
    private Date createTime;

    private NotificationDto(Builder builder) {
        this.receiveUserId = builder.receiveUserId;
        this.title = builder.title;
        this.content = builder.content;
        this.sendUserId = builder.sendUserId;
        this.serviceName = Optional.ofNullable(builder.serviceName).orElse("Unknown");
        //this.serviceName = MoreObjects.firstNonNull(builder.serviceName, "Unknown");
        this.abstractContent = Optional.ofNullable(builder.abstractContent).orElse(builder.content.substring(0, 32));
        this.type = Optional.ofNullable(builder.type).orElse(Type.System);
        this.priority = Optional.ofNullable(builder.priority).orElse(Priority.Normal);
        this.createTime = new Date();
    }

    @Setter
    public static class Builder {
        private final Long receiveUserId;
        private final String title;
        private final String content;

        private Long sendUserId;
        private String serviceName;
        private String abstractContent;
        private Type type;
        private Priority priority;

        public Builder(Long receiveUserId, String title, String content) {
            this.receiveUserId = receiveUserId;
            this.title = title;
            this.content = content;
        }

        public NotificationDto build() {
            return new NotificationDto(this);
        }
    }

    public enum Type {
        System, Operation, User
    }

    public enum Priority {
        Normal, Medium, Urgent
    }

}
