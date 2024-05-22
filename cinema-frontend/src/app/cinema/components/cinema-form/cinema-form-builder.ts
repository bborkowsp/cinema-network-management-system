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
          country: [
            '',
            [
              Validators.required,
              Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
            ],
          ],
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
          name: [
            '',
            [
              Validators.required,
              Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
            ]
          ],
          description: [
            '',
            [
              Validators.required,
              Validators.maxLength(FormValidatorLengths.DEFAULT_MAX_INPUT_LENGTH),
            ]
          ],
          image: ['', [Validators.required]],
        }),
      }),
      stepTwo: this.formBuilder.group({
        screeningRooms: ['', [Validators.required]],
      }),
      stepThree: this.formBuilder.group({
        contactDetails: ['', [Validators.required]],
      }),
      stepFour: this.formBuilder.group({
        cinemaManager: ['', [Validators.required]],
      }),
    });
  }

  public get stepOneFormGroup() {
    return this.form.get('stepOne') as FormGroup;
  }

  public get aboutCinemaFormGroup() {
    return this.form.get('stepOne')?.get('aboutCinema') as FormGroup;
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
          contactDetails: cinema.contactDetails,
        },
        stepFour: {
          cinemaManager: ['', [Validators.required]],
        }
      }
    )
  }

  getCreateCinemaRequestFromForm() {
    return new CreateCinemaRequest(
      this.stepOneFormGroup.get('aboutCinema')?.get('name')?.value,
      this.stepOneFormGroup.get('aboutCinema')?.get('description')?.value,
      new CreateAddressRequest(
        this.stepOneFormGroup.get('address')?.get('city')?.value,
        this.stepOneFormGroup.get('address')?.get('country')?.value,
        this.stepOneFormGroup.get('address')?.get('postalCode')?.value,
        this.stepOneFormGroup.get('address')?.get('streetAndBuildingNumber')?.value,
      ),
      new CreateImageRequest(
        this.imageFormGroup?.get('name')?.value,
        this.imageFormGroup?.get('type')?.value,
        this.imageFormGroup?.get('data')?.value,
      ),
      this.stepTwoFormGroup.get('screeningRooms')?.value,
      this.stepThreeFormGroup.get('contactDetails')?.value,
      this.stepFourFormGroup.get('cinemaManager')?.value,
    );
  }

  getUpdateCinemaRequestFromForm() {
    return new UpdateCinemaRequest(
      this.stepOneFormGroup.get('aboutCinema')?.get('name')?.value,
      this.stepOneFormGroup.get('aboutCinema')?.get('description')?.value,
      new CreateAddressRequest(
        this.stepOneFormGroup.get('address')?.get('city')?.value,
        this.stepOneFormGroup.get('address')?.get('country')?.value,
        this.stepOneFormGroup.get('address')?.get('postalCode')?.value,
        this.stepOneFormGroup.get('address')?.get('streetAndBuildingNumber')?.value,
      ),
      new CreateImageRequest(
        this.imageFormGroup?.get('name')?.value,
        this.imageFormGroup?.get('type')?.value,
        this.imageFormGroup?.get('data')?.value,
      ),
      this.stepTwoFormGroup.get('screeningRooms')?.value,
      this.stepThreeFormGroup.get('contactDetails')?.value,
      this.stepFourFormGroup.get('cinemaManager')?.value,
    );
  }
}
