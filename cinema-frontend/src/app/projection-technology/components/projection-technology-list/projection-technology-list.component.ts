import {Component, ViewChild} from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {PaginatorRequestParams} from '../../../shared/dtos/paginator-request-params';
import {ProjectionTechnologyService} from "../../services/projection-technology.service";
import {Router} from "@angular/router";
import {ProjectionTechnologyRequest} from "../../dtos/request/projection-technology.request";
import {ProjectionTechnologyResponse} from "../../dtos/response/projection-technology.response";

@Component({
  selector: 'app-projection-technology-list',
  templateUrl: './projection-technology-list.component.html',
  styleUrls: ['./projection-technology-list.component.scss']
})
export class ProjectionTechnologyListComponent {
  displayedColumns = ['options', 'technology', 'description'];
  projectionTechnologies$!: Observable<ProjectionTechnologyRequest[]>;
  dataLength = 0;
  @ViewChild(MatPaginator) readonly paginator!: MatPaginator;
  paginatorRequestParams = new PaginatorRequestParams(0, 10);
  protected isLoading = true;

  constructor(
    private readonly projectionTechnologyService: ProjectionTechnologyService,
    private readonly router: Router,
  ) {
    this.projectionTechnologies$ = this.getData();
  }

  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    this.projectionTechnologies$ = this.getData();
  }

  goToCreate(): void {
    const url = 'projection-technologies/create';
    this.router.navigateByUrl(url);
  }

  handleEdit(projectionTechnology: ProjectionTechnologyRequest): void {
    const url = `projection-technologies/edit/${projectionTechnology.technology}`;
    this.router.navigateByUrl(url);
  }

  handleDelete(projectionTechnology: ProjectionTechnologyRequest): void {
    this.projectionTechnologyService.deleteProjectionTechnology(projectionTechnology.technology).subscribe({
      next: () => (this.projectionTechnologies$ = this.getData()),
    });
  }

  handleShowDetails(projectionTechnology: ProjectionTechnologyResponse): void {
    const url = `projection-technologies/details/${projectionTechnology.technology}`;
    this.router.navigateByUrl(url);
  }

  private getData(): Observable<ProjectionTechnologyResponse[]> {
    return this.projectionTechnologyService.getProjectionTechnologies(this.paginatorRequestParams).pipe(
      tap({
        next: (projectionTechnologyPage) => {
          this.dataLength = projectionTechnologyPage.totalElements;
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map((projectionTechnologyPage) => projectionTechnologyPage.content),
    );
  }

}
