package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.cinema.core.domain.ContactDetails;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactDetailsSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String department;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true, fetch = FetchType.LAZY)
    private ContactTypeSchema contactType;

    public static ContactDetailsSchema fromContactDetails(ContactDetails contactDetails) {
        return ContactDetailsSchema.builder()
                .id(contactDetails.getId())
                .department(contactDetails.getDepartment())
                .contactType(ContactTypeSchema.fromContactType(contactDetails.getContactType()))
                .build();
    }

    public ContactDetails toContactDetails() {
        return new ContactDetails(
                this.id,
                this.department,
                this.contactType.toContactType()
        );
    }
}
