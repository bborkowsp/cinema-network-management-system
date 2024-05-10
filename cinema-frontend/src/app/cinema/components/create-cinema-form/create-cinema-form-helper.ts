import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import FormValidatorPatterns from "../../../shared/consts/form-validators-patterns";
import FormValidatorLengths from "../../../shared/consts/form-validators-lengths";
import {CreateCinemaRequest} from "../../dtos/request/create-cinema.request";
import {AddressRequest} from "../../dtos/request/address.request";
import {CreateImageRequest} from "../../../movie/dtos/request/create-image.request";

export class CreateCinemaFormHelper {

  public readonly form: FormGroup;

  constructor(private readonly formBuilder: FormBuilder) {
    this.form = this.createForm();
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

  async createCinemaRequestFromForm(): Promise<CreateCinemaRequest> {
    const selectedImage: File = this.imageFormGroup?.value;

    const name = selectedImage.name;
    const type = selectedImage.type;
    let imageData = '';

    const imageDataPromise = new Promise<string>((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(selectedImage);
      reader.onload = () => {
        const dataUrl = reader.result as string;
        imageData = dataUrl.split(',')[1];
        resolve(imageData);
      };
      reader.onerror = error => reject(error);
    });

    try {
      await imageDataPromise;
    } catch (error) {
    }

    return new CreateCinemaRequest(
      this.stepOneFormGroup.get('aboutCinema')?.get('name')?.value,
      this.stepOneFormGroup.get('aboutCinema')?.get('description')?.value,
      new AddressRequest(
        this.stepOneFormGroup.get('address')?.get('city')?.value,
        this.stepOneFormGroup.get('address')?.get('country')?.value,
        this.stepOneFormGroup.get('address')?.get('postalCode')?.value,
        this.stepOneFormGroup.get('address')?.get('streetAndBuildingNumber')?.value,
      ),
      new CreateImageRequest(name, type, imageData),
      this.stepTwoFormGroup.get('screeningRooms')?.value,
      this.stepThreeFormGroup.get('contactDetails')?.value,
    );
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
    });
  }
}
