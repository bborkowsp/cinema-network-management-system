import {NgModule} from "@angular/core";
import {RepertoryComponent} from "./components/repertory.component";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {MatInputModule} from "@angular/material/input";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import {BuyTicketComponent} from "./components/buy-ticket/buy-ticket.component";
import {StepsContainerComponent} from "./components/buy-ticket/components/steps-container/steps-container.component";
import {SeatSelectionComponent} from "./components/buy-ticket/components/steps/seat-selection/seat-selection.component";
import {MatStepperModule} from "@angular/material/stepper";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSelectModule} from "@angular/material/select";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatTooltipModule} from "@angular/material/tooltip";
import {
  SeatLimitDialogComponent
} from './components/buy-ticket/components/steps/seat-selection/seat-limit-dialog/seat-limit-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {SharedModule} from "../_shared/shared.module";
import {ScreeningListComponent} from './components/screening-list/screening-list.component';
import {CustomerDataComponent} from "./components/buy-ticket/components/steps/customer-data/customer-data.component";
import {DialogBuyTicketComponent} from "./components/screening-list/dialog-buy-ticket/dialog-buy-ticket.component";
import {RouterLink} from "@angular/router";
import {ScreeningDetailsComponent} from "./components/screening-details/screening-details.component";
import {MovieDetailsComponent} from "./components/screening-details/components/movie-details/movie-details.component";
import {
  ScreeningTimesComponent
} from "./components/screening-details/components/screening-times/screening-times.component";
import {CancelPaypalPaymentComponent} from "./components/cancel-paypal-payment/cancel-paypal-payment.component";
import {MatCard, MatCardContent, MatCardHeader, MatCardImage, MatCardTitle} from "@angular/material/card";
import {CapturePaypalPaymentComponent} from "./components/capture-paypal-payment/capture-paypal-payment.component";

@NgModule({
  declarations: [
    RepertoryComponent,
    BuyTicketComponent,
    StepsContainerComponent,
    SeatSelectionComponent,
    CustomerDataComponent,
    DialogBuyTicketComponent,
    SeatLimitDialogComponent,
    ScreeningListComponent,
    ScreeningDetailsComponent,
    CancelPaypalPaymentComponent,
    CapturePaypalPaymentComponent,
    MovieDetailsComponent,
    ScreeningTimesComponent,
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
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
    RouterLink,
    MatCard,
    MatCardTitle,
    MatCardHeader,
    MatCardContent,
    MatCardImage,
  ],
})
export class RepertoryModule {
}
