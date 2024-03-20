package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.ContactType;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;

@Data
@Entity
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactTypeSchema extends AbstractEntitySchema<Long> {

    @Column(length = 15)
    private String phoneNumber;

    @Email
    private String email;

    public static ContactTypeSchema fromContactType(ContactType contactType) {
        return ContactTypeSchema.builder()
                .phoneNumber(contactType.getPhoneNumber())
                .email(contactType.getEmail())
                .build();
    }

    public ContactType toContactType() {
        return new ContactType(
                this.phoneNumber,
                this.email
        );
    }
}
