import {NgModule} from "@angular/core";
import {RepertoryComponent} from "./components/repertory.component";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {MatInputModule} from "@angular/material/input";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {ReactiveFormsModule} from "@angular/forms";
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import {BuyTicketComponent} from "./components/buy-ticket/buy-ticket.component";
import {StepsContainerComponent} from "./components/buy-ticket/components/steps-container/steps-container.component";
import {SeatSelectionComponent} from "./components/buy-ticket/components/steps/seat-selection/seat-selection.component";
import {OrderFormComponent} from "./components/buy-ticket/components/steps/order-form/order-form.component";
import {MatStepperModule} from "@angular/material/stepper";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSelectModule} from "@angular/material/select";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatFormFieldModule} from "@angular/material/form-field";
import {DialogBuyTicketComponent} from './components/dialog-buy-ticket/dialog-buy-ticket.component';
import {MatIconModule} from "@angular/material/icon";
import {MatTooltipModule} from "@angular/material/tooltip";
import {
  SeatLimitDialogComponent
} from './components/buy-ticket/components/steps/seat-selection/seat-limit-dialog/seat-limit-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {SharedModule} from "../_shared/shared.module";
import { ScreeningListComponent } from './components/screening-list/screening-list.component';

@NgModule({
  declarations: [
    RepertoryComponent,
    BuyTicketComponent,
    StepsContainerComponent,
    SeatSelectionComponent,
    OrderFormComponent,
    DialogBuyTicketComponent,
    SeatLimitDialogComponent,
    ScreeningListComponent,
  ],
  imports: [
    CommonModule,
    MatInputModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    MatListModule,
    MatButtonModule,
    MatStepperModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    NgOptimizedImage,
    MatIconModule,
    MatTooltipModule,
    MatDialogModule,
    SharedModule
  ],
})
export class RepertoryModule {
}
