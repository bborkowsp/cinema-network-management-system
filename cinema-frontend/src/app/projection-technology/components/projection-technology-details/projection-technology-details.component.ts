import {Component, OnInit} from '@angular/core';
import {ProjectionTechnologyService} from "../../services/projection-technology.service";
import {ActivatedRoute, Router} from "@angular/router";
import {map, Observable, switchMap, tap} from "rxjs";
import {ProjectionTechnologyResponse} from "../../dtos/response/projection-technology.response";
import {MatDialog} from "@angular/material/dialog";
import {
  ConfirmDeletionProjectionTechnologyDialog
} from "../confirm-deletion-projection-technology-dialog/confirm-deletion-projection-technology-dialog.component";
import {AuthService} from "../../../user/services/auth.service";

@Component({
  selector: 'app-projection-technology-details',
  templateUrl: './projection-technology-details.component.html',
  styleUrls: ['./projection-technology-details.component.scss']
})
export class ProjectionTechnologyDetailsComponent implements OnInit {

  projectionTechnology$!: Observable<ProjectionTechnologyResponse>;
  technology: string = '';
  protected isLoading = true;
  protected isUserRoleCinemaManager = true;


  constructor(
    private readonly projectionTechnologyService: ProjectionTechnologyService,
    private readonly activatedRoute: ActivatedRoute,
    private router: Router,
    private readonly dialog: MatDialog,
    private readonly authService: AuthService
  ) {
  }

  ngOnInit(): void {
    this.isUserRoleCinemaManager = this.authService.checkIfLoggedInUserIsCinemaManager()
    this.getProjectTechnology();
    this.isLoading = false;
  }

  handleEditTechnology() {
    this.router.navigateByUrl(`/projection-technologies/edit/${this.technology}`);
  }

  handleDeleteTechnology() {
    const dialogRef = this.dialog.open(ConfirmDeletionProjectionTechnologyDialog, {
      data: this.technology,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.projectionTechnologyService.deleteProjectionTechnology(this.technology).subscribe({
          next: () => this.handleGoBack(),
        });
      }
    });
  }

  handleGoBack() {
    this.router.navigateByUrl('/projection-technologies');
  }

  private getProjectTechnology() {
    this.projectionTechnology$ = this.activatedRoute.paramMap.pipe(
      map((paramMap) => paramMap.get('technology')!),
      tap((technology) => (this.technology = technology)),
      switchMap((technology) => this.projectionTechnologyService.getProjectionTechnology(technology)),
    );
  }
}
