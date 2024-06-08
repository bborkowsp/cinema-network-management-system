import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MovieResponse} from "../../dtos/response/movie.response";
import {CreateImageRequest} from "../../dtos/request/create-image.request";
import {UpdateMovieRequest} from "../../dtos/request/update-movie.request";
import {ProductionDetailsRequest} from "../../dtos/request/production-details.request";
import {VideoFileRequest} from "../../dtos/request/video-file.request";
import {FilmMemberRequest} from "../../dtos/request/film-member.request";
import {SubtitleAndSoundOptionsRequest} from "../../dtos/request/subtitle-and-sound-options.request";
import {
  ProjectionTechnologyResponse
} from "../../../projection-technology/dtos/response/projection-technology.response";
import {CreateMovieRequest} from "../../dtos/request/create-movie.request";

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

  createForm(): FormGroup {
    return this.formBuilder.group({
      title: this.formBuilder.group({
        title: ['', [Validators.required, Validators.maxLength(255)]],
        originalTitle: ['', [Validators.required, Validators.maxLength(255)]]
      }),
      information: this.formBuilder.group({
        duration: ['', [Validators.required]],
        releaseDate: ['', [Validators.required]],
        description: ['', [Validators.required]],
      }),
      productionDetails: this.formBuilder.group({
        worldPremiereDate: ['', [Validators.required]],
        director: ['', [Validators.required, Validators.pattern('^[A-Za-z]+\\s[A-Za-z]+(?:,\\s[A-Za-z]+\\s[A-Za-z]+)*$')]],
        actors: ['', [Validators.required, Validators.pattern('^[A-Za-z]+\\s[A-Za-z]+(?:,\\s[A-Za-z]+\\s[A-Za-z]+)*$')]],
        originalLanguages: ['', [Validators.required]],
        productionCountries: ['', [Validators.required]],
      }),
      imageAndTrailer: this.formBuilder.group({
        image: ['', [Validators.required]],
        trailer: ['', [Validators.required]],
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
    const image = new CreateImageRequest(movie.poster.name, movie.poster.type, movie.poster.data);

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
        image: image,
        trailer: movie.trailer.url
      }
    });
  }

  async getUpdateMovieRequestFromForm(): Promise<UpdateMovieRequest> {
    const imageRequest = await this.createImageRequest();
    const directorRequest = this.createDirectorRequest();
    const actorsRequests = this.createActorsRequests();
    const originalLanguagesRequest = this.extractLanguages();
    const productionCountriesRequest = this.extractProductionCountries();
    const projectionTechnologies = this.createProjectionTechnologies();

    return new UpdateMovieRequest(
      this.form.get('title')?.value.title,
      this.form.get('title')?.value.originalTitle,
      this.form.get('information')?.value.duration,
      this.form.get('information')?.value.releaseDate,
      this.form.get('information')?.value.description,
      new ProductionDetailsRequest(
        this.form.get('productionDetails')?.value.worldPremiereDate,
        directorRequest,
        actorsRequests,
        originalLanguagesRequest,
        productionCountriesRequest
      ),
      this.createSubtitleAndSoundOptions(),
      this.form.get('ageRestrictionAndGenres')?.value.ageRestriction,
      imageRequest,
      new VideoFileRequest(
        this.form.get('imageAndTrailer')?.value.trailer
      ),
      this.form.get('ageRestrictionAndGenres')?.value.genres,
      projectionTechnologies
    );
  }

  async getCreateMovieRequestFromForm(): Promise<CreateMovieRequest> {
    const imageRequest = await this.createImageRequest();
    const directorRequest = this.createDirectorRequest();
    const actorsRequests = this.createActorsRequests();
    const originalLanguagesRequest = this.extractLanguages();
    const productionCountriesRequest = this.extractProductionCountries();
    const projectionTechnologies = this.createProjectionTechnologies();

    return new CreateMovieRequest(
      this.form.get('title')?.value.title,
      this.form.get('title')?.value.originalTitle,
      this.form.get('information')?.value.duration,
      this.form.get('information')?.value.releaseDate,
      this.form.get('information')?.value.description,
      new ProductionDetailsRequest(
        this.form.get('productionDetails')?.value.worldPremiereDate,
        directorRequest,
        actorsRequests,
        originalLanguagesRequest,
        productionCountriesRequest
      ),
      this.createSubtitleAndSoundOptions(),
      this.form.get('ageRestrictionAndGenres')?.value.ageRestriction,
      imageRequest,
      new VideoFileRequest(
        this.form.get('imageAndTrailer')?.value.trailer
      ),
      this.form.get('ageRestrictionAndGenres')?.value.genres,
      projectionTechnologies
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
    const file = new File([selectedImage], name, {type: type});
    const data = await this.readFileData(file);

    return new CreateImageRequest(name, type, data);
  }

  public get imageFormGroup() {
    return this.form.get('imageAndTrailer')?.get('image') as FormGroup;
  }

  createDirectorRequest(): FilmMemberRequest {
    const directorFullName: string = this.form.get('productionDetails')?.value.director || '';
    const [firstName, ...lastNameArray] = directorFullName.split(' ');
    const lastName = lastNameArray.join(' ');
    return new FilmMemberRequest(firstName, lastName);
  }

  createActorsRequests(): FilmMemberRequest[] {
    const actorsString: string = this.form.get('productionDetails')?.value.actors || '';
    const actorsArray: string[] = actorsString.split(',');
    return actorsArray.map(actor => {
      const [firstName, ...lastNameArray] = actor.trim().split(' ');
      const lastName = lastNameArray.join(' ');
      return new FilmMemberRequest(firstName, lastName);
    });
  }

  extractLanguages(): string[] {
    const languagesString: string = this.form.get('productionDetails')?.value.originalLanguages || '';
    return languagesString.split(',');
  }

  extractProductionCountries(): string[] {
    const countriesString: string = this.form.get('productionDetails')?.value.productionCountries || '';
    return countriesString.split(',');
  }

  createSubtitleAndSoundOptions(): SubtitleAndSoundOptionsRequest {
    const subtitlesAndSoundOptions = this.form.get('projectionDetails')?.value.subtitlesAndSoundOptions;
    return new SubtitleAndSoundOptionsRequest(
      subtitlesAndSoundOptions === 'Subtitles',
      subtitlesAndSoundOptions === 'Dubbing',
      subtitlesAndSoundOptions === 'Voice Over',
      subtitlesAndSoundOptions === 'Original Language'
    );
  }

  createProjectionTechnologies(): ProjectionTechnologyResponse[] {
    const selectedTechnologies = this.form.get('projectionDetails')?.value.projectionTechnologies || [];
    return selectedTechnologies.map((technology: string) => new ProjectionTechnologyResponse(technology, ''));
  }
}
