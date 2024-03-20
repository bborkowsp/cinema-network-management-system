package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.ContactDetails;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;

@Data
@Entity
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactDetailsSchema extends AbstractEntitySchema<Long> {

    @Column(nullable = false, length = 100)
    private String department;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private ContactTypeSchema contactType;

    public static ContactDetailsSchema fromContactDetails(ContactDetails contactDetails) {
        return ContactDetailsSchema.builder()
                .department(contactDetails.getDepartment())
                .contactType(ContactTypeSchema.fromContactType(contactDetails.getContactType()))
                .build();
    }

    public ContactDetails toContactDetails() {
        return new ContactDetails(
                this.department,
                this.contactType.toContactType()
        );
    }
}
