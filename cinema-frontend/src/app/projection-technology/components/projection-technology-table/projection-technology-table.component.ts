import {Component, ViewChild} from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {PaginatorRequestParams} from '../../../shared/dtos/paginator-request-params';
import {ProjectionTechnologyService} from "../../services/projection-technology.service";
import {Router} from "@angular/router";
import {CreateProjectionTechnologyRequest} from "../../dtos/request/create-projection-technology.request";
import {ProjectionTechnologyResponse} from "../../dtos/response/projection-technology.response";
import {MatDialog} from "@angular/material/dialog";
import {
  ConfirmDeletionProjectionTechnologyDialog
} from "../confirm-deletion-projection-technology-dialog/confirm-deletion-projection-technology-dialog.component";

@Component({
  selector: 'app-projection-technology-table',
  templateUrl: './projection-technology-table.component.html',
  styleUrls: ['./projection-technology-table.component.scss']
})
export class ProjectionTechnologyTableComponent {
  displayedColumns = ['options', 'technology', 'description'];
  projectionTechnologies$!: Observable<ProjectionTechnologyResponse[]>;
  dataLength = 0;
  @ViewChild(MatPaginator) readonly paginator!: MatPaginator;
  paginatorRequestParams = new PaginatorRequestParams(0, 10);
  protected isLoading = true;

  constructor(
    private readonly projectionTechnologyService: ProjectionTechnologyService,
    private readonly router: Router,
    private readonly dialog: MatDialog,
  ) {
    this.projectionTechnologies$ = this.getProjectionTechnologyPage();
  }

  private getProjectionTechnologyPage(): Observable<ProjectionTechnologyResponse[]> {
    return this.projectionTechnologyService.getProjectionTechnologiesPage(this.paginatorRequestParams).pipe(
      tap({
        next: (projectionTechnologyPage) => {
          this.dataLength = projectionTechnologyPage.totalElements;
          this.isLoading = false;
        },
        error: (err) => console.log(err),
      }),
      map(projectionTechnologyPage => projectionTechnologyPage.content),
    );
  }

  handlePageEvent(event: PageEvent): void {
    this.paginatorRequestParams = new PaginatorRequestParams(
      event.pageIndex,
      event.pageSize,
    );
    this.projectionTechnologies$ = this.getProjectionTechnologyPage();
  }

  handleEdit(projectionTechnology: CreateProjectionTechnologyRequest): void {
    const url = `projection-technologies/edit/${projectionTechnology.technology}`;
    this.router.navigateByUrl(url);
  }

  handleDelete(projectionTechnology: CreateProjectionTechnologyRequest): void {
    const matDialog = this.dialog.open(ConfirmDeletionProjectionTechnologyDialog, {
      data: projectionTechnology
    })

    matDialog.afterClosed().subscribe({
      next: (result) => {
        if (result) {
          this.projectionTechnologyService.deleteProjectionTechnology(projectionTechnology.technology).subscribe({
            next: () => (this.projectionTechnologies$ = this.getProjectionTechnologyPage()),
          });
        }
      },
    });
  }

  handleShowDetails(projectionTechnology: CreateProjectionTechnologyRequest): void {
    const url = `projection-technologies/details/${projectionTechnology.technology}`;
    this.router.navigateByUrl(url);
  }
}
