import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import FormValidatorLengths from "../../../shared/consts/form-validators-lengths";
import FormValidatorPatterns from "../../../shared/consts/form-validators-patterns";
import {CinemaResponse} from "../../dtos/response/cinema.response";
import {CreateCinemaRequest} from "../../dtos/request/create-cinema.request";
import {CreateAddressRequest} from "../../dtos/request/create-address.request";
import {CreateImageRequest} from "../../../movie/dtos/request/create-image.request";
import {UpdateCinemaRequest} from "../../dtos/request/update-cinema.request";
import {UserResponse} from "../../../user/dtos/response/user.response";

export class CinemaFormBuilder {
  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder
  ) {
    this.form = this.createForm();
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
    const image = new CreateImageRequest(cinema.image.name, cinema.image.type, cinema.image.data);
    this.form.setValue({
      stepOne: {
        address: {
          city: cinema.address.city,
          country: cinema.address.country,
          postalCode: cinema.address.postalCode,
          streetAndBuildingNumber: cinema.address.streetAndBuildingNumber,
        },
        aboutCinema: {
          name: cinema.name,
          description: cinema.description,
          image: image,
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
              seatType: [seat.seatType, Validators.required],
            }))
          ))
        ),
        supportedTechnologies: this.formBuilder.array(
          screeningRoom.supportedTechnologies.map(technology => this.formBuilder.group({
            technology: [technology.technology, Validators.required],
            description: [technology.description, Validators.required],
          })))
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


  async getCreateCinemaRequestFromForm(): Promise<CreateCinemaRequest> {
    const imageRequest = await this.createImageRequest();
    const commonFields = this.getCommonRequestFields();
    const cinemaManager = this.getCinemaManagerRequest();

    return new CreateCinemaRequest(
      commonFields.name,
      commonFields.description,
      commonFields.address,
      imageRequest,
      commonFields.screeningRooms,
      commonFields.contactDetails,
      cinemaManager
    );
  }

  async getUpdateCinemaRequestFromForm(): Promise<UpdateCinemaRequest> {
    const imageRequest = await this.createImageRequest();
    const commonFields = this.getCommonRequestFields();
    const cinemaManager = this.getCinemaManagerRequest();

    return new UpdateCinemaRequest(
      commonFields.name,
      commonFields.description,
      commonFields.address,
      imageRequest,
      commonFields.screeningRooms,
      commonFields.contactDetails,
      cinemaManager
    );
  }

  private async createImageRequest(): Promise<CreateImageRequest> {
    const selectedImage = this.imageFormGroup?.value;
    if (selectedImage instanceof CreateImageRequest)
      return selectedImage;

    const name = selectedImage.name;
    const type = selectedImage.type;
    const file = new File([selectedImage], name, {type: type});
    const data = await this.readFileData(file);

    return new CreateImageRequest(name, type, data);
  }

  private readFileData(file: File): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        const dataUrl = reader.result as string;
        const imageData = dataUrl.split(',')[1];
        resolve(imageData);
      };
      reader.onerror = error => reject(error);
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
      return new UserResponse('', '', '');
    }
  }
}

