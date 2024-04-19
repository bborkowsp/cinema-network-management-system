package org.example.cinemabackend.user.infrastructure.scheme;


//@Entity
//@Data
//@EqualsAndHashCode(callSuper = true)
//public class CinemaManagerSchema extends UserSchema {
//
//    public CinemaManagerSchema() {
//        this.role = Role.CINEMA_MANAGER;
//    }
//
//    public CinemaManagerSchema(CinemaManager cinemaManager) {
//        super(cinemaManager.getFirstName(), cinemaManager.getLastName(), cinemaManager.getEmail(), cinemaManager.getPasswordHash(), cinemaManager.getGender());
//        this.role = Role.CINEMA_MANAGER;
//    }
//
//    public static CinemaManagerSchema fromCinemaManager(CinemaManager cinemaManager) {
//        return new CinemaManagerSchema(cinemaManager);
//    }
//
//    public CinemaManager toCinemaManager() {
//        CinemaManager cinemaManager = new CinemaManager(
//                this.getFirstName(),
//                this.getLastName(),
//                this.getEmail(),
//                this.getPasswordHash(),
//                this.getGender()
//        );
//
//        cinemaManager.setId(this.getId());
//        return cinemaManager;
//    }
//
//}
