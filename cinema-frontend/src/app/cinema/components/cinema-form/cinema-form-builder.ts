import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import FormValidatorLengths from "../../../shared/consts/form-validators-lengths";
import FormValidatorPatterns from "../../../shared/consts/form-validators-patterns";
import {CinemaResponse} from "../../dtos/response/cinema.response";
import {CreateCinemaRequest} from "../../dtos/request/create-cinema.request";
import {CreateAddressRequest} from "../../dtos/request/create-address.request";
import {CreateImageRequest} from "../../../movie/dtos/request/create-image.request";
import {UpdateCinemaRequest} from "../../dtos/request/update-cinema.request";

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
          city: ['', [Validators.required, Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),],],
          country: ['', [Validators.required, Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),],],
          postalCode: [
            '',
            [
              Validators.required,
              Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
              Validators.pattern(FormValidatorPatterns.POSTAL_CODE),
            ],
          ],
          streetAndBuildingNumber: [
            '',
            [
              Validators.required,
              Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
              Validators.pattern(FormValidatorPatterns.STREET_AND_BUILDING_NUMBER),
            ],
          ],
        }),
        aboutCinema: this.formBuilder.group({
          name: ['', [Validators.required, Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),]],
          description: ['', [Validators.required, Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH)]],
          image: ['', [Validators.required]],
        }),
      }),
      stepTwo: this.formBuilder.group({
        screeningRooms: ['', [Validators.required]],
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
          image: cinema.image,
        }
      },
      stepTwo: {
        screeningRooms: cinema.screeningRooms,
      },
      stepThree: {
        contactDetails: [],
      },
      stepFour: {
        cinemaManager: cinema.cinemaManager
      }
    });
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

    console.log("ddddd")
  }


  async getCreateCinemaRequestFromForm(): Promise<CreateCinemaRequest> {
    const imageRequest = await this.createImageRequest();
    const commonFields = this.getCommonRequestFields();

    return new CreateCinemaRequest(
      commonFields.name,
      commonFields.description,
      commonFields.address,
      imageRequest,
      commonFields.screeningRooms,
      commonFields.contactDetails,
      commonFields.cinemaManager,
    );
  }

  async getUpdateCinemaRequestFromForm(): Promise<UpdateCinemaRequest> {
    const imageRequest = await this.createImageRequest();
    const commonFields = this.getCommonRequestFields();

    return new UpdateCinemaRequest(
      commonFields.name,
      commonFields.description,
      commonFields.address,
      imageRequest,
      commonFields.screeningRooms,
      commonFields.contactDetails,
      commonFields.cinemaManager,
    );
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

  private async createImageRequest(): Promise<CreateImageRequest> {
    const selectedImage: File = this.imageFormGroup?.value;
    const name = selectedImage.name;
    const type = selectedImage.type;
    const data = await this.readFileData(selectedImage);

    return new CreateImageRequest(name, type, data);
  }

  private createAddressRequest(): CreateAddressRequest {
    return new CreateAddressRequest(
      this.stepOneFormGroup.get('address')?.get('city')?.value,
      this.stepOneFormGroup.get('address')?.get('country')?.value,
      this.stepOneFormGroup.get('address')?.get('postalCode')?.value,
      this.stepOneFormGroup.get('address')?.get('streetAndBuildingNumber')?.value,
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
}

