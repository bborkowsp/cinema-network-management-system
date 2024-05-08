package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.ContactType;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactTypeSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String phoneNumber;

    @Email
    private String email;

    public static ContactTypeSchema fromContactType(ContactType contactType) {
        return ContactTypeSchema.builder()
                .id(contactType.getId())
                .phoneNumber(contactType.getPhoneNumber())
                .email(contactType.getEmail())
                .build();
    }

    public ContactType toContactType() {
        return new ContactType(
                this.id,
                this.phoneNumber,
                this.email
        );
    }
}
