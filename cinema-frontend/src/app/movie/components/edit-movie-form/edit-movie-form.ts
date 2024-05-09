import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProductionDetailsRequest} from "../../dtos/request/production-details.request";
import {CreateImageRequest} from "../../dtos/request/create-image.request";
import {VideoFileRequest} from "../../dtos/request/video-file.request";
import {
  ProjectionTechnologyResponse
} from "../../../projection-technology/dtos/response/projection-technology.response";
import {FilmMemberRequest} from "../../dtos/request/film-member.request";
import {SubtitleAndSoundOptionsRequest} from "../../dtos/request/subtitle-and-sound-options.request";
import {MovieResponse} from "../../dtos/response/movie.response";
import {UpdateMovieRequest} from "../../dtos/request/update-movie.request";

export class EditMovieForm {
  form: FormGroup;

  constructor(private readonly formBuilder: FormBuilder) {
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

  async updateMovieRequestFromForm() {
    const projectionTechnologies = this.createProjectionTechnologies();

    let name = '';
    let type = '';
    let imageData = '';
    if (this.form.get('imageAndTrailer')?.value.image instanceof File) {
      const selectedImage: File = this.form.get('imageAndTrailer')?.value.image;


      name = selectedImage.name;
      type = selectedImage.type;
      imageData = '';

      const imageDataPromise = new Promise<string>((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(selectedImage);
        console.log(selectedImage);
        reader.onload = () => {
          const dataUrl = reader.result as string;
          imageData = dataUrl.split(',')[1];
          console.log("imageData", imageData)
          resolve(imageData);
        };
        reader.onerror = error => reject(error);
      });

      try {
        await imageDataPromise;
      } catch (error) {
        return null;
      }
    } else {
      name = this.form.get('imageAndTrailer')?.value.image.name;
      type = this.form.get('imageAndTrailer')?.value.image.type;
      imageData = this.form.get('imageAndTrailer')?.value.image.data;
    }


    const directorRequest = this.createDirectorRequest();
    const actorsRequests = this.createActorsRequests();
    const originalLanguagesRequest = this.extractLanguages();
    const productionCountriesRequest = this.extractProductionCountries();

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
      new CreateImageRequest(name, type, imageData),
      new VideoFileRequest(
        this.form.get('imageAndTrailer')?.value.trailer
      ),
      this.form.get('ageRestrictionAndGenres')?.value.genres,
      projectionTechnologies
    );
  }


  createProjectionTechnologies(): ProjectionTechnologyResponse[] {
    const selectedTechnologies = this.form.get('projectionDetails')?.value.projectionTechnologies || [];
    return selectedTechnologies.map((technology: string) => new ProjectionTechnologyResponse(technology, ''));
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

  private createForm(): FormGroup {
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
        director: ['', [Validators.required]],
        actors: ['', [Validators.required]],
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
}
