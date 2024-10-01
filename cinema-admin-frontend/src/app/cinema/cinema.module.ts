import {NgModule} from "@angular/core";
import {CinemaTableComponent} from "./components/cinema-table/cinema-table.component";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {provideHttpClient, withInterceptorsFromDi} from "@angular/common/http";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatDialogModule} from "@angular/material/dialog";
import {MatMenuModule} from "@angular/material/menu";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatListModule} from "@angular/material/list";
import {MatExpansionModule} from "@angular/material/expansion";
import {CinemaDetailsComponent} from "./components/cinema-details/cinema-details.component";
import {MatSortModule} from "@angular/material/sort";
import {MatStepperModule} from "@angular/material/stepper";
import {CinemaFormComponent} from './components/cinema-form/cinema-form.component';
import {StepsContainerComponent} from './components/cinema-form/components/steps-container/steps-container.component';
import {StepTwoComponent} from "./components/cinema-form/components/steps-container/steps/step-two/step-two.component";
import {
  StepThreeComponent
} from "./components/cinema-form/components/steps-container/steps/step-three/step-three.component";
import {
  StepFourComponent
} from "./components/cinema-form/components/steps-container/steps/step-four/step-four.component";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {
  ConfirmDeletionCinemaDialog
} from "./components/confirm-deletion-cinema-dialog/confirm-deletion-cinema-dialog.component";
import {SharedModule} from "../_shared/shared.module";
import {
  CinemaManagerFormFieldsComponent
} from "./components/cinema-form/components/steps-container/components/cinema-manager-form-fields/cinema-manager-form-fields.component";
import {
  ContactDetailsFormFieldsComponent
} from "./components/cinema-form/components/steps-container/components/contact-details-form-fields/contact-details-form-fields.component";
import {
  ScreeningRoomFormFieldsComponent
} from "./components/cinema-form/components/steps-container/components/screening-room-form-fields/screening-room-form-fields.component";
import {StepOneComponent} from "./components/cinema-form/components/steps-container/steps/step-one/step-one.component";
import {
  AddressFormFieldsComponent
} from "./components/cinema-form/components/steps-container/components/address-form-fields/address-form-fields.component";
import {
  AboutCinemaFormFieldsComponent
} from "./components/cinema-form/components/steps-container/components/about-cinema-form-fields/about-cinema-form-fields.component";

@NgModule({
  declarations: [
    CinemaTableComponent,
    CinemaDetailsComponent,
    AboutCinemaFormFieldsComponent,
    AddressFormFieldsComponent,
    ScreeningRoomFormFieldsComponent,
    StepOneComponent,
    StepTwoComponent,
    StepThreeComponent,
    ContactDetailsFormFieldsComponent,
    StepFourComponent,
    CinemaManagerFormFieldsComponent,
    CinemaFormComponent,
    StepsContainerComponent,
    ConfirmDeletionCinemaDialog,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    SharedModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSortModule,
    MatInputModule,
    MatSelectModule,
    MatToolbarModule,
    MatIconModule,
    MatDialogModule,
    MatMenuModule,
    MatCheckboxModule,
    MatListModule,
    MatExpansionModule,
    MatStepperModule,
    MatProgressSpinnerModule
  ],
  providers: [
    provideHttpClient(withInterceptorsFromDi())
  ]
})

export class CinemaModule {
}
