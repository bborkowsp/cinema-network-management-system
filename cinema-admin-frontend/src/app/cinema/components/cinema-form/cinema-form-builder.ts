import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CinemaResponse} from "../../dtos/response/cinema.response";
import {CreateAddressRequest} from "../../dtos/request/create-address.request";
import {UserResponse} from "../../../user/dtos/response/user.response";
import {UpdateCinemaRequest} from "../../dtos/request/update-cinema.request";
import {CreateCinemaRequest} from "../../dtos/request/create-cinema.request";
import {FormValidatorPatterns} from "../../../_shared/validators/form-validators-patterns";
import {FormValidatorLengths} from "../../../_shared/validators/form-validators-lengths";

export class CinemaFormBuilder {
  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder
  ) {
    this.form = this.createForm();
  }

  public get stepOneFormGroup() {
    return this.form.get('stepOne') as FormGroup;
  }

  public get imageFormGroup() {
    return this.form.get('stepOne')?.get('aboutCinema')?.get('image') as FormGroup;
  }

  public get stepTwoFormGroup() {
    return this.form.get('stepTwo') as FormGroup;
  }

  public get stepThreeFormGroup() {
    return this.form.get('stepThree') as FormGroup;
  }

  public get stepFourFormGroup() {
    return this.form.get('stepFour') as FormGroup;
  }

  fillFormWithCinema(cinema: CinemaResponse) {
    const imageFileName = this.getImageFileNameWithoutUUID(cinema.image);
    this.form.setValue({
      stepOne: {
        address: {
          streetAndBuildingNumber: cinema.address.streetAndBuildingNumber,
          city: cinema.address.city,
          postalCode: cinema.address.postalCode,
          country: cinema.address.country,
        },
        aboutCinema: {
          name: cinema.name,
          description: cinema.description,
          image: imageFileName,
        }
      },
      stepTwo: {
        screeningRooms: [],
      },
      stepThree: {
        contactDetails: [],
      },
      stepFour: {
        cinemaManager: cinema.cinemaManager
      }
    });
    const screeningRoomsFormArray = this.formBuilder.array(
      cinema.screeningRooms.map(screeningRoom => this.formBuilder.group({
        name: [screeningRoom.name, Validators.required],
        seats: this.formBuilder.array(
          screeningRoom.seats.map(seatRow => this.formBuilder.array(
            seatRow.map(seat => this.formBuilder.group({
              seatRow: [seat.seatRow, Validators.required],
              seatColumn: [seat.seatColumn, Validators.required],
              seatZone: [seat.seatZone, Validators.required],
              seatStatus: [seat.seatStatus, Validators.required],
            }))
          ))
        )
      })));

    this.stepTwoFormGroup.setControl('screeningRooms', screeningRoomsFormArray);

    const contactDetailsFormArray = this.formBuilder.array(
      cinema.contactDetails.map(contactDetail => this.formBuilder.group({
        department: [contactDetail.department, Validators.required],
        contactType: this.formBuilder.group({
          email: [contactDetail.contactType.email, Validators.required],
          phoneNumber: [contactDetail.contactType.phoneNumber, Validators.required],
        })
      }))
    );
    this.stepThreeFormGroup.setControl('contactDetails', contactDetailsFormArray);
  }

  getUpdateCinemaRequestFromForm() {
    return this.getCinemaRequestFromForm(UpdateCinemaRequest);
  }

  getCreateCinemaRequestFromForm() {
    return this.getCinemaRequestFromForm(CreateCinemaRequest);
  }

  private getImageFileNameWithoutUUID(image: string) {
    return image.slice(37);
  }

  private getCinemaRequestFromForm(requestType: any) {
    const formData = new FormData();
    const image = this.imageFormGroup?.value;
    if (image instanceof File) {
      formData.append('image', image);
    }

    const commonFields = this.getCommonRequestFields();
    const cinemaManager = this.getCinemaManagerRequest();
    const cinemaRequest = new requestType(
      commonFields.name,
      commonFields.description,
      commonFields.address,
      commonFields.screeningRooms,
      commonFields.contactDetails,
      cinemaManager
    );

    formData.append(
      'cinemaRequest',
      new Blob([JSON.stringify(cinemaRequest)], {type: 'application/json'})
    );
    return formData;
  }

  private createForm() {
    return this.formBuilder.group({
      stepOne: this.formBuilder.group({
        address: this.formBuilder.group({
          streetAndBuildingNumber: [
            '',
            [
              Validators.required,
              Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
              Validators.pattern(FormValidatorPatterns.STREET_AND_BUILDING_NUMBER_PATTERN),
            ],
          ],
          city: ['', [Validators.required, Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),]],
          postalCode: [
            '',
            [
              Validators.required,
              Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
              Validators.pattern(FormValidatorPatterns.POSTAL_CODE_PATTERN),
            ],
          ],
          country: ['', [Validators.required, Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),],],
        }),
        aboutCinema: this.formBuilder.group({
          name: ['', [Validators.required, Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),]],
          description: ['', [Validators.required, Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH)]],
          image: ['', [Validators.required]],
        }),
      }),
      stepTwo: this.formBuilder.group({
        screeningRooms: this.formBuilder.array([], [Validators.required])
      }),
      stepThree: this.formBuilder.group({
        contactDetails: this.formBuilder.array([], [Validators.required])
      }),
      stepFour: this.formBuilder.group({
        cinemaManager: ['', [Validators.required]],
      }),
    });
  }

  private createAddressRequest(): CreateAddressRequest {
    return new CreateAddressRequest(
      this.stepOneFormGroup.get('address')?.get('streetAndBuildingNumber')?.value,
      this.stepOneFormGroup.get('address')?.get('city')?.value,
      this.stepOneFormGroup.get('address')?.get('postalCode')?.value,
      this.stepOneFormGroup.get('address')?.get('country')?.value,
    );
  }

  private getCommonRequestFields() {
    return {
      name: this.stepOneFormGroup.get('aboutCinema')?.get('name')?.value,
      description: this.stepOneFormGroup.get('aboutCinema')?.get('description')?.value,
      address: this.createAddressRequest(),
      screeningRooms: this.stepTwoFormGroup.get('screeningRooms')?.value,
      contactDetails: this.stepThreeFormGroup.get('contactDetails')?.value,
      cinemaManager: this.stepFourFormGroup.get('cinemaManager')?.value,
    };
  }

  private getCinemaManagerRequest() {
    const cinemaManager = this.stepFourFormGroup.get('cinemaManager')?.value;
    if (cinemaManager) {
      return cinemaManager;
    } else {
      return new UserResponse('', '', '', '');
    }
  }
}

