import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MovieResponse} from "../../dtos/response/movie.response";
import {UpdateMovieRequest} from "../../dtos/request/update-movie.request";
import {ProductionDetailsRequest} from "../../dtos/request/production-details.request";
import {FilmMemberRequest} from "../../dtos/request/film-member.request";
import {SubtitleAndSoundOptionsRequest} from "../../dtos/request/subtitle-and-sound-options.request";
import {
  ProjectionTechnologyResponse
} from "../../../projection-technology/dtos/response/projection-technology.response";
import {CreateMovieRequest} from "../../dtos/request/create-movie.request";
import FormValidatorPatterns from "../../../_shared/validators/form-validators-patterns";

export class MovieFormBuilder {
  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder
  ) {
    this.form = this.createForm();
  }

  public get titleFormGroup() {
    return this.form.get('title') as FormGroup;
  }

  public get informationFormGroup() {
    return this.form.get('information') as FormGroup;
  }

  public get productionDetailsFormGroup() {
    return this.form.get('productionDetails') as FormGroup;
  }

  public get projectionDetailsFormGroup() {
    return this.form.get('projectionDetails') as FormGroup;
  }

  public get ageRestrictionAndGenresFormGroup() {
    return this.form.get('ageRestrictionAndGenres') as FormGroup;
  }

  public get imageAndTrailerFormGroup() {
    return this.form.get('imageAndTrailer') as FormGroup;
  }

  public get imageFormGroup() {
    return this.form.get('imageAndTrailer')?.get('image') as FormGroup;
  }

  createForm(): FormGroup {
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
      projectionDetails: this.formBuilder.group({
        subtitlesAndSoundOptions: ['', [Validators.required]],
        projectionTechnologies: ['', [Validators.required]],
      }),
    });
  }

  fillFormWithMovie(movie: MovieResponse) {
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
      projectionDetails: {
        projectionTechnologies: movie.projectionTechnologies.map(technology => technology.technology),
        subtitlesAndSoundOptions: movie.subtitleAndSoundOptions.subtitles ? 'Subtitles'
          : movie.subtitleAndSoundOptions.dubbing ? 'Dubbing'
            : movie.subtitleAndSoundOptions.voiceOver ? 'Voice Over' : 'Original Language'
      },
      ageRestrictionAndGenres: {
        ageRestriction: movie.ageRestriction,
        genres: movie.genres
      },
      imageAndTrailer: {
        image: movie.poster,
        trailer: movie.trailer
      }
    });
  }

  getUpdateMovieRequestFromForm() {
    return this.getMovieRequestFromForm(UpdateMovieRequest);
  }

  getCreateMovieRequestFromForm() {
    return this.getMovieRequestFromForm(CreateMovieRequest);
  }

  private getMovieRequestFromForm(requestType: any) {
    const formData = new FormData();
    const image = this.imageFormGroup?.value;
    if (image instanceof File) {
      formData.append('image', image);
    }

    const movieRequest = new requestType(
      this.form.get('title')?.value.title,
      this.form.get('title')?.value.originalTitle,
      this.form.get('information')?.value.duration,
      this.form.get('information')?.value.releaseDate,
      this.form.get('information')?.value.description,
      this.createProductionDetailsRequest(),
      this.createSubtitleAndSoundOptions(),
      this.form.get('ageRestrictionAndGenres')?.value.ageRestriction,
      this.form.get('imageAndTrailer')?.value.trailer,
      this.form.get('ageRestrictionAndGenres')?.value.genres,
      this.createProjectionTechnologies()
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

  private createSubtitleAndSoundOptions(): SubtitleAndSoundOptionsRequest {
    const subtitlesAndSoundOptions = this.form.get('projectionDetails')?.value.subtitlesAndSoundOptions;
    return new SubtitleAndSoundOptionsRequest(
      subtitlesAndSoundOptions === 'Subtitles',
      subtitlesAndSoundOptions === 'Dubbing',
      subtitlesAndSoundOptions === 'Voice Over',
      subtitlesAndSoundOptions === 'Original Language'
    );
  }

  private createProjectionTechnologies(): ProjectionTechnologyResponse[] {
    const selectedTechnologies = this.form.get('projectionDetails')?.value.projectionTechnologies || [];
    return selectedTechnologies.map((technology: string) => new ProjectionTechnologyResponse(technology, ''));
  }
}
