package dev.imran.user_authentication.Models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name="user_token")
@Getter
@Setter
public class Token extends BaseModel {

    private String value;
    private Date expiryDate;
    private boolean isDeleted;

    @ManyToOne
    private User user;
}
