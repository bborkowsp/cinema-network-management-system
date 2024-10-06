import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MovieResponse} from "../../dtos/response/movie.response";
import {UpdateMovieRequest} from "../../dtos/request/update-movie.request";
import {ProductionDetailsRequest} from "../../dtos/request/production-details.request";
import {FilmMemberRequest} from "../../dtos/request/film-member.request";
import {CreateMovieRequest} from "../../dtos/request/create-movie.request";
import {getEnumKeyByValue, getEnumValueByKey} from "../../dtos/response/projection-technology";
import {MovieVariantResponse} from "../../dtos/response/movie-variant.response";
import {FormValidatorPatterns} from "../../../_shared/validators/form-validators-patterns";

export class MovieFormBuilder {
  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder
  ) {
    this.form = this.createForm();
  }

  get titleFormGroup() {
    return this.form.get('title') as FormGroup;
  }

  get informationFormGroup() {
    return this.form.get('information') as FormGroup;
  }

  get productionDetailsFormGroup() {
    return this.form.get('productionDetails') as FormGroup;
  }

  get ageRestrictionAndGenresFormGroup() {
    return this.form.get('ageRestrictionAndGenres') as FormGroup;
  }

  get imageAndTrailerFormGroup() {
    return this.form.get('imageAndTrailer') as FormGroup;
  }

  get imageFormGroup() {
    return this.form.get('imageAndTrailer')?.get('image') as FormGroup;
  }

  get movieVariantsFormGroup() {
    return this.form.get('movieVariants') as FormGroup;
  }

  get variants() {
    return this.movieVariantsFormGroup.get('variants') as FormArray;
  }

  fillFormWithMovie(movie: MovieResponse) {
    const variants = this.mapVariantsWhenFillForm(movie.movieVariants);
    variants.forEach(variant => {
      this.variants.push(this.formBuilder.group({
        projectionTechnology: [variant.projectionTechnology],
        language: [variant.language]
      }));
    });
    const posterFileName = this.getPosterFileNameWithoutUUID(movie.poster);
    this.form.setValue({
      title: {
        title: movie.title,
        originalTitle: movie.originalTitle
      },
      information: {
        duration: movie.duration,
        releaseDate: movie.releaseDate,
        description: movie.description
      },
      productionDetails: {
        worldPremiereDate: movie.productionDetails.worldPremiereDate,
        director: `${movie.productionDetails.director.firstName} ${movie.productionDetails.director.lastName}`,
        actors: movie.productionDetails.actors.map(actor => `${actor.firstName} ${actor.lastName}`).join(', '),
        originalLanguages: movie.productionDetails.originalLanguages.join(', '),
        productionCountries: movie.productionDetails.productionCountries.join(', ')
      },
      ageRestrictionAndGenres: {
        ageRestriction: movie.ageRestriction,
        genres: movie.genres
      },
      imageAndTrailer: {
        image: posterFileName,
        trailer: movie.trailer
      },
      movieVariants: {
        projectionTechnology: ' ',
        language: ' ',
        variants: variants
      }
    });
  }

  getUpdateMovieRequestFromForm() {
    return this.getMovieRequestFromForm(UpdateMovieRequest);
  }

  getCreateMovieRequestFromForm() {
    return this.getMovieRequestFromForm(CreateMovieRequest);
  }

  private createForm() {
    return this.formBuilder.group({
      title: this.formBuilder.group({
        title: ['', [Validators.required, Validators.maxLength(255)]],
        originalTitle: ['', [Validators.required, Validators.maxLength(255)]]
      }),
      information: this.formBuilder.group({
        duration: ['', [Validators.required, Validators.pattern(FormValidatorPatterns.ONLY_NUMBER_PATTERN)]],
        releaseDate: ['', [Validators.required]],
        description: ['', [Validators.required]],
      }),
      productionDetails: this.formBuilder.group({
        worldPremiereDate: ['', [Validators.required]],
        director: ['', [Validators.required, Validators.pattern(FormValidatorPatterns.DIRECTOR_AND_ACTORS_PATTERN)]],
        actors: ['', [Validators.required, Validators.pattern(FormValidatorPatterns.DIRECTOR_AND_ACTORS_PATTERN)]],
        originalLanguages: ['', [Validators.required]],
        productionCountries: ['', [Validators.required]],
      }),
      imageAndTrailer: this.formBuilder.group({
        image: ['', [Validators.required]],
        trailer: ['', [Validators.required, Validators.pattern(FormValidatorPatterns.TRAILER_URL_PATTERN)]],
      }),
      ageRestrictionAndGenres: this.formBuilder.group({
        ageRestriction: ['', [Validators.required]],
        genres: ['', [Validators.required]],
      }),
      movieVariants: this.formBuilder.group({
        projectionTechnology: ['',],
        language: ['',],
        variants: this.formBuilder.array([], Validators.required)
      })
    });
  }

  private getMovieRequestFromForm(requestType: any) {
    const formData = new FormData();

    const image = this.imageFormGroup?.value;
    if (image instanceof File) {
      formData.append('image', image);
    }

    const mappedVariants = this.mapVariantsWhenCreatingRequest(this.form.get('movieVariants')?.value.variants);

    const movieRequest = new requestType(
      this.form.get('title')?.value.title,
      this.form.get('title')?.value.originalTitle,
      this.form.get('information')?.value.duration,
      this.form.get('information')?.value.releaseDate,
      this.form.get('information')?.value.description,
      this.createProductionDetailsRequest(),
      this.form.get('ageRestrictionAndGenres')?.value.ageRestriction,
      this.form.get('imageAndTrailer')?.value.trailer,
      this.form.get('ageRestrictionAndGenres')?.value.genres,
      mappedVariants
    );

    formData.append(
      'movieRequest',
      new Blob([JSON.stringify(movieRequest)], {type: 'application/json'})
    );

    return formData;
  }

  private createProductionDetailsRequest(): ProductionDetailsRequest {
    return new ProductionDetailsRequest(
      this.form.get('productionDetails')?.value.worldPremiereDate,
      this.createDirectorRequest(),
      this.createActorsRequests(),
      this.extractLanguages(),
      this.extractProductionCountries()
    )
  }

  private createDirectorRequest(): FilmMemberRequest {
    const directorFullName: string = this.form.get('productionDetails')?.value.director || '';
    const [firstName, ...lastNameArray] = directorFullName.split(' ');
    const lastName = lastNameArray.join(' ');
    return new FilmMemberRequest(firstName, lastName);
  }

  private createActorsRequests(): FilmMemberRequest[] {
    const actorsString: string = this.form.get('productionDetails')?.value.actors || '';
    const actorsArray: string[] = actorsString.split(',');
    return actorsArray.map(actor => {
      const [firstName, ...lastNameArray] = actor.trim().split(' ');
      const lastName = lastNameArray.join(' ');
      return new FilmMemberRequest(firstName, lastName);
    });
  }

  private extractLanguages(): string[] {
    const languagesString: string = this.form.get('productionDetails')?.value.originalLanguages || '';
    return languagesString.split(',');
  }

  private extractProductionCountries(): string[] {
    const countriesString: string = this.form.get('productionDetails')?.value.productionCountries || '';
    return countriesString.split(',');
  }

  private mapVariantsWhenCreatingRequest(variants: any) {
    return variants.map((variant: any) => ({
      projectionTechnology: getEnumKeyByValue(variant.projectionTechnology),
      language: variant.language
    }));
  }

  private mapVariantsWhenFillForm(variants: MovieVariantResponse[]) {
    return variants.map((variant: MovieVariantResponse) => ({
      projectionTechnology: getEnumValueByKey(variant.projectionTechnology),
      language: variant.language
    }));
  }

  private getPosterFileNameWithoutUUID(poster: string) {
    return poster.slice(37);
  }
}
